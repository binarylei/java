package com.binarylei.design.principle.metrics.v2;

import com.binarylei.design.principle.metrics.RequestStat;
import com.google.gson.Gson;

import java.util.Map;

/**
 * @author binarylei
 * @version 2020-02-29
 */
public class ConsoleViewer implements StatViewer {
    public void output(
            Map<String, RequestStat> requestStats, long startTimeInMillis, long endTimeInMills) {
        System.out.println("Time Span: [" + startTimeInMillis + ", " + endTimeInMills + "]");
        Gson gson = new Gson();
        System.out.println(gson.toJson(requestStats));
    }
}
