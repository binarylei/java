package com.github.binarylei.scheduler;

import org.apache.karaf.scheduler.Job;
import org.apache.karaf.scheduler.JobContext;
import org.apache.karaf.scheduler.Scheduler;
import org.apache.karaf.scheduler.SchedulerError;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author: leigang
 * @version: 2018-03-22
 */

@Component
public class Demo {

    /*@Reference
    Scheduler scheduler;

    public void useScheduler() throws SchedulerError {
        scheduler.schedule(new MyJob(), scheduler.EXPR("0/2 * * * * ?"));
    }

    class MyJob implements Job {
        @Override
        public void execute(JobContext jobContext) {
            System.out.println("Demo");
        }
    }*/

}