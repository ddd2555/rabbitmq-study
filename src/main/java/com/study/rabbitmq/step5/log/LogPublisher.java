package com.study.rabbitmq.step5.log;

import com.study.rabbitmq.step5.config.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LogPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void publish(String routingKey, String message){
        rabbitTemplate.convertAndSend(RabbitMQConfig.DIRECT_EXCHANGE, routingKey, message);
        System.out.println("message published: " + routingKey + " : " + message);
    }

}
