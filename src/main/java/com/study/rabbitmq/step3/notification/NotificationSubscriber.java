package com.study.rabbitmq.step3.notification;

import com.study.rabbitmq.step3.config.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationSubscriber {

    public static final String CLIENT_URL = "/topic/notifications";
    private final SimpMessagingTemplate simpMessagingTemplate;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void subscriber(String message){
        System.out.println("[#] Received Notification: " + message);
        simpMessagingTemplate.convertAndSend(CLIENT_URL, message);
    }
}
