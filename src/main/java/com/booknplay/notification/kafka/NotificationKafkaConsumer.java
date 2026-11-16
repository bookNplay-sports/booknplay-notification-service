package com.booknplay.notification.kafka;

import com.booknplay.notification.dto.EmailNotificationRequest;
import com.booknplay.notification.service.EmailNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
@Component
@RequiredArgsConstructor
public class NotificationKafkaConsumer {
    private final EmailNotificationService emailNotificationService;
    @KafkaListener(
            topics = "notification-topic",
            groupId = "notification-group"
    )
    public void consumeNotification(EmailNotificationRequest request) {
        emailNotificationService.sendEmail(request);
    }
}
