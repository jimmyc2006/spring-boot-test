package mvc.study.basic.util;

import java.lang.reflect.Method;
import java.util.Map;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;

import mvc.study.basic.annotation.RoutingSwitch;

/** 
* @author shuwei 
* @version 创建时间：2018年9月20日 下午2:36:57 
* 类说明 
*/
public class RoutingBeanProxyFactory {
  public static Object createProxy(Class targetClass, Map<String, Object> beans) {
    ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.setInterfaces(targetClass);
    // 增加一个切面，来判断是否配置了A
    proxyFactory.addAdvice(new VersionRoutingMethodInterceptor(targetClass, beans));
    return proxyFactory.getProxy();
  }
  
  static class VersionRoutingMethodInterceptor implements MethodInterceptor {
    private String classSwitch;
    private Object beanOfSwitchOn;
    private Object beanOfSwitchOff;
    
    public VersionRoutingMethodInterceptor (Class clazz, Map<String, Object> beans) {
      beanOfSwitchOn = beans.get("helloServiceImpl1");
      beanOfSwitchOff = beans.get("helloServiceImpl2");
    }
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
      Method m = invocation.getMethod();
      RoutingSwitch rs = m.getAnnotation(RoutingSwitch.class);
      if (rs != null && rs.value().equals("A")) {
        return m.invoke(beanOfSwitchOn, invocation.getArguments());
      } else {
       return m.invoke(beanOfSwitchOff, invocation.getArguments());
      }
    }
    
  }
}
