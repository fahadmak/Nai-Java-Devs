package com.example.myapplication.profile;

import com.example.myapplication.View;
import com.example.myapplication.model.GithubUsers;

public interface SingleUserView extends View {
    void displaySingleUser(GithubUsers githubUsers);
}
