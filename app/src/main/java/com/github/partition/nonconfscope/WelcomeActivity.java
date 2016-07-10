package com.github.partition.nonconfscope;

import com.github.partition.nonconfscope.clicks.ClicksActivity;
import com.github.partition.nonconfscope.repos.ReposListActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_welcome);
    ButterKnife.bind(this);
  }

  @OnClick(R.id.clicks)
  void onClicks() {
    startActivity(ClicksActivity.class);
  }

  @OnClick(R.id.repos)
  void onRepos() {
    startActivity(ReposListActivity.class);
  }

  private void startActivity(Class<? extends Activity> clazz) {
    startActivity(new Intent(this, clazz));
  }
}
