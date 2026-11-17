package com.booknplay.notification.kafka;

import com.booknplay.notification.dto.EmailNotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
@Component
@RequiredArgsConstructor
public class NotificationKafkaProducer {
    private final KafkaTemplate<String, EmailNotificationRequest> kafkaTemplate;
    private static final String TOPIC = "notification-topic";
    public void sendNotification(EmailNotificationRequest request) {
        kafkaTemplate.send(TOPIC, request.getTo(), request);
    }
}