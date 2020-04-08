package com.carmel.guestjini.payments.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.util.TreeMap;

@Service
public class MailClient {
    private JavaMailSender mailSender;

    @Autowired
    MailContentBuilder mailContentBuilder;

    @Autowired
    private Environment env;


    @Autowired
    public MailClient(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendPaymentResponse(String recipient, TreeMap<String, String> paramaters) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("no-reply@carmelsolutions.net");
            messageHelper.setTo(recipient);
            messageHelper.setCc(env.getProperty("carmel.support-email"));
            messageHelper.setSubject("Guesture :: Payment reciept");
            String content = mailContentBuilder.buildWithParameters(paramaters);
            messageHelper.setText(content, true);
        };
        try {
            mailSender.send(messagePreparator);
        } catch (MailException e) {
            // runtime exception; compiler will not force you to handle it
        }
    }
}
