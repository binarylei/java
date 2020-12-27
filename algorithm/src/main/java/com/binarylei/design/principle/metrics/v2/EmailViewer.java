package com.binarylei.design.principle.metrics.v2;

import com.binarylei.design.principle.metrics.support.EmailSender;
import com.binarylei.design.principle.metrics.RequestStat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author binarylei
 * @version 2020-02-29
 */
public class EmailViewer implements StatViewer {
    private EmailSender emailSender;
    private List<String> toAddresses = new ArrayList<>();

    public EmailViewer(List<String> toAddresses) {
        this.toAddresses = toAddresses;
    }

    public EmailViewer() {
        this.emailSender = new EmailSender(/*省略参数*/);
    }

    public EmailViewer(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void addToAddress(String address) {
        toAddresses.add(address);
    }

    public void output(
            Map<String, RequestStat> requestStats, long startTimeInMillis, long endTimeInMills) {
        // format the requestStats to HTML style.
        // send it to email toAddresses.
    }
}
