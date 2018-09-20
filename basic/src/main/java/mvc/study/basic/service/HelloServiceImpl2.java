package mvc.study.basic.service;

import org.springframework.stereotype.Service;

/** 
* @author shuwei 
* @version 创建时间：2018年9月20日 下午2:09:39 
* 类说明 
*/
@Service
public class HelloServiceImpl2 implements HelloService{

  @Override
  public void sayHello() {
    System.out.println("Hello from V2");
  }

  @Override
  public void sayHi() {
    System.out.println("Hello fro V2");
  }

}
