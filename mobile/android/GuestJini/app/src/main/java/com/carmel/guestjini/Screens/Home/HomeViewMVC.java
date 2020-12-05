package com.carmel.guestjini.Screens.Home;

import com.carmel.guestjini.Networking.Guest.GuestResponse;
import com.carmel.guestjini.Networking.Users.UserInfo;
import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

public interface HomeViewMVC  extends ObservableViewMvc<HomeViewMVC.Listener> {

    public interface Listener {
    }

    void showProgressIndication();

    void hideProgressIndication();

    void bindGuestDetails(GuestResponse guestResponse);

    void bindUserInfo(UserInfo userInfo);

    void bindProfilePic(String response);
}