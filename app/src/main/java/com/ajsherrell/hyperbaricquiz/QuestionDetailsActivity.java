package com.ajsherrell.hyperbaricquiz;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import com.ajsherrell.hyperbaricquiz.adapter.QuestionsFragmentPagerAdapter;
import com.ajsherrell.hyperbaricquiz.model.QuizContent;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import androidx.annotation.RequiresApi;
import androidx.viewpager.widget.ViewPager;

public class QuestionDetailsActivity extends AppCompatActivity {

    private static final String TAG = QuestionDetailsActivity.class.getSimpleName();

    public static final String LIST_KEY = "list_key";
    public static final String LIST_ITEM_SELECTED = "list_item_selected";

    //the pager adapter reference
    ViewPager quizViewPager;

    private List<QuizContent> titles;
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

        for (int i = 0; i < titles.size(); i++) {
            name = String.valueOf(titles.get(i).getTitle());
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
            titles = bundle.getParcelable(LIST_KEY);
            questionSelected = bundle.getInt(LIST_ITEM_SELECTED);
        } else {
            Log.d(TAG, "onCreate: bundle error!!!!!" + titles);
        }

        //pager adapter implementation
        QuestionsFragmentPagerAdapter adapter = new QuestionsFragmentPagerAdapter(getApplicationContext(),
                Collections.singletonList(titles.get(Integer.parseInt(name))), getSupportFragmentManager());
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
                if (i < Objects.requireNonNull(quizViewPager.getAdapter()).getCount() - 1) {
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
