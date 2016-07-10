package com.github.partition.nonconfscope;

import com.github.partition.nonconfscope.dagger.NonConfigurationComponent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

  private NonConfigurationComponent injector;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    injector = retrieveInjectorOrCreateNew();
  }

  private NonConfigurationComponent retrieveInjectorOrCreateNew() {
    Object lastNonConfInstance = getLastCustomNonConfigurationInstance();
    if (lastNonConfInstance == null) {
      return NonConfApplication.applicationComponent().nonConfiguration();
    } else {
      return (NonConfigurationComponent) lastNonConfInstance;
    }
  }

  protected NonConfigurationComponent getInjector() {
    return injector;
  }

  @Override
  public Object onRetainCustomNonConfigurationInstance() {
    return injector;
  }
}
