package com.github.binarylei.email.client;

import com.github.binarylei.email.api.EmailService;

public class EmailClient {

    private EmailService emailService;

    public EmailService getEmailService() {
        return emailService;
    }

    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void init() {
        emailService.sendEmail("binarylei@qq.com", "blueprint", "成功了");
    }
}
