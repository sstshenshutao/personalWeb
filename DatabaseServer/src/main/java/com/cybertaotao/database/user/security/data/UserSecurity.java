package com.cybertaotao.database.user.security.data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserSecurity{
  @NotNull
  int uid;
  @NotNull
  String username;
  @NotNull
  String email;
  String question1;
  String answer1;
  String question2;
  String answer2;
  @NotNull
  String handy_number;
  @NotNull
  String password;

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

  public String getEmail () {
    return email;
  }

  public void setEmail (String email) {
    this.email = email;
  }

  public String getQuestion1 () {
    return question1;
  }

  public void setQuestion1 (String question1) {
    this.question1 = question1;
  }

  public String getAnswer1 () {
    return answer1;
  }

  public void setAnswer1 (String answer1) {
    this.answer1 = answer1;
  }

  public String getQuestion2 () {
    return question2;
  }

  public void setQuestion2 (String question2) {
    this.question2 = question2;
  }

  public String getAnswer2 () {
    return answer2;
  }

  public void setAnswer2 (String answer2) {
    this.answer2 = answer2;
  }

  public String getHandy_number () {
    return handy_number;
  }

  public void setHandy_number (String handy_number) {
    this.handy_number = handy_number;
  }

  public String getPassword () {
    return password;
  }

  public void setPassword (String password) {
    this.password = password;
  }

}
