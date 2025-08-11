package com.study.rabbitmq.step3.news;

import com.study.rabbitmq.step3.config.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NewsPublisher {

    private final RabbitTemplate rabbitTemplate;

    public String publishMessage(String news, String messageSuffix){
        String message = news + messageSuffix;
        rabbitTemplate.convertAndSend(RabbitMQConfig.FANOUT_EXCHANGE_FOR_NEWS, news, message);
        System.out.println("[#] News Published: " + message);
        return message;
    }

    public String publish(String news){
        return publishMessage(news, "관련 새 소식이 있어요!");
    }

    public String publishAPI(String news){
        return publishMessage(news, " - 관련 새 소식이 나왔습니다. (API)");
    }
}
