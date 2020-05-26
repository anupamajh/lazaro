package com.carmel.guestjini.Users;

import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Networking.Users.UserInterests;
import com.carmel.guestjini.Networking.Users.UserInterestsResponse;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaveMyInterestUseCase extends BaseObservableViewMvc<SaveMyInterestUseCase.Listener> {
    public interface Listener {
        void onUserInterestSaved(UserInterestsResponse response);

        void onUserInterestSaveFailed();

        void onNetworkFailed();
    }
    private final GuestJiniAPI guestJiniAPI;

    public SaveMyInterestUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }


    public void fetchInterestListAndNotify(
            String interestId,
            int isInterested
    ) {
        UserInterests userInterests = new UserInterests();
        userInterests.setInterestId(interestId);
        userInterests.setIsInterested(isInterested);
        this.guestJiniAPI.saveMyInterest(userInterests).enqueue(new Callback<UserInterestsResponse>() {
            @Override
            public void onResponse(Call<UserInterestsResponse> call, Response<UserInterestsResponse> response) {
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
            public void onFailure(Call<UserInterestsResponse> call, Throwable t) {
                notifyNetworkFailure();
            }
        });
    }


    private void notifyFailure() {
        for (Listener listener : getListeners()) {
            listener.onUserInterestSaveFailed();
        }
    }

    private void notifySuccess(UserInterestsResponse response) {
        for (Listener listener : getListeners()) {
            listener.onUserInterestSaved(response);
        }
    }

    private void notifyNetworkFailure() {
        for (Listener listener : getListeners()) {
            listener.onNetworkFailed();
        }
    }

}
