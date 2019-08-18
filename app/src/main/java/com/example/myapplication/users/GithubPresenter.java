package com.example.myapplication.users;

import com.example.myapplication.BasePresenter;
import com.example.myapplication.model.GithubUsersResponse;
import com.example.myapplication.service.GithubService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GithubPresenter implements BasePresenter<AllUsersView> {

    private final GithubService githubService;
    private Disposable disposable;

    private AllUsersView usersView;

    @Override
    public void attachView(AllUsersView view) {
        this.usersView = view;
    }


    public GithubPresenter() {
        this.githubService = new GithubService();
    }


    public void fetchUsers() {
        disposable = githubService.getAPI().getAllUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((GithubUsersResponse githubUsersResponse) -> {
                            usersView.hideLoading();
                            usersView.displayUsers(githubUsersResponse.getUsers());
                }
                );
    }

    @Override
    public void detachView() {
        disposable.dispose();
    }

}
