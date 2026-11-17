package com.booknplay.notification.template;

import org.springframework.stereotype.Component;
@Component
public class BookingConfirmedTemplate {
    public String getEmailBody(String bookingDetails) {
        return """
               Hello,
               Your BooknPlay turf booking has been confirmed successfully!
               Booking Details:
               %s
               Thank you for choosing BooknPlay!
               Regards,
               BooknPlay Team
               """.formatted(bookingDetails);
    }
}
