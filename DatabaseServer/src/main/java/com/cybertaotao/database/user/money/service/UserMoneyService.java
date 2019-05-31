package com.cybertaotao.database.user.money.service;

import com.cybertaotao.database.user.money.data.UserMoney;
import com.cybertaotao.database.user.money.mapper.UserMoneyMapper;
import com.cybertaotao.database.user.root.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cybertaotao.database.user.root.mapper.UserMapper;

import java.util.List;

@Service public class UserMoneyService {
  private final UserMoneyMapper dao;
  @Autowired
  public UserMoneyService (UserMoneyMapper dao) {
    this.dao = dao;
  }
  public boolean insertUserMoney (UserMoney model) {
    return dao.insert(model);
  }
  public UserMoney selectUserMoney (int uid) {
    return dao.select(uid);
  }
  public int getUid (int uid) {
    return selectUserMoney(uid).getUid();
  }
  public int getVirtual_money (int uid) {
    return selectUserMoney(uid).getVirtual_money();
  }
  public List<UserMoney> selectAllEntries () {
    return dao.selectAll();
  }
  public int updateEntry (UserMoney model) {
    return dao.update(model);
  }
  public boolean deleteEntry (Integer uid) {
    return dao.delete(uid);
  }
}
