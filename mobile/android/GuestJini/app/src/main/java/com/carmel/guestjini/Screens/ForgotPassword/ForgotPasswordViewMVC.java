package com.carmel.guestjini.Screens.ForgotPassword;

import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

public interface ForgotPasswordViewMVC extends ObservableViewMvc<ForgotPasswordViewMVC.Listener> {
    void showOTPSentToast();

    void showOTPFailedToast();

    public interface Listener {
        void onForgotPasswordClicked(String userName);

        void onAppAccessRequestClicked();

        void onBackPressed();
    }

    void showProgressIndication();

    void hideProgressIndication();

    void showInvalidUser(boolean show);

}
