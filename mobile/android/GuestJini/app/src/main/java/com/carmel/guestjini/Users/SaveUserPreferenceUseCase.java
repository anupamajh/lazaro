package com.carmel.guestjini.Users;

import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Networking.Users.UserPreference;
import com.carmel.guestjini.Networking.Users.UserPreferenceResponse;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaveUserPreferenceUseCase  extends BaseObservableViewMvc<SaveUserPreferenceUseCase.Listener> {
    public interface Listener {
        void onUserPreferenceSaved(UserPreferenceResponse userPreferenceResponse);

        void onUserPreferenceSaveFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public SaveUserPreferenceUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void saveUserPreferenceAndNotify(
            int preferenceType,
            int isHidden
    ) {
        Map<String, String> postData = new HashMap<>();
        postData.put("preferenceType", String.valueOf(preferenceType));
        postData.put("isHidden", String.valueOf(preferenceType));
        this.guestJiniAPI.saveUserPreference(postData).enqueue(new Callback<UserPreferenceResponse>() {
            @Override
            public void onResponse(Call<UserPreferenceResponse> call, Response<UserPreferenceResponse> response) {
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
            public void onFailure(Call<UserPreferenceResponse> call, Throwable t) {
                notifyNetworkFailure();
            }
        });

    }

    private void notifyFailure() {
        for (Listener listener : getListeners()) {
            listener.onUserPreferenceSaveFailed();
        }
    }

    private void notifySuccess(UserPreferenceResponse response) {
        for (Listener listener : getListeners()) {
            listener.onUserPreferenceSaved(response);
        }
    }

    private void notifyNetworkFailure() {
        for (Listener listener : getListeners()) {
            listener.onNetworkFailed();
        }
    }


}
