package com.carmel.guestjini.Services.Ticket;

import com.carmel.guestjini.Common.EndPoints;
import com.carmel.guestjini.Models.Ticket.KBResponse;
import com.carmel.guestjini.Models.Ticket.KBReview;
import com.carmel.guestjini.Models.Ticket.KBReviewResponse;
import com.carmel.guestjini.Models.Ticket.TicketRequest;
import com.carmel.guestjini.Models.Ticket.TicketResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface KBReviewService {
    String BASE_URL = EndPoints.END_POINT_URL;

    @POST("/guest-jini/kb-review/save")
    Call<KBResponse> save(
            @Body KBReview kbReview
    );

    @POST("/guest-jini/kb-review/get-all")
    Call<KBReviewResponse> getAll(
            @Body Map<String, String> postData
    );
}
