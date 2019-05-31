package com.cybertaotao.database.user.root.mapper;

import com.cybertaotao.database.user.root.data.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper @Component public interface UserMapper {

  /**
   * for http post
   *
   * @return true if success
   */
  @Insert("INSERT INTO `user`(uid,username) VALUES(#{uid},#{username})")
  boolean insert (User user);

  /**
   * for http delete
   *
   * @return true
   */
  @Delete("DELETE FROM `user` WHERE uid=#{uid}")
  boolean delete (Integer uid);

  @Select("SELECT * FROM `user` WHERE uid=#{uid}")
  User select (int uid);

  @Select("SELECT * FROM `user`")
  List<User> selectAll ();

  @Update("UPDATE `user` SET username=#{username} WHERE uid=#{uid}")
  int updateUsername (User user);
  //
  //  // 插入 并返回自增ID
  //  @Insert("INSERT INTO user(userid, username, password) VALUES(#{userid}, #{username}, #{password})")
  //  @SelectKey(statement = "SELECT seq userid FROM sqlite_sequence WHERE (name = 'user')", before = false, keyProperty
  //    = "id", resultType = int.class)
  //  int insert (TestUserDB model);
  //
  //  // 根据 ID 查询
  //  @Select("SELECT * FROM user WHERE userid=#{userid}")
  //  TestUserDB select(int userid);
  //
  //  // 查询全部
  //  @Select("SELECT * FROM user")
  //  List<TestUserDB> selectAll ();
  //
  //  // 更新 value
  //  @Update("UPDATE user SET password=#{password} WHERE userid=#{userid}")
  //  int updatePassword(TestUserDB model);
  //
  //  // 根据 ID 删除
  //  @Delete("DELETE FROM user WHERE userid=#{userid}")
  //  int delete(Integer userid);
}