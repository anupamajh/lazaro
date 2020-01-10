package com.carmel.android.guestjini.datalayer.User.API;

import com.carmel.android.guestjini.datalayer.Common.EndPoints;
import com.carmel.android.guestjini.models.User.AuthData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserAPI {
    String BASE_URL = EndPoints.END_POINT_URL;

    @FormUrlEncoded
    @POST("/auth/oauth/token")
    Call<AuthData> attemptLogin(
            @Header("Authorization") String credentials,
            @Field("grant_type") String grantType,
            @Field("username") String userName,
            @Field("password")  String password);

    @FormUrlEncoded
    @POST("/auth/oauth/token")
    Call<AuthData> refreshToken(
            @Header("Authorization") String credentials,
            @Field("grant_type") String grantType,
            @Field("refresh_token") String refreshToken
    );
}
