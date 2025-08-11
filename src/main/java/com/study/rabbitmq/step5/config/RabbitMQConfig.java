package com.study.rabbitmq.step5.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String DIRECT_EXCHANGE = "direct_exchange";

    public static final String ERROR_QUEUE = "errorQueue";
    public static final String WARN_QUEUE = "warnQueue";
    public static final String INFO_QUEUE = "infoQueue";

    @Bean
    public Queue errorQueue(){
        return new Queue(ERROR_QUEUE, false);
    }

    @Bean
    public Queue warnQueue(){
        return new Queue(WARN_QUEUE, false);
    }

    @Bean
    public Queue infoQueue(){
        return new Queue(INFO_QUEUE, false);
    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    @Bean
    public Binding errorBinding(Queue errorQueue, DirectExchange directExchange){
        return BindingBuilder.bind(errorQueue).to(directExchange).with("error");
    }

    @Bean
    public Binding warnBinding(Queue warnQueue, DirectExchange directExchange){
        return BindingBuilder.bind(warnQueue).to(directExchange).with("warn");
    }

    @Bean
    public Binding infoBinding(Queue infoQueue, DirectExchange directExchange){
        return BindingBuilder.bind(infoQueue).to(directExchange).with("info");
    }
}
