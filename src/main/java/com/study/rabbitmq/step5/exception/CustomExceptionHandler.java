package com.study.rabbitmq.step5.exception;

import com.study.rabbitmq.step5.log.LogPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomExceptionHandler {

    private final LogPublisher logPublisher;

    public void handleException(Exception e){
        String message = e.getMessage();

        String routingKey;

        if( e instanceof NullPointerException){
            routingKey = "error";
        }else if( e instanceof IllegalArgumentException){
            routingKey = "warn";
        }else {
            routingKey = "error";
        }

        logPublisher.publish(routingKey, "Exception이 발생했음 : " + message);
    }

    public void handleMessage(String message){
        String routingKey = "info";
        logPublisher.publish(routingKey, "Info Log: " + message);
    }

}
