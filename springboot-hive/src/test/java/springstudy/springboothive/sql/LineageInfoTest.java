package springstudy.springboothive.sql;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.TreeSet;

import org.apache.commons.io.FileUtils;
import org.apache.hadoop.hive.ql.parse.ParseException;
import org.apache.hadoop.hive.ql.parse.SemanticException;
import org.apache.hadoop.hive.ql.tools.LineageInfo;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author shuwei
 * @version 创建时间：2018年6月13日 下午6:44:41 类说明
 */
public class LineageInfoTest {

  private static final String sqlFile = "select.sql";
  private static List<String> sqls;

  @BeforeClass
  public static void readSqls() throws IOException {
    File f = new File(sqlFile);
    System.out.println(f.getAbsolutePath());
    sqls = FileUtils.readLines(new File(sqlFile));
  }

  @Test
  public void test() throws Exception {
    for (int i = 0; i < sqls.size(); i++) {
      String sql = sqls.get(i);
      if (sql.trim().startsWith("--")) {
        continue;
      }
      TreeSet<String> talbes = test(sql);
      check(talbes, i);
    }
  }

  private void check(TreeSet<String> tables, int sqlIndex)
      throws NoSuchMethodException, SecurityException, IllegalAccessException,
      IllegalArgumentException, InvocationTargetException {
    Method method = this.getClass().getDeclaredMethod("check_" + sqlIndex, TreeSet.class);
    method.invoke(this, tables);
  }

  protected void check_0(TreeSet<String> tables) {
    Assert.assertTrue(tables.contains("tab1"));
    Assert.assertTrue(tables.contains("tab2"));
  }

  protected void check_1(TreeSet<String> tables) {
    Assert.assertTrue(tables.contains("tab"));
  }

  protected void check_2(TreeSet<String> tables) {
    Assert.assertTrue(tables.contains("zpc"));
    Assert.assertTrue(tables.contains("def"));
  }

  protected void check_3(TreeSet<String> tables) {
    Assert.assertTrue(tables.contains("tab1"));
    Assert.assertTrue(tables.contains("tab2"));
  }

  protected void check_4(TreeSet<String> tables) {
    Assert.assertTrue(tables.contains("dwd_bobo.dwd_bobo_fast"));
  }

  protected void check_5(TreeSet<String> tables) {
    Assert.assertTrue(tables.contains("tmp.test"));
  }

  protected void check_6(TreeSet<String> tables) {
    Assert.assertTrue(tables.contains("zpc1"));
  }

  protected void check_7(TreeSet<String> tables) {
    Assert.assertTrue(tables.contains("zpc2"));
    Assert.assertTrue(tables.contains("city"));
  }

  protected void check_8(TreeSet<String> tables) {
    Assert.assertTrue(tables.contains("zpc3"));
    Assert.assertTrue(tables.contains("city"));
  }

  protected void check_9(TreeSet<String> tables) {
    Assert.assertTrue(tables.contains("p1"));
    Assert.assertTrue(tables.contains("p1"));
  }

  protected void check_10(TreeSet<String> tables) {
    Assert.assertTrue(tables.contains("zpc"));
  }

  protected void check_11(TreeSet<String> tables) {
    Assert.assertTrue(tables.contains("zpc"));
  }

  protected void check_12(TreeSet<String> tables) {
    Assert.assertTrue(tables.contains("tablename"));
  }

  protected void check_13(TreeSet<String> tables) {
    Assert.assertTrue(tables.contains("o2ostore.tdm_gds_monitor_rt"));
  }

  protected void check_14(TreeSet<String> tables) {
    Assert.assertTrue(tables.contains("a"));
    Assert.assertTrue(tables.contains("b"));
  }

  protected void check_15(TreeSet<String> tables) {
    Assert.assertTrue(tables.contains("login"));
    Assert.assertTrue(tables.contains("regusers"));
  }

  protected void check_16(TreeSet<String> tables) {
    Assert.assertTrue(tables.contains("dbx.zpc"));
    Assert.assertTrue(tables.contains("dby.def"));
  }

  public static TreeSet<String> test(String query) throws SemanticException, ParseException {
    LineageInfo lep = new LineageInfo();
    lep.getLineageInfo(query);
    return lep.getInputTableList();
  }
}
