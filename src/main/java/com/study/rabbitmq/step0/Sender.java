package com.study.rabbitmq.step0;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Sender {

    private final RabbitTemplate rabbitTemplate;

    public void send(String message){
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME,  message);
        log.info("Sent Message:{}",message);
    }
}
