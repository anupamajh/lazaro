package com.carmel.guestjini.Screens.OTP;

import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;
import com.carmel.guestjini.Screens.Settings.ChangePassword.ChangePasswordViewMVC;

public interface OTPViewMVC extends ObservableViewMvc<OTPViewMVC.Listener> {

    public interface Listener {
        void onVerifyOTPClicked(String otp);

        void onResendOTPClicked();

        void onBackPressed();
    }

    void showProgressIndication();

    void hideProgressIndication();

    void showInvalidOTP(boolean show);

    void showNetworkError();

    void showOTPSentToast();

    void showOTPFailedToast();

}
