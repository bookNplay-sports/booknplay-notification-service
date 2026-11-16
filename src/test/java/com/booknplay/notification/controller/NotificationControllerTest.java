package com.booknplay.notification.controller;

import com.booknplay.notification.dto.EmailNotificationRequest;
import com.booknplay.notification.kafka.NotificationKafkaProducer;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class NotificationControllerTest {

    private final NotificationKafkaProducer kafkaProducer = mock(NotificationKafkaProducer.class);
    private final NotificationController controller = new NotificationController(kafkaProducer);
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void sendEmail_shouldAcceptValidRequest() {
        EmailNotificationRequest request = new EmailNotificationRequest(
                "user@example.com",
                "Welcome",
                "REGISTRATION",
                null,
                null,
                null,
                null,
                null
        );

        ResponseEntity<String> response = controller.sendEmail(request);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals("Email notification request accepted", response.getBody());
        verify(kafkaProducer).sendNotification(request);
    }

    @Test
    void sendEmail_shouldRejectInvalidEmailAddress() {
        EmailNotificationRequest request = new EmailNotificationRequest(
                "not-an-email",
                "Welcome",
                "REGISTRATION",
                null,
                null,
                null,
                null,
                null
        );

        assertFalse(validator.validate(request).isEmpty());
    }

    @Test
    void sendEmail_shouldRejectBlankSubject() {
        EmailNotificationRequest request = new EmailNotificationRequest(
                "user@example.com",
                " ",
                "REGISTRATION",
                null,
                null,
                null,
                null,
                null
        );

        assertFalse(validator.validate(request).isEmpty());
    }

    @Test
    void sendEmail_shouldRejectBlankTemplateType() {
        EmailNotificationRequest request = new EmailNotificationRequest(
                "user@example.com",
                "Welcome",
                " ",
                null,
                null,
                null,
                null,
                null
        );

        assertFalse(validator.validate(request).isEmpty());
    }
}
