package com.example.myapplication.view;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.GithubUsersAdapter;
import com.example.myapplication.model.GithubUsers;
import com.example.myapplication.model.GithubUsersResponse;
import com.example.myapplication.presenter.GithubPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AllUsersView {

    private RecyclerView recyclerView;

    private List<GithubUsers> githubUsers;

    Parcelable listState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            githubUsers = savedInstanceState.getParcelableArrayList("users");
            listState = savedInstanceState.getParcelable("listState");
        } else {
            GithubPresenter githubPresenter = new GithubPresenter();
            githubPresenter.getAllUserProfiles(this);
        }
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void displayUserProfiles(GithubUsersResponse response) {
        githubUsers = response.getUsers();
        loadData(githubUsers);
    }

    void loadData(List<GithubUsers> users) {
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
