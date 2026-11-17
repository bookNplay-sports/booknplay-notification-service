package com.booknplay.notification.service;

import com.booknplay.notification.dto.EmailNotificationRequest;
import com.booknplay.notification.template.*;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailNotificationServiceImpl implements EmailNotificationService {

    private final JavaMailSender javaMailSender;
    private final RegistrationEmailTemplate registrationEmailTemplate;
    private final PasswordResetTemplate passwordResetTemplate;
    private final OnboardingStatusTemplate onboardingStatusTemplate;
    private final BookingConfirmedTemplate bookingConfirmedTemplate;
    private final BookingCancelledTemplate bookingCancelledTemplate;
    private final BookingReminderTemplate bookingReminderTemplate;
    private final ProfileUpdateTemplate profileUpdateTemplate;

    @Override
    @Async
    public void sendEmail(EmailNotificationRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.getTo());
        message.setSubject(request.getSubject());
        if ("REGISTRATION".equalsIgnoreCase(request.getTemplateType())) {
            message.setText(registrationEmailTemplate.getEmailBody());
        } else if ("PASSWORD_RESET".equalsIgnoreCase(request.getTemplateType())) {
            message.setText(
                    passwordResetTemplate.getEmailBody(request.getResetToken())
            );
        } else if ("ONBOARDING_STATUS".equalsIgnoreCase(request.getTemplateType())) {
            message.setText(onboardingStatusTemplate.getEmailBody(
                    request.getTurfName(),
                    request.getStatus(),
                    request.getAdminRemark()
            ));
        } else if ("BOOKING_CONFIRMED".equalsIgnoreCase(request.getTemplateType())) {
            message.setText(bookingConfirmedTemplate.getEmailBody(
                    request.getBookingDetails()
            ));
        }else if ("BOOKING_CANCELLED".equalsIgnoreCase(request.getTemplateType())) {
            message.setText(bookingCancelledTemplate.getEmailBody(
                    request.getBookingDetails()

            ));

        } else if ("BOOKING_REMINDER".equalsIgnoreCase(request.getTemplateType())) {
            message.setText(bookingReminderTemplate.getEmailBody(
                    request.getBookingDetails()
            ));
        }
        else if ("PROFILE_UPDATE".equalsIgnoreCase(request.getTemplateType())) {
            message.setText(profileUpdateTemplate.getEmailBody(
                    request.getBookingDetails()
            ));
        }
        else {
            message.setText("Notification: " + request.getTemplateType());
        }
        javaMailSender.send(message);
    }
}
