package com.cybertaotao.database.user.money;


import com.cybertaotao.database.user.money.data.UserMoney;
import com.cybertaotao.database.user.money.service.UserMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController @RequestMapping("/user/money") public class UserMoneyController {
  private final UserMoneyService service;
  @Autowired
  public UserMoneyController (UserMoneyService service) {
    this.service = service;
  }
  @RequestMapping(method = RequestMethod.GET)
  public Object index () {
    return service.selectAllEntries();
  }
  @RequestMapping(value = "/{id}",
                  method = RequestMethod.GET)
  public Object getIDIndex (@PathVariable("id") int id) {
    return service.selectUserMoney(id);
  }
  @RequestMapping(value = "/{id}/{attribute}",
                  method = RequestMethod.GET)
  public Object getAttribute (@PathVariable("id") int id, @PathVariable("attribute") String attribute) {
    switch (attribute) {
      case "uid":
        return service.getUid(id);
      case "virtual_money":
        return service.getVirtual_money(id);
    }
    return HttpStatus.BAD_REQUEST;
  }
  @RequestMapping(value = "/{id}",
                  method = RequestMethod.POST)
  public Object postChangeAttributes (@PathVariable("id") int id, @RequestBody UserMoney entry) {
    if (id == entry.getUid()) {
      if (service.selectUserMoney(id) == null) {
        service.insertUserMoney(entry);
      } else {
        service.updateEntry(entry);
      }
      return service.selectUserMoney(id);
    }
    return null;
  }
  @RequestMapping(method = RequestMethod.DELETE,
                  value = "/{uid}")
  public Object deleteOneEntry (@PathVariable("uid") int uid) {
    if (service.selectUserMoney(uid) != null) {
      service.deleteEntry(uid);
      return true;
    }
    return HttpStatus.BAD_REQUEST;
  }
}