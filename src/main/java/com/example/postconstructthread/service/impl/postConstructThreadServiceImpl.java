package com.example.postconstructthread.service.impl;

import com.example.postconstructthread.scheduler.SafeTurnOff;
import com.example.postconstructthread.service.postConstructThreadService;
import com.example.postconstructthread.thread.InsertQueueThread;
import com.example.postconstructthread.thread.UseQueueThread;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
@Service
public class postConstructThreadServiceImpl implements postConstructThreadService {

    private ArrayList<Thread> insertQueueThreadPool;
    private ArrayList<Thread> useQueueThreadPool;

    private LinkedBlockingQueue<String> commonQueue;

    @Autowired
    public postConstructThreadServiceImpl(LinkedBlockingQueue<String> commonQueue){
        this.insertQueueThreadPool = new ArrayList<Thread>();
        this.useQueueThreadPool = new ArrayList<Thread>();
        this.commonQueue = commonQueue;
    }

    @Override
    public void init() {
        Thread safeTurnOff = new Thread(new SafeTurnOff());
        safeTurnOff.setDaemon(true);
        safeTurnOff.start();

        Thread InsertQueueThread = new Thread(new InsertQueueThread(commonQueue));
        InsertQueueThread.setDaemon(true);
        insertQueueThreadPool.add(InsertQueueThread);

        Thread UseQueueThread = new Thread(new UseQueueThread(commonQueue));
        UseQueueThread.setDaemon(true);
        useQueueThreadPool.add(UseQueueThread);

    }

    @Override
    public void run() {
        insertQueueThreadPool.get(0).start();
        useQueueThreadPool.get(0).start();
        log.info("run");
    }
}
