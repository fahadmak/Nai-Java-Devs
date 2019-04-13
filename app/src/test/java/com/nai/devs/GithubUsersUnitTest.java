package com.nai.devs;


import android.os.Parcel;

import com.nai.devs.model.GithubUsers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class GithubUsersUnitTest {

    private GithubUsers githubUsers;

    @Before
    public void setUp(){
        githubUsers = new GithubUsers("username", "avatar",
                "ggogle.com", "maka bugo", "frank", "bully");
    }

    @Test
    public void test_github_user_parcelable() {

        Parcel parcel = Parcel.obtain();
        githubUsers.writeToParcel(parcel, githubUsers.describeContents());

        parcel.setDataPosition(0);
        GithubUsers createdFromParcel = GithubUsers.CREATOR.createFromParcel(parcel);

        assertThat(createdFromParcel.getUsername(), is("username"));
        assertThat(createdFromParcel, instanceOf(GithubUsers.class));
    }

    @Test
    public void test_get_avatar(){
        assertThat(githubUsers.getAvatar(), is("avatar"));
    }

    @Test
    public void test_get_username(){
        assertThat(githubUsers.getUsername(), is("username"));
    }

}
