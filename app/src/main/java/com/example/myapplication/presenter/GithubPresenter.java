package com.example.myapplication.presenter;

import com.example.myapplication.service.GithubService;
import com.example.myapplication.view.AllUsersView;
import com.example.myapplication.view.SingleUserView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GithubPresenter {

    GithubService githubService;
    Disposable disposable;

    public GithubPresenter() {
        this.githubService = new GithubService();
    }

    public void getAllUserProfiles(final AllUsersView allUsersView) {
        disposable = githubService.getAPI().getAllUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(githubUsersResponse ->
                        allUsersView.displayUserProfiles(githubUsersResponse)
                    );
    }

    public void getUserProfile(String username, final SingleUserView singleUserView) {

        disposable = githubService.getAPI().getSingleUser(username).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(
                        githubUsers -> singleUserView.displaySingleProfile(githubUsers));
    }

}
