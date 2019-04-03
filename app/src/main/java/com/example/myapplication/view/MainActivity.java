package com.example.myapplication.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.GithubUsersAdapter;
import com.example.myapplication.model.GithubUsersResponse;
import com.example.myapplication.presenter.GithubPresenter;

public class MainActivity extends AppCompatActivity implements AllUsersView {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        GithubPresenter githubPresenter = new GithubPresenter();

        githubPresenter.getAllUserProfiles(this);

    }

    @Override
    public void displayUserProfiles(GithubUsersResponse response) {
        GithubUsersAdapter githubUsersAdapter = new GithubUsersAdapter(response.getUsers());
        recyclerView.setAdapter(githubUsersAdapter);
    }
}
