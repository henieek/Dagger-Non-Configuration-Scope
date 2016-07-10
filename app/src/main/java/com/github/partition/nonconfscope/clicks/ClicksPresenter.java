package com.github.partition.nonconfscope.clicks;

import com.github.partition.nonconfscope.dagger.NonConfigurationScope;

import android.support.annotation.Nullable;

import javax.inject.Inject;

@NonConfigurationScope
class ClicksPresenter {

  private int clicksCount = 0;
  private ClicksView view;

  @Inject
  ClicksPresenter() {

  }

  void setView(@Nullable ClicksView view) {
    this.view = view;
    notifyViewWithClicksCount();
  }

  private void notifyViewWithClicksCount() {
    if (view != null) {
      view.setCount(clicksCount);
    }
  }

  void click() {
    clicksCount++;
    notifyViewWithClicksCount();
  }
}
