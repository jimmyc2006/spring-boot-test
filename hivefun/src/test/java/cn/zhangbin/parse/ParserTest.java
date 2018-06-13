package cn.zhangbin.parse;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import cn.zhangbin.bean.ColLine;
import cn.zhangbin.bean.SQLResult;

/**
 * @author shuwei
 * @version 创建时间：2018年6月13日 下午4:51:55 类说明
 */
public class ParserTest {
  private static final String sqlFile =
      "/Users/apple/eclipse-workspace/spring-boot-test/springboot-hive/select.sql";
  private static List<String> sqls;

  @BeforeClass
  public static void readSqls() throws IOException {
    sqls = FileUtils.readLines(new File(sqlFile));
  }

  @Test
  public void test() throws Exception {
    for (int i = 0; i < sqls.size(); i++) {
      String sql = sqls.get(i);
      if (sql.trim().startsWith("--")) {
        continue;
      }
      Parser parser = new Parser();
      List<SQLResult> list = new ArrayList<SQLResult>();
      list = parser.parse(sql);
      check(list.get(0), i);
      for (SQLResult r : list) {
        System.out.println("tables:" + r.getInputTables());
        System.out.println("outTables:" + r.getOutputTables());
        for (ColLine c : r.getColLineList()) {
          System.out.println(c.getFromNameSet());
        }
      }
    }
  }

  private void check(SQLResult result, int sqlIndex)
      throws NoSuchMethodException, SecurityException, IllegalAccessException,
      IllegalArgumentException, InvocationTargetException {
    Method method = this.getClass().getDeclaredMethod("check_" + sqlIndex, Set.class, Set.class);
    method.invoke(this, result.getInputTables(), result.getInputColumns());
  }

  private static final String DEFAULT_DB_NAME = "default";
  private static final String JOIN_SIGN = "&";
  
  protected void check_0(Set<String> tables, Set<String> columns) {
    Assert.assertTrue(tables.contains(DEFAULT_DB_NAME + ".tab1"));
    Assert.assertTrue(tables.contains(DEFAULT_DB_NAME + ".tab2"));
    Assert.assertTrue(columns.contains(DEFAULT_DB_NAME + ".tab1.ca"));
    Assert.assertTrue(columns.contains(DEFAULT_DB_NAME + ".tab1.cb"));
    Assert.assertTrue(columns.contains(DEFAULT_DB_NAME + ".tab2.ca"));
    Assert.assertTrue(columns.contains(DEFAULT_DB_NAME + ".tab2.cb"));
  }
  
  protected void check_1(Set<String> tables, Set<String> columns) {
    Assert.assertTrue(tables.contains(DEFAULT_DB_NAME + ".tab"));
    Assert.assertTrue(columns.contains(DEFAULT_DB_NAME + ".tab.ca"));
    Assert.assertTrue(columns.contains(DEFAULT_DB_NAME + ".tab.cb"));
  }
  
  protected void check_2(Set<String> tables, Set<String> columns) {
    String t1 = DEFAULT_DB_NAME + ".zpc";
    String t2 = DEFAULT_DB_NAME + ".def";
    Assert.assertTrue(tables.contains(t1));
    Assert.assertTrue(tables.contains(t2));
    // 两个表关联以后查询某个列
    Assert.assertTrue(columns.contains(t1 + JOIN_SIGN + t2 + ".name") || columns.contains(t2 + JOIN_SIGN + t1 + ".name"));
  }
  
  protected void check_3(Set<String> tables, Set<String> columns) {
    String t1 = DEFAULT_DB_NAME + ".tab1";
    String t2 = DEFAULT_DB_NAME + ".tab2";
    Assert.assertTrue(tables.contains(t1));
    Assert.assertTrue(tables.contains(t2));
    // 两个表关联以后查询某个列
    Assert.assertTrue(columns.contains(t1 + JOIN_SIGN + t2 + ".aa") || columns.contains(t2 + JOIN_SIGN + t1 + ".aa"));
    Assert.assertTrue(columns.contains(t1 + JOIN_SIGN + t2 + ".bb") || columns.contains(t2 + JOIN_SIGN + t1 + ".bb"));
  }
  
  protected void check_4(Set<String> tables, Set<String> columns) {
    String t1 = "dwd_bobo.dwd_bobo_fast";
    Assert.assertTrue(tables.contains(t1));
    // 两个表关联以后查询某个列
    Assert.assertTrue(columns.contains(t1 + ".real_abid"));
    Assert.assertTrue(columns.contains(t1 + ".parameters"));
  }
  
  protected void check_5(Set<String> tables, Set<String> columns) {
    String t1 = "tmp.test";
    Assert.assertTrue(tables.contains(t1));
    // 两个表关联以后查询某个列
    Assert.assertTrue(columns.contains(t1 + ".str"));
  }
  
