package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class GithubUsers {

    @SerializedName("login")
    private String username;

    @SerializedName("avatar_url")
    private String avatar;


    @SerializedName("url")
    private String url;

    public GithubUsers(String username, String avatar, String url) {
        this.username = username;
        this.avatar = avatar;
        this.url = url;
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
}
