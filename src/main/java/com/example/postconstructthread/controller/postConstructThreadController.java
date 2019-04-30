package com.example.postconstructthread.controller;

import com.example.postconstructthread.service.postConstructThreadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class postConstructThreadController {

    private postConstructThreadService postConstructThreadService;

    @Autowired
    public postConstructThreadController(postConstructThreadService postConstructThreadService){
        this.postConstructThreadService = postConstructThreadService;
    }

    @PostConstruct
    public void init(){
        postConstructThreadService.init();
        postConstructThreadService.run();
    }
}
