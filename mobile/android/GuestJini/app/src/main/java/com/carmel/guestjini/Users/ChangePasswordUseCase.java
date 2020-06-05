package com.carmel.guestjini.Users;

import com.carmel.guestjini.Networking.GenericResponse;
import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordUseCase extends BaseObservableViewMvc<ChangePasswordUseCase.Listener> {

    public interface Listener {
        void onChangePasswordSuccess(GenericResponse response);

        void onChangePasswordFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public ChangePasswordUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void changePasswordAndNotify(
            String currentPassword,
            String newPassword
    ) {
        Map<String, String> postData = new HashMap<>();
        postData.put("currentPassword", currentPassword);
        postData.put("newPassword", newPassword);
        this.guestJiniAPI.changePassword(postData).enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSuccess()) {
                        notifySuccess(response.body());
                    } else {
                        notifyFailure();
                    }
                } else {
                    notifyFailure();
                }
            }

            @Override
            public void onFailure(Call<GenericResponse> call, Throwable t) {
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
            listener.onChangePasswordFailed();
        }
    }

    private void notifySuccess(GenericResponse response) {
        for (Listener listener : getListeners()) {
            listener.onChangePasswordSuccess(response);
        }

    }
}
