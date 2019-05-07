package com.nai.devs.view;

import android.content.IntentFilter;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.VisibleForTesting;
import android.support.design.widget.Snackbar;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.nai.devs.R;
import com.nai.devs.adapter.GithubUsersAdapter;
import com.nai.devs.model.GithubUsers;
import com.nai.devs.model.GithubUsersResponse;
import com.nai.devs.presenter.GithubPresenter;
import com.nai.devs.util.NetworkListener;
import com.nai.devs.util.NetworkUtility;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AllUsersView, NetworkListener {

    SwipeRefreshLayout swipeRefreshLayout;
    Parcelable listState;
    CountingIdlingResource
            mCountingIdlingResource = new CountingIdlingResource("DATA LOADER");
    ProgressBar progressBar;
    private RecyclerView recyclerView;
    private List<GithubUsers> githubUsers;
    private static final String GITHUB_USERS = "GITHUB_USERS";
    private static final String LIST_STATE = "LIST_STATE";
    private final NetworkUtility networkUtility = new NetworkUtility(this);
    IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
    private Snackbar snackbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.registerReceiver(networkUtility, filter);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            gridSize(2);

        } else {
            gridSize(3);
        }
        if (savedInstanceState != null) {
            githubUsers = savedInstanceState.getParcelableArrayList(GITHUB_USERS);
            listState = savedInstanceState.getParcelable(LIST_STATE);
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

    public void refreshData() {
        mCountingIdlingResource.increment();
        GithubPresenter githubPresenter = new GithubPresenter();
        githubPresenter.getAllUserProfiles(this);
    }


    @Override
    public void displayUserProfiles(GithubUsersResponse response) {
        githubUsers = response.getUsers();
        loadData(githubUsers);
        mCountingIdlingResource.decrement();
    }
//
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
        outState.putParcelableArrayList(GITHUB_USERS,
                (ArrayList<? extends Parcelable>) githubUsers);
        outState.putParcelable(LIST_STATE,
                recyclerView.getLayoutManager().onSaveInstanceState());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        githubUsers = savedInstanceState.getParcelableArrayList(GITHUB_USERS);
        listState = savedInstanceState.getParcelable(LIST_STATE);
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

    @VisibleForTesting
    public CountingIdlingResource getIdlingResourceInTest() {
        return mCountingIdlingResource;
    }

    @Override
    public void withInternet() {
        if (snackbar != null) {
            Snackbar.make(findViewById(R.id.indeterminateBar),
                    "Connected to the internet", 3000).show();
            refreshData();
        }
    }

    @Override
    public void noInternet() {
        snackbar = Snackbar.make(findViewById(R.id.indeterminateBar),
                "No internet connection", Snackbar.LENGTH_INDEFINITE);
        snackbar.show();
    }

}
