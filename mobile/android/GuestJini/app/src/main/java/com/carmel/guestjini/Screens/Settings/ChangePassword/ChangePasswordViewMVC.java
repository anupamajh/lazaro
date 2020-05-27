package com.carmel.guestjini.Screens.Settings.ChangePassword;

import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

public interface ChangePasswordViewMVC extends ObservableViewMvc<ChangePasswordViewMVC.Listener> {
    public interface Listener{
        void onChangePasswordClicked(String oldPassword, String newPassword);

        void onBackPressed();
    }

    void showProgressIndication();

    void hideProgressIndication();

    void clearFields();
}
