package com.cybertaotao.database.user.level.service;

import com.cybertaotao.database.user.level.data.UserLevel;
import com.cybertaotao.database.user.level.mapper.UserLevelMapper;
import com.cybertaotao.database.user.root.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cybertaotao.database.user.root.mapper.UserMapper;

import java.util.List;

@Service public class UserLevelService {
  private final UserLevelMapper dao;
  @Autowired
  public UserLevelService (UserLevelMapper dao) {
    this.dao = dao;
  }
  public boolean insertUserLevel (UserLevel model) {
    return dao.insert(model);
  }
  public UserLevel selectUserLevel (int uid) {
    return dao.select(uid);
  }
  public int getUid (int uid) {
    return selectUserLevel(uid).getUid();
  }
  public int getManage_level (int uid) {
    return selectUserLevel(uid).getManage_level();
  }
  public int getAccess_level (int uid) {
    return selectUserLevel(uid).getAccess_level();
  }
  public List<UserLevel> selectAllEntries () {
    return dao.selectAll();
  }
  public int updateEntry (UserLevel model) {
    return dao.update(model);
  }
  public boolean deleteEntry (Integer uid) {
    return dao.delete(uid);
  }
}