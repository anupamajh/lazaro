package com.carmel.guestjini.Services.Ticket;

import com.carmel.guestjini.Common.EndPoints;
import com.carmel.guestjini.Models.Ticket.KBResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface KBService {
    String BASE_URL = EndPoints.END_POINT_URL;

    @POST("/guest-jini/kb/get-all")
    Call<KBResponse> getAll();

    @POST("/guest-jini/kb/get")
    Call<KBResponse> getById( @Body Map<String, String> postData);
}
