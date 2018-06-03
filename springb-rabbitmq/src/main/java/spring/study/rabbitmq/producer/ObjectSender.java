package spring.study.rabbitmq.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ObjectSender {
  @Autowired
  private AmqpTemplate rabbitTemplate;
  
  public void send(Object obj) {
    this.rabbitTemplate.convertAndSend("exchange", "q.userxxxxxxxxx", obj);
  }
  
  public void send2Fanout(Object obj) {
    this.rabbitTemplate.convertAndSend("fanoutExchange", "", obj);
  }
}
