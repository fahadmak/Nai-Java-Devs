package com.example.myapplication;

import android.content.Intent;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.myapplication.model.GithubUsers;
import com.example.myapplication.view.DetailActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class DetailActivityTest {

    private final String userName = "k33ptoo";
    private final String avatar = "https://avatars0.githubusercontent.com/u/6637970?v=4";
    private CountingIdlingResource countingIdlingResource;

    public void registerIdlingResource(){
        countingIdlingResource = mActivityTestRule.getActivity().getIdlingResourceInTest();
        IdlingRegistry.getInstance().register(countingIdlingResource);
    }

    @After
    public void unregisterIdlingResource(){
        if (countingIdlingResource != null){
            IdlingRegistry.getInstance().unregister(countingIdlingResource);
        }
    }

    @Rule
    public IntentsTestRule<DetailActivity>
            mActivityTestRule = new IntentsTestRule<>(DetailActivity.class, true, false);

    @Before
    public void stubUserName(){
        GithubUsers githubUsers = new GithubUsers(userName, avatar, "ggogle.com", "",
                "", "");
        Intent intent = new Intent();
        intent.putExtra("USERNAME", userName);
        intent.putExtra("AVATAR", avatar);
        mActivityTestRule.launchActivity(intent);
    }

    @Test
    public void userScreeenDisplayed() {
        onView(withId(R.id.username_info)).check(matches(withText(userName)));
    }

    @Test
    public void githubUserDetailsShared(){
        registerIdlingResource();
        onView(withId(R.id.share_item_menu)).perform(click());
    }
}
