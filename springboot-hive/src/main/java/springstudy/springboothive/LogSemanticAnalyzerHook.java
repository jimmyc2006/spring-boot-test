package springstudy.springboothive;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.hive.ql.parse.ASTNode;
import org.apache.hadoop.hive.ql.parse.AbstractSemanticAnalyzerHook;
import org.apache.hadoop.hive.ql.parse.HiveSemanticAnalyzerHookContext;
import org.apache.hadoop.hive.ql.parse.SemanticException;

/**
 * @author shuwei
 * @version 创建时间：2018年8月29日 上午10:12:35 类说明
 */
public class LogSemanticAnalyzerHook extends AbstractSemanticAnalyzerHook {

  @Override
  public ASTNode preAnalyze(HiveSemanticAnalyzerHookContext context, ASTNode ast)
      throws SemanticException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    try(BufferedWriter bw = new BufferedWriter(new FileWriter("/tmp/yyy.log", true))) {
      String hql = context.getCommand();
      String username = context.getUserName();
      String ip = context.getIpAddress();
      bw.write(sdf.format(new Date()) + " " + ip + ":" + username + ":" + hql + "\n");
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return super.preAnalyze(context, ast);
  }

}
