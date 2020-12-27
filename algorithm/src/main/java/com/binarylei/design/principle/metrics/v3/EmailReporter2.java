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

public class EmailReporter2 extends ScheduledReporter {
    private static final Long DAY_HOURS_IN_SECONDS = 86400L;

    private MetricsStorage metricsStorage;
    private Aggregator aggregator;
    private StatViewer viewer;

    public EmailReporter2(MetricsStorage metricsStorage, Aggregator aggregator, StatViewer viewer) {
        super(metricsStorage, aggregator, viewer);
    }


    public void startDailyReport() {
        Date firstTime = trimTimeFieldsToZeroOfNextDay();
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
     * 实际上，trimTimeFieldsToZeroOfNextDay 函数的可测试性仍然不好，因为它强依赖当前的系统时间。代码中有未买决行为逻辑
     * 解析方案：一般是将强依赖的部分通过参数传递进来，这有点类似我们之前讲的依赖注入。
     *
     * @return
     */
    @VisibleForTesting
    protected Date trimTimeFieldsToZeroOfNextDay() {
        Calendar calendar = Calendar.getInstance(); // 这里可以获取当前时间
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
