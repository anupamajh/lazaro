package com.carmel.guestjini.Guest;

import com.carmel.guestjini.Networking.Guest.GuestResponse;
import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchGuestDetailsByPhoneUseCase  extends BaseObservableViewMvc<FetchGuestDetailsByPhoneUseCase.Listener> {

    public interface Listener {
        void onGuestDetailsFetched(GuestResponse response);

        void onGuestDetailsFetchFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public FetchGuestDetailsByPhoneUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void fetchGuestDetailsAndNotify(String mobile) {
        Map<String, String> postData = new HashMap<>();
        postData.put("phone", mobile);
        this.guestJiniAPI.getGuestDetails(postData).enqueue(new Callback<GuestResponse>() {
            @Override
            public void onResponse(Call<GuestResponse> call, Response<GuestResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().isSuccess()) {
                        notifySuccess(response.body());
                    } else {
                        notifyFailure();
                    }
                } else {
                    notifyFailure();
                }
            }

            @Override
            public void onFailure(Call<GuestResponse> call, Throwable t) {
                notifyNetworkFailure();
            }
        });
    }


    private void notifyFailure() {
        for (Listener listener : getListeners()) {
            listener.onGuestDetailsFetchFailed();
        }
    }

    private void notifySuccess(GuestResponse response) {
        for (Listener listener : getListeners()) {
            listener.onGuestDetailsFetched(response);
        }
    }

    private void notifyNetworkFailure() {
        for (Listener listener : getListeners()) {
            listener.onNetworkFailed();
        }
    }

}
