package com.study.rabbitmq.step2;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class WorkQueueController {

    private final WorkQueueProducer workQueueProducer;

    @PostMapping("/workqueue")
    public String sendMessage(@RequestParam String message, @RequestParam int duration){
        workQueueProducer.sendWorkQueue(message, duration);
        return "[#] Work queue sent = " + message + ", (" + duration + ")";
    }
}
