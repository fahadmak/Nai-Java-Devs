package com.example.myapplication.service;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GithubService {

    private static final String BASE_URL = "https://api.github.com/";

    private OkHttpClient okHttpClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
    }

    private Retrofit retrofit() {
        Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL).client(okHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create());
        return builder.build();
    }

    public GithubApi getAPI() {
        return retrofit().create(GithubApi.class);
    }
}
