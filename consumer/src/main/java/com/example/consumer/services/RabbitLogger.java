package com.example.consumer.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RabbitLogger {

  @RabbitListener(queues = {"${rabbitmq.queue.info.name}"})
  public void consumeInfo(String message) {
    log.info("[INFO]: " + message);
  }

  @RabbitListener(queues = {"${rabbitmq.queue.error.name}"})
  public void consumeError(String message) {
    log.error("[ERROR]: " + message);
  }

  @RabbitListener(queues = {"${rabbitmq.queue.debug.name}"})
  public void consumeDebug(String message) {
    log.warn("[DEBUG]: " + message);
  }
}
