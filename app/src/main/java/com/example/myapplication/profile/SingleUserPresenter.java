package com.example.myapplication.profile;

import android.support.test.espresso.idling.CountingIdlingResource;

import com.example.myapplication.BasePresenter;
import com.example.myapplication.service.GithubService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SingleUserPresenter implements BasePresenter<SingleUserView> {

    private final GithubService githubService;
    private Disposable disposable;
    private SingleUserView userView;

    private final CountingIdlingResource mDetailResource = new CountingIdlingResource("Detail");

    public SingleUserPresenter() {
        this.githubService = new GithubService();
    }

    @Override
    public void attachView(SingleUserView view) {
        this.userView = view;
    }

    public void fetchSingleUsers(String username) {
        mDetailResource.increment();
        disposable = githubService.getAPI().getSingleUser(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(githubUsers -> {
                    if (!mDetailResource.isIdleNow()) {
                        mDetailResource.decrement();
                    }
                    userView.displaySingleUser(githubUsers);
                });
    }


    @Override
    public void detachView() {
        disposable.dispose();
    }

    public CountingIdlingResource getCountingIdlingResource() {
        return mDetailResource;
    }
}
