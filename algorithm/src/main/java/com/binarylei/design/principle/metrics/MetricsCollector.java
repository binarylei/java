package com.binarylei.design.principle.metrics;

/**
 * @author binarylei
 * @version 2020-02-29
 */
public class MetricsCollector {

    private MetricsStorage metricsStorage;

    public MetricsCollector(MetricsStorage metricsStorage) {
        this.metricsStorage = metricsStorage;
    }

    public void recordRequest(RequestInfo requestInfo) {

    }
}
