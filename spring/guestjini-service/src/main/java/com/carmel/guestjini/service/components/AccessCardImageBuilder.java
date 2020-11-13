package com.carmel.guestjini.service.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class AccessCardImageBuilder {
    private TemplateEngine templateEngine;

    @Autowired
    public  AccessCardImageBuilder(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String buildAccessCardImage(){
        Context context = new Context();
        return templateEngine.process(
                "accessCard/accesscard",
                context
        );
    }
}
