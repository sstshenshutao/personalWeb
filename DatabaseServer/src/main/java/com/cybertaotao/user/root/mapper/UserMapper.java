package com.cybertaotao.user.root.mapper;

import com.cybertaotao.user.root.data.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import com.cybertaotao.TestUserDB;

import java.util.List;

@Mapper
@Component
public interface UserMapper {

  /**
   * for http get
   * @return
   */
  User getUser ();

  /**
   * for http post
   * @return true if success
   */
  @Insert("INSERT INTO user(userid) VALUES(#{userid}")
  boolean addUser();

  /**
   * for http delete
   * @return true
   */
  boolean deleteUser();


  // 插入 并返回自增ID
  @Insert("INSERT INTO user(userid, username, password) VALUES(#{userid}, #{username}, #{password})")
  @SelectKey(statement = "SELECT seq userid FROM sqlite_sequence WHERE (name = 'user')", before = false, keyProperty
    = "id", resultType = int.class)
  int insert (TestUserDB model);

  // 根据 ID 查询
  @Select("SELECT * FROM user WHERE userid=#{userid}")
  TestUserDB select(int userid);

  // 查询全部
  @Select("SELECT * FROM user")
  List<TestUserDB> selectAll ();

  // 更新 value
  @Update("UPDATE user SET password=#{password} WHERE userid=#{userid}")
  int updatePassword(TestUserDB model);

  // 根据 ID 删除
  @Delete("DELETE FROM user WHERE userid=#{userid}")
  int delete(Integer userid);
}