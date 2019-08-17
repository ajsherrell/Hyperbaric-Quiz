package com.ajsherrell.hyperbaricquiz;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;


import androidx.annotation.RequiresApi;

public class QuestionDetailsActivity extends AppCompatActivity {

    private static final String TAG = QuestionDetailsActivity.class.getSimpleName();

    public static final String LIST_KEY = "list_key";
    public static final String LIST_ITEM_SELECTED = "list_item_selected";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            //create detail fragment and add it to the activity
            // using a fragment transaction
            Bundle arguments = new Bundle();
            arguments.putString(QuestionDetailsFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(QuestionDetailsFragment.ARG_ITEM_ID));
            QuestionDetailsFragment fragment = new QuestionDetailsFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.question_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //TODO: do i need to make menu options again?
        return super.onOptionsItemSelected(item);
    }
}
