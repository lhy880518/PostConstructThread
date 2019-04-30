package com.example.postconstructthread.thread;

import com.example.postconstructthread.scheduler.SafeTurnOff;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
public class InsertQueueThread implements Runnable {

    private LinkedBlockingQueue<String> commonQueue;

    public InsertQueueThread(LinkedBlockingQueue<String> commonQueue) {
        this.commonQueue = commonQueue;
    }

    @Override
    public void run() {
        while (SafeTurnOff.readyToWork){
            try{
                commonQueue.offer(String.valueOf(System.currentTimeMillis()));
                log.info("InsertQueueThread = {}",commonQueue);
                Thread.sleep(SafeTurnOff.THREAD_INSERT_TERM_MILLISECONDS);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        log.warn("============================");
        log.warn("==InsertQueueThread is End==");
        log.warn("============================");
    }
}
