package com.example.myapplication.users;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.myapplication.R;
import com.example.myapplication.model.GithubUsers;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AllUsersView {

    SwipeRefreshLayout swipeRefreshLayout;
    Parcelable listState;
    ProgressBar progressBar;
    private RecyclerView recyclerView;
    private List<GithubUsers> githubUsers;
    private static final String GITHUB_USERS = "GITHUB_USERS";
    private static final String LIST_STATE = "LIST_STATE";
    GithubPresenter githubPresenter = new GithubPresenter();
    GridLayoutManager mGridLayoutManager;

    CountingIdlingResource
            mCountingIdlingResource = new CountingIdlingResource("DATA LOADER");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.indeterminateBar);

        swipeRefreshLayout = findViewById(R.id.swipeContainer);

        recyclerView = findViewById(R.id.recycler_view);

        showLoading();

        githubPresenter.attachView(this);

        if (savedInstanceState != null) {
            githubUsers = savedInstanceState.getParcelableArrayList(GITHUB_USERS);
            listState = savedInstanceState.getParcelable(LIST_STATE);
        } else {
            mCountingIdlingResource.increment();
            githubPresenter.fetchUsers();
        }
        swipeRefreshLayout.setOnRefreshListener(() -> {
            mCountingIdlingResource.increment();
            githubPresenter.fetchUsers();
        });

    }

    private void gridSize(int size) {
        mGridLayoutManager = new GridLayoutManager(this, size,
                GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mGridLayoutManager);
    }

    @Override
    public void displayUsers(List<GithubUsers> githubUsers) {
        this.githubUsers = githubUsers;
        swipeRefreshLayout.setRefreshing(false);
        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            gridSize(2);

        } else {
            gridSize(3);
        }
        recyclerView.setHasFixedSize(true);
        GithubUsersAdapter githubUsersAdapter = new GithubUsersAdapter(this.githubUsers);
        recyclerView.setAdapter(githubUsersAdapter);
        if (!mCountingIdlingResource.isIdleNow()) {
            mCountingIdlingResource.decrement();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        listState = mGridLayoutManager.onSaveInstanceState();
        outState.putParcelable(LIST_STATE, mGridLayoutManager.onSaveInstanceState());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            listState = savedInstanceState.getParcelable(LIST_STATE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (listState != null) {
            mGridLayoutManager.onRestoreInstanceState(listState);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        githubPresenter.detachView();
    }

    @VisibleForTesting
    public CountingIdlingResource getIdlingResourceInTest() {
        return mCountingIdlingResource;
    }
    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);
    }

}
