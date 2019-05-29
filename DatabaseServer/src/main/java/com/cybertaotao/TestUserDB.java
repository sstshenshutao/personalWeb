package com.cybertaotao;

import javax.validation.constraints.NotNull;

public class TestUserDB {

  public int userid;
  @NotNull public String username;
  @NotNull public String password;

  public int getUserid () {
    return userid;
  }

  public void setUserid (int userid) {
    this.userid = userid;
  }

  public String getUsername () {
    return username;
  }

  public void setUsername (String username) {
    this.username = username;
  }

  public String getPassword () {
    return password;
  }

  public void setPassword (String password) {
    this.password = password;
  }

}
