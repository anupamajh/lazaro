package com.carmel.guestjini.service.HelpDesk.TicketFeedback.Service;

import com.carmel.guestjini.service.HelpDesk.TicketFeedback.Model.TicketFeedBack;
import com.carmel.guestjini.service.HelpDesk.TicketFeedback.Response.TicketFeedBackResponse;

import java.util.Map;
import java.util.Optional;

public interface TicketFeedBackService {
    TicketFeedBack save(TicketFeedBack ticketFeedBack) throws Exception;

    Optional<TicketFeedBack> findById(String id) throws Exception;

    TicketFeedBackResponse saveTicketFeedback(TicketFeedBack ticketFeedBack) throws Exception;

    TicketFeedBackResponse moveToTrash(Map<String, String> formData) throws Exception;

    TicketFeedBackResponse get(Map<String, String> formData) throws Exception;

    TicketFeedBackResponse getDeleted() throws Exception;

    TicketFeedBackResponse getAll() throws Exception;

    TicketFeedBackResponse getPaginated(Map<String, String> formData) throws Exception;

    TicketFeedBackResponse searchPaginated(Map<String, String> formData) throws Exception;

    TicketFeedBackResponse getByTicketId(Map<String, String> formData);
}
