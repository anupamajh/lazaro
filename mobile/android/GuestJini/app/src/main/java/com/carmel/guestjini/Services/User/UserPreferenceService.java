package com.carmel.guestjini.Services.User;

import com.carmel.guestjini.Common.EndPoints;
import com.carmel.guestjini.Models.GenericResponse;
import com.carmel.guestjini.Models.User.UserPreferenceResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserPreferenceService {
    String BASE_URL = EndPoints.END_POINT_URL;

    @POST("/common/user-preference/save")
    Call<UserPreferenceResponse> saveUserPreference(
            @Body Map<String, String> requestData
    );

    @POST("/common/user-preference/get-all")
    Call<UserPreferenceResponse> getUserPreference(
            @Body Map<String, String> requestData
    );




}
