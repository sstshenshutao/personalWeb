package com.cybertaotao.database.user.root.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cybertaotao.database.TestUserDB;
import com.cybertaotao.database.user.root.mapper.UserMapper;

import java.util.List;

@Service public class UserService {

  private final UserMapper dao;

  @Autowired
  public UserService (UserMapper dao) {
    this.dao = dao;
  }

  public int insert (TestUserDB model) {
    return dao.insert(model);
  }

  public TestUserDB select (int userid) {
    return dao.select(userid);
  }

  public List<TestUserDB> selectAll () {
    return dao.selectAll();
  }

  public int updatePassword (TestUserDB model) {
    return dao.updatePassword(model);
  }

  public int delete (Integer userid) {
    return dao.delete(userid);
  }

}
