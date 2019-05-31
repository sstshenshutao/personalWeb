package com.cybertaotao.database.user.money.mapper;

import com.cybertaotao.database.user.money.data.UserMoney;
import com.cybertaotao.database.user.root.data.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper @Component public interface UserMoneyMapper {

  @Insert("INSERT INTO `user_money`(uid,virtual_money) " + "VALUES(#{uid},#{virtual_money})")
  boolean insert (UserMoney model);

  @Delete("DELETE FROM `user_money` WHERE uid=#{uid}")
  boolean delete (Integer uid);

  @Select("SELECT * FROM `user_money` WHERE uid=#{uid}")
  UserMoney select (int uid);

  @Select("SELECT * FROM `user_money`")
  List<UserMoney> selectAll ();

  @Update("UPDATE `user_money` SET uid=#{uid},virtual_money=#{virtual_money}" + " WHERE uid=#{uid}")
  int update (UserMoney model);

}