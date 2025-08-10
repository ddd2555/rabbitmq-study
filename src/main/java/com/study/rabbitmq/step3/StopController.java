package com.study.rabbitmq.step3;

import com.study.rabbitmq.step3.notification.NotificationMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class StopController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/send")
    public void sendMessage(NotificationMessage notificationMessage) {
        String message = notificationMessage.getMessage();

        System.out.println("[#] message = " + message);
        simpMessagingTemplate.convertAndSend("/topic/notifications", message);
    }
}
