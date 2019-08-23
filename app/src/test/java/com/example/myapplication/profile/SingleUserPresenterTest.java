package com.example.myapplication.profile;

import com.example.myapplication.model.GithubUsers;
import com.example.myapplication.service.GithubApiService;
import com.example.myapplication.utils.RxSchedulersOverrideRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Single;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SingleUserPresenterTest {

    @Mock
    GithubApiService apiService;

    @Mock
    SingleUserView singleUserView;

    private SingleUserPresenter singleUserPresenter;

    @Rule
    public final RxSchedulersOverrideRule rxSchedulersOverrideRule = new RxSchedulersOverrideRule();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        singleUserPresenter = new SingleUserPresenter(apiService);
        singleUserPresenter.attachView(singleUserView);
    }

    @Test
    public void fetchSingleUsers() {
        GithubUsers githubUsers = new GithubUsers("thedancercodes", "avatar",
                "ggogle.com", "htmlUrl", "maka bugo",
                "frank", "bully");

        when(apiService.getSingleUser("thedancercodes"))
                .thenReturn(Single.just(githubUsers));

        singleUserPresenter.fetchSingleUsers("thedancercodes");

        verify(singleUserView).displaySingleUser(githubUsers);
    }
}
