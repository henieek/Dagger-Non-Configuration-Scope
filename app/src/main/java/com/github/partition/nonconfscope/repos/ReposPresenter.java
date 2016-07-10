package com.github.partition.nonconfscope.repos;

import com.github.partition.nonconfscope.dagger.NonConfigurationScope;
import com.github.partition.nonconfscope.rx.ObserverAdapter;
import com.github.partition.nonconfscope.rx.RxUtils;

import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import rx.subscriptions.Subscriptions;

@NonConfigurationScope
public class ReposPresenter {

  private final BehaviorSubject<List<Repo>> repos = BehaviorSubject.create();
  private Subscription viewSubscription = Subscriptions.empty();

  @Inject
  ReposPresenter(GithubService githubService) {
    githubService
        .getRepos()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(RxUtils.muteOnComplete(repos));
  }

  void setView(@Nullable final ReposView view) {
    viewSubscription.unsubscribe();
    if (view != null) {
      subscribeView(view);
    }
  }

  private void subscribeView(final ReposView view) {
    viewSubscription = repos
        .observeOn(Schedulers.immediate())
        .subscribe(new ObserverAdapter<List<Repo>>() {
          @Override
          public void onError(Throwable e) {
            Log.e("ReposPresenter", "Error", e);
            view.displayError();
          }

          @Override
          public void onNext(List<Repo> repos) {
            view.setRepos(repos);
          }
        });
  }
}
