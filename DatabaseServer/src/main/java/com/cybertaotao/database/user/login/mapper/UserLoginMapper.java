package com.cybertaotao.database.user.login.mapper;

import com.cybertaotao.database.user.login.data.UserLogin;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper @Component public interface UserLoginMapper {

  @Insert("INSERT INTO `user_login`(uid,username_allow,email_allow,handy_number_allow,login_allow) "
            + "VALUES(#{uid},#{username_allow},#{email_allow},#{handy_number_allow},#{login_allow})")
  boolean insert (UserLogin model);

  @Delete("DELETE FROM `user_login` WHERE uid=#{uid}")
  boolean delete (Integer uid);

  @Select("SELECT * FROM `user_login` WHERE uid=#{uid}")
  UserLogin select (int uid);

  @Select("SELECT * FROM `user_login`")
  List<UserLogin> selectAll ();

  @Update(
    "UPDATE `user_login` SET uid=#{uid},username_allow=#{username_allow},email_allow=#{email_allow},handy_number_allow=#{handy_number_allow},login_allow=#{login_allow}"
      + " WHERE uid=#{uid}")
  int update (UserLogin model);

}