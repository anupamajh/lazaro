package com.carmel.guestjini.Services.User;

import com.carmel.guestjini.Common.EndPoints;
import com.carmel.guestjini.Models.GenericResponse;
import com.carmel.guestjini.Models.User.UserInfo;

import java.util.Map;

import okhttp3.ResponseBody;
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


}
