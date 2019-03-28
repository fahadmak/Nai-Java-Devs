package com.example.myapplication.service;

import com.example.myapplication.model.GithubUsers;
import com.example.myapplication.model.GithubUsersResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubApi {

    @GET("search/users?q=type:User+location:Nairobi+language:JAVA")
    Call<GithubUsersResponse> getAllUsers();

    @GET("users/{username}")
    Call<GithubUsers> getSingleUser(@Path("username") String username);
}
