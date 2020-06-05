package com.carmel.guestjini.service.components;


import com.carmel.guestjini.service.request.Booking.BookingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class UserMailContentBuilder {
    private TemplateEngine templateEngine;

    @Autowired
    public  UserMailContentBuilder(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String buildGuestSignUpContent(BookingRequest bookingRequest){
        Context context = new Context();
        context.setVariable("bookingRequest", bookingRequest);
        return templateEngine.process(
                "mailTemplates/user/guest-sign-up-mail",
                context
        );
    }
}