package com.cybertaotao.database.user.login;

import com.cybertaotao.database.user.login.data.UserLogin;
import com.cybertaotao.database.user.login.service.UserLoginService;
import com.cybertaotao.database.user.root.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController @RequestMapping("/user/login") public class UserLoginController {

  private final UserLoginService service;

  @Autowired
  public UserLoginController (UserLoginService service) {
    this.service = service;
  }

  @RequestMapping(method = RequestMethod.GET)
  public Object index () {
    //    UserInfo userEntity = userRepositoy.findUserInfoById(id);
    //    ResultMsg resultMsg = new ResultMsg(ResultStatusCode.OK.getErrcode(), ResultStatusCode.OK.getErrmsg(), userEntity);
    //    return resultMsg
    //    List<User> testUserDB = service.selectAllUsers();
    return service.selectAllEntries();
  }

  /**
   * show entry
   *
   * @param id
   * @return
   */
  @RequestMapping(value = "/{id}",
                  method = RequestMethod.GET)
  public Object index (@PathVariable("id") int id) {
    //    UserInfo userEntity = userRepositoy.findUserInfoById(id);
    //    ResultMsg resultMsg = new ResultMsg(ResultStatusCode.OK.getErrcode(), ResultStatusCode.OK.getErrmsg(), userEntity);
    //    return resultMsg
    //    List<User> testUserDB = service.selectAllUsers();
    return service.selectLoginConstraint(id);
  }

  @RequestMapping(value = "/{id}/{allow}",
                  method = RequestMethod.GET)
  public Object getPermission (@PathVariable("id") int id, @PathVariable("allow") String loginMethod) {
    switch (loginMethod) {
      case "username_allow":
        return service.isUsernameAllow(id);
      case "email_allow":
        return service.isEmailAllow(id);
      case "handy_number_allow":
        return service.isHandyNumberAllow(id);
      case "login_allow":
        return service.isLoginAllow(id);
    }
    //    UserInfo userEntity = userRepositoy.findUserInfoById(id);
    //    ResultMsg resultMsg = new ResultMsg(ResultStatusCode.OK.getErrcode(), ResultStatusCode.OK.getErrmsg(), userEntity);
    //    return resultMsg
    //    List<User> testUserDB = service.selectAllUsers();
    return HttpStatus.BAD_REQUEST;
  }

  @RequestMapping(value = "/{id}",
                  method = RequestMethod.POST)
  public Object postChangeConstraint (@PathVariable("id") int id, @RequestBody UserLogin entry) {
    if (id == entry.getUid()) {
      if (service.selectLoginConstraint(id) != null) {
        service.insertLoginConstraint(entry);
      } else {
        service.updateEntry(entry);
      }
      return service.selectLoginConstraint(id);
    }
    return null;
  }

  @RequestMapping(method = RequestMethod.DELETE,
                  value = "/{uid}")
  public Object deleteOneUser (@PathVariable("uid") int uid) {
    if (service.selectLoginConstraint(uid) != null) {
      service.deleteEntry(uid);
      return true;
    }
    return HttpStatus.BAD_REQUEST;
  }

}
