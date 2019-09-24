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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ajsherrell.hyperbaricquiz.adapter.QuizAdapter;
import com.ajsherrell.hyperbaricquiz.adapter.TitleAdapter;
import com.ajsherrell.hyperbaricquiz.model.QuizContent;
import com.ajsherrell.hyperbaricquiz.model.Titles;
import com.ajsherrell.hyperbaricquiz.utilities.JsonUtils;

import java.util.ArrayList;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryListActivity extends AppCompatActivity {

    private static final String TAG = CategoryListActivity.class.getSimpleName();

    private Context context;

    //key for intents
    public static final String CATEGORY_KEY = "category_key";

    // booleans
    private boolean twoPane;

    //model var
    private ArrayList<QuizContent> content;

    //adapter
    private QuizAdapter adapter;

    Parcelable mSavedRecyclerLayout;

    private static final String BUNDLE_RECYCLER_LAYOUT = "CategoryListActivity.listRecyclerView.category_list";

    //recycler
    private static RecyclerView listRecyclerView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bundle to save place
        Bundle listBundle = getIntent().getExtras();
        if (listBundle != null && listBundle.containsKey(CATEGORY_KEY)) {
            content = listBundle.getParcelable(CATEGORY_KEY);
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.load_failure),
                    Toast.LENGTH_LONG).show();
            finish();
        }

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
            if (savedInstanceState == null && !content.isEmpty()) {
                makeList(0);
            }
        }

        Log.d(TAG, "onCreate: !!! this content is " + content);


        //The detail container view will be present only in the
        //large-screen layouts (res/w900dp).
        //If this view is present, then the activity should be in twoPane mode
        if (findViewById(R.id.question_detail_container) != null) {
            twoPane = true;
        } //TODO: should this if statement go into QuestionDetails?

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
        recyclerView.setAdapter(new QuizAdapter(context, content, new Constants.ClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                makeList(position);
            }
        }));
        Log.d(TAG, "setupRecyclerView: !!! rv is " + recyclerView + "quiz content is " + content);
    }

    //intent method with bundle.
    private void makeList(int position) {
        if (twoPane) {
            Bundle args = new Bundle();
            args.putParcelable(QuestionDetailsFragment.LIST_KEY, (Parcelable) content.get(position));
            QuestionDetailsFragment fragment = new QuestionDetailsFragment();
            fragment.setArguments(args);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.question_detail_container, fragment)
                    .commit();
        } else {
            Intent intent = new Intent(this, QuestionDetailsActivity.class);
            intent.putExtra(QuestionDetailsActivity.LIST_KEY, (Parcelable) content);
            intent.putExtra(QuestionDetailsActivity.LIST_ITEM_SELECTED, position);
            startActivity(intent);
        }
        Log.d(TAG, "makeList: this is !!!! position " + position);
    }

    //asyncTask
    public class QuizTask extends AsyncTask<String, Void, ArrayList<QuizContent>> {

        @Override
        protected ArrayList<QuizContent> doInBackground(String... strings) {
            Log.d(TAG, "doInBackground: !!! strings is " + strings);
            if (strings.length ==0) {
                return null;
            }
            String qContent = JsonUtils.loadJSONFromAsset("QuizData.json", context);
            Log.d(TAG, "doInBackground: !!! qContent is " + qContent);
            try {
                content = JsonUtils.parseQuizJson(qContent);
                Log.d(TAG, "doInBackground: content is !!! " + content);
                return content;
            } catch (Exception e) {
                e.printStackTrace();
                Log.d(TAG, "doInBackground: !!! in exception here " + e);
                return null;
            }
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected void onPostExecute(ArrayList<QuizContent> data) {
            Log.d(TAG, "onPostExecute: !!! adapter is " + adapter);
            adapter.clear();
            if (content != null) {
                content = data;
                adapter.add(data);
                Objects.requireNonNull(listRecyclerView.getLayoutManager()).onRestoreInstanceState(mSavedRecyclerLayout);
            } else {
                Log.d(TAG, "onPostExecute: !!! data is " + data);
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
