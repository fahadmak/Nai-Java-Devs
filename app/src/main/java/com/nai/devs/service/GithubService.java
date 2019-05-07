package com.nai.devs.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GithubService {

    private static final String BASE_URL = "https://api.github.com/";

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    public GithubApi getAPI() {
        return retrofit.create(GithubApi.class);
    }
}
