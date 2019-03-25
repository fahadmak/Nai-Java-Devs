package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "DetailActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        Bundle extras = intent.getExtras();

        String username = extras.getString(MainActivity.USER_NAME);

        TextView usernameText = findViewById(R.id.username_info);
        usernameText.setText(username);

        TextView nameText = findViewById(R.id.full_name);
        nameText.setText(username);

        int imageResource = extras.getInt(MainActivity.IMAGE);

        ImageView imageView = findViewById(R.id.detail_image);
        imageView.setImageResource(imageResource);

    }
}
