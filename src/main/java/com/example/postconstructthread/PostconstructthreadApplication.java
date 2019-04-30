package com.example.postconstructthread;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class PostconstructthreadApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(PostconstructthreadApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(PostconstructthreadApplication.class, args);
    }

}
