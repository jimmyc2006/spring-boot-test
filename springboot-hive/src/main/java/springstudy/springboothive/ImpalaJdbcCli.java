package springstudy.springboothive;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author shuwei
 * @version 创建时间：2018年6月13日 下午7:29:37 连接impala的调研
 */
public class ImpalaJdbcCli {

  public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    String driver = "org.apache.hive.jdbc.HiveDriver";
    String url = "jdbc:hive2://test-005.qianjin.com:21050/crm;auth=noSasl";
    String user = "hive";
    Class.forName(driver);
    Connection conn = null;
    Statement s = null;
    ResultSet rs = null;
    long start = System.currentTimeMillis();
    BufferedWriter bw = null;
    try {
      bw = new BufferedWriter(new FileWriter("aa.txt"));
      System.out.println(sdf.format(new Date()) + " 开始创建连接");
      conn = DriverManager.getConnection(url, user, "");
      s = conn.createStatement();
      s.setFetchSize(200000);
      int num = 0;
      rs = s.executeQuery(
          "select *,1111 from test.ttt");
      // System.out.println(getSql());
      // rs = s.executeQuery(getSql());
      while (rs.next()) {
        num++;
        if (num % 500000 == 0) {
          System.out.println(sdf.format(new Date()) + " 获取第" + num + "条记录," + rs.getLong("user_id")
              + ", " + rs.getString("mobile") + ", " + rs.getString("email"));
          bw.flush();
        }
        bw.write(rs.getLong("user_id") + "|" + rs.getString("mobile") + "|" + rs.getString("email")
            + "|" + rs.getInt("r_num"));
        bw.newLine();
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      rs.close();
      s.close();
      conn.close();
      bw.close();
    }
    System.out
        .println(sdf.format(new Date()) + " 共耗时:" + (System.currentTimeMillis() - start) + "毫秒");
  }

  static String getSql() {
    String sqlInclude1 =
        "select user_id,mobile,email,r_num from crm.crm_user_result where etl_tx_dt=20180823";
    String sqlInclude2 =
        "select user_id,mobile,email,r_num from crm.crm_user_result where etl_tx_dt=20180712 and user_id=15169457";
    String sqlExclude1 = "select user_id,mobile,email,r_num from "
        + "(select tt.user_id, x.mobile as mobile, x.email as email, 1 as r_num "
        + "from (select tab1.label1 as label1, case when applying_amt>0 then 1 else 0 end as label2, case "
        + "when tab3.user_id is not null then 1 else 0 end as label3, case when tab4.can_apply_amt>0 "
        + "then 1 else 0 end as label4, tab0.user_id from (select user_id from mda.yuying_temp_0820_t3 "
        + "where label in ('case3','case4','case5','case6') group by user_id) as tab0 "
        + "left join (select a.user_id as user_id, case when b.user_id is not null then 1 else 0 end as label1 "
        + "from (select user_id from mid.mid_user_invest_detail where etl_tx_dt=20180820 group by user_id) as a "
        + "left join (select user_id from mid.mid_user_invest_detail where etl_tx_dt=20180820 and to_date(invest_time) "
        + "between '2018-07-21' and '2018-08-20' group by user_id) b on a.user_id=b.user_id) as tab1 on tab0.user_id=tab1.user_id "
        + "left join (select user_id,apply_exit_amount+trans_amount as applying_amt from dm.fact_duanchi_jianchi_increase where etl_tx_dt=20180820 "
        + "group by user_id,apply_exit_amount+trans_amount) as tab2 on tab0.user_id=tab2.user_id left join (select userid as user_id "
        + "from sda.sda10_app_log where etl_tx_dt between 20180814 and 20180820 and to_date(actiontime) between '2018-08-14' and '2018-08-20' group by userid) "
        + "as tab3 on tab0.user_id=tab3.user_id left join (select user_id,total_risk_exposure_amt as can_apply_amt from mda.yuying_temp_shuhui_amt_0820 "
        + "where data_date=20180820 group by user_id,total_risk_exposure_amt) as tab4 on tab0.user_id=tab4.user_id) as tt left join mid.mid_user_detail_temp "
        + "as x on x.user_id=tt.user_id left join (select distinct user_id from sda.sda11_user_group_detail where user_group_id = 4284 and etl_tx_dt=20180808) y "
        + "on y.user_id=tt.user_id where label1=0 and label2=0 and label3=0 and label4=1 and x.etl_tx_dt=20180820 and x.mobile is not null and y.user_id is null "
        + "group by tt.user_id, x.mobile, x.email, r_num order by rand() limit 4000) tmp where 1=1";
    String sqlInclude = "select * from (" + sqlInclude1 + "  union " + sqlInclude2 + ") tt1";
    String sql1 = "select t1.* from (" + sqlInclude + ") t1 left join (" + sqlExclude1
        + ") t2 on t1.user_id = t2.user_id where t2.user_id is null";
    return sql1;
  }
}
