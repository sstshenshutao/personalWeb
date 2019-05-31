package com.cybertaotao.database.user.root;

import com.cybertaotao.database.user.root.data.User;
import com.cybertaotao.database.user.root.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController @RequestMapping("/user") public class UserController {

  private final UserService service;

  @Autowired
  public UserController (UserService service) {
    this.service = service;
  }

  /**
   * get = search
   *
   * @return
   */
  @RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
  public Object getAll () {
    //    UserInfo userEntity = userRepositoy.findUserInfoById(id);
    //    ResultMsg resultMsg = new ResultMsg(ResultStatusCode.OK.getErrcode(), ResultStatusCode.OK.getErrmsg(), userEntity);
    //    return resultMsg
    List<User> testUserDB = service.selectAllUsers();
    return testUserDB;
  }

  /**
   * get = search
   *
   * @return
   */
  @RequestMapping(method = RequestMethod.GET,
                  value = "/{uid}")
  public Object getOneUser (
    //    HttpServletRequest request,
    @PathVariable("uid") int uid) {
    //    UserInfo userEntity = userRepositoy.findUserInfoById(id);
    //    ResultMsg resultMsg = new ResultMsg(ResultStatusCode.OK.getErrcode(), ResultStatusCode.OK.getErrmsg(), userEntity);
    //    return resultMsg
    User user = service.selectUser(uid);
    return user;
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
