package com.booknplay.notification.template;

import org.springframework.stereotype.Component;
@Component
public class ProfileUpdateTemplate {
    public String getEmailBody(String name) {
        return """
               Hello ,
               Your BooknPlay profile has been
               successfully updated.
               If you did not make this change,
               please contact our support team.
               Regards,
               BooknPlay Team
               """.formatted(name);
    }
}
