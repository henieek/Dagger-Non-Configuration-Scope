package com.github.partition.nonconfscope.repos;

import com.google.gson.annotations.SerializedName;

public class Repo {
  @SerializedName("name")
  public String name;

  @Override
  public String toString() {
    return name;
  }
}
