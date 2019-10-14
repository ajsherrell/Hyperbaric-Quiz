package com.ajsherrell.hyperbaricquiz.model;

import android.os.Parcel;
import android.os.Parcelable;


import java.util.ArrayList;
import java.util.List;

public class QuizContent extends ArrayList<QuizContent> implements Parcelable {

    private List<String> category;
    private String title;
    private List<String> questions;
    private String id;
    private String questionText;
    private List<String> options;
    private String answer;

    //empty constructor
    public QuizContent() {}

    // constructor
    public QuizContent(List<String> category, String title, List<String> questions, String id, String question, List<String> options, String answer) {
        this.category = category;
        this.title = title;
        this.questions = questions;
        this.id = id;
        this.questionText = question;
        this.options = options;
        this.answer = answer;
    }

    protected QuizContent(Parcel in) {
        in.readList(category, QuizContent.class.getClassLoader());
        title = in.readString();
        in.readList(questions, QuizContent.class.getClassLoader());
        id = in.readString();
        questionText = in.readString();
        in.readList(options, QuizContent.class.getClassLoader());
        answer = in.readString();
    }

    public static final Creator<QuizContent> CREATOR = new Creator<QuizContent>() {
        @Override
        public QuizContent createFromParcel(Parcel in) {
            return new QuizContent(in);
        }

        @Override
        public QuizContent[] newArray(int size) {
            return new QuizContent[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(category);
        dest.writeString(title);
        dest.writeList(questions);
        dest.writeString(id);
        dest.writeString(questionText);
        dest.writeList(options);
        dest.writeString(answer);
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getQuestions() {
        return questions;
    }

    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
