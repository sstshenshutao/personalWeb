package com.cybertaotao.database.user.level.mapper;

import com.cybertaotao.database.user.level.data.UserLevel;
import com.cybertaotao.database.user.root.data.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper @Component public interface UserLevelMapper {

  @Insert("INSERT INTO `user_level`(uid,manage_level,access_level) " + "VALUES(#{uid},#{manage_level},#{access_level})")
  boolean insert (UserLevel model);

  @Delete("DELETE FROM `user_level` WHERE uid=#{uid}")
  boolean delete (Integer uid);

  @Select("SELECT * FROM `user_level` WHERE uid=#{uid}")
  UserLevel select (int uid);

  @Select("SELECT * FROM `user_level`")
  List<UserLevel> selectAll ();

  @Update("UPDATE `user_level` SET uid=#{uid},manage_level=#{manage_level},access_level=#{access_level}"
            + " WHERE uid=#{uid}")
  int update (UserLevel model);

}