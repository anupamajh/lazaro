package com.carmel.guestjini.Services.Authentication;

import com.carmel.guestjini.Common.EndPoints;
import com.carmel.guestjini.Models.Authentication.AccessToken;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AuthService {

    String BASE_URL = EndPoints.END_POINT_URL;

    @FormUrlEncoded
    @POST("/auth/oauth/token")
    Call<AccessToken> attemptLogin(
            @Header("Authorization") String credentials,
            @Field("grant_type") String grantType,
            @Field("username") String userName,
            @Field("password") String password);

    @FormUrlEncoded
    @POST("/auth/oauth/token")
    Call<AccessToken> refreshToken(
            @Header("Authorization") String credentials,
            @Field("grant_type") String grantType,
            @Field("refresh_token") String refreshToken
    );


}
