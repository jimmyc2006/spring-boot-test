package mvc.study.basic.service;

import mvc.study.basic.annotation.RoutingSwitch;

/** 
* @author shuwei 
* @version 创建时间：2018年9月20日 下午2:07:10 
* 类说明 
*/
public interface HelloService {
  @RoutingSwitch("A")
  void sayHello();
  void sayHi();
}
