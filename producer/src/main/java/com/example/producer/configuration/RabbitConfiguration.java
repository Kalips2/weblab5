package com.example.producer.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class RabbitConfiguration {

  @Value("${rabbitmq.queue.info.name}")
  private String queue_info;
  @Value("${rabbitmq.queue.error.name}")
  private String queue_error;
  @Value("${rabbitmq.queue.debug.name}")
  private String queue_debug;
  @Value("${rabbitmq.queue.exchange}")
  private String exchange;

  @Bean
  TopicExchange exchange() {
    return new TopicExchange(exchange);
  }

  @Bean
  Binding infoBinding() {
    // routing_key for ingo queue will be "info"
    return BindingBuilder.bind(createQueueInfo()).to(exchange()).with("info");
  }

  @Bean
  Binding errorBinding() {
    return BindingBuilder.bind(createQueueError()).to(exchange()).with("error");
  }

  @Bean
  Binding debugBinding() {
    return BindingBuilder.bind(createQueueDebug()).to(exchange()).with("debug");
  }

  @Bean
  public Queue createQueueInfo() {
    return new Queue(queue_info, false);
  }

  @Bean
  public Queue createQueueError() {
    return new Queue(queue_error, false);
  }

  @Bean
  public Queue createQueueDebug() {
    return new Queue(queue_debug, false);
  }

  @Bean
  SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    return container;
  }

}
