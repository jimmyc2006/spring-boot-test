package spring.study.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import spring.study.rabbitmq.bean.ttt.UserT;

@Component
@RabbitListener(queues = "q.user")
public class HelloReceiver {
  @RabbitHandler
  public void process(String user) {
    System.out.println(this.getClass().getName() + " : " + Thread.currentThread().getName() + " : "
        + System.currentTimeMillis() + " Receiver  : " + user);
    UserT u2 = JSON.parseObject(user, UserT.class);
    System.out.println(u2.getClass());
    System.out.println(u2);
  }
}
