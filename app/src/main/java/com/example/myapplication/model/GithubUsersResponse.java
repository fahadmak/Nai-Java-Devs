package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GithubUsersResponse {

    @SerializedName("items")
    private final List<GithubUsers> githubUsers;

    public GithubUsersResponse() {

        githubUsers = new ArrayList<GithubUsers>();
    }

    public final List<GithubUsers> getUsers() {
        return githubUsers;
    }
}
