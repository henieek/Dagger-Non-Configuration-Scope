package com.github.partition.nonconfscope.repos;

import java.util.List;

interface ReposView {
  void setRepos(List<Repo> repos);
  void displayError();
}
