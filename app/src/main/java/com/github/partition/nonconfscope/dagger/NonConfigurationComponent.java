package com.github.partition.nonconfscope.dagger;

import com.github.partition.nonconfscope.clicks.ClicksActivity;
import com.github.partition.nonconfscope.repos.ReposListActivity;

import dagger.Subcomponent;

@Subcomponent
@NonConfigurationScope
public interface NonConfigurationComponent {
  void inject(ReposListActivity i);
  void inject(ClicksActivity i);
}
