package com.study.rabbitmq.step3.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String FANOUT_EXCHANGE_FOR_NEWS = "newsExchange";

    public static final String JAVA_QUEUE = "javaQueue";
    public static final String SPRING_QUEUE = "springQueue";
    public static final String VUE_QUEUE = "vueQueue";

    @Bean
    public Queue javaQueue(){
        return new Queue(JAVA_QUEUE, false);
    }

//    @Bean
//    public Queue springQueue(){
//        return new Queue(SPRING_QUEUE, false);
//    }
//
//    @Bean
//    public Queue vueQueue(){
//        return new Queue(VUE_QUEUE, false);
//    }

    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(FANOUT_EXCHANGE_FOR_NEWS);
    }

    @Bean
    public Binding javaBinding(Queue javaQueue, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(javaQueue).to(fanoutExchange);
    }

//    @Bean
//    public Binding springBinding(Queue springQueue, FanoutExchange fanoutExchange){
//        return BindingBuilder.bind(springQueue).to(fanoutExchange);
//    }
//
//    @Bean
//    public Binding vueBinding(Queue vueQueue, FanoutExchange fanoutExchange){
//        return BindingBuilder.bind(vueQueue).to(fanoutExchange);
//    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter jsonMessageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter);
        return rabbitTemplate;
    }
}
