package authority.representation;

import authority.database.DatabaseOps;
import dataStructure.DrawAppAttribute;
import dataStructure.DumpFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController @RequestMapping("/dumpfile") public class TestUsageDumpFileController {
  //  private final static String AUTHORITY_URL = "https://localhost:7101/dumpfile/";

  /**
   * this is the method that return the default page, should be same with "anonymous".
   *
   * @param request
   * @return
   */
  @RequestMapping(method = RequestMethod.GET)
  public String getPage (HttpServletRequest request) {
    return "get without parameter";
  }

  @RequestMapping(method = RequestMethod.GET,
                  value = "/{uid}")
  public @ResponseBody
  DumpFile getPage (HttpServletRequest request, @PathVariable("uid") String uid, @RequestParam("sid") String sid) {
    String appPart = "<p>this is a test index page</p>";
    int reqUID;
    int reqUL;
    try {
      reqUID = Integer.parseInt(uid);

    } catch (NumberFormatException e) {
      reqUID = -1;
      reqUL = 2;
      //2->anonymous
    }
    return new DumpFile(appPart, DatabaseOps.readApps(10000, 5, 1));
  }

}
