package springstudy.springboothive.sql;

import java.util.List;
import java.util.Map;

import org.apache.hadoop.hive.ql.lib.DefaultGraphWalker;
import org.apache.hadoop.hive.ql.lib.DefaultRuleDispatcher;
import org.apache.hadoop.hive.ql.lib.Dispatcher;
import org.apache.hadoop.hive.ql.lib.GraphWalker;
import org.apache.hadoop.hive.ql.lib.NodeProcessor;
import org.apache.hadoop.hive.ql.lib.Rule;
import org.apache.hadoop.hive.ql.parse.ASTNode;
import org.apache.hadoop.hive.ql.parse.ParseDriver;
import org.apache.hadoop.hive.ql.parse.ParseException;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @author shuwei
 * @version 创建时间：2018年6月12日 下午2:16:58 类说明
 */
public class SqlParser {

  public static void test1() throws ParseException {
    String sql = "select bb,count(*) from t1 where t1.aa='aa' group by bb";
    ParseDriver pd = new ParseDriver();
    ASTNode tree = pd.parse(sql);
    System.out.println("start to analyze sql: " + sql + ", ASTNode: " + tree.dump());
    while ((tree.getToken() == null) && (tree.getChildCount() > 0)) {
      tree = (ASTNode) tree.getChild(0);
    }
  }

  public static void main(String[] args) throws ParseException {
    org.apache.hadoop.hive.ql.parse.ParseDriver ll;
    test1();
  }
}
