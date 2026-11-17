package com.booknplay.notification.template;

import org.springframework.stereotype.Component;
@Component
public class BookingCancelledTemplate {
    public String getEmailBody(String bookingDetails) {
        return """
               Hello,
               Your BooknPlay turf booking has been cancelled.
               Booking Details:
               %s
               If you have any questions, please contact us.
               Regards,
               BooknPlay Team
               """.formatted(bookingDetails);
    }
}
