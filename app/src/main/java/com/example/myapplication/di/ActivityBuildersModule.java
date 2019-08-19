package com.example.myapplication.di;

import com.example.myapplication.profile.DetailActivity;
import com.example.myapplication.users.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract MainActivity contributesMainActivity();

    @ContributesAndroidInjector
    abstract DetailActivity contributesDetailActivity();
}
