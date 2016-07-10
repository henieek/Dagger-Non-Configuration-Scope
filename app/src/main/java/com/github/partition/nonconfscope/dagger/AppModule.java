package com.github.partition.nonconfscope.dagger;

import com.github.partition.nonconfscope.repos.GithubService;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

@Module
public class AppModule {

  @Provides
  @AppScope
  OkHttpClient provideOkHttpClient() {
    return new OkHttpClient();
  }

  @Provides
  @AppScope
  Retrofit provideRetrofit(OkHttpClient okHttpClient) {
    return new Retrofit.Builder()
        .baseUrl("https://api.github.com")
        .client(okHttpClient)
        .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  @Provides
  @AppScope
  GithubService provideGithubService(Retrofit retrofit) {
    return retrofit.create(GithubService.class);
  }
}
