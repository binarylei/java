package com.github.binarylei.scheduler;

import org.apache.karaf.scheduler.Job;
import org.apache.karaf.scheduler.JobContext;
import org.apache.karaf.scheduler.Scheduler;
import org.osgi.service.component.annotations.Component;

/**
 * @author: leigang
 * @version: 2018-03-22
 */
@Component(immediate = true, property = {
        Scheduler.PROPERTY_SCHEDULER_EXPRESSION + "=0/2 * * * * ?",
} )
public class SchedulerPing implements Job {

    @Override
    public void execute(JobContext context) {
        context.getConfiguration();
        System.out.println("---------------");
    }
}