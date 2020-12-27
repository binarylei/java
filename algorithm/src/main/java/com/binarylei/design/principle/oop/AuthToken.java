package com.binarylei.design.principle.oop;

/**
 * @author binarylei
 * @version 2020-02-27
 */
public class AuthToken {
    private String token;
    private long timestamp;

    public AuthToken(String token, long timestamp) {
        this.token = token;
        this.timestamp = timestamp;
    }

    public static AuthToken generate(String originalUrl, String appId, String password, long timestamp) {
        return null;
    }

    public boolean isExpired() {
        return timestamp < System.currentTimeMillis() - 2000L;
    }

    public boolean match(AuthToken clientAuthToken) {
        return false;
    }
}
