package com.wetorek.rabbitmq.producer.service;

import com.wetorek.rabbitmq.cluster.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.wetorek.rabbitmq.cluster.Consts.DIRECT_EXCHANGE_NAME;

@Service
@RequiredArgsConstructor
@Slf4j
public class Sender {

    private static final String ROUTING_KEY = "test.routing.key";
    private final RabbitTemplate rabbitTemplate;

    @Scheduled(fixedRate = 3000L)
    public void send() {
        var message = new Message();
        message.setPayload(String.format("Message with id: %s", UUID.randomUUID()));
        rabbitTemplate.convertAndSend(DIRECT_EXCHANGE_NAME, ROUTING_KEY, message);
        log.info("Message: {} sent", message);
    }
}
