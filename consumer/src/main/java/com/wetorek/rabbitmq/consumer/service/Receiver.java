package com.wetorek.rabbitmq.consumer.service;

import com.wetorek.rabbitmq.cluster.Message;
import com.wetorek.rabbitmq.consumer.config.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class Receiver {

    @RabbitListener(queues = Config.QUEUE_NAME)
    public void listen(Message message) {
        log.info("Message received: {}", message);
    }
}
