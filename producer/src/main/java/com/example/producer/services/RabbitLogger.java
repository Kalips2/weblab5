package com.example.producer.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class RabbitLogger {
  @Value("${rabbitmq.queue.exchange}")
  private String exchange;

  private final RabbitTemplate template;

  public void sendMessage(String message, String routingKey) {
    try {
      template.convertAndSend(exchange, routingKey, message);
      log.info(String.format("Message sent -> %s to %s", message, routingKey));
    } catch (Exception e) {
      log.error(e.getMessage());
    }
  }
}
