package com.github.binarylei.email.internal;

import com.github.binarylei.email.api.EmailService;
import org.osgi.framework.*;

public class Activator implements BundleActivator {
    @Override
    public void start(BundleContext context) throws Exception {
        //1. 获取163的服务
        ServiceReference<?>[] refs = context.getServiceReferences(EmailService.class.getName(), "(vendor=163)");
        if (refs != null) {
            for (ServiceReference ref : refs) {
                EmailService emailService = (EmailService) context.getService(ref);
                System.out.println(emailService);
            }
        }

        context.addServiceListener(new ServiceListener() {
            @Override
            public void serviceChanged(ServiceEvent event) {
                System.out.println(event.getSource() + " ==> " + event.getType());
            }
        }, "(vendor=163)");
    }

    @Override
    public void stop(BundleContext context) throws Exception {

    }
}
