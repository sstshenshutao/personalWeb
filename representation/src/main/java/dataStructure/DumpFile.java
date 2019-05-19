package dataStructure;

import org.jsoup.nodes.Element;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DumpFile implements Serializable {

  private static final long serialVersionUID = 5279775918571601528L;
  String appPart;
  List<DrawAppAttribute> drawApps;

  public DumpFile (String appPart, List<DrawAppAttribute> drawApps) {
    this.appPart = appPart;
    this.drawApps = drawApps;
  }

  public DumpFile () {
    this.drawApps = new ArrayList<>();
    appPart = "";
  }

  public String getAppPart () {
    return appPart;
  }

  public void setAppPart (String appPart) {
    this.appPart = appPart;
  }

  public List<DrawAppAttribute> getDrawApps () {
    return drawApps;
  }

  public void setDrawApps (List<DrawAppAttribute> drawApps) {
    this.drawApps = drawApps;
  }

//  public static void main (String[] args) throws Exception {
    //test serialization ... ok
//    DumpFile d = new DumpFile("haha",new ArrayList<>());
//    ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(new File("123.txt")));
//    o.writeObject(d);
//    ObjectInputStream i = new ObjectInputStream(new FileInputStream(new File("123.txt")));
//    DumpFile dd =(DumpFile)i.readObject();
//    System.out.println(dd.appPart);
//  }
}
