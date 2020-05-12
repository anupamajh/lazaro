package com.carmel.guestjini.Services.User;

import com.carmel.guestjini.Common.EndPoints;
import com.carmel.guestjini.Models.GenericResponse;
import com.carmel.guestjini.Models.User.AppAccessRequestResponse;
import com.carmel.guestjini.Models.User.ForgotPasswordResponse;
import com.carmel.guestjini.Models.User.UserInfo;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {
    String BASE_URL = EndPoints.END_POINT_URL;

    @GET("/common/user/me")
    Call<UserInfo> getMyProfile();

    @GET("/common/user/me/pic")
    Call<String> getMyProfilePic();

    @POST("/common/user/change-password")
    Call<GenericResponse> changePassword(
            @Body Map<String, String> requestData
    );

    @POST("/public/api/reset-password")
    Call<ForgotPasswordResponse> restPassword(
            @Body Map<String, String> postData);

    @POST("/public/api/app-access-request")
    Call<AppAccessRequestResponse> appAccessRequest(
            @Body Map<String, String> postData);

    @POST("/common/address-book/save-profile-pic")
    Call<UserInfo> saveProfilePic
            (
                    @Body Map<String, String> postData
            );


}
