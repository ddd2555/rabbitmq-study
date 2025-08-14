package com.study.rabbitmq.step3.news;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageDto {

    private String newsType;
    private String content;

}
