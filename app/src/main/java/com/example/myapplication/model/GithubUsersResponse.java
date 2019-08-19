package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GithubUsersResponse {

    @SerializedName("items")
    public List<GithubUsers> githubUsers;

    public GithubUsersResponse() {

        githubUsers = new ArrayList<GithubUsers>();
    }

    public List<GithubUsers> getUsers() {
        return githubUsers;
    }

    public void setGithubUsers(List<GithubUsers> githubUsers) {
        this.githubUsers = githubUsers;
    }
}
