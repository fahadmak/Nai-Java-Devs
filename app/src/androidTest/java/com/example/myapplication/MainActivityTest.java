package com.example.myapplication;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.myapplication.users.MainActivity;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    private CountingIdlingResource countingIdlingResource;
    private static final int ITEM_AT_POSITION = 10;

    @Rule
    public ActivityTestRule<MainActivity>
            mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

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

    @Test
    public void itemInPositionTwelve() {
        registerIdlingResource();
        onView(withId(R.id.recycler_view))
                .perform(
                        RecyclerViewActions.actionOnItemAtPosition(ITEM_AT_POSITION, click())
                );
    }
}
