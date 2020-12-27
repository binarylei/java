package com.binarylei.design.principle.metrics.v2;

import com.binarylei.design.principle.metrics.RequestStat;

import java.util.Map;

/**
 * @author binarylei
 * @version 2020-02-29
 */

public interface StatViewer {
    void output(Map<String, RequestStat> requestStats, long startTimeInMillis, long endTimeInMills);
}

