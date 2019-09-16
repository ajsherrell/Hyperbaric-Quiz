package com.ajsherrell.hyperbaricquiz.adapter;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ajsherrell.hyperbaricquiz.QuestionDetailsFragment;
import com.ajsherrell.hyperbaricquiz.model.QuizContent;
import com.ajsherrell.hyperbaricquiz.model.Titles;

import java.util.List;

public class QuestionsFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context context;
    private List<QuizContent> contents;

    public QuestionsFragmentPagerAdapter(Context context, List<QuizContent> contents, FragmentManager fm) {
        super(fm);
        this.context = context;
        this.contents = contents;
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {
        Bundle args = new Bundle();
        args.putParcelable(QuestionDetailsFragment.LIST_KEY, contents.get(i));
        QuestionDetailsFragment fragment = new QuestionDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return contents.size();
    }
}
