package com.carmel.guestjini.service.components;

import com.carmel.guestjini.service.config.CarmelConfig;
import com.carmel.guestjini.service.model.Principal.UserInfo;
import com.carmel.guestjini.service.request.Booking.BookingRequest;
import com.carmel.guestjini.service.request.HelpDesk.TicketRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;

@Service
public class MailClient {
    private JavaMailSender mailSender;

    @Autowired
    UserMailContentBuilder userMailContentBuilder;

    @Autowired
    CarmelConfig carmelConfig;

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


    public void setTicketEmail(TicketRequest ticketRequest, UserInfo userInfo, String strInventory) {
        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("no-reply@guesture.in");
            messageHelper.setTo(InternetAddress.parse(carmelConfig.getTicketAgents()));
            messageHelper.setSubject("Guesture :: New Ticket");
            String content = userMailContentBuilder.buildTicketMailContent(ticketRequest, userInfo, strInventory);
            messageHelper.setText(content, true);
        };
        try {
            new Runnable(){
                @Override
                public void run() {
                    mailSender.send(mimeMessagePreparator);
                }
            }.run();

        } catch (MailException e) {
            String str = e.getMessage();
            // runtime exception; compiler will not force you to handle it
        }
    }
}