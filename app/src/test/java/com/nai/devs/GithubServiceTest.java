package com.nai.devs;

import com.nai.devs.service.GithubApi;
import com.nai.devs.service.GithubService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;


@RunWith(RobolectricTestRunner.class)
public class GithubServiceTest {

    private GithubService service = new GithubService();

    @Test
    public void getAPI() {
        assertThat(service.getAPI(), instanceOf(GithubApi.class));
    }
}