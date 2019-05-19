package representator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

import java.io.*;

public class HtmlLayout {

  /**
   * main doc
   */
  private Document doc;

  /**
   * html frame
   */
  private Element html;
  private Element head;
  private Element style;
  private Element body;
  /**
   * app part:
   */
  private Element appPart;
  private static final String APP_PART = "<!-- current app handle part -->";
  private static final String END_APP_PART = "<!-- end current app handle part -->";

  /**
   * shc part:
   */
  private Element shcPart;
  private static final String SHC_PART = "<!-- SHC handle part -->";
  private static final String END_SHC_PART = "<!-- end SHC handle part -->";
  //   inside Shc
  private Element selectPart;
  private static final String NON_SELECT_PART = "<!-- sidebar app -->";
  private static final String END_NON_SELECT_PART = "<!-- end sidebar app -->";

  private Element sidebarApp;
  private static final String SELECT_PART = "<!-- selected app -->";
  private static final String END_SELECT_PART = "<!-- end selected app -->";

  private HtmlLayout (Document doc, Element html, Element head, Element style, Element body, Element appPart,
    Element shcPart, Element selectPart, Element sidebarApp) {
    this.doc = doc;
    this.html = html;
    this.head = head;
    this.style = style;
    this.body = body;
    this.appPart = appPart;
    this.shcPart = shcPart;
    this.selectPart = selectPart;
    this.sidebarApp = sidebarApp;
  }

  public static HtmlLayout parseTemplateHtml (String filePath) {
    return parseTemplateHtml(new File(filePath));
  }

  public static HtmlLayout parseTemplateHtml (File file) {
    try {
      BufferedReader fi = new BufferedReader(new FileReader(file));
      StringBuffer stringBuffer = new StringBuffer();
      fi.lines().sequential().forEach(s -> stringBuffer.append(s));
      fi.close();
      return parseTemplateHtml(stringBuffer.toString(), true);
    } catch (IOException e) {
      //log out
    }
    return null;
  }

  private static HtmlLayout cacheLayout = null;

  public static HtmlLayout parseTemplateHtml (String s, boolean isHtml) {
    if (cacheLayout!=null){
      return cacheLayout;
    }
    if (isHtml) {
      Document doc = Jsoup.parse(s);
      Element html = doc.getElementsByTag("html").first();
      Element head = doc.getElementsByTag("head").first();
      Element style = doc.getElementsByTag("style").first();
      Element body = doc.getElementsByTag("body").first();
      //app:
      Element appPart = (Element) Util.parsePart(body, APP_PART, END_APP_PART);
      //shc:
      Element shcPart = (Element) Util.parsePart(body, SHC_PART, END_SHC_PART);
      Element selectPart = (Element) Util.parsePart(shcPart, SELECT_PART, END_SELECT_PART);
      Element sidebarApp = (Element) Util.parsePart(shcPart, NON_SELECT_PART, END_NON_SELECT_PART);
      cacheLayout = new HtmlLayout(doc, html, head, style, body, appPart, shcPart, selectPart, sidebarApp);
      System.out.println("new a HtmlLayout! high payoff!!");
      return cacheLayout;
    } else {
      return null;
    }
  }

  public String getBeforeComment (String tag) {
    return getComment(tag, true);
  }

  public String getAfterComment (String tag) {
    return getComment(tag, false);
  }

  private String getComment (String tag, boolean before) {
    String retComment = null;
    switch (tag) {
      case "html":
        break;
      case "head":
        break;
      case "style":
        break;
      case "body":
        break;
      case "appPart":
        retComment = before
                     ? APP_PART
                     : END_APP_PART;
        break;
      case "shcPart":
        retComment = before
                     ? SHC_PART
                     : END_SHC_PART;
        break;
      case "selectPart":
        retComment = before
                     ? SELECT_PART
                     : END_SELECT_PART;
        break;
      case "sidebarApp":
        retComment = before
                     ? NON_SELECT_PART
                     : END_NON_SELECT_PART;
        break;
      default:
        return null;
    }
    return retComment;
  }

  public Element decorateWithTemplate (Element tag, String text) {
    return decorateWithTemplate(tag).text(text);
  }

  public Element decorateWithTemplate (String tagName, String text) {
    return decorateWithTemplate(tagName).text(text);
  }

  public Element decorateWithTemplate (Element tag) {
    return decorateWithTemplate(tag.nodeName());
  }

  public Element decorateWithTemplate (String tagName) {
    Element retEle = null;
    Attributes retAttrs = null;
    switch (tagName) {
      case "html":
        retAttrs = this.html.attributes().clone();
        break;
      case "head":
        //todo:future problem
        return this.head;
      case "style":
        //todo:future problem
        return this.style;
      case "body":
        retAttrs = this.body.attributes().clone();
        break;
      case "appPart":
        retAttrs = this.appPart.attributes().clone();
        tagName = this.appPart.tagName();
        break;
      case "shcPart":
        retAttrs = this.shcPart.attributes().clone();
        tagName = this.shcPart.tagName();
        break;
      case "selectPart":
        retAttrs = this.selectPart.attributes().clone();
        tagName = this.selectPart.tagName();
        break;
      case "sidebarApp":
        retAttrs = this.sidebarApp.attributes().clone();
        tagName = this.sidebarApp.tagName();
        break;
      default:
        return null;
    }
    retEle = new Element(Tag.valueOf(tagName), "", retAttrs);
    return retEle;
  }

  public static void main (String[] args) {
    HtmlLayout htmlLayout = HtmlLayout
      .parseTemplateHtml("/Users/shenshutao/WebstormProjects/untitled/src/toolbar" + ".html");
    System.out.println(htmlLayout.sidebarApp);
  }

}
