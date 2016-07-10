package com.github.partition.nonconfscope.dagger;

import dagger.Component;

@Component(modules = AppModule.class)
@AppScope
public interface ApplicationComponent {
  NonConfigurationComponent nonConfiguration();
}
