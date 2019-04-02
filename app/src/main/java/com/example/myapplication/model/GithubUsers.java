package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class GithubUsers {

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
}
