package authority.database;

import dataStructure.DrawAppAttribute;
import dataStructure.WebAppDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DatabaseOps {

  public static void main (String[] args) {
    /*
    appid int primary key not null,
   ...> appname text not null,
   ...> applink text not null,
   ...> isrunning int not null)
     */
    try {
      Class.forName("org.sqlite.JDBC");
      Connection connection = DriverManager.getConnection("jdbc:sqlite:/Users/shenshutao/newWeb/testBasedDB.db");
      Statement statement = connection.createStatement();
      //      statement.executeUpdate("insert into user values(500656,'testuser1','testuser1');");
      //      statement.executeUpdate("insert into user values(500657,'testuser2','testuser2');");
      //      statement.executeUpdate("insert into user values(500658,'testuser3','testuser3');");
      //
      //      statement.executeUpdate("insert into userlevel values(500658,3);");
      //      statement.executeUpdate("insert into userlevel values(500657,2);");
      //      statement.executeUpdate("insert into userlevel values(500656,1);");
      //      statement.executeUpdate("insert into webapps values(1,'google1-2','https://www.google.de\',1,3,2);");
      //      statement.executeUpdate("insert into webapps values(2,'google2-3','https://www.google.de\',1,3,3);");
      //      statement.executeUpdate("insert into webapps values(3,'google3-2','https://www.google.de\',1,3,2);");
      //      statement.executeUpdate("insert into webapps values(4,'google4-1','https://www.google.de\',1,3,1);");
      //      statement.executeUpdate("insert into webapps values(5,'google5-3','https://www.google.de\',1,3,3);");
      //return
      //      ResultSet rs = statement.executeQuery("select * from webapps");
      //      while (rs.next()) {
      //        // read the result set
      //        System.out.println("appname = " + rs.getString("appname"));
      //        System.out.println("appid = " + rs.getInt("appid"));
      //      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  static{
    try {
      Class.forName("org.sqlite.JDBC");
      connection = DriverManager.getConnection("jdbc:sqlite:/Users/shenshutao/newWeb/testBasedDB.db");
      System.out.println("db init finished!");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static Connection connection;

  public static List<DrawAppAttribute> readApps (int uid, int userlevel, int choosedAppID) {
    List<DrawAppAttribute> ret = new ArrayList<>();
    try {
      Statement statement = connection.createStatement();
      ResultSet rs = statement.executeQuery("select * from webapps");
      ret = WebAppDB.fromDB(rs).stream().sequential().map(x -> {
        if (x.appid == uid || x.accesslevel <= userlevel) {
          return new DrawAppAttribute.Builder().setRunning(x.isrunning == 1).setName(x.appname).setLink(x.applink)
                                               .setId(x.appid).setChoosed(x.appid == choosedAppID).build();
        } else {
          return null;
        }
      }).filter(x -> x != null).collect(Collectors.toList());
      statement.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return ret;
  }
//  public static getUser

}
