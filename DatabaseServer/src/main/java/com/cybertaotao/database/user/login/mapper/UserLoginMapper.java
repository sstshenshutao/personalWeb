package com.cybertaotao.database.user.login.mapper;

import com.cybertaotao.database.user.login.data.UserLogin;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper @Component public interface UserLoginMapper {

  //    uid | username_allow | email_allow | handy_number_allow | login_allow
  @Insert("INSERT INTO `user_login`(uid,username_allow,email_allow,handy_number_allow,login_allow) "
            + "VALUES(#{uid},#{username_allow},#{email_allow},#{handy_number_allow},#{login_allow})")
  boolean insert (UserLogin userLogin);

  @Delete("DELETE FROM `user_login` WHERE uid=#{uid}")
  boolean delete (Integer uid);

  @Select("SELECT * FROM `user_login` WHERE uid=#{uid}")
  UserLogin select (int uid);

  @Select("SELECT * FROM `user_login`")
  List<UserLogin> selectAll ();

  @Update("UPDATE `user_login` SET username_allow=#{username_allow}" + ", handy_number_allow=#{handy_number_allow}"
            + ", email_allow=#{email_allow}" + ", login_allow=#{login_allow}" + " WHERE uid=#{uid}")
  int update (UserLogin userLogin);
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