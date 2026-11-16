package com.booknplay.notification.service;

import com.booknplay.notification.dto.EmailNotificationRequest;

public interface EmailNotificationService {

    void sendEmail(EmailNotificationRequest request);
}
