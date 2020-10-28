package com.carmel.guestjini.service.components;


import com.carmel.guestjini.service.model.Principal.UserInfo;
import com.carmel.guestjini.service.request.Booking.BookingRequest;
import com.carmel.guestjini.service.request.HelpDesk.TicketRequest;
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

    public String buildTicketMailContent(TicketRequest ticketRequest, UserInfo userInfo, String strInventory) {
        Context context = new Context();
        context.setVariable("ticketRequest", ticketRequest);
        context.setVariable("user", userInfo);
        context.setVariable("inventory", strInventory);
        return templateEngine.process(
                "mailTemplates/ticket/new-ticket-email",
                context
        );
    }
}