package com.carmel.guestjini.Users;

import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Networking.Users.Interest;
import com.carmel.guestjini.Networking.Users.InterestResponse;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchInterestListUseCase extends BaseObservableViewMvc<FetchInterestListUseCase.Listener> {
    public interface Listener {
        void onInterestListFetched(List<Interest> response);

        void onInterestListFetchFailed();

        void onNetworkFailed();

    }

    private final GuestJiniAPI guestJiniAPI;

    public FetchInterestListUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }


    public void fetchInterestListAndNotify() {
        this.guestJiniAPI.getInterestList().enqueue(new Callback<InterestResponse>() {
            @Override
            public void onResponse(Call<InterestResponse> call, Response<InterestResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        notifySuccess(response.body().getInterestList());
                    } else {
                        notifyFailure();
                    }
                } else {
                    notifyFailure();
                }
            }

            @Override
            public void onFailure(Call<InterestResponse> call, Throwable t) {
                notifyNetworkFailure();
            }
        });
    }


    private void notifyFailure() {
        for (Listener listener : getListeners()) {
            listener.onInterestListFetchFailed();
        }
    }

    private void notifySuccess(List<Interest> response) {
        for (Listener listener : getListeners()) {
            listener.onInterestListFetched(response);
        }
    }

    private void notifyNetworkFailure() {
        for (Listener listener : getListeners()) {
            listener.onNetworkFailed();
        }
    }
}
