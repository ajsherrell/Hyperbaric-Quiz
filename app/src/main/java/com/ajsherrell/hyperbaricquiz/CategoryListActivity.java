package com.ajsherrell.hyperbaricquiz;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Parcelable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ajsherrell.hyperbaricquiz.adapter.QuizAdapter;
import com.ajsherrell.hyperbaricquiz.adapter.QuizAdapter.QuizAdapterOnClickHandler;
import com.ajsherrell.hyperbaricquiz.model.Category;
import com.ajsherrell.hyperbaricquiz.model.QuizContent;
import com.ajsherrell.hyperbaricquiz.utilities.JsonUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryListActivity extends AppCompatActivity implements QuizAdapterOnClickHandler {

    private static final String TAG = CategoryListActivity.class.getSimpleName();

    private Context context;

    // booleans
    private boolean twoPane;

    //model var
    private QuizContent mainQuizObject = new QuizContent();

    //initiate gson
    private Gson gson = new Gson();

    //adapter
    private QuizAdapter adapter;

    Parcelable mSavedRecyclerLayout;

    private static final String BUNDLE_RECYCLER_LAYOUT = "CategoryListActivity.listRecyclerView.activity_main";

    //recycler
    private static RecyclerView listRecyclerView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bundle to save place
//        Bundle listBundle = getIntent().getExtras();
//        if (listBundle != null && listBundle.containsKey(CATEGORY_KEY)) {
//            content = listBundle.getParcelable(CATEGORY_KEY);
//        } else {
//            Toast.makeText(getApplicationContext(), getString(R.string.load_failure),
//                    Toast.LENGTH_LONG).show();
//            finish();
//        }
        context = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);

        if (savedInstanceState != null) {
            mSavedRecyclerLayout = savedInstanceState.getParcelable(BUNDLE_RECYCLER_LAYOUT);
            Objects.requireNonNull(listRecyclerView.getLayoutManager()).onRestoreInstanceState(mSavedRecyclerLayout);
            Log.d(TAG, "onCreate: onRestoreState!!! " + savedInstanceState);
        }

        //two pane devices
        twoPane = getResources().getBoolean(R.bool.isTwoPane);
        if (twoPane) {
            //two pane logic with savedInstanceState.
            if (savedInstanceState != null) {
                mSavedRecyclerLayout = savedInstanceState.getParcelable(BUNDLE_RECYCLER_LAYOUT);
                Objects.requireNonNull(listRecyclerView.getLayoutManager()).onRestoreInstanceState(mSavedRecyclerLayout);
                Log.d(TAG, "onCreate: onRestoreState!!! " + savedInstanceState);
            }
            if (savedInstanceState == null && !mainQuizObject.getCategory().isEmpty()) {
                //makeList(mainQuizObject);
            }
        }

        Log.d(TAG, "onCreate: !!! this content is " + mainQuizObject.toString());


        //The detail container view will be present only in the
        //large-screen layouts (res/w900dp).
        //If this view is present, then the activity should be in twoPane mode
        if (findViewById(R.id.question_detail_container) != null) {
            twoPane = true;
        } //TODO: should this if statement go into QuestionDetails?

        adapter = new QuizAdapter(context, this, mainQuizObject);

        listRecyclerView = findViewById(R.id.image_rv);
        assert  listRecyclerView != null;
        setupRecyclerView(listRecyclerView);
        Log.d(TAG, "onCreate: RV!!!!!" + listRecyclerView);

        // execute asyncTask
        QuizTask task = new QuizTask();
        task.execute();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putParcelable(BUNDLE_RECYCLER_LAYOUT, Objects.requireNonNull(listRecyclerView.getLayoutManager()).onSaveInstanceState());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        GridLayoutManager categoryLayoutManager = new GridLayoutManager(this, numColumns());
        recyclerView.setLayoutManager(categoryLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        Log.d(TAG, "setupRecyclerView: !!! rv is " + recyclerView + "quiz content is " + mainQuizObject.toString());
    }

    //intent method with bundle.
    private void makeList(Category category) {
        String categoryJson = gson.toJson(category, Category.class);
        if (twoPane) {
            Bundle args = new Bundle();
            //args.putParcelable(QuestionDetailsFragment.LIST_KEY, (Parcelable) content);
            QuestionDetailsFragment fragment = new QuestionDetailsFragment();
            fragment.setArguments(args);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.question_detail_container, fragment)
                    .commit();
        } else {
            Intent intent = new Intent(this, QuestionDetailsActivity.class);
            //intent.putExtra(QuestionDetailsActivity.LIST_KEY, content);
            intent.putExtra(QuestionDetailsActivity.LIST_ITEM_SELECTED, categoryJson);
            startActivity(intent);
        }
        Log.d(TAG, "makeList: this is !!!! category " + category);
    }

    @Override
    public void onClick(Category category) {
        makeList(category);
    }


    //asyncTask
    public class QuizTask extends AsyncTask<String, Void, QuizContent> {

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected QuizContent doInBackground(String... strings) {
            Gson gson = new Gson();

            String qContent = JsonUtils.loadJSONFromAsset(context);

            Log.d(TAG, "doInBackground: !!! qContent is " + qContent);
            try {
                QuizContent quizContent = gson.fromJson(qContent, QuizContent.class);
                Log.d(TAG, "doInBackground: quizContent from GSON is !!! " + quizContent);

                return quizContent;
            } catch (Exception e) {
                e.printStackTrace();
                Log.d(TAG, "doInBackground: !!! in exception here " + e);
                return null;
            }
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected void onPostExecute(QuizContent data) {
            Log.d(TAG, "onPostExecute: !!! adapter is " + adapter);
            if (mainQuizObject != null) {
                mainQuizObject = data;
                adapter.add(mainQuizObject);
                Objects.requireNonNull(listRecyclerView.getLayoutManager()).onRestoreInstanceState(mSavedRecyclerLayout);
            } else {
                Log.d(TAG, "onPostExecute: !!! data is " + data.getCategory().get(0).getTitle());
            }
            adapter.notifyDataSetChanged();
            super.onPostExecute(data);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    // menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //TODO: create switch case for menu items.
        switch (id) {
            case R.id.action_about:
                //TODO: link to about activity
                break;
            case R.id.action_high_score:
                //TODO: link to high score activity
                break;
            case R.id.action_exam_ready:
                //TODO: link to exam ready activity
                break;
            default:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private int numColumns() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        // change this divider to adjust the size of poster
        int divider = 500;
        int width = metrics.widthPixels;
        int columns = width / divider;
        if (columns < 2) return 2;
        return columns;
    }

    // SAVE FOR LATER
    // referenced https://stackoverflow.com/questions/1560788/how-to-check-internet-access-on-android-inetaddress-never-times-out
    private boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            return netInfo != null && netInfo.isConnectedOrConnecting();
        }
        return false;
    }
}
