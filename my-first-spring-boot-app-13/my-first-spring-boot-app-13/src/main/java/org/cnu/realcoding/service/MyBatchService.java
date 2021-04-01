package org.cnu.realcoding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class MyBatchService {

    @Autowired
    AsyncService asyncService;
    int counter = 0;

    @Scheduled(fixedDelay = 1000L)
    public void count(){
//        log.info("count: {}", counter++);
        if(counter < 10){
            asyncService.increaseCounter();
        }
    }
}
