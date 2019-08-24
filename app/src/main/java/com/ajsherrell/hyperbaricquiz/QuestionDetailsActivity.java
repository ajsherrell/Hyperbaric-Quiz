package com.ajsherrell.hyperbaricquiz;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;


import com.ajsherrell.hyperbaricquiz.adapter.QuestionsFragmentPagerAdapter;
import com.ajsherrell.hyperbaricquiz.model.QuizContent;

import java.util.Collections;
import java.util.List;

import androidx.annotation.RequiresApi;

public class QuestionDetailsActivity extends AppCompatActivity {

    private static final String TAG = QuestionDetailsActivity.class.getSimpleName();

    public static final String LIST_KEY = "list_key";
    public static final String LIST_ITEM_SELECTED = "list_item_selected";

    //the pager adapter reference
    ViewPager quizViewPager;

    private List<QuizContent> content;
    private int questionSelected;
    private String name;

    private Button submitButton;
    private Button nextButton;
    private Button previousButton;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_detail);

        for (int i = 0; i < content.size(); i++) {
            name = String.valueOf(content.get(i).getTitle());
        }

        quizViewPager = (ViewPager) findViewById(R.id.quiz_viewpager);
        submitButton = (Button) findViewById(R.id.submit_button);
        nextButton = (Button) findViewById(R.id.next_button);
        previousButton = (Button) findViewById(R.id.previous_button);
        previousButton.setVisibility(View.GONE);
        submitButton.setVisibility(View.GONE);

        //button functions
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quizViewPager.setCurrentItem(quizViewPager.getCurrentItem() + 1, true);
            }
        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quizViewPager.setCurrentItem(quizViewPager.getCurrentItem() - 1, true);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: set to score activity
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(name);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey(LIST_KEY) && bundle.containsKey(LIST_ITEM_SELECTED)) {
            content = bundle.getParcelable(LIST_KEY);
            questionSelected = bundle.getInt(LIST_ITEM_SELECTED);
        } else {
            Log.d(TAG, "onCreate: bundle error!!!!!" + content);
        }
//        if (savedInstanceState == null) {
//            //create detail fragment and add it to the activity
//            // using a fragment transaction
//            Bundle arguments = new Bundle();
//            arguments.putString(QuestionDetailsFragment.ARG_ITEM_ID,
//                    getIntent().getStringExtra(QuestionDetailsFragment.ARG_ITEM_ID));
//            QuestionDetailsFragment fragment = new QuestionDetailsFragment();
//            fragment.setArguments(arguments);
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.question_detail_container, fragment)
//                    .commit();
//        }

        //pager adapter implementation
        QuestionsFragmentPagerAdapter adapter = new QuestionsFragmentPagerAdapter(getApplicationContext(),
                Collections.singletonList(content.get(Integer.parseInt(name))), getSupportFragmentManager());
        quizViewPager.setAdapter(adapter);
        quizViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (actionBar != null) {
                    actionBar.setTitle(name);
                }

                //change the buttons
                if (i == 0) {
                    previousButton.setVisibility(View.GONE);
                } else {
                    previousButton.setVisibility(View.VISIBLE);
                }
                if (i < quizViewPager.getAdapter().getCount() - 1) {
                    nextButton.setVisibility(View.VISIBLE);
                } else {
                    nextButton.setVisibility(View.GONE);
                    submitButton.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        quizViewPager.setCurrentItem(questionSelected);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //TODO: do i need to make menu options again?
        return super.onOptionsItemSelected(item);
    }
}
