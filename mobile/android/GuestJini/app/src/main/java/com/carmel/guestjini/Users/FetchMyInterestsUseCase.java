package com.carmel.guestjini.Users;


import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Networking.Users.UserInterests;
import com.carmel.guestjini.Networking.Users.UserInterestsResponse;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchMyInterestsUseCase extends BaseObservableViewMvc<FetchMyInterestsUseCase.Listener> {
    public interface Listener {
        void onUserInterestListFetched(List<UserInterests> response);

        void onUserInterestListFetchFailed();

        void onNetworkFailed();

    }


    private final GuestJiniAPI guestJiniAPI;

    public FetchMyInterestsUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }


    public void fetchMyInterestAndNotify() {
        this.guestJiniAPI.getMyInterests().enqueue(new Callback<UserInterestsResponse>() {
            @Override
            public void onResponse(Call<UserInterestsResponse> call, Response<UserInterestsResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        notifySuccess(response.body().getUserInterestsList());
                    } else {
                        notifyFailure();
                    }
                } else {
                    notifyFailure();
                }
            }

            @Override
            public void onFailure(Call<UserInterestsResponse> call, Throwable t) {
                notifyNetworkFailure();
            }
        });
    }


    private void notifyFailure() {
        for (Listener listener : getListeners()) {
            listener.onUserInterestListFetchFailed();
        }
    }

    private void notifySuccess(List<UserInterests> response) {
        for (Listener listener : getListeners()) {
            listener.onUserInterestListFetched(response);
        }
    }

    private void notifyNetworkFailure() {
        for (Listener listener : getListeners()) {
            listener.onNetworkFailed();
        }
    }
}
