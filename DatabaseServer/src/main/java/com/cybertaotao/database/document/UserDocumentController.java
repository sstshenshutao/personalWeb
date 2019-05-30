package com.cybertaotao.database.document;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController @RequestMapping("/document") public class UserDocumentController {

  @Value("${mySetting.webPrefix}") private String webPrefix;

  @Value("${mySetting.documentPath}") private String documentPath;
  @Value("${server.port}") private String serverPort;

  @RequestMapping(method = { RequestMethod.POST, RequestMethod.GET })
  public List<DocumentEntry> allDocs () {
    List<DocumentEntry> ret = new ArrayList<>();
    ret.add(new DocumentEntry("user", webPrefix +":"+ serverPort + documentPath + "/user"));
    ret.add(new DocumentEntry("user_info", webPrefix +":"+ serverPort + documentPath + "/user/info"));
    ret.add(new DocumentEntry("user_level", webPrefix +":"+ serverPort + documentPath + "/user/level"));
    ret.add(new DocumentEntry("user_login", webPrefix +":"+ serverPort + documentPath + "/user/login"));
    ret.add(new DocumentEntry("user_money", webPrefix +":"+ serverPort + documentPath + "/user/money"));
    return ret;
  }

  @RequestMapping(value = "/user",
                  method = { RequestMethod.POST, RequestMethod.GET })
  public DatabaseDocumentEntry userDocs () {
    String htu =
      "todo: add doc for user";
    DatabaseDocumentEntry ret = new DatabaseDocumentEntry(htu);
    return ret;
  }
  @RequestMapping(value = "/user/info",
                  method = { RequestMethod.POST, RequestMethod.GET })
  public DatabaseDocumentEntry userInfoDocs () {
    String htu =
      "todo: add doc for user_info";
    DatabaseDocumentEntry ret = new DatabaseDocumentEntry(htu);
    return ret;
  }
  @RequestMapping(value = "/user/level",
                  method = { RequestMethod.POST, RequestMethod.GET })
  public DatabaseDocumentEntry userLevelDocs () {
    String htu =
      "todo: add doc for user_level";
    DatabaseDocumentEntry ret = new DatabaseDocumentEntry(htu);
    return ret;
  }
  @RequestMapping(value = "/user/login",
                  method = { RequestMethod.POST, RequestMethod.GET })
  public DatabaseDocumentEntry userLoginDocs () {
    String htu =
      "todo: add doc for user_login";
    DatabaseDocumentEntry ret = new DatabaseDocumentEntry(htu);
    return ret;
  }
  @RequestMapping(value = "/user/money",
                  method = { RequestMethod.POST, RequestMethod.GET })
  public DatabaseDocumentEntry userMoneyDocs () {
    String htu =
      "todo: add doc for user_money";
    DatabaseDocumentEntry ret = new DatabaseDocumentEntry(htu);
    return ret;
  }
}
