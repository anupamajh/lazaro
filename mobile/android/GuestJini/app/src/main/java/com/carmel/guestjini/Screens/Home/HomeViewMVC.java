package com.carmel.guestjini.Screens.Home;

import com.carmel.guestjini.Networking.Booking.Booking;
import com.carmel.guestjini.Networking.Users.UserInfo;
import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

public interface HomeViewMVC  extends ObservableViewMvc<HomeViewMVC.Listener> {

    public interface Listener {
    }

    void showProgressIndication();

    void hideProgressIndication();

    void bindGuestDetails(Booking booking);

    void bindUserInfo(UserInfo userInfo);

    void bindProfilePic(String response);
}