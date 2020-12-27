package com.binarylei.design.principle.metrics.support;

import com.binarylei.design.principle.metrics.MetricsStorage;
import com.binarylei.design.principle.metrics.RequestInfo;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author binarylei
 * @version 2020-02-29
 */
public class RedisMetricsStorage implements MetricsStorage {

    public Map<String, List<RequestInfo>> getRequestInfos(long startTimeInMillis, long endTimeInMillis) {
        return Collections.emptyMap();
    }
}
