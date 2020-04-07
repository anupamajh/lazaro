package com.carmel.guestjini.payments.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.TreeMap;

@Service
public class MailContentBuilder {

    private TemplateEngine templateEngine;

    @Autowired
    public MailContentBuilder(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }



    public String buildWithParameters(TreeMap<String, String> parameters) {
        Context context = new Context();
        context.setVariable("parameters", parameters);
        return templateEngine.process("pgresponse-mail", context);
    }
}
