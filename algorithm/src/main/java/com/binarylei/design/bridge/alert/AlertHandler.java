package com.binarylei.design.bridge.alert;

import com.binarylei.design.bridge.alert.v1.Notification;

/**
 * 在API监控告警的例子中，我们如下方式来使用Notification类：
 *
 * @author binarylei
 * @version 2020-03-02
 */
public abstract class AlertHandler {
    protected AlertRule rule;
    protected Notification notification;

    public AlertHandler(AlertRule rule, Notification notification) {
        this.rule = rule;
        this.notification = notification;
    }

    protected abstract void check(ApiStatInfo apiStatInfo);
}
