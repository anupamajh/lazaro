package com.carmel.guestjini.Common.DependencyInjection;

import androidx.fragment.app.FragmentActivity;

import com.carmel.guestjini.Common.Constants;
import com.carmel.guestjini.Components.OkHttpClientInstance;
import com.carmel.guestjini.Networking.Common.AuthService;
import com.carmel.guestjini.Networking.Common.AuthServiceHolder;
import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsEventBus;
import com.carmel.guestjini.Screens.Login.LoginEventBus;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class CompositionRoot {
    private Retrofit mRetrofit;
    private DialogsEventBus mDialogsEventBus;
    private Retrofit authenticatedRetrofit;
    private Retrofit clientAuthenticatedRetrofit;
    private LoginEventBus loginEventBus;

    private Retrofit getRetrofit() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }

    private Retrofit getAuthenticatedRetrofit(String accessToken, FragmentActivity activity) {
        if (authenticatedRetrofit == null) {
            AuthServiceHolder authServiceHolder = new AuthServiceHolder();
            OkHttpClient okHttpClient = new OkHttpClientInstance.Builder(activity, authServiceHolder)
                    .addHeader("Authorization", accessToken)
                    .build();
            authenticatedRetrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
            AuthService authService = authenticatedRetrofit.create(AuthService.class);
            authServiceHolder.set(authService);
        }
        return authenticatedRetrofit;
    }


    public GuestJiniAPI getGuestJiniAPI() {
        return getRetrofit().create(GuestJiniAPI.class);
    }

    public DialogsEventBus getDialogsEventBus() {
        if (mDialogsEventBus == null) {
            mDialogsEventBus = new DialogsEventBus();
        }
        return mDialogsEventBus;
    }

    public LoginEventBus getLoginEventBus() {
        if (loginEventBus == null) {
            loginEventBus = new LoginEventBus();
        }
        return loginEventBus;
    }

    public GuestJiniAPI getAuthenticatedGuestJiniAPI(String accessToken, FragmentActivity activity) {
        return getAuthenticatedRetrofit(accessToken, activity).create(GuestJiniAPI.class);
    }

    public Retrofit getClientAuthenticatedRetrofit(String clientId, String clientSecrete, FragmentActivity activity) {
        if (clientAuthenticatedRetrofit == null) {
            AuthServiceHolder authServiceHolder = new AuthServiceHolder();
            OkHttpClient okHttpClient = new OkHttpClientInstance.Builder(activity, authServiceHolder)
                    .addHeader("Authorization", Credentials.basic(clientId, clientSecrete))
                    .build();
            clientAuthenticatedRetrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
            AuthService authService = authenticatedRetrofit.create(AuthService.class);
            authServiceHolder.set(authService);
        }
        return clientAuthenticatedRetrofit;
    }

    public GuestJiniAPI getClientAuthenticatedGuestJiniAPI(String clientId, String clientSecrete, FragmentActivity activity) {
        return getClientAuthenticatedRetrofit(clientId, clientSecrete, activity).create(GuestJiniAPI.class);
    }
}
