package com.cybertaotao.database.user.login.service;

import com.cybertaotao.database.user.login.data.UserLogin;
import com.cybertaotao.database.user.login.mapper.UserLoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service public class UserLoginService {
  private final UserLoginMapper dao;
  @Autowired
  public UserLoginService (UserLoginMapper dao) {
    this.dao = dao;
  }
  public boolean insertUserLogin (UserLogin model) {
    return dao.insert(model);
  }
  public UserLogin selectUserLogin (int uid) {
    return dao.select(uid);
  }
  public int getUid (int uid) {
    return selectUserLogin(uid).getUid();
  }
  public boolean isUsername_allow (int uid) {
    return selectUserLogin(uid).isUsername_allow();
  }
  public boolean isEmail_allow (int uid) {
    return selectUserLogin(uid).isEmail_allow();
  }
  public boolean isHandy_number_allow (int uid) {
    return selectUserLogin(uid).isHandy_number_allow();
  }
  public boolean isLogin_allow (int uid) {
    return selectUserLogin(uid).isLogin_allow();
  }
  public List<UserLogin> selectAllEntries () {
    return dao.selectAll();
  }
  public int updateEntry (UserLogin model) {
    return dao.update(model);
  }
  public boolean deleteEntry (Integer uid) {
    return dao.delete(uid);
  }
}
