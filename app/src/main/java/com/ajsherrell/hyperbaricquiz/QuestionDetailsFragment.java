package com.ajsherrell.hyperbaricquiz;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.ajsherrell.hyperbaricquiz.model.QuizContent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionDetailsFragment extends Fragment {

    // fragment ID
    public static final String LIST_KEY = "list_key";
    public static final String LIST_ITEM_SELECTED = "list_item_selected";
    //public static final String ARG_ITEM_ID = "item_id";
    public static final String ITEM_POSITION = "item_position";

    private long currentPosition = 0;
    int score;

    //vars
    private TextView id;
    private TextView question;
    private String answer;
    private Button submit;
    private boolean isCorrect = false;
    private int listItemSelected;

    //TODO: make code for correct answer

    //radios
    private RadioButton radioButtonA;
    private RadioButton radioButtonB;
    private RadioButton radioButtonC;

    private List<QuizContent> quizContentList;

    public QuestionDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question_details, container, false);

        if (getArguments() != null && getArguments().containsKey(LIST_KEY)) {
            quizContentList = getArguments().getParcelable(LIST_KEY);
        }

        populateUI();

        id = (TextView) view.findViewById(R.id.id);
        question = (TextView) view.findViewById(R.id.question);
        radioButtonA = (RadioButton) view.findViewById(R.id.radio_A);
        radioButtonB = (RadioButton) view.findViewById(R.id.radio_B);
        radioButtonC = (RadioButton) view.findViewById(R.id.radio_C);

        if (savedInstanceState != null && savedInstanceState.containsKey(LIST_ITEM_SELECTED)) {
            currentPosition = savedInstanceState.getLong(LIST_ITEM_SELECTED);
        }
        return view;
    }

    public void populateUI() {
        for (int i = 0; i < quizContentList.size(); i++) {
            Collections.shuffle(quizContentList);
            if (quizContentList != null) {
                if (id != null) {
                    id.setText(quizContentList.get(i).getId());
                }

                if (question != null) {
                    question.setText(quizContentList.get(i).getQuestion());
                }

                if (quizContentList.get(i).getOptions() != null) {
                    List<String> optionArr;
                    optionArr = quizContentList.get(i).getOptions();
                    Collections.shuffle(optionArr);
                    radioButtonA.setText(optionArr.get(0));
                    radioButtonB.setText(optionArr.get(1));
                    radioButtonC.setText(optionArr.get(2));
                }
                if (answer != null) {
                    answer = quizContentList.get(i).getAnswer();
                    if (answer == radioButtonA.getText()) {
                        isCorrect = true;
                    } else {
                        isCorrect = false;
                    }
                    if (answer == radioButtonB.getText()) {
                        isCorrect = true;
                    } else {
                        isCorrect = false;
                    }
                    if (answer == radioButtonC.getText()) {
                        isCorrect = true;
                    } else {
                        isCorrect = false;
                    }
                }
            }
        }
    }

}
