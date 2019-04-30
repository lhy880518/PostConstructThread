package com.example.postconstructthread.thread;

import com.example.postconstructthread.scheduler.SafeTurnOff;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
public class UseQueueThread implements Runnable {

    private LinkedBlockingQueue<String> commonQueue;

    public UseQueueThread(LinkedBlockingQueue<String> commonQueue) {
        this.commonQueue = commonQueue;
    }

    @Override
    public void run() {
        while (SafeTurnOff.readyToWork){
            try{
                log.info("UseQueueThread = {}",commonQueue.poll());
                Thread.sleep(SafeTurnOff.THREAD_TERM_MILLISECONDS);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        log.warn("============================");
        log.warn("==   UseQueueThread is End==");
        log.warn("============================");
    }
}
