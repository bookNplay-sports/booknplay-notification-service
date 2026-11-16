package com.booknplay.notification.controller;

import com.booknplay.notification.dto.EmailNotificationRequest;
import com.booknplay.notification.kafka.NotificationKafkaProducer;
import com.booknplay.notification.service.EmailNotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/notify")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationKafkaProducer kafkaProducer;

    @PostMapping("/email")
    public ResponseEntity<String> sendEmail(
            @Valid @RequestBody EmailNotificationRequest request) {
        kafkaProducer.sendNotification(request);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body("Email notification request accepted");
    }
}
