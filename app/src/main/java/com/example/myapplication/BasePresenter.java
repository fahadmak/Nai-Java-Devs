package com.example.myapplication;

public interface BasePresenter<T extends View> {
    void attachView(T view);
    void detachView();
}
