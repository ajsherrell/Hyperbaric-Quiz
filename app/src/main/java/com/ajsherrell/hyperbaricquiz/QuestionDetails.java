package com.ajsherrell.hyperbaricquiz;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.ajsherrell.hyperbaricquiz.model.QuizContent;

import androidx.viewpager.widget.ViewPager;

public class QuestionDetails extends AppCompatActivity {

    private static final String TAG = QuestionDetails.class.getSimpleName();

    public static final String LIST_KEY = "list_key";
    public static final String LIST_ITEM_SELECTED = "list_item_selected";

    //vars
    private TextView question;
    private boolean correct;
    private int listItemSelected;
    private QuizContent quizContent;
    ViewPager questionViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_detail);
    }
}
