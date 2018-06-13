package spring.study.rabbitmq.producer;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

@Component
public class ObjectSender {
  @Autowired
  private AmqpTemplate rabbitTemplate;
  
  /**
   * 将对象转化为json格式发送，并且设置为不持久化
   * @param obj
   * @throws UnsupportedEncodingException
   */
  public void sendJsonNotPersistent(Object obj) throws UnsupportedEncodingException {
    MessageProperties mp = new MessageProperties();
    mp.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
    mp.setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT);
    Message mes = new Message(JSON.toJSONString(obj).getBytes("utf-8"), mp);
    this.rabbitTemplate.send("exchange", "q.user", mes);
  }
  
  /**
   * 使用默认的java序列化将对象发送
   * 缺点是对象必须使用这个类来接收此消息，包名不一样都不可以
   * @param obj 实现了序列化接口的
   */
  public void sendPersistent(Serializable obj) {
    this.rabbitTemplate.convertAndSend("exchange", "q.user", obj);
  }
  
  // 使用的比较多,发送json数据，使用text方式
  public void sendJsonPersistent(Object obj) {
    this.rabbitTemplate.convertAndSend("exchange", "q.user", JSON.toJSONString(obj));
  }
  
  public void send2Fanout(Object obj) {
    this.rabbitTemplate.convertAndSend("fanoutExchange", "", obj);
  }
}
