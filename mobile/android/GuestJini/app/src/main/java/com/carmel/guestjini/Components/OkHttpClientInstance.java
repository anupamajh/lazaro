package com.carmel.guestjini.Components;

import android.content.Context;
import android.content.SharedPreferences;

import com.carmel.guestjini.Services.Authentication.AuthServiceHolder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpClientInstance {
    public static class Builder {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        private HashMap<String, String> headers = new HashMap<>();
        private Context context;
        private AuthServiceHolder authServiceHolder;

        public Builder(Context context, AuthServiceHolder authServiceHolder) {
            this.context = context;
            this.authServiceHolder = authServiceHolder;
        }

        public Builder addHeader(String key, String value) {
            headers.put(key, value);
            return this;
        }

        public OkHttpClient build() {
            TokenAuthenticator authenticator = new TokenAuthenticator(context, authServiceHolder);
            OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .addInterceptor(
                            new Interceptor() {
                                @Override
                                public Response intercept(Interceptor.Chain chain) throws IOException {
                                    // Add default headers
                                    Request.Builder requestBuilder = chain.request().newBuilder()
                                            .addHeader("accept", "*/*")
                                            .addHeader("accept-encoding:gzip", "gzip, deflate")
                                            .addHeader("accept-language", "en-US,en;q=0.9");

                                    // Add additional headers
                                    Iterator it = headers.entrySet().iterator();

                                    for (Map.Entry<String, String> entry : headers.entrySet()) {
                                        if (entry.getKey() != null && entry.getValue() != null) {
                                            requestBuilder.addHeader(entry.getKey(), entry.getValue());
                                        }
                                    }

                                    if (context != null) {
                                        SharedPreferences settings = context.getSharedPreferences("GuestJini", context.MODE_PRIVATE);
                                        String token = settings.getString("access_token", null);
                                        if (token != null) {
                                            requestBuilder.addHeader("Authorization", "Bearer " + token);
                                        }
                                    }

                                    return chain.proceed(requestBuilder.build());
                                }
                            }
                    )
                    .connectTimeout(200, TimeUnit.SECONDS)
                    .writeTimeout(200, TimeUnit.SECONDS)
                    .readTimeout(300, TimeUnit.SECONDS);

            okHttpClientBuilder.authenticator(authenticator);

            return okHttpClientBuilder.build();
        }
    }
}
