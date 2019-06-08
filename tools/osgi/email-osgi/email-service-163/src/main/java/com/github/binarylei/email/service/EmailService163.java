package com.github.binarylei.email.service;

import com.github.binarylei.email.api.EmailService;

public class EmailService163 implements EmailService {

    public void sendEmail(String dest, String title, String content) {
        System.out.println("163 email send. dest=" + dest + ",title=" + title + ",content=" + content);
    }
}
