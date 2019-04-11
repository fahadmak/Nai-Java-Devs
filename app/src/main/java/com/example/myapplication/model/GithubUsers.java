package com.example.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class GithubUsers implements Parcelable {

    @SerializedName("login")
    private final String username;

    @SerializedName("avatar_url")
    private final String avatar;

    @SerializedName("url")
    private final String url;

    @SerializedName("name")
    private final String fullName;

    @SerializedName("company")
    private final String company;

    @SerializedName("bio")
    private final String bio;

    public GithubUsers(String username, String avatar, String url, String fullName,
                       String company, String bio) {
        this.username = username;
        this.avatar = avatar;
        this.url = url;
        this.fullName = fullName;
        this.company = company;
        this.bio = bio;
    }

    public GithubUsers(Parcel in) {
        username = in.readString();
        avatar = in.readString();
        url = in.readString();
        fullName = in.readString();
        company = in.readString();
        bio = in.readString();
    }

    public static final Creator<GithubUsers> CREATOR = new Creator<GithubUsers>() {
        @Override
        public GithubUsers createFromParcel(Parcel in) {
            return new GithubUsers(in);
        }

        @Override
        public GithubUsers[] newArray(int size) {
            return new GithubUsers[size];
        }
    };

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getFullName() {
        return fullName;
    }

    public String getCompany() {
        return company;
    }

    public String getBio() {
        return bio;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(avatar);
        dest.writeString(url);
        dest.writeString(fullName);
        dest.writeString(company);
        dest.writeString(bio);
    }
}
