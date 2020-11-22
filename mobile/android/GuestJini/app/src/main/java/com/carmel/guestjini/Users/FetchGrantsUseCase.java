package com.carmel.guestjini.Users;

import com.carmel.guestjini.Common.BaseObservable;
import com.carmel.guestjini.Common.Constants;
import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Networking.Users.UserGrants;

import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchGrantsUseCase extends BaseObservable<FetchGrantsUseCase.Listener> {

    public interface Listener {
        void onGrantFetched(UserGrants userGrants);

        void onGrantFetchFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public FetchGrantsUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void fetchGrantsAndNotify(String token) {
        String credentials = Credentials.basic(Constants.CLIENT_ID, Constants.CLIENT_SECRETE);
        this.guestJiniAPI.getGrants(credentials, token).enqueue(new Callback<UserGrants>() {
            @Override
            public void onResponse(Call<UserGrants> call, Response<UserGrants> response) {
                if (response.isSuccessful()) {
                    if (response.body().getUser_name() != null) {
                        notifySuccess(response.body());
                    } else {
                        notifyFailure();
                    }
                } else {
                    notifyFailure();
                }
            }

            @Override
            public void onFailure(Call<UserGrants> call, Throwable t) {
                notifyNetworkFailure();
            }
        });
    }


    private void notifyFailure() {
        for (FetchGrantsUseCase.Listener listener : getListeners()) {
            listener.onGrantFetchFailed();
        }
    }

    private void notifySuccess(UserGrants response) {
        for (FetchGrantsUseCase.Listener listener : getListeners()) {
            listener.onGrantFetched(response);
        }
    }

    private void notifyNetworkFailure() {
        for (FetchGrantsUseCase.Listener listener : getListeners()) {
            listener.onNetworkFailed();
        }
    }
}
