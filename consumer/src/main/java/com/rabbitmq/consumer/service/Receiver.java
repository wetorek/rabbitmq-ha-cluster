package com.rabbitmq.consumer.service;

import com.wetorek.rabbitmq.cluster.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import static com.wetorek.rabbitmq.cluster.Consts.QUEUE_NAME;


@Service
@Slf4j
public class Receiver {

    @RabbitListener(queues = QUEUE_NAME)
    public void listen(Message message) {
        log.info("Message received: {}", message);
    }
}
