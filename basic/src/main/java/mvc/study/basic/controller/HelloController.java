package mvc.study.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import mvc.study.basic.annotation.RoutingInjected;
import mvc.study.basic.service.HelloService;

/** 
* @author shuwei 
* @version 创建时间：2018年9月20日 下午2:11:32 
* 神奇的事情：一个bean中包含2个bean的调用，根据方法的注释来区分到底调用哪个
* 知识点：给方法加切面,注解配合spring的使用
* BeanPostProcessor的使用
*/
@Controller
@RequestMapping("test")
public class HelloController {
  // 需要包含HelloServiceImpl1和HelloServiceImpl2,有的方法调用1的有的方法调用2的，根据配置
  @RoutingInjected
  private HelloService helloService;
  
  @RequestMapping("sayHello")
  public String sayHello() {
    this.helloService.sayHello();
    return "ok";
  }
  
  @RequestMapping("sayHi")
  public String sayHi() {
    this.helloService.sayHi();
    return "ok";
  }
}
