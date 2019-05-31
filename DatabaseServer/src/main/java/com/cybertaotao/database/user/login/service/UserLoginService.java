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

  public boolean insertLoginConstraint (UserLogin userLogin) {
    return dao.insert(userLogin);
  }

  public UserLogin selectLoginConstraint (int uid) {
    return dao.select(uid);
  }

  public boolean isUsernameAllow (int uid) {
    return selectLoginConstraint(uid).isUsername_allow();
  }

  public boolean isEmailAllow (int uid) {
    return selectLoginConstraint(uid).isEmail_allow();
  }

  public boolean isHandyNumberAllow (int uid) {
    return selectLoginConstraint(uid).isHandy_number_allow();
  }

  public boolean isLoginAllow (int uid) {
    return selectLoginConstraint(uid).isLogin_allow();
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
