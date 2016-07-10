package com.github.partition.nonconfscope.rx;

import rx.Observer;

public abstract class ObserverAdapter<T> implements Observer<T> {

  @Override
  public void onCompleted() {

  }

  @Override
  public void onError(Throwable e) {

  }

  @Override
  public void onNext(T t) {

  }
}
