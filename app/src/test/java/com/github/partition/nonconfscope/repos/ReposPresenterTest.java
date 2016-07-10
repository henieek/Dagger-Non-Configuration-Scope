package com.github.partition.nonconfscope.repos;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import rx.subjects.BehaviorSubject;

@RunWith(RobolectricTestRunner.class)
public class ReposPresenterTest {

  @Mock
  private ReposView viewMock;
  @Mock
  private GithubService githubService;
  private final BehaviorSubject<List<Repo>> reposSubject = BehaviorSubject.create();
  private ReposPresenter sut;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    when(githubService.getRepos()).thenReturn(reposSubject);

    sut = new ReposPresenter(githubService);
  }

  @Test
  public void delivers_repos_to_view() throws Exception {
    sut.setView(viewMock);

    List<Repo> repos = sampleRepos();
    reposSubject.onNext(repos);

    verify(viewMock).setRepos(repos);
  }

  @Test
  public void delivers_error_to_view() throws Exception {
    sut.setView(viewMock);

    reposSubject.onError(new IOException());

    verify(viewMock).displayError();
  }

  @Test
  public void delivers_repos_to_each_new_view() throws Exception {
    List<Repo> repos = sampleRepos();
    reposSubject.onNext(repos);

    sut.setView(viewMock);

    verify(viewMock).setRepos(repos);
  }

  @Test
  public void delivers_errors_to_each_new_view() throws Exception {
    reposSubject.onError(new IOException());

    sut.setView(viewMock);

    verify(viewMock).displayError();
  }

  @Test
  public void still_delivers_repos_after_onComplete() throws Exception {
    List<Repo> repos = sampleRepos();
    reposSubject.onNext(repos);
    reposSubject.onCompleted();

    sut.setView(viewMock);

    verify(viewMock).setRepos(repos);
  }

  @Test
  public void still_delivers_errors_after_onComplete() throws Exception {
    reposSubject.onError(new IOException());
    reposSubject.onCompleted();

    sut.setView(viewMock);

    verify(viewMock).displayError();
  }

  private List<Repo> sampleRepos() {
    return Arrays.asList(repo("ButterKnife"), repo("Dagger"), repo("Truth"));
  }

  private Repo repo(String name) {
    Repo repo = new Repo();
    repo.name = name;
    return repo;
  }
}
