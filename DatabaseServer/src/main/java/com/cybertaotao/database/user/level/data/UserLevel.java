package com.cybertaotao.database.user.level.data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserLevel{
  @NotNull
  int uid;
  int manage_level;
  int access_level;

  public int getUid () {
    return uid;
  }

  public void setUid (int uid) {
    this.uid = uid;
  }

  public int getManage_level () {
    return manage_level;
  }

  public void setManage_level (int manage_level) {
    this.manage_level = manage_level;
  }

  public int getAccess_level () {
    return access_level;
  }

  public void setAccess_level (int access_level) {
    this.access_level = access_level;
  }

}