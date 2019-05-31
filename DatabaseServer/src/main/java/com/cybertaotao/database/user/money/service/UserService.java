package com.cybertaotao.database.user.root.service;

import com.cybertaotao.database.user.root.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cybertaotao.database.user.root.mapper.UserMapper;

import java.util.List;

@Service public class UserService {

  private final UserMapper dao;

  @Autowired
  public UserService (UserMapper dao) {
    this.dao = dao;
  }

  public boolean addUser (User model) {
    return dao.insert(model);
  }

  public User selectUser (int uid) {
    return dao.select(uid);
  }

  public List<User> selectAllUsers () {
    return dao.selectAll();
  }

  public int updateUsername (User model) {
    return dao.updateUsername(model);
  }

  public boolean deleteUser (Integer uid) {
    return dao.delete(uid);
  }

}
