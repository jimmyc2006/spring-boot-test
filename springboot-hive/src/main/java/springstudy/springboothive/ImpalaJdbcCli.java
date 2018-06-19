package springstudy.springboothive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/** 
* @author shuwei 
* @version 创建时间：2018年6月13日 下午7:29:37 
* 连接impala的调研
*/
public class ImpalaJdbcCli {
  
  public static void main(String[] args) throws ClassNotFoundException, SQLException {
    String driver = "org.apache.hive.jdbc.HiveDriver";
    String url = "jdbc:hive2://ip:21050/crm;auth=noSasl";
    String user = "username";
    Class.forName(driver);
    Connection conn = DriverManager.getConnection(url,
        user, "");
    Statement s = conn.createStatement();
    ResultSet rs = s.executeQuery("show tables");
    while(rs.next()) {
      System.out.println(rs.getString(1));
    }
    rs.close();
    conn.close();
  }
}
