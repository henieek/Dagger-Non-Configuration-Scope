package com.github.partition.nonconfscope.repos;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

public interface GithubService {

  @GET("/users/nasa/repos")
  Observable<List<Repo>> getRepos();
}
