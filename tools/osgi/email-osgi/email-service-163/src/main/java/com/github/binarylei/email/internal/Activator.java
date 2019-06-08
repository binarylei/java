package com.github.binarylei.email.internal;

import com.github.binarylei.email.api.EmailService;
import com.github.binarylei.email.service.EmailService163;
import com.github.binarylei.email.service.EmailServiceFactory;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

public class Activator implements BundleActivator {
    ServiceRegistration<EmailService> serviceRegistration;

    @Override
    public void start(BundleContext context) throws Exception {
        Dictionary properties = new Hashtable<>();
        properties.put("vendor", "163");
        serviceRegistration = context.registerService(EmailService.class.getName(), new EmailServiceFactory(), properties);

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                ;
            }
            Dictionary properties2 = new Hashtable<>();
            properties.put("vendor", "456");
            serviceRegistration.setProperties(properties2);
        }).start();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        serviceRegistration.unregister();
    }
}
