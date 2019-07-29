package com.ajsherrell.hyperbaricquiz;

import com.ajsherrell.hyperbaricquiz.model.QuizContent;

public class Constants {

    // click listener interface
    public static final class ClickListener {
        public interface OnItemClickListener {
            void onItemClick(QuizContent position);
        }
    }

}
