package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class GithubUsers {

    @SerializedName("login")
    private String username;

    @SerializedName("avatar_url")
    private String avatar;


    @SerializedName("url")
    private String url;

    @SerializedName("name")
    private String fullName;

    @SerializedName("company")
    private String company;

    @SerializedName("bio")
    private String bio;

    public GithubUsers(String username, String avatar, String url, String fullName) {
        this.username = username;
        this.avatar = avatar;
        this.url = url;
        this.fullName = fullName;
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
