package dataStructure;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class WebAppDB {

  /**
   * appid int primary key not null,
   * appname text not null,
   * applink text not null,
   * isrunning int not null,
   * owner int REFERENCES user (userid) not null,
   * accesslevel int REFERENCES userlevel (userlevel) not null
   */
  public int appid;
  public String appname;
  public String applink;
  public int isrunning;
  public int owner;
  public int accesslevel;

  public WebAppDB (int appid, String appname, String applink, int isrunning, int owner, int accesslevel) {
    this.appid = appid;
    this.appname = appname;
    this.applink = applink;
    this.isrunning = isrunning;
    this.owner = owner;
    this.accesslevel = accesslevel;
  }

  public static List<WebAppDB> fromDB (ResultSet rs) {
    List<WebAppDB> ret = new ArrayList<>();
    try {
      while (rs.next()) {
        int appID = rs.getInt("appid");
        String appName = rs.getString("appname");
        String appLink = rs.getString("applink");
        int isRunning = rs.getInt("isrunning");
        int owner = rs.getInt("owner");
        int accesslevel = rs.getInt("accesslevel");
        ret.add(new WebAppDB(appID, appName, appLink, isRunning, owner, accesslevel));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return ret;
  }

}
