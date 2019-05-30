package com.cybertaotao.representation.index;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("/") public class Controller {

  @Value("${mySetting.webPrefix}") private String webPrefix;

  @Value("${mySetting.documentPath}") private String documentPath;
  @Value("${server.port}") private String serverPort;

  /**
   * todo: autoload configuration {domain + port}
   *
   * @return
   */
  @RequestMapping(method = { RequestMethod.POST, RequestMethod.GET })
  public NoIndexPage wrongPage () {
    return new NoIndexPage("No INDEX HTML, this is a Intra-Service-Server.", webPrefix +":"+ serverPort + documentPath);
  }

}
