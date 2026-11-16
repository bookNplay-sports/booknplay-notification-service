package com.booknplay.notification.controller;

import com.booknplay.notification.dto.EmailNotificationRequest;
import com.booknplay.notification.kafka.NotificationKafkaProducer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/notify")
@RequiredArgsConstructor
@Tag(
        name = "Notification Management",
        description = "APIs for notification management and email notifications"
)
public class NotificationController {

    private final NotificationKafkaProducer kafkaProducer;

    @PostMapping("/email")
    @Operation(
            summary = "Send email notification",
            description = "Allows a user to send an email notification through the notification service."
    )
    public ResponseEntity<String> sendEmail(
            @Valid @RequestBody EmailNotificationRequest request) {
        kafkaProducer.sendNotification(request);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body("Email notification request accepted");
    }
}
