package mvc.study.basic.service;

import org.springframework.stereotype.Service;

/** 
* @author shuwei 
* @version 创建时间：2018年9月20日 下午2:08:35 
* 类说明 
*/
@Service
public class HelloServiceImpl1 implements HelloService {

  @Override
  public void sayHello() {
    System.out.println("Hello from V1");
  }

  @Override
  public void sayHi() {
    System.out.println("Hi from V1");
  }

}
