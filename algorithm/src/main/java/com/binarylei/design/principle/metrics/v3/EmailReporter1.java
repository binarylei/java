package com.binarylei.design.principle.metrics.v3;

import com.binarylei.design.principle.metrics.MetricsStorage;
import com.binarylei.design.principle.metrics.v2.Aggregator;
import com.binarylei.design.principle.metrics.v2.StatViewer;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * EmailReporter 可测性提升
 *
 * @author binarylei
 * @version 2020-02-29
 */

public class EmailReporter1 extends ScheduledReporter {
    private static final Long DAY_HOURS_IN_SECONDS = 86400L;

    private MetricsStorage metricsStorage;
    private Aggregator aggregator;
    private StatViewer viewer;

    public EmailReporter1(MetricsStorage metricsStorage, Aggregator aggregator, StatViewer viewer) {
        super(metricsStorage, aggregator, viewer);
    }

    /**
     * startDailyReport 可测试性不好，一是依赖系统时间；二是多线程
     * 解决方案：1. 简化 startDailyReport 函数，当代码足够简单时，一般就不用测试多线程。
     *          2. 系统时间当参数进行传递。
     */
    public void startDailyReport() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date firstTime = calendar.getTime();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                long durationInMillis = DAY_HOURS_IN_SECONDS * 1000;
                long endTimeInMillis = System.currentTimeMillis();
                long startTimeInMillis = endTimeInMillis - durationInMillis;
                doStatAndReport(startTimeInMillis, endTimeInMillis);
            }
        }, firstTime, DAY_HOURS_IN_SECONDS * 1000);
    }
}
