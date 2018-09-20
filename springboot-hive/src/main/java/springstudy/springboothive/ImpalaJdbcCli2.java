package springstudy.springboothive;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author shuwei
 * @version 创建时间：2018年6月13日 下午7:29:37 连接impala的调研
 */
public class ImpalaJdbcCli2 {

  static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {

    String driver = "org.apache.hive.jdbc.HiveDriver";
    String url = "jdbc:hive2://192.168.40.28:10000/crm";
    String user = "hive";
    Class.forName(driver);
    Connection conn = null;
    Statement s = null;
    long start = System.currentTimeMillis();
    try {
      System.out.println(sdf.format(new Date()) + " 开始创建连接:" + user);
      conn = DriverManager.getConnection(url, user, "");
      readXls(conn);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      conn.close();
    }
    System.out
        .println(sdf.format(new Date()) + " 共耗时:" + (System.currentTimeMillis() - start) + "毫秒");
  }

  private static final ExecutorService RULE_THREAD_POOL = Executors.newFixedThreadPool(10);

  static public void readXls(Connection conn)
      throws IOException, SQLException, InterruptedException {
    int batch = 500000;
    int num = 0;
    InputStream is = new FileInputStream("ttt.xlsx");
    XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
    // Read the Sheet
    for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
      XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
      int size = xssfSheet.getLastRowNum() % batch == 0 ? xssfSheet.getLastRowNum() / batch
          : xssfSheet.getLastRowNum() / batch + 1;
      if (xssfSheet == null) {
        continue;
      }
      StringBuilder sb = new StringBuilder(
          "insert into tmp.user_group_detail_tmp_zzh PARTITION(etl_tx_dt=20180827) (user_id,mobile)  values");
      // Read the Row
      for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
        XSSFRow xssfRow = xssfSheet.getRow(rowNum);
        if (xssfRow != null) {
          XSSFCell userId = xssfRow.getCell(0);
          XSSFCell mobile = xssfRow.getCell(1);
          num++;
          sb.append("(" + getValue(userId) + ", '" + getValue(mobile) + "'),");
          num++;
          sb.append("(" + getValue(userId) + ", '" + getValue(mobile) + "'),");
          num++;
          sb.append("(" + getValue(userId) + ", '" + getValue(mobile) + "'),");
          num++;
          sb.append("(" + getValue(userId) + ", '" + getValue(mobile) + "'),");
          num++;
          sb.append("(" + getValue(userId) + ", '" + getValue(mobile) + "'),");
          num++;
          sb.append("(" + getValue(userId) + ", '" + getValue(mobile) + "'),");
          num++;
          sb.append("(" + getValue(userId) + ", '" + getValue(mobile) + "'),");
          num++;
          sb.append("(" + getValue(userId) + ", '" + getValue(mobile) + "'),");
          num++;
          sb.append("(" + getValue(userId) + ", '" + getValue(mobile) + "'),");
          num++;
          sb.append("(" + getValue(userId) + ", '" + getValue(mobile) + "'),");
          if (num % batch == 0) {
            sb.deleteCharAt(sb.length() - 1);
            System.out.println(sdf.format(new Date()) + " 开始执行插入:" + num);
            String sql = sb.toString();
            Statement s = conn.createStatement();
            long start = System.currentTimeMillis();
            s.execute(sql);
            System.out.println(sdf.format(new Date()) + " 使用hive插入耗时" + (System.currentTimeMillis() - start));
            s.close();
            sb = new StringBuilder(
                "insert into tmp.user_group_detail_tmp_zzh PARTITION(etl_tx_dt=20180827) (user_id,mobile)  values");
          }
        }
      }
      if (sb
          .length() > "insert into tmp.user_group_detail_tmp_zzh PARTITION(etl_tx_dt=20180827) (user_id,mobile)  values"
              .length()) {
        sb.deleteCharAt(sb.length() - 1);
        System.out.println("最后插入");
        Statement s = conn.createStatement();
        long start = System.currentTimeMillis();
        s.execute(sb.toString());
        System.out.println("SSSSS" + (System.currentTimeMillis() - start));
        s.close();
      }
      System.out.println("准备关闭链接");
    }
    xssfWorkbook.close();
    is.close();
  }

  private static String getValue(XSSFCell xssfRow) {
    if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
      return String.valueOf(xssfRow.getBooleanCellValue());
    } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
      return xssfRow.getRawValue();
    } else {
      return String.valueOf(xssfRow.getStringCellValue());
    }
  }
}
