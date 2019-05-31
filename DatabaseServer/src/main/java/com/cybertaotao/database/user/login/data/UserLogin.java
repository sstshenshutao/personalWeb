package com.cybertaotao.database.user.login.data;

import javax.validation.constraints.NotNull;

public class UserLogin{
  @NotNull
  int uid;
  boolean username_allow;
  boolean email_allow;
  boolean handy_number_allow;
  boolean login_allow;

  public int getUid () {
    return uid;
  }

  public void setUid (int uid) {
    this.uid = uid;
  }

  public boolean isUsername_allow () {
    return username_allow;
  }

  public void setUsername_allow (boolean username_allow) {
    this.username_allow = username_allow;
  }

  public boolean isEmail_allow () {
    return email_allow;
  }

  public void setEmail_allow (boolean email_allow) {
    this.email_allow = email_allow;
  }

  public boolean isHandy_number_allow () {
    return handy_number_allow;
  }

  public void setHandy_number_allow (boolean handy_number_allow) {
    this.handy_number_allow = handy_number_allow;
  }

  public boolean isLogin_allow () {
    return login_allow;
  }

  public void setLogin_allow (boolean login_allow) {
    this.login_allow = login_allow;
  }

}
