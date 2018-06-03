package spring.study.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
  @Bean
  public Queue queue() {
    return new Queue("q.user");
  }
  
  @Bean
  public Queue queueObj() {
    return new Queue("q.users");
  }
  
  @Bean
  public Queue queueThree() {
    return new Queue("q.test");
  }
  
  @Bean
  public TopicExchange exchange() {
    return new TopicExchange("exchange");
  }
  
  @Bean
  Binding bindingExchangeMessage() {
    // 将exchange绑定到queue,只有routeKey是q.user的时候才发送
    return BindingBuilder.bind(queue()).to(exchange()).with("q.user");
  }
  
  @Bean
  Binding bindingExchangeMessages() {
    // 将exchange绑定到queueObj,只要q.后面任何的routekey都会给这个queue发送消息
    return BindingBuilder.bind(queueObj()).to(exchange()).with("q.#");
  }
  
  @Bean
  FanoutExchange fanoutExchange() {
    return new FanoutExchange("fanoutExchange");
  }
  
  @Bean
  Binding bindingExchange() {
    // 绑定第一个队列
    return BindingBuilder.bind(queue()).to(fanoutExchange());
  }
  
  @Bean
  Binding bindingExchange2() {
    // 绑定第二个队列
    return BindingBuilder.bind(queueObj()).to(fanoutExchange());
  }
  
  @Bean
  Binding bindingExchange3() {
    // 绑定第三个队列
    return BindingBuilder.bind(queueThree()).to(fanoutExchange());
  }
}
