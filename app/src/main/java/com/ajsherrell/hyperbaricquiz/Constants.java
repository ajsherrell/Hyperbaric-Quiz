package com.ajsherrell.hyperbaricquiz;

import android.content.res.AssetManager;

import com.ajsherrell.hyperbaricquiz.model.QuizContent;

public class Constants {

    // query string constants
    public static final String TITLE = "title";
    public static final String ID = "id";
    public static final String QUESTION = "question";
    public static final String ANSWER = "answer";
    public static final String IMAGE = "image";


    // click listener interface
    public static final class ClickListener {
        public interface OnItemClickListener {
            void onItemClick(QuizContent position);
        }
    }
}
