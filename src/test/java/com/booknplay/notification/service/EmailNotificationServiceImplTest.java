package com.booknplay.notification.service;

import com.booknplay.notification.dto.EmailNotificationRequest;
import com.booknplay.notification.template.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmailNotificationServiceImplTest {

    @Mock
    private JavaMailSender javaMailSender;

    @Mock
    private RegistrationEmailTemplate registrationEmailTemplate;

    @Mock
    private PasswordResetTemplate passwordResetTemplate;

    @Mock
    private OnboardingStatusTemplate onboardingStatusTemplate;

    @Mock
    private BookingConfirmedTemplate bookingConfirmedTemplate;

    @Mock
    private BookingCancelledTemplate bookingCancelledTemplate;

    @Mock
    private BookingReminderTemplate bookingReminderTemplate;

    @InjectMocks
    private EmailNotificationServiceImpl emailNotificationService;

    @Test
    void sendEmail_shouldUseRegistrationTemplate() {
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
        when(registrationEmailTemplate.getEmailBody()).thenReturn("Welcome to BooknPlay!");

        emailNotificationService.sendEmail(request);

        ArgumentCaptor<SimpleMailMessage> messageCaptor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(javaMailSender).send(messageCaptor.capture());

        SimpleMailMessage sentMessage = messageCaptor.getValue();
        assertEquals("Welcome", sentMessage.getSubject());
        assertArrayEquals(new String[]{"user@example.com"}, sentMessage.getTo());
        assertEquals("Welcome to BooknPlay!", sentMessage.getText());
    }

    @Test
    void sendEmail_shouldUsePasswordResetTemplate() {
        EmailNotificationRequest request = new EmailNotificationRequest(
                "user@example.com",
                "Reset Password",
                "PASSWORD_RESET",
                "ABC123",
                null,
                null,
                null,
                null
        );
        when(passwordResetTemplate.getEmailBody("ABC123")).thenReturn("Reset code: ABC123");

        emailNotificationService.sendEmail(request);

        ArgumentCaptor<SimpleMailMessage> messageCaptor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(javaMailSender).send(messageCaptor.capture());

        assertEquals("Reset code: ABC123", messageCaptor.getValue().getText());
    }

    @Test
    void sendEmail_shouldUseOnboardingStatusTemplate() {
        EmailNotificationRequest request = new EmailNotificationRequest(
                "user@example.com",
                "Onboarding Update",
                "ONBOARDING_STATUS",
                null,
                "North Turf",
                "APPROVED",
                "Good quality turf",
                null
        );
        when(onboardingStatusTemplate.getEmailBody("North Turf", "APPROVED", "Good quality turf"))
                .thenReturn("Onboarding status updated");

        emailNotificationService.sendEmail(request);

        ArgumentCaptor<SimpleMailMessage> messageCaptor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(javaMailSender).send(messageCaptor.capture());

        assertEquals("Onboarding status updated", messageCaptor.getValue().getText());
    }

    @Test
    void sendEmail_shouldUseBookingConfirmedTemplate() {
        EmailNotificationRequest request = new EmailNotificationRequest(
                "user@example.com",
                "Booking Confirmed",
                "BOOKING_CONFIRMED",
                null,
                null,
                null,
                null,
                "2:00 PM - Badminton"
        );
        when(bookingConfirmedTemplate.getEmailBody("2:00 PM - Badminton"))
                .thenReturn("Booking confirmed details");

        emailNotificationService.sendEmail(request);

        ArgumentCaptor<SimpleMailMessage> messageCaptor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(javaMailSender).send(messageCaptor.capture());

        assertEquals("Booking confirmed details", messageCaptor.getValue().getText());
    }

    @Test
    void sendEmail_shouldUseBookingCancelledTemplate() {
        EmailNotificationRequest request = new EmailNotificationRequest(
                "user@example.com",
                "Booking Cancelled",
                "BOOKING_CANCELLED",
                null,
                null,
                null,
                null,
                "2:00 PM - Tennis"
        );
        when(bookingCancelledTemplate.getEmailBody("2:00 PM - Tennis"))
                .thenReturn("Booking cancelled details");

        emailNotificationService.sendEmail(request);

        ArgumentCaptor<SimpleMailMessage> messageCaptor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(javaMailSender).send(messageCaptor.capture());

        assertEquals("Booking cancelled details", messageCaptor.getValue().getText());
    }

    @Test
    void sendEmail_shouldUseBookingReminderTemplate() {
        EmailNotificationRequest request = new EmailNotificationRequest(
                "user@example.com",
                "Booking Reminder",
                "BOOKING_REMINDER",
                null,
                null,
                null,
                null,
                "Tomorrow 6:00 PM - Football"
        );
        when(bookingReminderTemplate.getEmailBody("Tomorrow 6:00 PM - Football"))
                .thenReturn("Booking reminder details");

        emailNotificationService.sendEmail(request);

        ArgumentCaptor<SimpleMailMessage> messageCaptor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(javaMailSender).send(messageCaptor.capture());

        assertEquals("Booking reminder details", messageCaptor.getValue().getText());
    }

    @Test
    void sendEmail_shouldHandleTemplateTypeCaseInsensitively() {
        EmailNotificationRequest request = new EmailNotificationRequest(
                "user@example.com",
                "Password Reset",
                "password_reset",
                "TOKEN-45",
                null,
                null,
                null,
                null
        );
        when(passwordResetTemplate.getEmailBody("TOKEN-45")).thenReturn("Token is TOKEN-45");

        emailNotificationService.sendEmail(request);

        ArgumentCaptor<SimpleMailMessage> messageCaptor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(javaMailSender).send(messageCaptor.capture());

        assertEquals("Token is TOKEN-45", messageCaptor.getValue().getText());
    }

    @Test
    void sendEmail_shouldUseDefaultMessageForUnknownTemplateType() {
        EmailNotificationRequest request = new EmailNotificationRequest(
                "user@example.com",
                "Unknown",
                "UNKNOWN_TEMPLATE",
                null,
                null,
                null,
                null,
                null
        );

        emailNotificationService.sendEmail(request);

        ArgumentCaptor<SimpleMailMessage> messageCaptor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(javaMailSender).send(messageCaptor.capture());

        assertEquals("Notification: UNKNOWN_TEMPLATE", messageCaptor.getValue().getText());
    }
}
