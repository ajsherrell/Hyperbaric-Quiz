package com.ajsherrell.hyperbaricquiz;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.ajsherrell.hyperbaricquiz.model.QuizContent;

public class QuestionDetailsActivity extends AppCompatActivity {

    private static final String TAG = QuestionDetailsActivity.class.getSimpleName();

    public static final String LIST_KEY = "list_key";
    public static final String LIST_ITEM_SELECTED = "list_item_selected";

    //vars
    private TextView id;
    private TextView question;
    private Button submit;
    private boolean isCorrect = false;
    private int listItemSelected;
    private QuizContent quizContent;

    //radios
    private RadioButton radioButtonA;
    private RadioButton radioButtonB;
    private RadioButton radioButtonC;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_detail);

        //find views
        id = findViewById(R.id.id);
        question = findViewById(R.id.question);
        submit = findViewById(R.id.submit_button);
        radioButtonA = findViewById(R.id.radio_A);
        radioButtonB = findViewById(R.id.radio_B);
        radioButtonC = findViewById(R.id.radio_C);
    }
}
