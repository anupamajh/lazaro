package com.carmel.guestjini.Users;

import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Networking.Users.ForgotPasswordResponse;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPasswordUseCase extends BaseObservableViewMvc<ResetPasswordUseCase.Listener> {

    public interface Listener {
        void onPasswordReset(ForgotPasswordResponse response);

        void onPasswordResetFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public ResetPasswordUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void resetPasswordAndNotify(String userName) {
        Map<String, String> postData = new HashMap<>();
        postData.put("user_name", userName);
        this.guestJiniAPI.restPassword(postData).enqueue(new Callback<ForgotPasswordResponse>() {
            @Override
            public void onResponse(Call<ForgotPasswordResponse> call, Response<ForgotPasswordResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getUserName() != null) {
                        notifySuccess(response.body());
                    } else {
                        notifyFailure();
                    }
                } else {
                    notifyFailure();
                }
            }

            @Override
            public void onFailure(Call<ForgotPasswordResponse> call, Throwable t) {
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
            listener.onPasswordResetFailed();
        }
    }

    private void notifySuccess(ForgotPasswordResponse response) {
        for (Listener listener : getListeners()) {
            listener.onPasswordReset(response);
        }
    }
}
