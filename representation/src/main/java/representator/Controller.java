package representator;

import dataStructure.DumpFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@RestController @RequestMapping("/representation") public class Controller {

  // TODO: 16.05.19 change http to https, change the port to autoload
  private final static String AUTHORITY_URL = "http://localhost:7101/dumpfile/";

  public Controller () {
    System.out.println("constructor Controller");
  }
  {
    System.out.println("nonstatic block");
  }
  static {
    System.out.println("Static block");
  }
  @RequestMapping(method = RequestMethod.GET)
  public String getPage (HttpServletRequest request) {
    String sid = request.getSession(true).getId();
    System.out.println("justsid|" + sid);
    return new WebRepresentator().drawOut(getDumpFile("anonymous", sid));
  }

  @RequestMapping(method = RequestMethod.GET,
                  value = "/{uid}")
  public String getPage (HttpServletRequest request, @PathVariable("uid") String uid) {
    if (uid == null || uid.trim().length() == 0) {
      uid = "anonymous";
    }
    String sid = request.getSession(true).getId();
    System.out.println(uid + "|" + sid);
    return new WebRepresentator().drawOut(getDumpFile(uid, sid));
  }

  private DumpFile getDumpFile (String uid, String sid) {
    RestTemplate restTemplate = new RestTemplate();
    System.out.println(AUTHORITY_URL + uid + "?sid=" + sid);
    return restTemplate.getForObject(AUTHORITY_URL + uid + "?sid=" + sid, DumpFile.class);
  }

  @PostMapping("/")
  public String postDefault (@RequestBody DumpFile dumpFile) {
    return new WebRepresentator().drawOut(dumpFile);
  }

}
