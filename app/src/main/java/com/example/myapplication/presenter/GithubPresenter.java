package com.example.myapplication.presenter;

import com.example.myapplication.model.GithubUsers;
import com.example.myapplication.model.GithubUsersResponse;
import com.example.myapplication.service.GithubService;
import com.example.myapplication.view.AllUsersView;
import com.example.myapplication.view.SingleUserView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubPresenter {

    GithubService githubService;

    public GithubPresenter() {
        this.githubService = new GithubService();
    }

    public void getAllUserProfiles(final AllUsersView allUsersView) {
        githubService.getAPI().getAllUsers().enqueue(new Callback<GithubUsersResponse>() {
            @Override
            public void onResponse(Call<GithubUsersResponse> call,
                                   Response<GithubUsersResponse> response) {
                allUsersView.displayUserProfiles(response.body());
            }

            @Override
            public void onFailure(Call<GithubUsersResponse> call, Throwable t) {
                displayError();
            }
        });
    }

    public void getUserProfile(String username, final SingleUserView singleUserView) {
        githubService.getAPI().getSingleUser(username).enqueue(new Callback<GithubUsers>() {
            @Override
            public void onResponse(Call<GithubUsers> call, Response<GithubUsers> response) {
                singleUserView.displaySingleProfile(response.body());
            }

            @Override
            public void onFailure(Call<GithubUsers> call, Throwable t) {
                displayError();
            }
        });
    }

    public void displayError() {
        try {
            throw new InterruptedException("Something went wrong!");
        } catch (InterruptedException e) {
        }
    }
}
