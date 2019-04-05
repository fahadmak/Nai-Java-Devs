package com.example.myapplication.view;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.myapplication.R;
import com.example.myapplication.adapter.GithubUsersAdapter;
import com.example.myapplication.model.GithubUsers;
import com.example.myapplication.model.GithubUsersResponse;
import com.example.myapplication.presenter.GithubPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AllUsersView {

    SwipeRefreshLayout swipeRefreshLayout;
    Parcelable listState;
    private RecyclerView recyclerView;
    private List<GithubUsers> githubUsers;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            gridSize(2);

        } else {
            gridSize(3);
        }

        if (savedInstanceState != null) {
            githubUsers = savedInstanceState.getParcelableArrayList("users");
            listState = savedInstanceState.getParcelable("listState");
        } else {
            refreshData();
        }
        swipeRefreshLayout = findViewById(R.id.swipeContainer);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });

    }

    private void gridSize(int size) {
        recyclerView.setLayoutManager(new GridLayoutManager(this, size,
                GridLayoutManager.VERTICAL, false));
    }

    void refreshData() {
        GithubPresenter githubPresenter = new GithubPresenter();
        githubPresenter.getAllUserProfiles(this);

    }


    @Override
    public void displayUserProfiles(GithubUsersResponse response) {
        githubUsers = response.getUsers();
        loadData(githubUsers);
    }

    void loadData(List<GithubUsers> users) {
        progressBar = findViewById(R.id.indeterminateBar);
        progressBar.setVisibility(View.INVISIBLE);
        swipeRefreshLayout.setRefreshing(false);
        GithubUsersAdapter githubUsersAdapter = new GithubUsersAdapter(users);
        recyclerView.setAdapter(githubUsersAdapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("users", (ArrayList<? extends Parcelable>) githubUsers);
        outState.putParcelable("listState", recyclerView.getLayoutManager().onSaveInstanceState());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        githubUsers = savedInstanceState.getParcelableArrayList("users");
        listState = savedInstanceState.getParcelable("listState");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (listState != null) {
            recyclerView.getLayoutManager().onRestoreInstanceState(listState);
            loadData(githubUsers);
        }
    }

}
