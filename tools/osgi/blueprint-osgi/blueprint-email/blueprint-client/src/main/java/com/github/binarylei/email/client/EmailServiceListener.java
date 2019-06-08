package com.github.binarylei.email.client;

import com.github.binarylei.email.api.EmailService;
import org.osgi.framework.ServiceReference;

import java.util.Map;

public class EmailServiceListener {

    public void register(EmailService emailService, Map<?, ?> properties) {
        System.out.println("======register=====");
        System.out.println(properties);
        emailService.sendEmail("binarylei@qq.com", "blueprint", "成功了");
    }

    /*public void register(EmailService emailService) {
    }*/

    /*public void register(ServiceReference reference) {
    }*/

    public void unregister(EmailService emailService, Map<?, ?> properties) {
        System.out.println("======unregister=====");
        System.out.println(properties);
        emailService.sendEmail("binarylei@qq.com", "blueprint", "成功了");
    }
}
