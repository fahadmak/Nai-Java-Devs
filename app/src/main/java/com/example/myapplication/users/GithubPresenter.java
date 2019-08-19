package com.example.myapplication.users;

import com.example.myapplication.BasePresenter;
import com.example.myapplication.model.GithubUsersResponse;
import com.example.myapplication.service.GithubApiService;
import com.example.myapplication.util.EspressoIdlingResource;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GithubPresenter implements BasePresenter<AllUsersView> {

    private final GithubApiService githubService;
    private Disposable disposable;

    private AllUsersView usersView;

    @Override
    public void attachView(AllUsersView view) {
        this.usersView = view;
    }

    @Inject
    public GithubPresenter(GithubApiService githubService) {
        this.githubService = githubService;
    }


    public void fetchUsers() {
        EspressoIdlingResource.increment();
        disposable = githubService.getAllUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((GithubUsersResponse githubUsersResponse) -> {
                    usersView.hideLoading();
                    usersView.displayUsers(githubUsersResponse.getUsers());
                    if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                        EspressoIdlingResource.decrement(); // Set app as idle.
                    }
                }
                );
    }

    @Override
    public void detachView() {
        disposable.dispose();
    }

}
