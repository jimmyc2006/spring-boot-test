package spring.study.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import spring.study.rabbitmq.bean.User;
import spring.study.rabbitmq.producer.HelloSender;
import spring.study.rabbitmq.producer.ObjectSender;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMQApplicationTests {
  @Autowired
  private HelloSender helloSender;
  @Autowired
  private ObjectSender objSender;

  @Test
  public void sendUser() {
    User user = new User();
    user.setName("shuwei");
    user.setPass("passShuwei");
    user.setAge(101);
    this.objSender.send2Fanout(user);
  }
}
