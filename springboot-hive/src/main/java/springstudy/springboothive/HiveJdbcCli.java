package springstudy.springboothive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HiveJdbcCli {
  private static String driverName = "org.apache.hive.jdbc.HiveDriver";
  private static String sql = "unset hive.exec.post.hooks; select 1 from test.ttt";
  private static ResultSet res;
  private static final Logger log = LoggerFactory.getLogger(HiveJdbcCli.class);

  public static void main(String[] args) {
    Connection conn = null;
    Statement stmt = null;
    long startTime = System.currentTimeMillis();
    try {
      Class.forName(driverName);
      conn = DriverManager.getConnection("",
          "", "");
      // conn = getConn();
      stmt = conn.createStatement();
//      stmt.execute("reset hive.exec.post.hooks");
//      stmt.close();
//      stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("select * from test.ttt");
      while (rs.next()) {
        System.out.println("----------");
        System.out.println(rs.getObject(1));
//        System.out.println(rs.getObject(2));
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (conn != null) {
          conn.close();
          conn = null;
        }
      } catch (SQLException e) {
      }
    }
    System.out.println("执行完毕！！！～！～！耗时:" + (System.currentTimeMillis() - startTime));
  }
}
