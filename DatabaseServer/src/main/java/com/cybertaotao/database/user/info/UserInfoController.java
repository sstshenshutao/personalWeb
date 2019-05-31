package com.cybertaotao.database.user.info;

import com.cybertaotao.database.user.info.data.UserInfo;
import com.cybertaotao.database.user.info.service.UserInfoService;
import com.cybertaotao.database.user.root.data.User;
import com.cybertaotao.database.user.root.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController @RequestMapping("/user/info") public class UserInfoController {
  private final UserInfoService service;
  @Autowired
  public UserInfoController (UserInfoService service) {
    this.service = service;
  }
  @RequestMapping(method = RequestMethod.GET)
  public Object index () {
    return service.selectAllEntries();
  }
  @RequestMapping(value = "/{id}",
                  method = RequestMethod.GET)
  public Object getIDIndex (@PathVariable("id") int id) {
    return service.selectUserInfo(id);
  }
  @RequestMapping(value = "/{id}/{attribute}",
                  method = RequestMethod.GET)
  public Object getAttribute (@PathVariable("id") int id, @PathVariable("attribute") String attribute) {
    switch (attribute) {
      case "uid":
        return service.getUid(id);
      case "username":
        return service.getUsername(id);
      case "handy_number":
        return service.getHandy_number(id);
      case "email":
        return service.getEmail(id);
      case "hobbys":
        return service.getHobbys(id);
      case "sex":
        return service.getSex(id);
      case "age":
        return service.getAge(id);
      case "job":
        return service.getJob(id);
      case "nickname":
        return service.getNickname(id);
      case "homepage":
        return service.getHomepage(id);
      case "head_photo":
        return service.getHead_photo(id);
      case "true_name":
        return service.getTrue_name(id);
      case "residence":
        return service.getResidence(id);
      case "country":
        return service.getCountry(id);
      case "birthday":
        return service.getBirthday(id);
    }
    return HttpStatus.BAD_REQUEST;
  }
  @RequestMapping(value = "/{id}",
                  method = RequestMethod.POST)
  public Object postChangeAttributes (@PathVariable("id") int id, @RequestBody UserInfo entry) {
    if (id == entry.getUid()) {
      if (service.selectUserInfo(id) == null) {
        service.insertUserInfo(entry);
      } else {
        service.updateEntry(entry);
      }
      return service.selectUserInfo(id);
    }
    return null;
  }
  @RequestMapping(method = RequestMethod.DELETE,
                  value = "/{uid}")
  public Object deleteOneEntry (@PathVariable("uid") int uid) {
    if (service.selectUserInfo(uid) != null) {
      service.deleteEntry(uid);
      return true;
    }
    return HttpStatus.BAD_REQUEST;
  }
}