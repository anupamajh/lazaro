package com.carmel.guestjini.Users;

import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Networking.OTP.OTPResponse;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyOTPUseCase extends BaseObservableViewMvc<VerifyOTPUseCase.Listener> {

    public interface Listener {
        void onOTPVerificationSuccess(OTPResponse otpResponse);

        void onOTPVerificationFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public VerifyOTPUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void verifyOTPAndNotify(
            String sessionId,
            String otp
    ) {
        Map<String, String> postData = new HashMap<>();
        postData.put("sessionId", sessionId);
        postData.put("otp", otp);

        this.guestJiniAPI.verifyOTP(postData).enqueue(new Callback<OTPResponse>() {
            @Override
            public void onResponse(Call<OTPResponse> call, Response<OTPResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus().toLowerCase().equals("success") ) {
                        notifySuccess(response.body());
                    } else {
                        notifyFailure();
                    }
                } else {
                    notifyFailure();
                }
            }

            @Override
            public void onFailure(Call<OTPResponse> call, Throwable t) {
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
            listener.onOTPVerificationFailed();
        }
    }

    private void notifySuccess(OTPResponse otpResponse) {
        for (Listener listener : getListeners()) {
            listener.onOTPVerificationSuccess(otpResponse);
        }

    }
}
