package cn.springboot.sundry.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="test")
public class Test {
  private int aa;

  public int getAa() {
    return aa;
  }

  public void setAa(int aa) {
    this.aa = aa;
  }
}
