package com.ajsherrell.hyperbaricquiz.model;

import android.os.Parcel;
import android.os.Parcelable;

public class QuizContent implements Parcelable {

    private String mCategory;
    private int mId;
    private String mQuestion;
    private String mAnswerA;
    private String mAnswerB;
    private String mAnswerC;

    public QuizContent(String category, int id, String question, String answerA,
                       String answerB, String answerC) {
        this.mCategory = category;
        this.mId = id;
        this.mQuestion = question;
        this.mAnswerA = answerA;
        this.mAnswerB = answerB;
        this.mAnswerC = answerC;
    }

    protected QuizContent(Parcel in) {
        mCategory = in.readString();
        mId = in.readInt();
        mQuestion = in.readString();
        mAnswerA = in.readString();
        mAnswerB = in.readString();
        mAnswerC = in.readString();
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
        dest.writeString(mCategory);
        dest.writeInt(mId);
        dest.writeString(mQuestion);
        dest.writeString(mAnswerA);
        dest.writeString(mAnswerB);
        dest.writeString(mAnswerC);
    }


    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        this.mCategory = category;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String question) {
        this.mQuestion = question;
    }

    public String getAnswerA() {
        return mAnswerA;
    }

    public void setAnswerA(String answerA) {
        this.mAnswerA = answerA;
    }

    public String getAnswerB() {
        return mAnswerB;
    }

    public void setAnswerB(String answerB) {
        this.mAnswerB = answerB;
    }

    public String getAnswerC() {
        return mAnswerC;
    }

    public void setAnswerC(String answerC) {
        this.mAnswerC = answerC;
    }
}
