package com.binarylei.design.principle.metrics;

import java.util.List;
import java.util.Map;

/**
 * @author binarylei
 * @version 2020-02-29
 */
public interface MetricsStorage {
    Map<String, List<RequestInfo>> getRequestInfos(long startTimeInMillis, long endTimeInMillis);
}
