package com.example.myapplication.profile;

import com.example.myapplication.BasePresenter;
import com.example.myapplication.service.GithubApiService;
import com.example.myapplication.util.EspressoIdlingResource;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SingleUserPresenter implements BasePresenter<SingleUserView> {

    private final GithubApiService githubService;
    private Disposable disposable;
    private SingleUserView userView;

    @Inject
    public SingleUserPresenter(GithubApiService githubService) {
        this.githubService = githubService;
    }

    @Override
    public void attachView(SingleUserView view) {
        this.userView = view;
    }

    public void fetchSingleUsers(String username) {
        EspressoIdlingResource.increment();
        disposable = githubService.getSingleUser(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(githubUsers -> {
                    userView.displaySingleUser(githubUsers);
                    if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                        EspressoIdlingResource.decrement(); // Set app as idle.
                    }
                });
    }


    @Override
    public void detachView() {
        disposable.dispose();
    }

}
