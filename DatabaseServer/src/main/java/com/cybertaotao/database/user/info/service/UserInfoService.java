package com.cybertaotao.database.user.info.service;

import com.cybertaotao.database.user.info.data.UserInfo;
import com.cybertaotao.database.user.info.mapper.UserInfoMapper;
import com.cybertaotao.database.user.root.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cybertaotao.database.user.root.mapper.UserMapper;

import java.util.List;

@Service public class UserInfoService {
  private final UserInfoMapper dao;
  @Autowired
  public UserInfoService (UserInfoMapper dao) {
    this.dao = dao;
  }
  public boolean insertUserInfo (UserInfo model) {
    return dao.insert(model);
  }
  public UserInfo selectUserInfo (int uid) {
    return dao.select(uid);
  }
  public int getUid (int uid) {
    return selectUserInfo(uid).getUid();
  }
  public String getUsername (int uid) {
    return selectUserInfo(uid).getUsername();
  }
  public String getHandy_number (int uid) {
    return selectUserInfo(uid).getHandy_number();
  }
  public String getEmail (int uid) {
    return selectUserInfo(uid).getEmail();
  }
  public String getHobbys (int uid) {
    return selectUserInfo(uid).getHobbys();
  }
  public int getSex (int uid) {
    return selectUserInfo(uid).getSex();
  }
  public int getAge (int uid) {
    return selectUserInfo(uid).getAge();
  }
  public String getJob (int uid) {
    return selectUserInfo(uid).getJob();
  }
  public String getNickname (int uid) {
    return selectUserInfo(uid).getNickname();
  }
  public String getHomepage (int uid) {
    return selectUserInfo(uid).getHomepage();
  }

  /**
   * todo: return need to change to "blob"
   * @param uid
   * @return
   */
  public String getHead_photo (int uid) {
    return selectUserInfo(uid).getHead_photo();
  }
  public String getTrue_name (int uid) {
    return selectUserInfo(uid).getTrue_name();
  }
  public String getResidence (int uid) {
    return selectUserInfo(uid).getResidence();
  }
  public String getCountry (int uid) {
    return selectUserInfo(uid).getCountry();
  }
  public String getBirthday (int uid) {
    return selectUserInfo(uid).getBirthday();
  }
  public List<UserInfo> selectAllEntries () {
    return dao.selectAll();
  }
  public int updateEntry (UserInfo model) {
    return dao.update(model);
  }
  public boolean deleteEntry (Integer uid) {
    return dao.delete(uid);
  }
}