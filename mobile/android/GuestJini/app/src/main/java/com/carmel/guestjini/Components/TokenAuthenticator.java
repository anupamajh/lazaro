package com.carmel.guestjini.Components;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.carmel.guestjini.Common.EndPoints;
import com.carmel.guestjini.Models.Authentication.AccessToken;
import com.carmel.guestjini.Services.Authentication.AuthServiceHolder;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public class TokenAuthenticator implements Authenticator {
    private Context context;
    private AuthServiceHolder authServiceHolder;

    public TokenAuthenticator(Context context, AuthServiceHolder authServiceHolder) {
        this.context = context;
        this.authServiceHolder = authServiceHolder;
    }

    @Override
    public Request authenticate(Route route, Response response) throws IOException {
        if (authServiceHolder == null) {
            return null;
        }

        SharedPreferences preferences = context.getSharedPreferences("GuestJini", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();
        String refreshToken = preferences.getString("refresh_token", null);
        String username = preferences.getString("username", null);
        String credentials = Credentials.basic(EndPoints.CLIENT_ID, EndPoints.CLIENT_SECRETE);
        try {
            retrofit2.Response retrofitResponse = authServiceHolder.get().refreshToken(credentials, "refresh_token", refreshToken).execute();
            if (retrofitResponse != null) {
                AccessToken accessToken = (AccessToken) retrofitResponse.body();
                String newAccessToken = accessToken.getAccessToken();
                if (accessToken != null) {
                    if (accessToken.getAccessToken() != null) {
                        if (accessToken.getAccessToken().trim() != "") {
                            editor.putBoolean("isLoggedIn", true);
                            editor.putString("access_token", accessToken.getAccessToken());
                            editor.putString("refresh_token", accessToken.getRefreshToken());
                            editor.putString("token_type", accessToken.getTokenType());
                            editor.putLong("expires_in", accessToken.getExpiresIn());
                            editor.commit();
                        }
                    }
                }
                return response.request().newBuilder()
                        .header("Authorization", "Bearer " + newAccessToken)
                        .build();
            }
        } catch (Exception ex) {
            Log.d("Exception", ex.toString());
        }
        return null;
    }
}
