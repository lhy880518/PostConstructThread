package com.example.postconstructthread.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.concurrent.LinkedBlockingQueue;

@Configuration
@PropertySource("classpath:static/properties/threadInfo.properties")
public class AppConfig {

    @Bean(name="commonQueue")
    public LinkedBlockingQueue<String> commonQueue(){
        return new LinkedBlockingQueue<String>();
    }
}
