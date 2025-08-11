package com.study.rabbitmq.step5.log;

import com.study.rabbitmq.step5.exception.CustomExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/logs")
@RequiredArgsConstructor
public class LogController {

    private final CustomExceptionHandler exceptionHandler;

    @GetMapping("error")
    public ResponseEntity<String> errorAPI(){
        try{
            String value = null;
            value.getBytes();
        }catch (Exception e){
            exceptionHandler.handleException(e);
        }

        return ResponseEntity.ok("Controller NullPointer Exception 처리 ");
    }

    @GetMapping("/warn")
    public ResponseEntity<String> warnAPI(){
        try{
            throw new IllegalArgumentException("invalid argument");
        }catch (Exception e){
            exceptionHandler.handleException(e);
        }

        return ResponseEntity.ok("Controller IllegalArgument Exception 처리 ");
    }

    @PostMapping("/info")
    public ResponseEntity<String> infoAPI(@RequestBody String message){
        exceptionHandler.handleMessage(message);

        return ResponseEntity.ok("Controller Info log 발송 처리");
    }
}
