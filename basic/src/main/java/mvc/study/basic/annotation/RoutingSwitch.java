package mvc.study.basic.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/** 
* @author shuwei 
* @version 创建时间：2018年9月20日 下午2:16:10 
* 类说明 
*/
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RoutingSwitch {
  /**
   * 在配置系统中开关的属性名称，应用系统将会实时读取配置系统中对应开关的值来决定是调用哪个版本
   */
  String value() default "";
}
