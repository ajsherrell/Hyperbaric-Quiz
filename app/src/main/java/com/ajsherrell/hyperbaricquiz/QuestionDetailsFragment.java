package com.ajsherrell.hyperbaricquiz;


import android.os.Bundle;
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

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionDetailsFragment extends Fragment {

    // fragment ID
    public static final String ARG_ITEM_ID = "item_id";
    public static final String ITEM_POSITION = "item_position";

    //vars
    private TextView id;
    private TextView question;
    private TextView answer;
    private Button submit;
    private boolean isCorrect = false;
    private int listItemSelected;

    //TODO: make code for correct answer

    //radios
    private RadioButton radioButtonA;
    private RadioButton radioButtonB;
    private RadioButton radioButtonC;

    private QuizContent quizContentList;

    public QuestionDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null && getArguments().containsKey(ARG_ITEM_ID)) {
            quizContentList = getArguments().getParcelable(ARG_ITEM_ID);
        }

        populateUI();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question_details, container, false);
        id = (TextView) view.findViewById(R.id.id);
        question = (TextView) view.findViewById(R.id.question);
        radioButtonA = (RadioButton) view.findViewById(R.id.radio_A);
        radioButtonB = (RadioButton) view.findViewById(R.id.radio_B);
        radioButtonC = (RadioButton) view.findViewById(R.id.radio_C);
        return view;
    }

    public void populateUI() {
        if (quizContentList != null) {
            if (id != null) {
                id.setText(quizContentList.getId());
            }

            if (question != null) {
                question.setText(quizContentList.getQuestion());
            }
            //TODO: parse
        }
    }

}
