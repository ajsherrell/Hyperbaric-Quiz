package com.ajsherrell.hyperbaricquiz.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Titles implements Parcelable {

    String mTitle;

    public Titles() {}

    public Titles(String title) {
        this.mTitle = title;
    }

    protected Titles(Parcel in) {
        mTitle = in.readString();
    }

    public static final Creator<Titles> CREATOR = new Creator<Titles>() {
        @Override
        public Titles createFromParcel(Parcel in) {
            return new Titles(in);
        }

        @Override
        public Titles[] newArray(int size) {
            return new Titles[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mTitle);
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }
}
