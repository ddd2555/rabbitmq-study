package com.study.rabbitmq.step3.news;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class NewsController {

    private final NewsPublisher newsPublisher;

    @MessageMapping("/subscribe")
    public void handleSubscribe(MessageDto dto){
        System.out.println("[#] newsType: " + dto);

        String newsMessage = newsPublisher.publish(dto);

        System.out.println("[#] newsMessage: " + newsMessage);
    }
}
