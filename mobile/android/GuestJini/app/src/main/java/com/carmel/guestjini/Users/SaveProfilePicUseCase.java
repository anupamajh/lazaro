package com.carmel.guestjini.Users;

import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Networking.Users.UserInfo;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaveProfilePicUseCase extends BaseObservableViewMvc<SaveProfilePicUseCase.Listener> {
    public interface Listener {
        void onProfilePicSaved(UserInfo userInfo);

        void onProfilePicSaveFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public SaveProfilePicUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void saveProfilePicAndNotify(
            String imageData
    ) {
        Map<String, String> postData = new HashMap<>();
        postData.put("imageData", imageData);
        this.guestJiniAPI.saveProfilePic(postData).enqueue(new Callback<UserInfo>() {
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
            listener.onProfilePicSaveFailed();
        }
    }

    private void notifySuccess(UserInfo userInfo) {
        for (Listener listener : getListeners()) {
            listener.onProfilePicSaved(userInfo);
        }
    }

    private void notifyNetworkFailure() {
        for (Listener listener : getListeners()) {
            listener.onNetworkFailed();
        }
    }

}