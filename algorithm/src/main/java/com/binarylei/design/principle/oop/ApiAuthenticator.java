package com.binarylei.design.principle.oop;

/**
 * @author binarylei
 * @version 2020-02-27
 */
public interface ApiAuthenticator {
    void auth(String url);

    void auth(ApiRequest apiRequest);
}
