package com.binarylei.design.principle.metrics.v3;

import com.binarylei.design.principle.metrics.MetricsStorage;
import com.binarylei.design.principle.metrics.support.RedisMetricsStorage;
import com.binarylei.design.principle.metrics.v2.Aggregator;
import com.binarylei.design.principle.metrics.v2.ConsoleViewer;
import com.binarylei.design.principle.metrics.v2.StatViewer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author binarylei
 * @version 2020-02-29
 */
public class ConsoleReporter extends ScheduledReporter {
    private ScheduledExecutorService executor;

    // 兼顾代码的易用性，新增一个封装了默认依赖的构造函数
    public ConsoleReporter() {
        this(new RedisMetricsStorage(), new Aggregator(), new ConsoleViewer());
    }

    // 兼顾灵活性和代码的可测试性，这个构造函数继续保留
    public ConsoleReporter(MetricsStorage metricsStorage, Aggregator aggregator, StatViewer viewer) {
        super(metricsStorage, aggregator, viewer);
        this.executor = Executors.newSingleThreadScheduledExecutor();
    }

}
