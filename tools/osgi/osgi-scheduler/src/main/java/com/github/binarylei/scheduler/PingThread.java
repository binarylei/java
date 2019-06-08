package com.github.binarylei.scheduler;

import org.osgi.service.component.annotations.Component;

/**
 * @author: leigang
 * @version: 2018-03-22
 */
@Component(immediate = true, property = {
        "scheduler.period:Long=60",
        "scheduler.concurrent:Boolean=false",
        "scheduler.name=PingJob"
}
)
public class PingThread implements Runnable {

    @Override
    public void run() {
        System.out.println("PingThread");
    }

}
