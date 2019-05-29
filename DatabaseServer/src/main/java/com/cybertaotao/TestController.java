package com.cybertaotao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.cybertaotao.user.service.UserService;

@RestController @RequestMapping("/testUser") public class TestController {

  private final UserService service;

  @Autowired
  public TestController (UserService service) {
    this.service = service;
  }

  @RequestMapping(method = RequestMethod.GET)
  public Object getTest () {
    //    UserInfo userEntity = userRepositoy.findUserInfoById(id);
    //    ResultMsg resultMsg = new ResultMsg(ResultStatusCode.OK.getErrcode(), ResultStatusCode.OK.getErrmsg(), userEntity);
    //    return resultMsg
    TestUserDB testUserDB = service.select(500656);
    return testUserDB;
  }

}
