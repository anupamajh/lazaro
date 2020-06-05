package com.carmel.guestjini.service.components;

import com.carmel.guestjini.service.request.Booking.BookingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class MailClient {
    private JavaMailSender mailSender;

    @Autowired
    UserMailContentBuilder userMailContentBuilder;

    @Autowired
    public MailClient(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setGuestSignUpEmail(BookingRequest bookingRequest) {
        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("no-reply@guesture.in");
            messageHelper.setTo(bookingRequest.getEmailAddress());
            messageHelper.setSubject("Guesture :: Please Activate Your Account");
            String content = userMailContentBuilder.buildGuestSignUpContent(bookingRequest);
            messageHelper.setText(content, true);
        };
        try {
            mailSender.send(mimeMessagePreparator);
        } catch (MailException e) {
            // runtime exception; compiler will not force you to handle it
        }
    }
}