package com.example.myapplication.profile;

import android.support.test.espresso.idling.CountingIdlingResource;

import com.example.myapplication.BasePresenter;
import com.example.myapplication.service.GithubApiService;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.example.myapplication.util.Constants.DETAIL_ACTIVITY_RESOURCE;

public class SingleUserPresenter implements BasePresenter<SingleUserView> {

    private final GithubApiService githubService;
    private Disposable disposable;
    private SingleUserView userView;

    private final CountingIdlingResource
            mDetailResource = new CountingIdlingResource(DETAIL_ACTIVITY_RESOURCE);

    @Inject
    public SingleUserPresenter(GithubApiService githubService) {
        this.githubService = githubService;
    }

    @Override
    public void attachView(SingleUserView view) {
        this.userView = view;
    }

    public void fetchSingleUsers(String username) {
        mDetailResource.increment();
        disposable = githubService.getSingleUser(username)
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
