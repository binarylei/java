package com.binarylei.design.principle.metrics.v3;

import com.binarylei.design.principle.metrics.MetricsStorage;
import com.binarylei.design.principle.metrics.support.RedisMetricsStorage;
import com.binarylei.design.principle.metrics.v2.Aggregator;
import com.binarylei.design.principle.metrics.v2.EmailViewer;
import com.binarylei.design.principle.metrics.v2.StatViewer;

import java.util.List;

/**
 * 最终版本 EmailReporter3
 *
 * @author binarylei
 * @version 2020-02-29
 */
public class EmailReporter extends EmailReporter3 {
    private static final Long DAY_HOURS_IN_SECONDS = 86400L;

    // 兼顾代码的易用性，新增一个封装了默认依赖的构造函数
    public EmailReporter(List<String> emailToAddresses) {
        this(new RedisMetricsStorage(), new Aggregator(), new EmailViewer(emailToAddresses));
    }

    // 兼顾灵活性和代码的可测试性，这个构造函数继续保留
    public EmailReporter(MetricsStorage metricsStorage, Aggregator aggregator, StatViewer viewer) {
        super(metricsStorage, aggregator, viewer);
    }
    // 省略其他代码...
}
