package com.ajsherrell.hyperbaricquiz;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class QuestionDetails extends AppCompatActivity {

    private static final String TAG = QuestionDetails.class.getSimpleName();

    //vars
    private TextView question;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_detail);
    }
}
