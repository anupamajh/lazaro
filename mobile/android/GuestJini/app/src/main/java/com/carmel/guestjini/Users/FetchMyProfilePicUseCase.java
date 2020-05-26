package com.carmel.guestjini.Users;

import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchMyProfilePicUseCase extends BaseObservableViewMvc<FetchMyProfilePicUseCase.Listener> {
    public interface Listener {
        void onProfilePicFetched(String response);

        void onProfilePicFetchFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public FetchMyProfilePicUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }


    public void fetchProfilePicAndNotify() {
        this.guestJiniAPI.getMyProfilePic().enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        notifySuccess(response.body());
                    } else {
                        notifyFailure();
                    }
                } else {
                    notifyFailure();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                notifyNetworkFailure();
            }
        });
    }


    private void notifyFailure() {
        for (Listener listener : getListeners()) {
            listener.onProfilePicFetchFailed();
        }
    }

    private void notifySuccess(String response) {
        for (Listener listener : getListeners()) {
            listener.onProfilePicFetched(response);
        }
    }

    private void notifyNetworkFailure() {
        for (Listener listener : getListeners()) {
            listener.onNetworkFailed();
        }
    }
}
