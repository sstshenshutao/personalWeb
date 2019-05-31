package com.cybertaotao.database.user.info.data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserInfo {

  @NotNull int uid;
  @NotNull String username;
  String handy_number;
  String email;
  String hobbys;
  int sex;
  int age;
  String job;
  String nickname;
  String homepage;
  /**
   * todo:"blob" need to change to BlobTypeHandler
   */
  String head_photo;
  String true_name;
  String residence;
  String country;
  String birthday;

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

  public String getHandy_number () {
    return handy_number;
  }

  public void setHandy_number (String handy_number) {
    this.handy_number = handy_number;
  }

  public String getEmail () {
    return email;
  }

  public void setEmail (String email) {
    this.email = email;
  }

  public String getHobbys () {
    return hobbys;
  }

  public void setHobbys (String hobbys) {
    this.hobbys = hobbys;
  }

  public int getSex () {
    return sex;
  }

  public void setSex (int sex) {
    this.sex = sex;
  }

  public int getAge () {
    return age;
  }

  public void setAge (int age) {
    this.age = age;
  }

  public String getJob () {
    return job;
  }

  public void setJob (String job) {
    this.job = job;
  }

  public String getNickname () {
    return nickname;
  }

  public void setNickname (String nickname) {
    this.nickname = nickname;
  }

  public String getHomepage () {
    return homepage;
  }

  public void setHomepage (String homepage) {
    this.homepage = homepage;
  }

  public String getHead_photo () {
    return head_photo;
  }

  public void setHead_photo (String head_photo) {
    this.head_photo = head_photo;
  }

  public String getTrue_name () {
    return true_name;
  }

  public void setTrue_name (String true_name) {
    this.true_name = true_name;
  }

  public String getResidence () {
    return residence;
  }

  public void setResidence (String residence) {
    this.residence = residence;
  }

  public String getCountry () {
    return country;
  }

  public void setCountry (String country) {
    this.country = country;
  }

  public String getBirthday () {
    return birthday;
  }

  public void setBirthday (String birthday) {
    this.birthday = birthday;
  }

}
