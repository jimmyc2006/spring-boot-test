package spring.study.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import spring.study.rabbitmq.bean.User;

//@Component
//@RabbitListener(queues = "q.user")
public class HelloReceiver {
  //@RabbitHandler
  public void process(User hello) {
    System.out.println(this.getClass().getName() + " : " + Thread.currentThread().getName() + " : "
        + System.currentTimeMillis() + " Receiver  : " + hello);
  }
}
