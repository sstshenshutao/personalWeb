package com.cybertaotao.database.user.money.data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserMoney{
  @NotNull
  int uid;
  int virtual_money;

  public int getUid () {
    return uid;
  }

  public void setUid (int uid) {
    this.uid = uid;
  }

  public int getVirtual_money () {
    return virtual_money;
  }

  public void setVirtual_money (int virtual_money) {
    this.virtual_money = virtual_money;
  }

}
