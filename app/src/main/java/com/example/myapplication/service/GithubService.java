package com.example.myapplication.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GithubService {

    private static final String BASE_URL = "https://api.github.com";

    Retrofit retrofit;

    public GithubService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
