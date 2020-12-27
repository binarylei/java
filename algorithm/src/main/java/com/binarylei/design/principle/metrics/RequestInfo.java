package com.binarylei.design.principle.metrics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author binarylei
 * @version 2020-02-29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestInfo {

    private String apiName;
    private double responseTime;
    private long timestamp;

}
