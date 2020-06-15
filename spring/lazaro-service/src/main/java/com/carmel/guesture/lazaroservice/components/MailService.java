package com.carmel.guesture.lazaroservice.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Service
public class MailService {
    @Autowired
    private JavaMailSender sender;

    @Autowired
    YAMLConfig yamlConfig;

    @Autowired
    private TemplateEngine templateEngine;

    private static final Logger logger = LoggerFactory.getLogger(MailService.class);

    public MailService() {
    }

    public static int noOfQuickServiceThreads = 20;

    /**
     * this statement create a thread pool of twenty threads
     * here we are assigning send mail task using ScheduledExecutorService.submit();
     */
    private ScheduledExecutorService quickService = Executors.newScheduledThreadPool(noOfQuickServiceThreads);

    public void sendASynchronousMail(String subject, String text) throws MailException, RuntimeException {
        logger.debug("inside sendASynchronousMail method");
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("no-reply@carmelsolutions.net");
            messageHelper.setTo(yamlConfig.getLeadMailTo().split(","));
            messageHelper.setSubject(subject);
            String content = text;
            messageHelper.setText(content, true);
        };
        quickService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    sender.send(messagePreparator);
                } catch (Exception e) {
                    logger.error("Exception occur while send a mail : ", e);
                }
            }
        });
    }
}
