package com.example.myapplication.presenter;

import android.util.Log;

import com.example.myapplication.model.GithubUsers;
import com.example.myapplication.model.GithubUsersResponse;
import com.example.myapplication.service.GithubService;
import com.example.myapplication.view.AllUsersView;
import com.example.myapplication.view.SingleUserView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubPresenter {

    private static final String TAG = "GithubPresenter";

    private GithubService githubService;

    public GithubPresenter() {
        this.githubService = new GithubService();
    }

    public void getAllUserProfiles(final AllUsersView allUsersView){
        githubService.getAPI().getAllUsers().enqueue(new Callback<GithubUsersResponse>() {
            @Override
            public void onResponse(Call<GithubUsersResponse> call, Response<GithubUsersResponse> response) {
                Log.d(TAG, "onResponse: " + response.body());
                allUsersView.displayUserProfiles(response.body());
            }

            @Override
            public void onFailure(Call<GithubUsersResponse> call, Throwable t) {
                Log.d(TAG, t.toString());
                allUsersView.displayError();
            }
        });
    }

    public void getUserProfile(String username, final SingleUserView singleUserView){
        githubService.getAPI().getSingleUser(username).enqueue(new Callback<GithubUsers>() {
            @Override
            public void onResponse(Call<GithubUsers> call, Response<GithubUsers> response) {
                Log.d(TAG, "onResponse: " + response.body());
                singleUserView.displaySingleProfile(response.body());
            }

            @Override
            public void onFailure(Call<GithubUsers> call, Throwable t) {
                Log.d(TAG, t.toString());
            }
        });
    }
}
