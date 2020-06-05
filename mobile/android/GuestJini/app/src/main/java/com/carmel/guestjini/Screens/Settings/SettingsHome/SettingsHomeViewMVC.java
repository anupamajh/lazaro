package com.carmel.guestjini.Screens.Settings.SettingsHome;

import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

public interface SettingsHomeViewMVC extends ObservableViewMvc<SettingsHomeViewMVC.Listener> {
    public interface Listener {
        void onMyProfileClicked();

        void onChangePasswordClicked();

        void onPrivacyPolicyClicked();

        void onTermsAndConditionsClicked();

        void onNotificationsClicked();

        void onLogoutClicked();
    }
}
