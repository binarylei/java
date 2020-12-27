package com.binarylei.design.bridge.alert;

import lombok.Data;

/**
 * @author binarylei
 * @version 2020-03-02
 */
@Data
public class ApiStatInfo {

    private long errorCount;
    private long maxErrorCount;
    private String api;

}
