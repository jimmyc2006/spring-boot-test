package springstudy.springboothive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import springstudy.springboothive.util.OutUtil;

public class HiveJdbcCli {
  private static String driverName = "org.apache.hive.jdbc.HiveDriver";
  private static String sql = "select * from crm_user_detail limit 5";
  private static ResultSet res;
  private static final Logger log = LoggerFactory.getLogger(HiveJdbcCli.class);

  public static void main(String[] args) {
    LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
    ch.qos.logback.classic.Logger logger = loggerContext.getLogger("root");
    logger.setLevel(Level.toLevel("INFO"));

    Connection conn = null;
    Statement stmt = null;
    long startTime = System.currentTimeMillis();
    try {
      Class.forName(driverName);
      conn = DriverManager.getConnection("jdbc:hive2://ip:10000/crm",
          "username", "");
      // conn = getConn();
      stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(sql);
      rs.setFetchSize(OutUtil.LOG_BATCH);
      String fileName = "/Users/apple/Downloads/tmp/res.xls";
      System.out.println("开始写入结果文件");
      OutUtil.write2Excel(rs, fileName);
      /*
       * ResultSetMetaData metaData = rs.getMetaData(); int columnCount = metaData.getColumnCount();
       * for(int i = 1; i <= columnCount; i++) { System.out.println(metaData.getColumnTypeName(i) +
       * ":" + metaData.getColumnName(i)); } while(rs.next()) { for (int i = 1; i <= columnCount;
       * i++) { System.out.print(rs.getString(i) + ","); } System.out.println(); }
       */
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

  private static void countData(Statement stmt, String tableName) throws SQLException {
    sql = "select count(1) from " + tableName;
    System.out.println("Running:" + sql);
    res = stmt.executeQuery(sql);
    System.out.println("执行“regular hive query”运行结果:");
    while (res.next()) {
      System.out.println("count ------>" + res.getString(1));
    }
  }

  private static void selectData(Statement stmt, String tableName) throws SQLException {
    sql = "select * from " + tableName;
    System.out.println("Running:" + sql);
    res = stmt.executeQuery(sql);
    System.out.println("执行 select * query 运行结果:");
    while (res.next()) {
      System.out.println(res.getInt(1) + "\t" + res.getString(2));
    }
  }

  private static void loadData(Statement stmt, String tableName) throws SQLException {
    String filepath = "/home/hadoop01/data";
    sql = "load data local inpath '" + filepath + "' into table " + tableName;
    System.out.println("Running:" + sql);
    res = stmt.executeQuery(sql);
  }

  private static void describeTables(Statement stmt, String tableName) throws SQLException {
    sql = "describe " + tableName;
    System.out.println("Running:" + sql);
    res = stmt.executeQuery(sql);
    System.out.println("执行 describe table 运行结果:");
    while (res.next()) {
      System.out.println(res.getString(1) + "\t" + res.getString(2));
    }
  }

  private static void showTables(Statement stmt, String tableName) throws SQLException {
    sql = "show tables '" + tableName + "'";
    System.out.println("Running:" + sql);
    res = stmt.executeQuery(sql);
    System.out.println("执行 show tables 运行结果:");
    if (res.next()) {
      System.out.println(res.getString(1));
    }
  }

  private static void createTable(Statement stmt, String tableName) throws SQLException {
    sql = "create table " + tableName
        + " (key int, value string)  row format delimited fields terminated by '\t'";
    stmt.executeQuery(sql);
  }

  private static String dropTable(Statement stmt) throws SQLException {
    // 创建的表名
    String tableName = "testHive";
    sql = "drop table " + tableName;
    stmt.executeQuery(sql);
    return tableName;
  }
}
