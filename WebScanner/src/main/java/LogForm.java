import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class LogForm {

  public static List<String> getList() {
    FileReader fileReader = null;
    try {
      fileReader = new FileReader(new File("http"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    BufferedReader bufferedReader = new BufferedReader(fileReader);
    List<String> list = new ArrayList<>();
    bufferedReader.lines().forEach(x -> {
      if (x.contains("<message>")) {
        int a = x.indexOf("<message>");
        int b = x.indexOf("</message>");
        list.add(x.substring(a+"<message>".length(),b));
      }
    });
    System.out.println(list.size());
    return list;
  }

}
