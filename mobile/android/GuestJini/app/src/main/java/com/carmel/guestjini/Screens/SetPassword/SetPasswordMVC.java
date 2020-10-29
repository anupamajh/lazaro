package com.carmel.guestjini.Screens.SetPassword;

import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

public interface SetPasswordMVC extends ObservableViewMvc<SetPasswordMVC.Listener> {

    public interface Listener {
        void onSubmitClicked(String password);

        void onBackPressed();
    }

    void showProgressIndication();

    void hideProgressIndication();

    void showPasswordSetMessage();

    void showPasswordSetFailedMessage();

    void bindFullName(String full_name);
}