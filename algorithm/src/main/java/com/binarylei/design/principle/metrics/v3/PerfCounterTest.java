package com.binarylei.design.principle.metrics.v3;

import com.binarylei.design.principle.metrics.RequestInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author binarylei
 * @version 2020-02-29
 */

public class PerfCounterTest {
    public static void main(String[] args) {
        List<String> emailToAddresses = new ArrayList<>();
        emailToAddresses.add("binarylei@qq.com");
        EmailReporter emailReporter = new EmailReporter(emailToAddresses);
        emailReporter.startDailyReport();

        MetricsCollector collector = new MetricsCollector();
        collector.recordRequest(new RequestInfo("register", 123, 10234));
        collector.recordRequest(new RequestInfo("register", 223, 11234));
        collector.recordRequest(new RequestInfo("register", 323, 12334));
        collector.recordRequest(new RequestInfo("login", 23, 12434));
        collector.recordRequest(new RequestInfo("login", 1223, 14234));

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
