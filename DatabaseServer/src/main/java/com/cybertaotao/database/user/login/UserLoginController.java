package com.cybertaotao.database.user.login;

import com.cybertaotao.database.user.login.data.UserLogin;
import com.cybertaotao.database.user.login.service.UserLoginService;
import com.cybertaotao.database.user.root.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/user/login") public class UserLoginController {
  private final UserLoginService service;
  @Autowired
  public UserLoginController (UserLoginService service) {
    this.service = service;
  }
  @RequestMapping(method = RequestMethod.GET)
  public Object index () {
    return service.selectAllEntries();
  }
  @RequestMapping(value = "/{id}",
                  method = RequestMethod.GET)
  public Object getIDIndex (@PathVariable("id") int id) {
    return service.selectUserLogin(id);
  }
  @RequestMapping(value = "/{id}/{attribute}",
                  method = RequestMethod.GET)
  public Object getAttribute (@PathVariable("id") int id, @PathVariable("attribute") String attribute) {
    switch (attribute) {
      case "uid":
        return service.getUid(id);
      case "username_allow":
        return service.isUsername_allow(id);
      case "email_allow":
        return service.isEmail_allow(id);
      case "handy_number_allow":
        return service.isHandy_number_allow(id);
      case "login_allow":
        return service.isLogin_allow(id);
    }
    return HttpStatus.BAD_REQUEST;
  }
  @RequestMapping(value = "/{id}",
                  method = RequestMethod.POST)
  public Object postChangeAttributes (@PathVariable("id") int id, @RequestBody UserLogin entry) {
    if (id == entry.getUid()) {
      if (service.selectUserLogin(id) == null) {
        service.insertUserLogin(entry);
      } else {
        service.updateEntry(entry);
      }
      return service.selectUserLogin(id);
    }
    return null;
  }
  @RequestMapping(method = RequestMethod.DELETE,
                  value = "/{uid}")
  public Object deleteOneEntry (@PathVariable("uid") int uid) {
    if (service.selectUserLogin(uid) != null) {
      service.deleteEntry(uid);
      return true;
    }
    return HttpStatus.BAD_REQUEST;
  }
}
