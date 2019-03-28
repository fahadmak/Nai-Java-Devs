package com.example.myapplication.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.GithubUsersAdapter;
import com.example.myapplication.model.GithubUsers;
import com.example.myapplication.model.GithubUsersResponse;
import com.example.myapplication.presenter.GithubPresenter;

public class MainActivity extends AppCompatActivity implements AllUsersView {

    public static final String USER_NAME = "com.example.myapplication.USERNAME";
    public static final String IMAGE = "com.example.myapplication.IMAGE";
    private static final String TAG = "MainActivity";
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
        Log.d(TAG, "displayUserProfiles: jowe" + response.getUsers());

        for(GithubUsers user: response.getUsers()){
            Log.d(TAG, " " + user.getUsername() + " " + user.getUrl());
        }
        GithubUsersAdapter githubUsersAdapter = new GithubUsersAdapter(response.getUsers());
        recyclerView.setAdapter(githubUsersAdapter);

    }

    @Override
    public void displayError() {
        Log.d(TAG, "displayUserProfiles: fahad");
        Toast.makeText(MainActivity.this, "krava", Toast.LENGTH_SHORT).show();
    }

}
