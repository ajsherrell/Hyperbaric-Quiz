package com.ajsherrell.hyperbaricquiz.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ajsherrell.hyperbaricquiz.QuestionDetailsFragment;
import com.ajsherrell.hyperbaricquiz.model.QuizContent;

import java.util.List;

public class QuestionsFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context context;
    private List<QuizContent> contents;

    public QuestionsFragmentPagerAdapter(Context context, List<QuizContent> contents, FragmentManager fm) {
        super(fm);
        this.context = context;
        this.contents = contents;
    }

    @Override
    public Fragment getItem(int i) {
        Bundle args = new Bundle();
        args.putParcelable(QuestionDetailsFragment.ARG_ITEM_ID, contents.get(i));
        QuestionDetailsFragment fragment = new QuestionDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return contents.size();
    }
}
