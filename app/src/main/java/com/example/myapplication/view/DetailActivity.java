package com.example.myapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.GithubUsers;
import com.example.myapplication.model.GithubUsersResponse;
import com.example.myapplication.presenter.GithubPresenter;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity implements SingleUserView {

    private static final String TAG = "DetailActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        Bundle extras = intent.getExtras();

        String username = extras.getString("USERNAME");

        GithubPresenter githubPresenter = new GithubPresenter();
        githubPresenter.getUserProfile(username, this);

    }


    @Override
    public void displaySingleProfile(GithubUsers githubUsers) {
        TextView usernameText = findViewById(R.id.username_info);
        usernameText.setText(githubUsers.getUsername());

        TextView nameText = findViewById(R.id.full_name);
        nameText.setText(githubUsers.getFullName());

        TextView companyText = findViewById(R.id.company_info);
        companyText.setText(githubUsers.getCompany());

        TextView bioText = findViewById(R.id.bio_data);
        bioText.setText(githubUsers.getBio());

        TextView urlText = findViewById(R.id.url_info);
        urlText.setText(githubUsers.getUrl());

        ImageView imageView = findViewById(R.id.detail_image);
        Picasso.get().load(githubUsers.getAvatar()).into(imageView);



    }

    @Override
    public void displayError() {

    }
}
