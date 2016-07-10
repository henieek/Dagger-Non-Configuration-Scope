package com.github.partition.nonconfscope.repos;

import com.github.partition.nonconfscope.BaseActivity;
import com.github.partition.nonconfscope.R;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class ReposListActivity extends BaseActivity implements ReposView {

  @Inject
  ReposPresenter presenter;

  private ArrayAdapter<Repo> adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    prepareView();
    getInjector().inject(this);
    presenter.setView(this);
  }

  private void prepareView() {
    setContentView(R.layout.activity_repos);
    ListView list = ButterKnife.findById(this, R.id.list);
    adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1);
    list.setAdapter(adapter);
  }

  @Override
  public void setRepos(List<Repo> repos) {
    adapter.clear();
    adapter.addAll(repos);
  }

  @Override
  public void displayError() {
    Toast.makeText(getApplicationContext(), R.string.repos_download_error, Toast.LENGTH_SHORT).show();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    presenter.setView(null);
  }
}
