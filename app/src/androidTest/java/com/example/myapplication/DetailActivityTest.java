package com.example.myapplication;

import android.content.Intent;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.myapplication.model.GithubUsers;
import com.example.myapplication.profile.DetailActivity;
import com.example.myapplication.util.EspressoIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
public class DetailActivityTest {

    private final String userName = "k33ptoo";
    private final String avatar = "https://avatars0.githubusercontent.com/u/6637970?v=4";

    @Before
    public void registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource());
    }

    // Unregister your Idling Resource so it can be garbage collected and does not leak any memory
    @After
    public void unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource());
    }
    @Rule
    public IntentsTestRule<DetailActivity>
            mActivityTestRule = new IntentsTestRule<>(DetailActivity.class, true, false);

    @Before
    public void stubUserName(){
        GithubUsers githubUsers = new GithubUsers(userName, avatar, "ggogle.com", "htmlUrl", "",
                "", "");
        Intent intent = new Intent();
        intent.putExtra("USERNAME", userName);
        intent.putExtra("AVATAR", avatar);
        mActivityTestRule.launchActivity(intent);
    }

    @Test
    public void userScreeenDisplayed() {
        registerIdlingResource();
        onView(withId(R.id.username_info)).check(matches(isDisplayed()));
    }

    @Test
    public void githubUserDetailsShared(){
        registerIdlingResource();
        onView(withId(R.id.share_item_menu)).perform(click());
    }
}
