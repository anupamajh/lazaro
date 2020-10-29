package com.carmel.guestjini.Authentication;

import com.carmel.guestjini.Common.BaseObservable;
import com.carmel.guestjini.Common.Constants;
import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Networking.Users.AccessToken;

import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AttemptClientLoginUseCase  extends BaseObservable<AttemptClientLoginUseCase.Listener> {
    public interface Listener {
        void onLoginSuccess(AccessToken accessToken);

        void onLoginFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public AttemptClientLoginUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void attemptLoginAndNotify(
    ) {
        String credentials = Credentials.basic(Constants.CLIENT_ID, Constants.CLIENT_SECRETE);

        this.guestJiniAPI.attemptLogin(
                credentials,
                Constants.GRANT_TYPE_CLIENT,
                Constants.CLIENT_ID,
                Constants.GRANT_TYPE_PASSWORD
        ).enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                if (response.isSuccessful()) {
                    notifySuccess(response.body());
                } else {
                    notifyFailure();
                }
            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                notifyNetworkFailure();
            }
        });
    }

    private void notifyNetworkFailure() {
        for (Listener listener : getListeners()) {
            listener.onNetworkFailed();
        }
    }

    private void notifyFailure() {
        for (Listener listener : getListeners()) {
            listener.onLoginFailed();
        }
    }

    private void notifySuccess(AccessToken accessToken) {
        for (Listener listener : getListeners()) {
            listener.onLoginSuccess(accessToken);
        }
    }
}
