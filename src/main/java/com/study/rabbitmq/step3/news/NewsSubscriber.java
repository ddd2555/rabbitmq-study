package com.study.rabbitmq.step3.news;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.rabbitmq.step3.config.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class NewsSubscriber {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @RabbitListener(queues = RabbitMQConfig.JAVA_QUEUE)
    public void javaNews(MessageDto message) throws JsonProcessingException {

        ResponseDto response = ResponseDto.builder()
                .newsType(message.getNewsType())
                .content(message.getContent())
                .date(LocalDateTime.now())
                .build();

        String json = new ObjectMapper().writeValueAsString(response);
        simpMessagingTemplate.convertAndSend("/topic/java", json);
    }

//    @RabbitListener(queues = RabbitMQConfig.SPRING_QUEUE)
//    public void springNews(String message){
//        simpMessagingTemplate.convertAndSend("/topic/spring", message);
//    }
//
//    @RabbitListener(queues = RabbitMQConfig.VUE_QUEUE)
//    public void vueNews(String message){
//        simpMessagingTemplate.convertAndSend("/topic/vue", message);
//    }
}
