package org.cnu.realcoding.service;

import jdk.jfr.Enabled;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
@Enabled
public class AsyncService {
    AtomicLong counter = new AtomicLong();

    @Async
    public void increaseCounter(){
        for(int i=0; i<1000000; i++){
            long count = counter.incrementAndGet();
//            Process log;
//            log.info("count: {}", count);
        }
    }
}
