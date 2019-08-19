package com.example.myapplication.users;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.myapplication.R;
import com.example.myapplication.model.GithubUsers;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

import static com.example.myapplication.util.Constants.LIST_STATE;

public class MainActivity extends DaggerAppCompatActivity implements AllUsersView {

    @Inject
    GithubPresenter githubPresenter;

    SwipeRefreshLayout swipeRefreshLayout;
    Parcelable listState;
    ProgressBar progressBar;
    private RecyclerView recyclerView;
    GridLayoutManager mGridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.indeterminateBar);

        swipeRefreshLayout = findViewById(R.id.swipeContainer);

        recyclerView = findViewById(R.id.recycler_view);

        showLoading();
        githubPresenter.attachView(this);
        githubPresenter.fetchUsers();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefresh());

    }

    class SwipeRefresh implements SwipeRefreshLayout.OnRefreshListener {

        @Override
        public void onRefresh() {
            githubPresenter.fetchUsers();
        }
    }

    private void gridSize(int size) {
        mGridLayoutManager = new GridLayoutManager(this, size,
                GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mGridLayoutManager);
    }

    @Override
    public void displayUsers(List<GithubUsers> githubUsers) {
        swipeRefreshLayout.setRefreshing(false);
        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            gridSize(2);

        } else {
            gridSize(3);
        }
        recyclerView.setHasFixedSize(true);
        GithubUsersAdapter githubUsersAdapter = new GithubUsersAdapter(githubUsers);
        recyclerView.setAdapter(githubUsersAdapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        listState = mGridLayoutManager.onSaveInstanceState();
        outState.putParcelable(LIST_STATE, listState);
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

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);
    }

}
