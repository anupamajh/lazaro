package com.carmel.android.guestjini.datalayer.Ticket.API;

import com.carmel.android.guestjini.datalayer.Common.EndPoints;
import com.carmel.android.guestjini.models.Ticket.Ticket;
import com.carmel.android.guestjini.models.Ticket.TicketResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface TicketAPI {

    String BASE_URL = EndPoints.END_POINT_URL;

    @POST("/helpdesk/task-ticket/save")
    Call<TicketResponse>  saveTicket(
            @Header("Authorization") String accessToken,
            @Body Ticket ticket
    );

    @POST("/helpdesk/task-ticket/get-all")
    Call<TicketResponse>  getAll(
            @Header("Authorization") String accessToken
    );

    @POST("/helpdesk/task-ticket/get")
    Call<TicketResponse>  get(
            @Header("Authorization") String accessToken,
            @Body Map<String, String> requestData
            );


}
