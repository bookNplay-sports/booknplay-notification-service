package com.booknplay.notification.template;

import org.springframework.stereotype.Component;
@Component
public class OnboardingStatusTemplate {
    public String getEmailBody(String turfName, String status, String adminRemark) {
        return """
               Hello,
               Your turf onboarding request has been updated.
               Turf Name: %s
               Status: %s
               Admin Remark: %s
               Thank you for choosing BooknPlay!
               Regards,
               BooknPlay Team
               """.formatted(turfName, status, adminRemark);
    }
}
