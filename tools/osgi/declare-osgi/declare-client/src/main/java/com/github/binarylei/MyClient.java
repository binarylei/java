package com.github.binarylei;

import java.util.Map;

public class MyClient {

    public void bind(Runnable service, Map<?, ?> properties) {
        System.out.println("start...");
        service.run();
    }

    public void unbind(Runnable service, Map<?, ?> properties) {
        System.out.println("end...");
        service.run();
    }
}
