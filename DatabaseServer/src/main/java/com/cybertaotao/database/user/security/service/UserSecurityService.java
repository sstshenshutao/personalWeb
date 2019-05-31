package com.cybertaotao.database.user.security.service;

import com.cybertaotao.database.user.security.data.UserSecurity;
import com.cybertaotao.database.user.security.mapper.UserSecurityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service public class UserSecurityService {
  private final UserSecurityMapper dao;
  @Autowired
  public UserSecurityService (UserSecurityMapper dao) {
    this.dao = dao;
  }
  public boolean insertUserSecurity (UserSecurity model) {
    return dao.insert(model);
  }
  public UserSecurity selectUserSecurity (int uid) {
    return dao.select(uid);
  }
  public int getUid (int uid) {
    return selectUserSecurity(uid).getUid();
  }
  public String getUsername (int uid) {
    return selectUserSecurity(uid).getUsername();
  }
  public String getEmail (int uid) {
    return selectUserSecurity(uid).getEmail();
  }
  public String getQuestion1 (int uid) {
    return selectUserSecurity(uid).getQuestion1();
  }
  public String getAnswer1 (int uid) {
    return selectUserSecurity(uid).getAnswer1();
  }
  public String getQuestion2 (int uid) {
    return selectUserSecurity(uid).getQuestion2();
  }
  public String getAnswer2 (int uid) {
    return selectUserSecurity(uid).getAnswer2();
  }
  public String getHandy_number (int uid) {
    return selectUserSecurity(uid).getHandy_number();
  }
  public String getPassword (int uid) {
    return selectUserSecurity(uid).getPassword();
  }
  public List<UserSecurity> selectAllEntries () {
    return dao.selectAll();
  }
  public int updateEntry (UserSecurity model) {
    return dao.update(model);
  }
  public boolean deleteEntry (Integer uid) {
    return dao.delete(uid);
  }
}