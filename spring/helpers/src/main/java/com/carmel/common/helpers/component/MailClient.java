package com.carmel.common.helpers.component;

import com.carmel.common.helpers.model.UserInfo;
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
    MailContentBuilder mailContentBuilder;

    @Autowired
    public MailClient(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendPasswordResetMail(String recipient, UserInfo user) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("no-reply@carmelsolutions.net");
            messageHelper.setTo(recipient);
            messageHelper.setSubject("Guesture :: Reset your password");
            String content = mailContentBuilder.buildWithUser(user , "mailTemplates/resetPassword");
            messageHelper.setText(content, true);
        };
        try {
            mailSender.send(messagePreparator);
        } catch (MailException e) {
            // runtime exception; compiler will not force you to handle it
        }
    }

    public void sendAppAccessRequestMail(String recipient, UserInfo userInfo) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("no-reply@carmelsolutions.net");
            messageHelper.setTo(recipient);
            messageHelper.setSubject("Guesture :: App access request");
            String content = mailContentBuilder.buildWithUser(userInfo , "mailTemplates/appAccessRequest");
            messageHelper.setText(content, true);
        };
        try {
            mailSender.send(messagePreparator);
        } catch (MailException e) {
            // runtime exception; compiler will not force you to handle it
        }
    }
}
