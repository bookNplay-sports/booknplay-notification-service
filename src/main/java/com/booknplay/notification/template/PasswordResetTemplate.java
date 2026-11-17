package com.booknplay.notification.template;

import org.springframework.stereotype.Component;
@Component
public class PasswordResetTemplate {
    public String getEmailBody(String resetToken) {
        return """
               Hello,
               We received a request to reset your BooknPlay password.
               Your temporary reset token is: %s
               Please use this token to reset your password.
               If you did not request a password reset, please ignore this email.
               Regards,
               BooknPlay Team
               """.formatted(resetToken);
    }
}
