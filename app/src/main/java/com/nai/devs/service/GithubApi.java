package com.nai.devs.service;

import com.nai.devs.model.GithubUsers;
import com.nai.devs.model.GithubUsersResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubApi {

    @GET("search/users?q=type:User+location:Nairobi+language:JAVA"
            + "?access_token=7b2b9b477a57d613ea8852adbfb204bd64d7bd8d")
    Call<GithubUsersResponse> getAllUsers();

    @GET("users/{username}?access_token=7b2b9b477a57d613ea8852adbfb204bd64d7bd8d")
    Call<GithubUsers> getSingleUser(@Path("username") String username);
}
