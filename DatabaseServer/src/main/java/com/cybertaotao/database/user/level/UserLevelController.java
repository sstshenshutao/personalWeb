package com.cybertaotao.database.user.level;

import com.cybertaotao.database.user.level.data.UserLevel;
import com.cybertaotao.database.user.level.service.UserLevelService;
import com.cybertaotao.database.user.root.data.User;
import com.cybertaotao.database.user.root.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController @RequestMapping("/user/level") public class UserLevelController {
  private final UserLevelService service;
  @Autowired
  public UserLevelController (UserLevelService service) {
    this.service = service;
  }
  @RequestMapping(method = RequestMethod.GET)
  public Object index () {
    return service.selectAllEntries();
  }
  @RequestMapping(value = "/{id}",
                  method = RequestMethod.GET)
  public Object getIDIndex (@PathVariable("id") int id) {
    return service.selectUserLevel(id);
  }
  @RequestMapping(value = "/{id}/{attribute}",
                  method = RequestMethod.GET)
  public Object getAttribute (@PathVariable("id") int id, @PathVariable("attribute") String attribute) {
    switch (attribute) {
      case "uid":
        return service.getUid(id);
      case "manage_level":
        return service.getManage_level(id);
      case "access_level":
        return service.getAccess_level(id);
    }
    return HttpStatus.BAD_REQUEST;
  }
  @RequestMapping(value = "/{id}",
                  method = RequestMethod.POST)
  public Object postChangeAttributes (@PathVariable("id") int id, @RequestBody UserLevel entry) {
    if (id == entry.getUid()) {
      if (service.selectUserLevel(id) == null) {
        service.insertUserLevel(entry);
      } else {
        service.updateEntry(entry);
      }
      return service.selectUserLevel(id);
    }
    return null;
  }
  @RequestMapping(method = RequestMethod.DELETE,
                  value = "/{uid}")
  public Object deleteOneEntry (@PathVariable("uid") int uid) {
    if (service.selectUserLevel(uid) != null) {
      service.deleteEntry(uid);
      return true;
    }
    return HttpStatus.BAD_REQUEST;
  }
}