package com.binarylei.design.principle.metrics.v3;

import com.binarylei.design.principle.metrics.MetricsStorage;
import com.binarylei.design.principle.metrics.v2.Aggregator;
import com.binarylei.design.principle.metrics.v2.StatViewer;
import com.google.common.annotations.VisibleForTesting;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author binarylei
 * @version 2020-02-29
 */

public class EmailReporter3 extends ScheduledReporter {
    private static final Long DAY_HOURS_IN_SECONDS = 86400L;

    private MetricsStorage metricsStorage;
    private Aggregator aggregator;
    private StatViewer viewer;

    public EmailReporter3(MetricsStorage metricsStorage, Aggregator aggregator, StatViewer viewer) {
        super(metricsStorage, aggregator, viewer);
    }


    /**
     * 事实上，我认为 startDailyReport 可以不用测试了，因为代码已经足够简单，BUG 无处隐藏
     */
    public void startDailyReport() {
        Date firstTime = trimTimeFieldsToZeroOfNextDay(new Date());
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                long durationInMillis = DAY_HOURS_IN_SECONDS * 1000;
                long endTimeInMillis = firstTime.getTime();
                long startTimeInMillis = endTimeInMillis - durationInMillis;
                doStatAndReport(startTimeInMillis, endTimeInMillis);
            }
        }, firstTime, DAY_HOURS_IN_SECONDS * 1000);
    }

    // 设置成 protected 而非 private 是为了方便写单元测试

    /**
     * 将系统时间当作参数传递，解决函数的强依赖问题
     *
     * @param date 系统当前时间，解耦强制依赖关系
     * @return 下一天的时间
     */
    @VisibleForTesting
    protected Date trimTimeFieldsToZeroOfNextDay(Date date) {
        Calendar calendar = Calendar.getInstance(); // 这里可以获取当前时间
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
