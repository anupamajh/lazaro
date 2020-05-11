package com.carmel.guestjini.Services.User;


import com.carmel.guestjini.Common.EndPoints;
import com.carmel.guestjini.Models.User.PeopleResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PeopleService {
    String BASE_URL = EndPoints.END_POINT_URL;

    @POST("/common/people/get-people")
    Call<PeopleResponse> getPeopleList();

    @POST("/common/people/get-person")
    Call<PeopleResponse> getPersonDetails(
            @Body Map<String, String> postData
    );

    @POST("/common/people/add-remove-favourite")
    Call<PeopleResponse> addFavourite(
            @Body Map<String, String> postData
    );

}
