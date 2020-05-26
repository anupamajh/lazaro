package com.carmel.guestjini.Screens.Settings.MyProfile;

import com.carmel.guestjini.Networking.Users.UserInfo;
import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

public interface MyProfileViewMVC extends ObservableViewMvc<MyProfileViewMVC.Listener> {

    public interface Listener {
        void onBackClicked();

        void onUserPreferenceChange(int preferenceType, boolean isVisible);

        void onShowInterestsClicked();
    }

    void bindMyProfile(UserInfo userInfo);

    void showProgressIndication();

    void hideProgressIndication();
}
