package representation.representation;

import dataStructure.DumpFile;

public interface Representation {

  /**
   * input dumpfile(App-part and drawApps) from intra-Service-Server and render them out.
   * intra-Service-Server usage
   * @param dumpFile
   * @return
   */
  String render (DumpFile dumpFile);

  /**
   * intra-Service-Server usage, special cases:
   *      high priority service or service want to use self template.
   * @param shcPart
   * @param appPart
   * @return
   */
  String render (String shcPart, String appPart);


}
