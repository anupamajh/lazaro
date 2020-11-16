package com.carmel.guestjini.Screens.AppAccessRequest;

import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

public interface AppAccessRequestViewMVC extends ObservableViewMvc<AppAccessRequestViewMVC.Listener> {

    public interface Listener {
        void onAppAccessRequestClicked(String mobile);

        void onBackPressed();
    }

    void showProgressIndication();

    void hideProgressIndication();

    void showInvalidPhoneNumberError(boolean showError);

    void showHasUser();

    void showNoBooking();

    void showOTPSentToast();

    void showOTPFailedToast();

    void showNotResiding();
}
