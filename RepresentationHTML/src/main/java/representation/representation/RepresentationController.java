package representation.representation;

import dataStructure.DumpFile;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController @RequestMapping("/representation") public class RepresentationController {

  // TODO: 16.05.19 change http to https, change the port to autoload in "@init()"
  private final static String AUTHORITY_URL = "http://localhost:7101/dumpfile/";
  private final static String TEMPLATE_FILE_NAME = "/Users/shenshutao/newWeb/representation/src/main/resources/html/template.html";

  private List<Representation> representations;
  private Representation currentRepresentation;

  public RepresentationController () {
    init();
  }

  /**
   * init all of the variables
   */
  private void init () {
    this.representations = new ArrayList<>();
    representations.add(WebRepresentation.createWebRepresentationWithTemplate(TEMPLATE_FILE_NAME));
    currentRepresentation = representations.get(0);
  }

  /**
   * for other Service to change the templates
   * todo: ***check user level***
   *
   * @param index
   * @return true if successful
   */
  @RequestMapping(method = RequestMethod.POST,
                  value = "/changeRepresentation")
  public boolean changeCurrentRepresentation (int index) {
    if (index >= 0 && index < representations.size()) {
      currentRepresentation = representations.get(index);
      return true;
    }
    return false;
  }

  /**
   * load other templates
   * todo: ***check user level***
   *
   * @param fileName
   */
  @RequestMapping(method = RequestMethod.POST,
                  value = "/loadRepresentation")
  public int loadRepresentation (@RequestBody String fileName) {
    int ret = this.representations.size();
    this.representations.add(WebRepresentation.createWebRepresentationWithTemplate(fileName));
    return ret;
  }

  /**
   * return template
   *
   * @return
   */
  @RequestMapping(method = RequestMethod.GET)
  public String getDefault () {
    //    String sid = request.getSession(true).getId();
    //    System.out.println("justsid|" + sid);
    return this.currentRepresentation.render("", "");
  }

  /**
   * @param dumpFile
   * @return
   */
  @RequestMapping(method = RequestMethod.POST)
  public String postPage (@RequestBody DumpFile dumpFile) {
    return this.currentRepresentation.render(dumpFile);
  }
  //  @RequestMapping(method = RequestMethod.POST)
  //  public String postPageWithBrowser () {
  //    return this.currentRepresentation.render("", "this page does not support DIY");
  //  }
  //  public String getPage (HttpServletRequest request, @PathVariable("uid") String uid) {
  //    if (uid == null || uid.trim().length() == 0) {
  //      uid = "anonymous";
  //    }
  //    String sid = request.getSession(true).getId();
  //    System.out.println(uid + "|" + sid);
  //    return this.currentRepresentation.render(getDumpFile(uid, sid));
  //  }
  //
  //  private DumpFile getDumpFile (String uid, String sid) {
  //    RestTemplate restTemplate = new RestTemplate();
  //    System.out.println(AUTHORITY_URL + uid + "?sid=" + sid);
  //    return restTemplate.getForObject(AUTHORITY_URL + uid + "?sid=" + sid, DumpFile.class);
  //  }
}
