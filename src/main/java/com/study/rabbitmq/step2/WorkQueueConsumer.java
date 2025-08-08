package com.study.rabbitmq.step2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WorkQueueConsumer {

    public void workQueueConsumer(String message){
        String[] messageParts = message.split("\\|");
        String originMessage = messageParts[0];
        int duration = Integer.parseInt(messageParts[1].trim() );

        log.info("Received Message from Queue: {} Duration: {}ms", originMessage, duration);

        try{
            int seconds = duration / 1000;
            for(int i = 0; i<= seconds; i++){
                Thread.sleep(1000);
                log.info(".");
            }

            log.info("now... sleep time {}ms", duration);
            Thread.sleep(duration);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }

        log.info("Completed {}", originMessage);
    }
}
