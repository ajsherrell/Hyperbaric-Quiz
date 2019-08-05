package com.ajsherrell.hyperbaricquiz;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.ajsherrell.hyperbaricquiz.Constants.ClickListener.OnItemClickListener;
import com.ajsherrell.hyperbaricquiz.adapter.QuizAdapter;
import com.ajsherrell.hyperbaricquiz.model.QuizContent;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryListActivity extends AppCompatActivity
    implements OnItemClickListener {

    //key for intents
    public static final String CATEGORY_KEY = "category_key";

    // booleans
    private boolean twoPane;

    //model var
    private List<QuizContent> quizContentList;

    Parcelable mSavedRecyclerLayout;

    // images
    private ImageView imageView;

    //recycler
    RecyclerView listRecyclerView;

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

        setContentView(R.layout.category_list);

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

        //action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.app_name);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        setupRecyclerView();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setupRecyclerView() {
        GridLayoutManager categoryLayoutManager = new GridLayoutManager(this, numColumns());
        listRecyclerView.setLayoutManager(categoryLayoutManager);
        listRecyclerView.setHasFixedSize(true);
        listRecyclerView.setAdapter(new QuizAdapter(quizContentList, new OnItemClickListener() {
            @Override
            public void onItemClick(QuizContent position) {
                makeList(position.getTitle());
            }
        }));
    }

    //intent method with bundle.
    private void makeList(List<QuizContent> position) {
        if (twoPane) {
            Bundle args = new Bundle();
            args.putParcelable(CategoryListFragment.ARG_LIST_ID, position.get(0));
            CategoryListFragment fragment = new CategoryListFragment();
            fragment.setArguments(args);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.question_detail_container, fragment)
                    .commit();
        } else {
            Intent intent = new Intent(this, QuestionDetails.class);
            intent.putExtra(QuestionDetails.LIST_KEY, quizContentList);
            intent.putExtra(QuestionDetails.LIST_ITEM_SELECTED, position);
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
