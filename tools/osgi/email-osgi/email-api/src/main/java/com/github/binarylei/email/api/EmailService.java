package com.github.binarylei.email.api;

public interface EmailService {
    void sendEmail(String to, String title, String content);
}
