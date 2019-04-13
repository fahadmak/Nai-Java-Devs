package com.nai.devs.presenter;

import com.nai.devs.model.GithubUsers;
import com.nai.devs.model.GithubUsersResponse;
import com.nai.devs.service.GithubService;
import com.nai.devs.view.AllUsersView;
import com.nai.devs.view.SingleUserView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubPresenter {

    private final GithubService githubService;

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
