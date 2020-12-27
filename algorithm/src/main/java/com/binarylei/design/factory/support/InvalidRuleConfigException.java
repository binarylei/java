package com.binarylei.design.factory.support;

/**
 * @author binarylei
 * @version 2020-02-29
 */
public class InvalidRuleConfigException extends RuntimeException {
    public InvalidRuleConfigException() {
        super();
    }

    public InvalidRuleConfigException(String message) {
        super(message);
    }

    public InvalidRuleConfigException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidRuleConfigException(Throwable cause) {
        super(cause);
    }

    protected InvalidRuleConfigException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
