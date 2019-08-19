package com.example.myapplication.di;

import com.example.myapplication.profile.SingleUserPresenter;
import com.example.myapplication.service.GithubApiService;
import com.example.myapplication.users.GithubPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.myapplication.util.Constants.BASE_URL;

@Module
public class AppModule {

    @Singleton
    @Provides
    static OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
    }

    @Singleton
    @Provides
    static Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL).client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
        return builder.build();
    }

    @Singleton
    @Provides
    static GithubApiService provideGithubApiService(Retrofit retrofit) {
        return retrofit.create(GithubApiService.class);
    }

    @Singleton
    @Provides
    public GithubPresenter provideGithubPresenter(GithubApiService apiService) {
        return new GithubPresenter(apiService);
    }

    @Singleton
    @Provides
    public SingleUserPresenter provideSingleUserPresenter(GithubApiService apiService) {
        return new SingleUserPresenter(apiService);
    }
}
