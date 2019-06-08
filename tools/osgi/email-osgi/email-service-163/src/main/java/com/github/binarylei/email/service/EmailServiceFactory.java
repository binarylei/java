package com.github.binarylei.email.service;

import com.github.binarylei.email.api.EmailService;
import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceRegistration;

public class EmailServiceFactory implements ServiceFactory<EmailService> {

    @Override
    public EmailService getService(Bundle bundle, ServiceRegistration<EmailService> registration) {
        return new EmailService163();
    }

    @Override
    public void ungetService(Bundle bundle, ServiceRegistration<EmailService> registration, EmailService service) {
    }
}
