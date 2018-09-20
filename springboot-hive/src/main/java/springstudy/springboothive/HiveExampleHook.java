package springstudy.springboothive;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.hive.ql.hooks.ExecuteWithHookContext;
import org.apache.hadoop.hive.ql.hooks.HookContext;

/** 
* @author shuwei 
* @version 创建时间：2018年8月29日 下午12:00:25 
* 类说明 
*/
public class HiveExampleHook implements ExecuteWithHookContext {

  @Override
  public void run(HookContext hookContext) throws Exception {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    try(BufferedWriter bw = new BufferedWriter(new FileWriter("/tmp/yyy.log", true))) {
      String hql = hookContext.getQueryPlan().getQueryStr();
      String username = hookContext.getUserName();
      String ip = hookContext.getIpAddress();
      bw.write(sdf.format(new Date()) + " " + ip + ":" + username + ":" + hql + "\n");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
