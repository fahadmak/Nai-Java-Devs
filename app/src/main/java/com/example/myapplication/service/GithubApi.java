package com.example.myapplication.service;

import com.example.myapplication.model.GithubUsers;
import com.example.myapplication.model.GithubUsersResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubApi {

    @GET("search/users?q=type:User+location:Nairobi+language:JAVA"
            + "?access_token=7b2b9b477a57d613ea8852adbfb204bd64d7bd8d")
    Single<GithubUsersResponse> getAllUsers();

    @GET("users/{username}?access_token=7b2b9b477a57d613ea8852adbfb204bd64d7bd8d")
    Single<GithubUsers> getSingleUser(@Path("username") String username);
}
