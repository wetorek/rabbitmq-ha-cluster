package com.rabbitmq.producer.service;

import com.wetorek.rabbitmq.cluster.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.rabbitmq.producer.config.Config.DIRECT_EXCHANGE_NAME;
import static com.rabbitmq.producer.config.Config.ROUTING_KEY;

@Service
@RequiredArgsConstructor
@Slf4j
public class Sender {

    private final RabbitTemplate rabbitTemplate;

    @Scheduled(fixedRate = 3000L)
    public void send() {
        var message = new Message();
        message.setPayload(String.format("Message with id: %s", UUID.randomUUID()));
        rabbitTemplate.convertAndSend(DIRECT_EXCHANGE_NAME, ROUTING_KEY, message);
        log.info("Message: {} sent", message);
    }
}
