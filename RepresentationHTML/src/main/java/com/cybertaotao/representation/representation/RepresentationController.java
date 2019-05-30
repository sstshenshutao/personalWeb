package com.cybertaotao.representation.representation;

import com.cybertaotao.dataStructure.DumpFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController @RequestMapping("/representation") public class RepresentationController {

  private List<Representation> representations;
  private Representation currentRepresentation;

  public RepresentationController (@Value("${mySetting.defaultTemplate}") String defaultTemplate) {
    init(defaultTemplate);
  }

  /**
   * init all of the variables
   */
  private void init (String defaultTemplate) {
    this.representations = new ArrayList<>();
    representations.add(WebRepresentation.createWebRepresentationWithTemplate(defaultTemplate));
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
    return this.currentRepresentation.render("", "<p>This is the default page</p>");
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
