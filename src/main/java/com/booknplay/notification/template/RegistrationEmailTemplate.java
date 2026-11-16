package com.booknplay.notification.template;

import org.springframework.stereotype.Component;

@Component
public class RegistrationEmailTemplate {
    public String getEmailBody() {
        return """
               Hello,
               Welcome to BooknPlay!
               Your registration has been completed successfully.
               You can now explore sports turfs and book your
               favourite playing slots.
               Thank you for joining BooknPlay!
               Regards,
               BooknPlay Team
               """;
    }
}
