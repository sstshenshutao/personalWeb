package com.cybertaotao.database.user.security.mapper;

import com.cybertaotao.database.user.security.data.UserSecurity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper @Component public interface UserSecurityMapper {
  @Insert("INSERT INTO `user_security`(uid,username,email,question1,answer1,question2,answer2,handy_number,password) "
            + "VALUES(#{uid},#{username},#{email},#{question1},#{answer1},#{question2},#{answer2},#{handy_number},#{password})")
  boolean insert (UserSecurity model);
  @Delete("DELETE FROM `user_security` WHERE uid=#{uid}")
  boolean delete (Integer uid);  @Select("SELECT * FROM `user_security` WHERE uid=#{uid}")
  UserSecurity select (int uid);
  @Select("SELECT * FROM `user_security`")
  List<UserSecurity> selectAll ();  @Update("UPDATE `user_security` SET uid=#{uid},username=#{username},email=#{email},question1=#{question1},answer1=#{answer1},question2=#{question2},answer2=#{answer2},handy_number=#{handy_number},password=#{password}" + " WHERE uid=#{uid}")
  int update (UserSecurity model);
}