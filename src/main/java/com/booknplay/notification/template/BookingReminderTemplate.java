package com.booknplay.notification.template;

import org.springframework.stereotype.Component;
@Component
public class BookingReminderTemplate {
    public String getEmailBody(String bookingDetails) {
        return """
               Hello,
               This is a reminder for your upcoming BooknPlay turf booking.
               Booking Details:
               %s
               Please arrive on time and enjoy your game!
               Regards,
               BooknPlay Team
               """.formatted(bookingDetails);
    }
}