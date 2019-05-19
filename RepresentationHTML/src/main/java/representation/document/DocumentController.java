package representation.document;

import api.NoIndexPage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController @RequestMapping("/document") public class DocumentController {

  @RequestMapping(method = { RequestMethod.POST, RequestMethod.GET })
  public List<DocumentEntry> allDocs () {
    List<DocumentEntry> ret = new ArrayList<>();
    ret.add(new DocumentEntry("Representation", "/document/Representation"));
    return ret;
  }
  @RequestMapping( value = "/Representation", method = { RequestMethod.POST, RequestMethod.GET })
  public RepresentationDocumentEntry representationDocs () {
    String htu = "we use a dumpfile object to input the information, and the representation will output the "
      + "information with the Template style. Dumpfile consist of a String appPart and a List of DrawAppAttribute, "
      + "which consist of a String link, a String name, an int id, a boolean isChoosed, a boolean isRunning. e.g. {\"appPart\":\"this is a test index page\",\"drawApps\":[{\"link\":\"https://www.google.de\",\"name\":\"google1-2\",\"id\":1, \"isChoosed\":true,\"isRunning\":true},{\"link\":\"https://www.google.de\",\"name\":\"google2-3\",\"id\":2,\"isChoosed\":false,\"isRunning\":true},{\"link\":\"https://www.google.de\",\"name\":\"google3-2\",\"id\":3,\"isChoosed\":false,\"isRunning\":true},{\"link\":\"https://www.google.de\",\"name\":\"google4-1\",\"id\":4,\"isChoosed\":false,\"isRunning\":true},{\"link\":\"https://www.google.de\",\"name\":\"google5-3\",\"id\":5,\"isChoosed\":false,\"isRunning\":true}]}";
    RepresentationDocumentEntry ret = new RepresentationDocumentEntry(htu);
    return ret;
  }


}
