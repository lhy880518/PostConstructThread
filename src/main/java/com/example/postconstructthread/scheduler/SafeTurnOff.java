package com.example.postconstructthread.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

@Slf4j
public class SafeTurnOff implements Runnable{

    public static boolean readyToWork = true;

    public static final int THREAD_TERM_MILLISECONDS = 5000;
    public static final int THREAD_INSERT_TERM_MILLISECONDS = 1000;

    @Override
    public void run() {
        while (true){
            try {
                ClassPathResource classPathResource = new ClassPathResource("static/properties/threadInfo.properties");
                File file = classPathResource.getFile();
                Scanner scanner = new Scanner(file);
                while (scanner.hasNext()){
                    String line = scanner.nextLine();
                    if(line.contains("ready.to.work")){
                        if(line.contains("false")){
                            this.readyToWork = false;
                        }else{
                            this.readyToWork = true;
                        }
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try{
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
