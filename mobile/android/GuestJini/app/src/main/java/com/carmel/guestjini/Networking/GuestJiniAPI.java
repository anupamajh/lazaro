package com.carmel.guestjini.Networking;



import com.carmel.guestjini.Networking.KnowledgeBase.KBResponse;
import com.carmel.guestjini.Networking.Users.AccessToken;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface GuestJiniAPI {
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

    @POST("/guest-jini/kb/get-all")
    Call<KBResponse> getKBList();

    @POST("/guest-jini/kb/get")
    Call<KBResponse> getKBById(@Body Map<String, String> postData);
}
