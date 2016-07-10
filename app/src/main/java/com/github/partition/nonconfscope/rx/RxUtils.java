package com.github.partition.nonconfscope.rx;

import rx.Observer;

public abstract class RxUtils {

  private RxUtils() {

  }

  public static <T> Observer<T> muteOnComplete(final Observer<T> observer) {
    return new ObserverAdapter<T>() {
      @Override
      public void onError(Throwable e) {
        observer.onError(e);
      }

      @Override
      public void onNext(T t) {
        observer.onNext(t);
      }
    };
  }
}
