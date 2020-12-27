package com.binarylei.design.principle.oop;

import lombok.Data;

/**
 * @author binarylei
 * @version 2020-02-27
 */
@Data
public class ApiRequest {
    private String appId;
    private String token;
    private long timestamp;
    private String originalUrl;

    public static ApiRequest buildFromUrl(String url) {
        return null;
    }
}
