package com.example.myapplication.users;

import com.example.myapplication.View;
import com.example.myapplication.model.GithubUsers;

import java.util.List;

public interface AllUsersView extends View {
    void showLoading();
    void hideLoading();
    void displayUsers(List<GithubUsers> githubUsers);
}
