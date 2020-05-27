package com.carmel.guestjini.Screens.Settings.MyProfile;

import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;

import com.carmel.guestjini.Networking.Users.UserInfo;
import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

public interface MyProfileViewMVC extends ObservableViewMvc<MyProfileViewMVC.Listener> {


    public interface Listener {
        void onBackClicked();

        void onUserPreferenceChange(int preferenceType, boolean isVisible);

        void onShowInterestsClicked();

        void onCameraClicked();

        void onGalleryClicked();
    }

    void bindMyProfile(UserInfo userInfo);

    void bindProfilePic(String response);

    void enableProfileIcon(boolean enable);

    void setImageURI(Uri data);

    BitmapDrawable getProfilePicDrawable();

    void showProgressIndication();

    void hideProgressIndication();

}
