package com.binarylei.design.bridge.alert.v2;

import java.util.List;

/**
 * 桥接模式：MsgSender
 *
 * @author binarylei
 * @version 2020-03-02
 */

public interface MsgSender {
    void send(String message);
}

class TelephoneMsgSender implements MsgSender {
    private List<String> telephones;

    public TelephoneMsgSender(List<String> telephones) {
        this.telephones = telephones;
    }

    @Override
    public void send(String message) {
        //...
    }

}

class EmailMsgSender implements MsgSender {
    @Override
    public void send(String message) {

    }
}

class WechatMsgSender implements MsgSender {
    @Override
    public void send(String message) {

    }
}

