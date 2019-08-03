package com.ajsherrell.hyperbaricquiz.model;

import android.os.Parcel;
import android.os.Parcelable;

public class QuizContent implements Parcelable {

    private String mTitle;
    private int mId;
    private String mQuestion;
    private String mAnswer;
    private String mImage;

    public QuizContent(String title, int id, String question, String answer, String image) {
        this.mTitle = title;
        this.mId = id;
        this.mQuestion = question;
        this.mAnswer = answer;
        this.mImage = image;
    }

    protected QuizContent(Parcel in) {
        mTitle = in.readString();
        mId = in.readInt();
        mQuestion = in.readString();
        mAnswer = in.readString();
        mImage = in.readString();
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
        dest.writeString(mTitle);
        dest.writeInt(mId);
        dest.writeString(mQuestion);
        dest.writeString(mAnswer);
        dest.writeString(mImage);
    }


    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
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

    public String getAnswer() {
        return mAnswer;
    }

    public void setAnswer(String answer) {
        this.mAnswer = answer;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        this.mImage = image;
    }
}
