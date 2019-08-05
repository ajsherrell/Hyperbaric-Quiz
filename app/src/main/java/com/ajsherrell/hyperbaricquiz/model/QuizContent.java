package com.ajsherrell.hyperbaricquiz.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class QuizContent implements Parcelable {

    private List<String> mTitle;
    private int mId;
    private List<String> mQuestion;
    private List<String> mAnswer;

    /**
     * No args constructor for use in serialization
     */
    public QuizContent() {}

    public QuizContent(List<String> title, int id, List<String> question, List<String> answer) {
        this.mTitle = title;
        this.mId = id;
        this.mQuestion = question;
        this.mAnswer = answer;
    }

    protected QuizContent(Parcel in) {
        in.readList(this.mTitle, QuizContent.class.getClassLoader());
        this.mId = in.readInt();
        in.readList(this.mQuestion, QuizContent.class.getClassLoader());
        in.readList(this.mAnswer, QuizContent.class.getClassLoader());
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
        dest.writeList(this.mTitle);
        dest.writeInt(this.mId);
        dest.writeList(this.mQuestion);
        dest.writeList(this.mAnswer);
    }

    public List<String> getTitle() {
        return mTitle;
    }

    public int getId() {
        return mId;
    }

    public List<String> getQuestion() {
        return mQuestion;
    }

    public List<String> getAnswer() {
        return mAnswer;
    }

}
