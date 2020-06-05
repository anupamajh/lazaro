package com.carmel.guestjini.Users;

import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Networking.Users.UserInfo;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchMyProfileUseCase extends BaseObservableViewMvc<FetchMyProfileUseCase.Listener> {

    public interface Listener {
        void onProfileFetched(UserInfo response);

        void onProfileFetchFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public FetchMyProfileUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void fetchProfileAndNotify() {
        this.guestJiniAPI.getMyProfile().enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                if (response.isSuccessful()) {
                    if (response.body().getAddressBook() != null) {
                        notifySuccess(response.body());
                    } else {
                        notifyFailure();
                    }
                } else {
                    notifyFailure();
                }
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                notifyNetworkFailure();
            }
        });
    }


    private void notifyFailure() {
        for (Listener listener : getListeners()) {
            listener.onProfileFetchFailed();
        }
    }

    private void notifySuccess(UserInfo response) {
        for (Listener listener : getListeners()) {
            listener.onProfileFetched(response);
        }
    }

    private void notifyNetworkFailure() {
        for (Listener listener : getListeners()) {
            listener.onNetworkFailed();
        }
    }

}
