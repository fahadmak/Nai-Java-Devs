package com.example.myapplication.users;

import com.example.myapplication.model.GithubUsers;
import com.example.myapplication.model.GithubUsersResponse;
import com.example.myapplication.service.GithubApiService;
import com.example.myapplication.utils.RxSchedulersOverrideRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GithubPresenterTest {

    @Mock
    GithubApiService apiService;

    @Mock
    AllUsersView usersView;

    GithubPresenter githubPresenter;

    @Rule
    public final RxSchedulersOverrideRule rxSchedulersOverrideRule = new RxSchedulersOverrideRule();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        githubPresenter = new GithubPresenter(apiService);
        githubPresenter.attachView(usersView);
    }

    @Test
    public void fetchUsers() {
        GithubUsers githubUsers = new GithubUsers("username", "avatar",
                "ggogle.com", "htmlUrl", "maka bugo", "frank",
                "bully");

        List<GithubUsers> githubUsersList = new ArrayList<>();
        githubUsersList.add(githubUsers);
        GithubUsersResponse githubUsersResponse = new GithubUsersResponse();
        githubUsersResponse.setGithubUsers(githubUsersList);
        when(apiService.getAllUsers()).thenReturn(Single.just(githubUsersResponse));

        githubPresenter.fetchUsers();

        verify(usersView).displayUsers(anyList());
        verify(usersView).hideLoading();

    }
}