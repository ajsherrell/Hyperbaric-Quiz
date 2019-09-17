package com.ajsherrell.hyperbaricquiz.model;

import android.os.Parcel;
import android.os.Parcelable;


import java.util.ArrayList;
import java.util.List;

public class QuizContent extends ArrayList<QuizContent> implements Parcelable {

    private String mId;
    private String mQuestion;
    private List<String> mOptions;
    private String mAnswer;

    //empty constructor
    public QuizContent() {}

    /**
     * No args constructor for use in serialization
     * @param id
     * @param question
     * @param options
     * @param answer
     */
    public QuizContent(String id, String question, List<String> options, String answer) {
        this.mId = id;
        this.mQuestion = question;
        this.mOptions = options;
        this.mAnswer = answer;
    }

    protected QuizContent(Parcel in) {
        mId = in.readString();
        mQuestion = in.readString();
        in.readList(mOptions, QuizContent.class.getClassLoader());
        mAnswer = in.readString();
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
        dest.writeString(mId);
        dest.writeString(mQuestion);
        dest.writeList(mOptions);
        dest.writeString(mAnswer);
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String question) {
        this.mQuestion = question;
    }

    public List<String> getOptions() {
        return mOptions;
    }

    public void setOptions(List<String> options) {
        this.mOptions = options;
    }

    public String getAnswer() {
        return mAnswer;
    }

    public void setAnswer(String answer) {
        this.mAnswer = answer;
    }
}
