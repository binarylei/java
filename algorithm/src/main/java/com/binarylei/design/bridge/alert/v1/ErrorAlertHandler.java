package com.binarylei.design.bridge.alert.v1;

import com.binarylei.design.bridge.alert.AlertHandler;
import com.binarylei.design.bridge.alert.AlertRule;
import com.binarylei.design.bridge.alert.ApiStatInfo;

/**
 * 在API监控告警的例子中，我们如下方式来使用Notification类：
 *
 * @author binarylei
 * @version 2020-03-02
 */
public class ErrorAlertHandler extends AlertHandler {
    public ErrorAlertHandler(AlertRule rule, Notification notification) {
        super(rule, notification);
    }

    @Override
    public void check(ApiStatInfo apiStatInfo) {
        if (apiStatInfo.getErrorCount() > rule.getMatchedRule(apiStatInfo.getApi()).getMaxErrorCount()) {
            notification.notify(NotificationEmergencyLevel.SEVERE, "...");
        }
    }
}
