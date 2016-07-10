package com.github.partition.nonconfscope.clicks;

import com.github.partition.nonconfscope.BaseActivity;
import com.github.partition.nonconfscope.R;

import android.os.Bundle;
import android.widget.Button;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClicksActivity extends BaseActivity implements ClicksView {

  @Inject
  ClicksPresenter presenter;

  @BindView(R.id.clicker)
  Button clicker;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_clicks);
    ButterKnife.bind(this);
    getInjector().inject(this);
    presenter.setView(this);
  }

  @Override
  public void setCount(int clicksCount) {
    clicker.setText(String.valueOf(clicksCount));
  }

  @OnClick(R.id.clicker)
  void onClickerClicked() {
    presenter.click();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    presenter.setView(null);
  }
}
