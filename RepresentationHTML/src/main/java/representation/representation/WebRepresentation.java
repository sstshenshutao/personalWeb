package representation.representation;

import dataStructure.DrawAppAttribute;
import dataStructure.DumpFile;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

import java.util.ArrayList;
import java.util.List;

public class WebRepresentation implements Representation {

  //create a new doc
  private Document docOut = null;
  private Element html = null;
  private Element head = null;
  private Element style = null;
  private Element body = null;
  private Element shcPart = null;
  private Element appPart = null;
  private HtmlLayout htmlLayout;

  private WebRepresentation (String templateFile) {
    this.htmlLayout = HtmlLayout.parseTemplateHtml(templateFile);
  }

  public static WebRepresentation createWebRepresentationWithTemplate (String loadTemplateFileName) {
    return new WebRepresentation(loadTemplateFileName);
  }

  private void appendWithComment (Element father, Element son, String constantName) {
    //comment before...
    String beforeComment = htmlLayout.getBeforeComment(constantName);
    if (beforeComment != null && beforeComment.trim().length() != 0) {
      father.append(beforeComment);
    }
    //append child
    father.appendChild(son);
    //comment end...
    String afterComment = htmlLayout.getAfterComment(constantName);
    if (afterComment != null && afterComment.trim().length() != 0) {
      father.append(afterComment);
    }
  }

  private void renewDoc (String shcPartStr, String appPartStr) {
    //doc->html
    this.docOut = new Document("");
    //    this.docOut.append("<!DOCTYPE html>");//html5
    this.html = htmlLayout.decorateWithTemplate("html");
    appendWithComment(this.docOut, this.html, "html");
    //html->style body head
    this.head = htmlLayout.decorateWithTemplate("head");
    this.style = htmlLayout.decorateWithTemplate("style");
    this.body = htmlLayout.decorateWithTemplate("body");
    appendWithComment(this.html, this.style, "style");
    appendWithComment(this.html, this.head, "head");
    appendWithComment(this.html, this.body, "body");
    //body -> shcPart appPart
    this.shcPart = htmlLayout.decorateWithTemplate("shcPart");
    this.appPart = htmlLayout.decorateWithTemplate("appPart");
    appendWithComment(this.body, this.shcPart, "shcPart");
    appendWithComment(this.body, this.appPart, "appPart");
    //shcPart && appPart (use dump)
    if (shcPartStr != null && shcPartStr.trim().length() != 0) {
      this.shcPart.append(shcPartStr);
    }
    if (appPartStr != null && appPartStr.trim().length() != 0) {
      this.appPart.append(appPartStr);
    }
  }

  private String dumpShcPart (List<DrawAppAttribute> drawApps) {
    //shcPart -> selectPart sidebarApp
    List<String> retList = new ArrayList<>();
    boolean isSidebarComment = false;
    for (DrawAppAttribute drawApp : drawApps) {
      if (!drawApp.isRunning) {
        //todo: it will be a different style to show the stopped Apps, later
        continue;
      }
      if (drawApp.isChoosed) {
        Element selectPart = htmlLayout.decorateWithTemplate("selectPart");
        Element selectApp = new Element(Tag.valueOf("h2"), "",
          htmlLayout.decorateWithTemplate("sidebarApp").attributes().clone())
          .attr("onclick", "location.href='" + drawApp.link + "'").text(drawApp.name);
        selectPart.appendChild(selectApp);
        retList.add(htmlLayout.getBeforeComment("selectPart"));
        retList.add(selectPart.toString());
        retList.add(htmlLayout.getAfterComment("selectPart"));
      } else {
        Element sidebarApp = htmlLayout.decorateWithTemplate("sidebarApp")
                                       .attr("onclick", "location.href='" + drawApp.link + "'").text(drawApp.name);
        if (!isSidebarComment) {
          retList.add(htmlLayout.getBeforeComment("sidebarApp"));
          retList.add(sidebarApp.toString());
          retList.add(htmlLayout.getAfterComment("sidebarApp"));
          isSidebarComment = true;
        } else {
          retList.add(sidebarApp.toString());
        }
      }
    }
    return String.join("\n", retList);
  }

  private String dumpAppPart (String appPart) {
    return appPart;
  }

  @Override
  public String render (DumpFile dumpFile) {
    renewDoc(dumpShcPart(dumpFile.getDrawApps()), dumpAppPart(dumpFile.getAppPart()));
    return this.docOut.toString();
  }

  @Override
  public String render (String shcPart, String appPart) {
    renewDoc(shcPart,appPart);
    return this.docOut.toString();
  }

  //  private void testDrawOut () {
  //    List<DrawAppAttribute> apps = new ArrayList<>();
  //    apps.add(
  //      new DrawAppAttribute.Builder().setName("cybertaotao").setLink("https://www.baidu.com").setChoosed(true).build());
  //    apps.add(new DrawAppAttribute.Builder().setName("baidu").setLink("https://www.baidu.com").build());
  //    apps.add(new DrawAppAttribute.Builder().setName("google").setLink("https://www.google.com").build());
  //    apps.add(new DrawAppAttribute.Builder().setName("dashen").setLink("https://www.cybertaotao.com").build());
  //    apps.add(new DrawAppAttribute.Builder().setName("sohu").setLink("https://www.sohu.com").build());
  //    renewDoc(dumpShcPart(apps), "<h3>this is the choosed App</h3>");
  //    System.out.println(this.docOut);
  //  }
  //  public static void main (String[] args) {
  //    WebRepresentator webRepresentator = new WebRepresentator(
  //      "/Users/shenshutao/WebstormProjects/untitled/src/toolbar" + ".html");
  //    webRepresentator.testDrawOut();
  //  }
}
