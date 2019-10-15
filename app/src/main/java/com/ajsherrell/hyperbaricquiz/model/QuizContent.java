package com.ajsherrell.hyperbaricquiz.model;

import android.os.Parcel;
import android.os.Parcelable;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class QuizContent extends ArrayList<QuizContent> implements Parcelable {

    @SerializedName("category")
    @Expose
    private List<String> category;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("questions")
    @Expose
    private List<String> questions;

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("questionText")
    @Expose
    private String questionText;

    @SerializedName("options")
    @Expose
    private List<String> options;

    @SerializedName("answer")
    @Expose
    private String answer;

    //empty constructor
    public QuizContent() {}

    // constructor
    public QuizContent(List<String> category, String title, List<String> questions, int id, String question, List<String> options, String answer) {
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
        id = in.readInt();
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
        dest.writeInt(id);
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