  protected void check_6(Set<String> tables, Set<String> columns) {
    String t1 = DEFAULT_DB_NAME + ".zpc1";
    Assert.assertTrue(tables.contains(t1));
    // 两个表关联以后查询某个列
    Assert.assertTrue(columns.contains(t1 + ".*"));
  }
  
  protected void check_7(Set<String> tables, Set<String> columns) {
    String t1 = DEFAULT_DB_NAME + ".zpc2";
    String t2 = DEFAULT_DB_NAME + ".city";
    Assert.assertTrue(tables.contains(t1));
    Assert.assertTrue(tables.contains(t2));
    // 两个表关联以后查询某个列
    Assert.assertTrue(columns.contains(t2 + ".area"));
    Assert.assertTrue(columns.contains(t1 + ".name"));
    Assert.assertTrue(columns.contains(t1 + ".ip"));
  }
  
  protected void check_8(Set<String> tables, Set<String> columns) {
    String t1 = DEFAULT_DB_NAME + ".zpc3";
    String t2 = DEFAULT_DB_NAME + ".city";
    Assert.assertTrue(tables.contains(t1));
    Assert.assertTrue(tables.contains(t2));
    // 两个表关联以后查询某个列
    Assert.assertTrue(columns.contains(t1 + JOIN_SIGN + t2 + ".name") || columns.contains(t2 + JOIN_SIGN + t1 + ".name") );
    Assert.assertTrue(columns.contains(t1 + JOIN_SIGN + t2 + ".ip") || columns.contains(t2 + JOIN_SIGN + t1 + ".ip") );
  }
  
  protected void check_9(Set<String> tables, Set<String> columns) {
    String t1 = DEFAULT_DB_NAME + ".p1";
    Assert.assertTrue(tables.contains(t1));
    // 两个表关联以后查询某个列
    Assert.assertTrue(columns.contains(t1 +".id"));
    Assert.assertTrue(columns.contains(t1 +".value"));
  }
  
  protected void check_10(Set<String> tables, Set<String> columns) {
    String t1 = DEFAULT_DB_NAME + ".zpc";
    Assert.assertTrue(tables.contains(t1));
    // 两个表关联以后查询某个列
    Assert.assertTrue(columns.contains(t1 +".id"));
  }
  
  protected void check_11(Set<String> tables, Set<String> columns) {
    String t1 = DEFAULT_DB_NAME + ".zpc";
    Assert.assertTrue(tables.contains(t1));
    // 两个表关联以后查询某个列
    Assert.assertTrue(columns.contains(t1 +".id"));
  }
  
  protected void check_12(Set<String> tables, Set<String> columns) {
    String t1 = DEFAULT_DB_NAME + ".tablename";
    Assert.assertTrue(tables.contains(t1));
    // 两个表关联以后查询某个列
    Assert.assertTrue(columns.contains(t1 +".*"));
  }
  
  protected void check_13(Set<String> tables, Set<String> columns) {
    String t1 = "o2ostore.tdm_gds_monitor_rt";
    Assert.assertTrue(tables.contains(t1));
    // 两个表关联以后查询某个列
    Assert.assertTrue(columns.contains(t1 +".statis_date"));
    Assert.assertTrue(columns.contains(t1 +".time_interval"));
    Assert.assertTrue(columns.contains(t1 +".gds_cd"));
    Assert.assertTrue(columns.contains(t1 +".gds_nm"));
    Assert.assertTrue(columns.contains(t1 +".sale_cnt"));
    Assert.assertTrue(columns.contains(t1 +".discount_amt"));
    Assert.assertTrue(columns.contains(t1 +".discount_rate"));
    Assert.assertTrue(columns.contains(t1 +".price"));
    Assert.assertTrue(columns.contains(t1 +".etl_time"));
    Assert.assertTrue(columns.contains(t1 +".pay_amt"));
  }
  
  protected void check_14(Set<String> tables, Set<String> columns) {
    String t1 = DEFAULT_DB_NAME + ".a";
    String t2 = DEFAULT_DB_NAME + ".b";
    Assert.assertTrue(tables.contains(t1));
    Assert.assertTrue(tables.contains(t2));
    // 两个表关联以后查询某个列
    Assert.assertTrue(columns.contains(t1 +".*"));
  }
  
  protected void check_15(Set<String> tables, Set<String> columns) {
    String t1 = DEFAULT_DB_NAME + ".login";
    String t2 = DEFAULT_DB_NAME + ".regusers";
    Assert.assertTrue(tables.contains(t1));
    Assert.assertTrue(tables.contains(t2));
    // 两个表关联以后查询某个列
    Assert.assertTrue(columns.contains(t1 +".uid"));
  }
  
  protected void check_16(Set<String> tables, Set<String> columns) {
    String t1 = "dbx.zpc";
    String t2 = "dby.def";
    Assert.assertTrue(tables.contains(t1));
    Assert.assertTrue(tables.contains(t2));
    // 两个表关联以后查询某个列
    Assert.assertTrue(columns.contains(t2 +".bb"));
  }
}
