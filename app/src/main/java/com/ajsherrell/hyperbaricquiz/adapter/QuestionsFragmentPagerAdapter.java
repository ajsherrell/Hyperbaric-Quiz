package com.ajsherrell.hyperbaricquiz.adapter;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ajsherrell.hyperbaricquiz.QuestionDetailsFragment;
import com.ajsherrell.hyperbaricquiz.model.Category;
import com.ajsherrell.hyperbaricquiz.model.Question;
import com.ajsherrell.hyperbaricquiz.model.QuizContent;
import com.google.gson.Gson;

import java.util.List;

public class QuestionsFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context context;
    private Category category;
    private Gson gson = new Gson();

    public QuestionsFragmentPagerAdapter(Context context, Category category, FragmentManager fm) {
        super(fm);
        this.context = context;
        this.category = category;
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {
        Bundle args = new Bundle();
        Question question = category.getQuestions().get(i);
        String questionJson = gson.toJson(question);
        QuestionDetailsFragment fragment = new QuestionDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return category.getQuestions().size();
    }
}
