package com.cybertaotao.database.user.info.mapper;

import com.cybertaotao.database.user.info.data.UserInfo;
import com.cybertaotao.database.user.root.data.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper @Component public interface UserInfoMapper {
  @Insert("INSERT INTO `user_info`(uid,username,handy_number,email,hobbys,sex,age,job,nickname,homepage,head_photo,true_name,residence,country,birthday) "
            + "VALUES(#{uid},#{username},#{handy_number},#{email},#{hobbys},#{sex},#{age},#{job},#{nickname},#{homepage},#{head_photo},#{true_name},#{residence},#{country},#{birthday})")
  boolean insert (UserInfo model);
  @Delete("DELETE FROM `user_info` WHERE uid=#{uid}")
  boolean delete (Integer uid);  @Select("SELECT * FROM `user_info` WHERE uid=#{uid}")
  UserInfo select (int uid);
  @Select("SELECT * FROM `user_info`")
  List<UserInfo> selectAll ();  @Update("UPDATE `user_info` SET uid=#{uid},username=#{username},handy_number=#{handy_number},email=#{email},hobbys=#{hobbys},sex=#{sex},age=#{age},job=#{job},nickname=#{nickname},homepage=#{homepage},head_photo=#{head_photo},true_name=#{true_name},residence=#{residence},country=#{country},birthday=#{birthday}" + " WHERE uid=#{uid}")
  int update (UserInfo model);
}