package representation.representation;

import org.jsoup.nodes.Node;

import java.util.ArrayList;
import java.util.List;

public class Util {

  public static Node parsePart (Node node, String start, String end) {
    List<Node> tmp = new ArrayList<>();
    boolean sign = false;
    for (Node n : node.childNodes()) {
      //      System.out.println(n.nodeName()+"|"+n.toString());
      if (sign) {
        if (!n.nodeName().equals("#text")) {
          tmp.add(n);
        }
      }
      if (n.nodeName().equals("#comment") && n.toString().trim().equals(start)) {
        sign = true;
      } else if (n.nodeName().equals("#comment") && n.toString().trim().equals(end)) {
        sign = false;
        tmp.remove(tmp.size() - 1);
      }
    }
    //    tmp.forEach(x -> System.out.println("---" + x.toString()));
    return tmp.get(0);
  }

}
