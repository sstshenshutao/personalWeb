package com.cybertaotao.database.user.root.data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class User {
  @NotNull
  int uid;
  @NotNull
  @NotEmpty
  String username;

  public int getUid () {
    return uid;
  }

  public void setUid (int uid) {
    this.uid = uid;
  }

  public String getUsername () {
    return username;
  }

  public void setUsername (String username) {
    this.username = username;
  }

}
