package com.carmel.guestjini.service.HelpDesk.TicketFeedback.Controller;

import com.carmel.guestjini.service.HelpDesk.TicketFeedback.Model.TicketFeedBack;
import com.carmel.guestjini.service.HelpDesk.TicketFeedback.Response.TicketFeedBackResponse;
import com.carmel.guestjini.service.HelpDesk.TicketFeedback.Service.TicketFeedBackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping(value = "/ticket-feedback")
public class TicketFeedBackController {
    Logger logger = LoggerFactory.getLogger(TicketFeedBackController.class);

    @Autowired
    TicketFeedBackService ticketFeedBackService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public TicketFeedBackResponse save(@Valid @RequestBody TicketFeedBack ticketFeedBack) {
        logger.trace("Entering");
        TicketFeedBackResponse ticketFeedBackResponse = new TicketFeedBackResponse();
        try {
            ticketFeedBackResponse = ticketFeedBackService
                    .saveTicketFeedback(ticketFeedBack);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            ticketFeedBackResponse.setSuccess(true);
            ticketFeedBackResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return ticketFeedBackResponse;
    }

    @RequestMapping(value = "/trash", method = RequestMethod.POST)
    public TicketFeedBackResponse moveToTrash(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        TicketFeedBackResponse ticketFeedBackResponse = new TicketFeedBackResponse();
        try {
            ticketFeedBackResponse = ticketFeedBackService.moveToTrash(formData);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            ticketFeedBackResponse.setSuccess(false);
            ticketFeedBackResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return ticketFeedBackResponse;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public TicketFeedBackResponse get(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        TicketFeedBackResponse ticketFeedBackResponse = new TicketFeedBackResponse();
        try {
            ticketFeedBackResponse = ticketFeedBackService.get(formData);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            ticketFeedBackResponse.setSuccess(false);
            ticketFeedBackResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return ticketFeedBackResponse;
    }

    @RequestMapping(value = "/get-by-ticket-id", method = RequestMethod.POST)
    public TicketFeedBackResponse getByTicketId(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        TicketFeedBackResponse ticketFeedBackResponse = new TicketFeedBackResponse();
        try {
            ticketFeedBackResponse = ticketFeedBackService.getByTicketId(formData);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            ticketFeedBackResponse.setSuccess(false);
            ticketFeedBackResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return ticketFeedBackResponse;
    }

    @RequestMapping(value = "/get-deleted", method = RequestMethod.POST)
    public TicketFeedBackResponse getDeleted() {
        logger.trace("Entering");
        TicketFeedBackResponse ticketFeedBackResponse = new TicketFeedBackResponse();
        try {
            ticketFeedBackResponse = ticketFeedBackService.getDeleted();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            ticketFeedBackResponse.setSuccess(false);
            ticketFeedBackResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return ticketFeedBackResponse;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.POST)
    public TicketFeedBackResponse getAll() {
        logger.trace("Entering");
        TicketFeedBackResponse ticketFeedBackResponse = new TicketFeedBackResponse();
        try {
            ticketFeedBackResponse = ticketFeedBackService.getAll();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            ticketFeedBackResponse.setSuccess(false);
            ticketFeedBackResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return ticketFeedBackResponse;
    }


    @RequestMapping(value = "/get-ticket-feedbacks", method = RequestMethod.POST)
    public TicketFeedBackResponse getPaginated(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        TicketFeedBackResponse ticketFeedBackResponse = new TicketFeedBackResponse();
        try {
            ticketFeedBackResponse = ticketFeedBackService.getPaginated(formData);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            ticketFeedBackResponse.setSuccess(false);
            ticketFeedBackResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return ticketFeedBackResponse;
    }

    @RequestMapping(value = "/search-ticket-feedbacks", method = RequestMethod.POST)
    public TicketFeedBackResponse searchPaginated(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        TicketFeedBackResponse ticketFeedBackResponse = new TicketFeedBackResponse();
        try {
            ticketFeedBackResponse = ticketFeedBackService.searchPaginated(formData);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            ticketFeedBackResponse.setSuccess(false);
            ticketFeedBackResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return ticketFeedBackResponse;
    }
}
