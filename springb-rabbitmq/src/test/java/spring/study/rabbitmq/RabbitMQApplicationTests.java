package spring.study.rabbitmq;

import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import spring.study.rabbitmq.bean.User;
import spring.study.rabbitmq.producer.ObjectSender;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMQApplicationTests {
  @Autowired
  private ObjectSender objSender;

  @Test
  public void sendUser() {
    User user = new User();
    user.setName("shuwei");
    user.setPass("passShuwei");
    user.setAge(123);
    try {
      this.objSender.sendJsonNotPersistent(user);
    } catch (UnsupportedEncodingException e1) {
      e1.printStackTrace();
    }
    try {
      Thread.sleep(1000 * 10);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
