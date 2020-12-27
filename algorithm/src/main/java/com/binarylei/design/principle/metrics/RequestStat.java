package com.binarylei.design.principle.metrics;

import lombok.Data;

/**
 * @author binarylei
 * @version 2020-02-29
 */
@Data
public class RequestStat {

    private double maxResponseTime;
    private double minResponseTime;
    private double avgResponseTime;
    private double p999ResponseTime;
    private double p99ResponseTime;
    private long count;
    private long tps;
}
