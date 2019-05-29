package com.cybertaotao.user;

import com.cybertaotao.TestUserDB;
import com.cybertaotao.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController @RequestMapping("/user") public class UserController {

  private final UserService service;

  @Autowired
  public UserController (UserService service) {
    this.service = service;
  }

  /**
   * get = search
   * @return
   */
  @RequestMapping(method = RequestMethod.GET)
  public Object getAll () {
    //    UserInfo userEntity = userRepositoy.findUserInfoById(id);
    //    ResultMsg resultMsg = new ResultMsg(ResultStatusCode.OK.getErrcode(), ResultStatusCode.OK.getErrmsg(), userEntity);
    //    return resultMsg
    TestUserDB testUserDB = service.select(500656);
    return testUserDB;
  }
  /**
   * get = search
   * @return
   */
  @RequestMapping(method = RequestMethod.GET, value = "/{uid}")
  public Object getOneUser (
    HttpServletRequest request, @PathVariable("uid") String uid, @RequestParam("sid") String sid) {
    //    UserInfo userEntity = userRepositoy.findUserInfoById(id);
    //    ResultMsg resultMsg = new ResultMsg(ResultStatusCode.OK.getErrcode(), ResultStatusCode.OK.getErrmsg(), userEntity);
    //    return resultMsg
    TestUserDB testUserDB = service.select(500656);
    return testUserDB;
  }

//  public Object

}
