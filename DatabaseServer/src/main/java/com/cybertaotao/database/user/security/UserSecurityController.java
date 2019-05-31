package com.cybertaotao.database.user.security;

import com.cybertaotao.database.user.login.data.UserLogin;
import com.cybertaotao.database.user.root.data.User;
import com.cybertaotao.database.user.root.service.UserService;
import com.cybertaotao.database.user.security.data.UserSecurity;
import com.cybertaotao.database.user.security.service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
//
//  private final UserSecurityService service;
//
//  @Autowired
//  public UserSecurityController (UserSecurityService service) {
//    this.service = service;
//  }
//
//  @RequestMapping(method = RequestMethod.GET)
//  public Object index () {
//    //    UserInfo userEntity = userRepositoy.findUserInfoById(id);
//    //    ResultMsg resultMsg = new ResultMsg(ResultStatusCode.OK.getErrcode(), ResultStatusCode.OK.getErrmsg(), userEntity);
//    //    return resultMsg
////    List<User> testUserDB = service.selectAllUsers();
//    return "please use post method to login";
//  }
//
//  @RequestMapping(value = "/{id}",method = RequestMethod.GET)
//  public Object index (@PathVariable("id")int id) {
//
//    //    UserInfo userEntity = userRepositoy.findUserInfoById(id);
//    //    ResultMsg resultMsg = new ResultMsg(ResultStatusCode.OK.getErrcode(), ResultStatusCode.OK.getErrmsg(), userEntity);
//    //    return resultMsg
//    //    List<User> testUserDB = service.selectAllUsers();
//    return ;
//  }
//
//  @RequestMapping(value = "/{id}",
//                  method = RequestMethod.POST)
//  public Object postChangeConstraint (@PathVariable("id") int id, @RequestBody UserSecurity entry) {
//    if (id == entry.getUid()) {
//      if (service.selectUserSecurity(id) != null) {
//        service.insertUserSecurity(entry);
//      } else {
//        service.updateEntry(entry);
//      }
//      return service.selectUserSecurity(id);
//    }
//    return null;
//  }
//
//    //  /**
////   * todo:(should be done in loginServer) need to check if the parameter can be null,
////   * todo:(should be done in loginServer) for real login 1. check banned? 2.check ssid and maintain the table (uid|ssid)
////   *
////   * @param password
////   * @param username
////   * @param handyNumber
////   * @param email
////   * @return
////   */
////  @RequestMapping(method = RequestMethod.POST)
////  public Object postLogin (HttpServletRequest request,
////    @RequestParam(value = "password", required = true)String password
////    ,@RequestParam(value = "username",required = false) String username
////    ,@RequestParam(value = "handy_number",required = false) String handyNumber
////    ,@RequestParam(value = "email",required = false) String email) {
////    System.out.println(username+handyNumber+email);
////    return "post test";
////  }
//
//
//  @RequestMapping(method = RequestMethod.GET,
//                  value = "/{uid}/{attribute}")
//  public Object getSomeAttribute (
//    //    HttpServletRequest request,
//    @PathVariable("uid") int uid,
//                           @PathVariable("attribute") String attribute) {
//    //    UserInfo userEntity = userRepositoy.findUserInfoById(id);
//    //    ResultMsg resultMsg = new ResultMsg(ResultStatusCode.OK.getErrcode(), ResultStatusCode.OK.getErrmsg(), userEntity);
//    //    return resultMsg
//    if(service.selectUser(uid)!=null){
//      switch (attribute){
//        case "email":
//          break;
//        case "handy_number":
//          break;
//        case "username":
//          break;
//        case "password":
//          break;
//      }
//    }
//    return ;
//  }
//
//  @RequestMapping(method = RequestMethod.POST,
//                  value = "/{uid}")
//  public Object postOneUser (@RequestBody User user, @PathVariable("uid") int uid) {
//    if (user.getUid() != uid) {
//      return HttpStatus.BAD_REQUEST;
//    } else {
//      if (service.selectUser(uid) != null) {
//        return HttpStatus.BAD_REQUEST;
//      } else {
//        service.addUser(user);
//        return service.selectUser(uid);
//      }
//    }
//  }
//
//  @RequestMapping(method = RequestMethod.DELETE,
//                  value = "/{uid}")
//  public Object deleteOneUser (@PathVariable("uid") int uid) {
//    if (service.selectUser(uid) != null) {
//      service.deleteUser(uid);
//      return true;
//    }
//    return HttpStatus.BAD_REQUEST;
//  }

@RestController @RequestMapping("/user/security") public class UserSecurityController {

  private final UserSecurityService service;

  @Autowired
  public UserSecurityController (UserSecurityService service) {
    this.service = service;
  }

  @RequestMapping(method = RequestMethod.GET)
  public Object index () {
    return service.selectAllEntries();
  }

  @RequestMapping(value = "/{id}",
                  method = RequestMethod.GET)
  public Object getIDIndex (@PathVariable("id") int id) {
    return service.selectUserSecurity(id);
  }

  @RequestMapping(value = "/{id}/{attribute}",
                  method = RequestMethod.GET)
  public Object getAttribute (@PathVariable("id") int id, @PathVariable("attribute") String attribute) {
    switch (attribute) {
      case "uid":
        return service.getUid(id);
      case "username":
        return service.getUsername(id);
      case "email":
        return service.getEmail(id);
      case "question1":
        return service.getQuestion1(id);
      case "answer1":
        return service.getAnswer1(id);
      case "question2":
        return service.getQuestion2(id);
      case "answer2":
        return service.getAnswer2(id);
      case "handy_number":
        return service.getHandy_number(id);
      case "password":
        return service.getPassword(id);
    }
    return HttpStatus.BAD_REQUEST;
  }

  @RequestMapping(value = "/{id}",
                  method = RequestMethod.POST)
  public Object postChangeAttributes (@PathVariable("id") int id, @RequestBody UserSecurity entry) {
    if (id == entry.getUid()) {
      if (service.selectUserSecurity(id) == null) {
        service.insertUserSecurity(entry);
      } else {
        service.updateEntry(entry);
      }
      return service.selectUserSecurity(id);
    }
    return null;
  }

  @RequestMapping(method = RequestMethod.DELETE,
                  value = "/{uid}")
  public Object deleteOneEntry (@PathVariable("uid") int uid) {
    if (service.selectUserSecurity(uid) != null) {
      service.deleteEntry(uid);
      return true;
    }
    return HttpStatus.BAD_REQUEST;
  }

}

