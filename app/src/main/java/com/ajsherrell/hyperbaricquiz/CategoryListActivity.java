package com.ajsherrell.hyperbaricquiz;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.ajsherrell.hyperbaricquiz.adapter.QuizAdapter;
import com.ajsherrell.hyperbaricquiz.model.QuizContent;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryListActivity extends AppCompatActivity {

    //key for intents
    public static final String CATEGORY_KEY = "category_key";

    // booleans
    private boolean twoPane;

    //model var
    private List<QuizContent> quizContentList;

    //adapter reference
    QuizAdapter adapter;

    Parcelable mSavedRecyclerLayout;

    // images
    private ImageView imageView;

    //text views
    private TextView categoryTextView;

    //recycler
    RecyclerView listRecyclerView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //bundle to save place
        Bundle listBundle = getIntent().getExtras();
        if (listBundle != null && listBundle.containsKey(CATEGORY_KEY)) {
            quizContentList = listBundle.getParcelable(CATEGORY_KEY);
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.load_failure),
                    Toast.LENGTH_LONG).show();
            finish();
        }

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);

        //two pane devices
        twoPane = getResources().getBoolean(R.bool.isTwoPane);
        if (twoPane) {
            //two pane logic with savedInstanceState.
            if (savedInstanceState != null) {
                mSavedRecyclerLayout = savedInstanceState.getParcelable(CATEGORY_KEY);
                listRecyclerView.getLayoutManager().onRestoreInstanceState(mSavedRecyclerLayout);
                makeList(quizContentList);
            }
        }

        //The detail container view will be present only in the
        //large-screen layouts (res/w900dp).
        //If this view is present, then the activity should be in twoPane mode
        if (findViewById(R.id.question_detail_container) != null) {
            twoPane = true;
        }

        listRecyclerView = findViewById(R.id.image_rv);
        assert  listRecyclerView != null;
        setupRecyclerView(listRecyclerView);
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
    }

    //intent method with bundle.
    private void makeList(List<QuizContent> position) {
        if (twoPane) {
            Bundle args = new Bundle();
            args.putParcelable(QuestionDetailsFragment.ARG_LIST_ID, position.get(0));
            QuestionDetailsFragment fragment = new QuestionDetailsFragment();
            fragment.setArguments(args);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.question_detail_container, fragment)
                    .commit();
        } else {
            Intent intent = new Intent(this, QuestionDetailsActivity.class);
            intent.putExtra(QuestionDetailsActivity.LIST_KEY, (Parcelable) quizContentList);
            intent.putExtra(QuestionDetailsActivity.LIST_ITEM_SELECTED, (Parcelable) position);
            startActivity(intent);
        }
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

    @Override
    public void onItemClick(QuizContent position) {

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
