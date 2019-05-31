package com.cybertaotao.database.user.login;

import com.cybertaotao.database.user.root.data.User;
import com.cybertaotao.database.user.root.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController @RequestMapping("/user/login") public class UserLoginController {

  private final UserService service;

  @Autowired
  public UserLoginController (UserService service) {
    this.service = service;
  }

  @RequestMapping(method = RequestMethod.GET)
  public Object index () {
    //    UserInfo userEntity = userRepositoy.findUserInfoById(id);
    //    ResultMsg resultMsg = new ResultMsg(ResultStatusCode.OK.getErrcode(), ResultStatusCode.OK.getErrmsg(), userEntity);
    //    return resultMsg
//    List<User> testUserDB = service.selectAllUsers();
    return "please use post method to login";
  }

  /**
   * show if the id is banned(give the whole entry back)
   * @param id
   * @return
   */
  @RequestMapping(value = "/{id}",method = RequestMethod.GET)
  public Object index (@PathVariable("id")int id) {

    //    UserInfo userEntity = userRepositoy.findUserInfoById(id);
    //    ResultMsg resultMsg = new ResultMsg(ResultStatusCode.OK.getErrcode(), ResultStatusCode.OK.getErrmsg(), userEntity);
    //    return resultMsg
    //    List<User> testUserDB = service.selectAllUsers();
    return "please use post method to login";
  }

  /**
   * todo: need to check if the parameter can be null,
   * todo: for real login 1. check banned? 2.check ssid and maintain the table (uid|ssid)
   *
   * @param password
   * @param username
   * @param handyNumber
   * @param email
   * @return
   */
  @RequestMapping(method = RequestMethod.POST)
  public Object postLogin (HttpServletRequest request,
    @RequestParam(value = "password", required = true)String password
    ,@RequestParam(value = "username",required = false) String username
    ,@RequestParam(value = "handy_number",required = false) String handyNumber
    ,@RequestParam(value = "email",required = false) String email) {
    System.out.println(username+handyNumber+email);
    return "post test";
  }


  @RequestMapping(method = RequestMethod.GET,
                  value = "/{uid}/{attribute}")
  public Object getSomeAttribute (
    //    HttpServletRequest request,
    @PathVariable("uid") int uid,
                           @PathVariable("attribute") String attribute) {
    //    UserInfo userEntity = userRepositoy.findUserInfoById(id);
    //    ResultMsg resultMsg = new ResultMsg(ResultStatusCode.OK.getErrcode(), ResultStatusCode.OK.getErrmsg(), userEntity);
    //    return resultMsg
    if(service.selectUser(uid)!=null){
      switch (attribute){
        case "email":
          break;
        case "handy_number":
          break;
        case "username":
          break;
        case "password":
          break;
      }
    }
    return ;
  }

  @RequestMapping(method = RequestMethod.POST,
                  value = "/{uid}")
  public Object postOneUser (@RequestBody User user, @PathVariable("uid") int uid) {
    if (user.getUid() != uid) {
      return HttpStatus.BAD_REQUEST;
    } else {
      if (service.selectUser(uid) != null) {
        return HttpStatus.BAD_REQUEST;
      } else {
        service.addUser(user);
        return service.selectUser(uid);
      }
    }
  }

  @RequestMapping(method = RequestMethod.DELETE,
                  value = "/{uid}")
  public Object deleteOneUser (@PathVariable("uid") int uid) {
    if (service.selectUser(uid) != null) {
      service.deleteUser(uid);
      return true;
    }
    return HttpStatus.BAD_REQUEST;
  }

}
