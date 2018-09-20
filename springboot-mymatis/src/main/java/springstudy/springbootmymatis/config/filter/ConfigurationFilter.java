package springstudy.springbootmymatis.config.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/** 
* @author shuwei 
* @version 创建时间：2018年8月16日 下午3:39:26 
* 打印调用者传进来的参数 
*/
@Configuration
public class ConfigurationFilter {

  @Bean
  public FilterRegistrationBean testFilterReg() {
    FilterRegistrationBean frb = new FilterRegistrationBean();
    frb.setFilter(new ParamPrintFilter());
    frb.addUrlPatterns("/*");
    frb.setName("ParamPrintFilter");
    frb.setOrder(Ordered.HIGHEST_PRECEDENCE);
    return frb;
  }
}
