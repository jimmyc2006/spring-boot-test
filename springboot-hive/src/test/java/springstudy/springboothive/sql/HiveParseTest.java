package springstudy.springboothive.sql;

import java.util.Set;

import org.apache.hadoop.hive.ql.parse.ASTNode;
import org.apache.hadoop.hive.ql.parse.ParseDriver;
import org.apache.hadoop.hive.ql.parse.ParseException;
import org.junit.Test;

/** 
* @author shuwei 
* @version 创建时间：2018年6月13日 下午3:01:14 
* 类说明 
*/
public class HiveParseTest {
  @Test
  public void test1() throws ParseException {
    String sql = "Select * from zpc1";
    HiveParse hp = new HiveParse();
    ParseDriver pd = new ParseDriver();
    ASTNode ast = pd.parse(sql);
    hp.parse(ast);
    Set<String> tablesAndOpers = hp.getTables();
    
  }
}
