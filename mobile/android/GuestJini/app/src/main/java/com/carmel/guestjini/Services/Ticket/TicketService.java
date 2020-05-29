package com.carmel.guestjini.Services.Ticket;

import com.carmel.guestjini.Common.EndPoints;
import com.carmel.guestjini.Models.Ticket.TicketRequest;
import com.carmel.guestjini.Models.Ticket.TicketResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TicketService {
    String BASE_URL = EndPoints.END_POINT_URL;

    @POST("/guest-jini/task-ticket/save")
    Call<TicketResponse> saveTicket(
            @Body TicketRequest ticketRequest
    );

    @POST("/guest-jini/task-ticket/get-all")
    Call<TicketResponse> getAll();

    @POST("/guest-jini/task-ticket/get")
    Call<TicketResponse> get(
            @Body Map<String, String> requestData
    );
}
