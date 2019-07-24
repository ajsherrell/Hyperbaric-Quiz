package com.ajsherrell.hyperbaricquiz.model;

import android.os.Parcel;
import android.os.Parcelable;

public class QuizContent implements Parcelable {

    private String mCategory;
    private String mId;
    private String mQuestion;
    private String mAnswerA;
    private String mAnswerB;
    private String mAnswerC;
    private String mIsCorrect;

    public QuizContent(String category, String id, String question, String answerA,
                       String answerB, String answerC, String isCorrect) {
        this.mCategory = category;
        this.mId = id;
        this.mQuestion = question;
        this.mAnswerA = answerA;
        this.mAnswerB = answerB;
        this.mAnswerC = answerC;
        this.mIsCorrect = isCorrect;
    }

    protected QuizContent(Parcel in) {
        mCategory = in.readString();
        mId = in.readString();
        mQuestion = in.readString();
        mAnswerA = in.readString();
        mAnswerB = in.readString();
        mAnswerC = in.readString();
        mIsCorrect = in.readString();
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
        dest.writeString(mId);
        dest.writeString(mQuestion);
        dest.writeString(mAnswerA);
        dest.writeString(mAnswerB);
        dest.writeString(mAnswerC);
        dest.writeString(mIsCorrect);
    }


    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        this.mCategory = category;
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

    public String getIsCorrect() {
        return mIsCorrect;
    }

    public void setIsCorrect(String isCorrect) {
        this.mIsCorrect = isCorrect;
    }
}
