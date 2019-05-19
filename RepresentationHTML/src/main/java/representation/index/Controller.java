package representation.index;

import api.NoIndexPage;
import dataStructure.DumpFile;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("/")
public class Controller {

  /**
   * todo: autoload configuration {domain + port}
   * @return
   */
  @RequestMapping(method = {RequestMethod.POST,RequestMethod.GET})
  public NoIndexPage wrongPage () {
    return new NoIndexPage("No INDEX HTML, this is a Intra-Service-Server.","/document");
  }
}
