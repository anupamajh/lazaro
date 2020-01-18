package com.carmel.common.helpers.component;

import com.carmel.common.helpers.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailContentBuilder {

    private TemplateEngine templateEngine;

    @Autowired
    public MailContentBuilder(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String build(String message, String templateName) {
        Context context = new Context();
        context.setVariable("message", message);
        return templateEngine.process(templateName, context);
    }

    public String buildWithUser(UserInfo user, String templateName) {
        Context context = new Context();
        context.setVariable("user", user);
        return templateEngine.process(templateName, context);
    }
}
