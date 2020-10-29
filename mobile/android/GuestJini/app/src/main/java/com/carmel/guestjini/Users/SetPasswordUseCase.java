package com.carmel.guestjini.Users;

import com.carmel.guestjini.Networking.GenericResponse;
import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetPasswordUseCase extends BaseObservableViewMvc<SetPasswordUseCase.Listener> {

    public interface Listener {
        void onPasswordSet(GenericResponse genericResponse);

        void onPasswordSetFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public SetPasswordUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void setPasswordAndNotify(
            String mobile,
            String password
    ) {
        Map<String, String> postData = new HashMap<>();
        postData.put("password", password);
        postData.put("phone", mobile);

        this.guestJiniAPI.setPassword(postData).enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
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
            listener.onPasswordSetFailed();
        }
    }

    private void notifySuccess(GenericResponse genericResponse) {
        for (Listener listener : getListeners()) {
            listener.onPasswordSet(genericResponse);
        }
    }

}