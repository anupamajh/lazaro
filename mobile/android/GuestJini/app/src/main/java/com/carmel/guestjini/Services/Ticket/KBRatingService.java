package com.carmel.guestjini.Services.Ticket;

import com.carmel.guestjini.Models.Ticket.KBRating;
import com.carmel.guestjini.Models.Ticket.KBRatingResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface KBRatingService {

    @POST("/guest-jini/kb-rating/save")
    Call<KBRatingResponse> save(@Body  KBRating kbRating);

    @POST("/guest-jini/kb-rating/get-my-rating")
    Call<KBRatingResponse> getRatings(@Body Map<String, String> postData);

    @POST("/guest-jini/kb-rating/get-rating-percent")
    Call<KBRatingPercentResponse> getRatingPercentage(@Body Map<String, String> postData);
}
