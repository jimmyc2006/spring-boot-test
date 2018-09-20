package springstudy.springbootmymatis.config.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author shuwei
 * @version 创建时间：2018年8月16日 下午3:44:00 打印调用参数
 */
public class ParamPrintFilter implements Filter {
  private Logger log = LoggerFactory.getLogger(ParamPrintFilter.class);

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {}

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    StringBuilder sb = new StringBuilder("请求的URI:" + httpRequest.getRequestURI() + ",参数:");
    int length = sb.length();
    httpRequest.getParameterMap()
        .forEach((k, v) -> sb.append(k + "=" + (v.length > 0 ? v[0] : null) + ","));
    if (sb.length() > length) {
      log.info(sb.toString());
    }
    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {}

}
