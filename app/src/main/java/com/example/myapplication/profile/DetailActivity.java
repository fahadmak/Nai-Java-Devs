package com.example.myapplication.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.GithubUsers;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class DetailActivity extends DaggerAppCompatActivity implements SingleUserView {

    private GithubUsers mGithubUsers;

    @Inject
    SingleUserPresenter singleUserPresenter;

    String username = null;
    String avatar = null;
    String htmlUrl = null;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int i = item.getItemId();
        if (i == R.id.share_item_menu) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this awesome developer "
                    +
                    "@" + mGithubUsers.getUsername() + ", " + mGithubUsers.getUrl() + ".");
            startActivity(Intent.createChooser(shareIntent, "Share via"));
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);
        singleUserPresenter.attachView(this);

        if (savedInstanceState != null) {
            mGithubUsers = savedInstanceState.getParcelable("user");
            displaySingleUser(mGithubUsers);
        } else {
            Intent intent = getIntent();

            Bundle extras = intent.getExtras();

            if (extras != null) {
                username = extras.getString("USERNAME");
                avatar = extras.getString("AVATAR");
                htmlUrl = extras.getString("HTML_URL");
            }

            singleUserPresenter.fetchSingleUsers(username);
        }

    }

    @Override
    public void displaySingleUser(GithubUsers githubUsers) {

        TextView usernameText = findViewById(R.id.username_info);
        usernameText.setText(username);

        TextView urlText = findViewById(R.id.url_info);
        urlText.setText(htmlUrl);

        ImageView imageView = findViewById(R.id.detail_image);
        Picasso.get().load(avatar).into(imageView);

        mGithubUsers = githubUsers;
        TextView nameText = findViewById(R.id.full_name);
        nameText.setText(mGithubUsers.getFullName());

        TextView companyText = findViewById(R.id.company_info);
        companyText.setText(mGithubUsers.getCompany());

        TextView bioText = findViewById(R.id.bio_data);
        bioText.setText(mGithubUsers.getBio());

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("user", mGithubUsers);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        mGithubUsers = savedInstanceState.getParcelable("user");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onStop() {
        super.onStop();
        singleUserPresenter.detachView();
    }

}
