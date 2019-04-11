package com.example.myapplication;

import com.example.myapplication.service.GithubApi;
import com.example.myapplication.service.GithubService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;


@RunWith(RobolectricTestRunner.class)
public class GithubServiceTest {

    private GithubService service = new GithubService();

    @Test
    public void getAPI() {
        assertThat(service.getAPI(), instanceOf(GithubApi.class));
    }
}