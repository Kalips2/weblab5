package com.example.producer.controllers;

import com.example.producer.services.RabbitLogger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LogController {

  private final RabbitLogger producer;

  @GetMapping("/{routing}/{message}")
  public ResponseEntity<?> sendLog(@PathVariable("message") String message,
                                   @PathVariable("routing") String routing) {
    try {
      producer.sendMessage(message, routing);
      return ResponseEntity.ok(message + " has been sent to " + routing);
    } catch (Exception e) {
      return ResponseEntity.status(500).body("Error!");
    }
  }
}
