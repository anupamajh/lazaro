package com.carmel.guestjini.Screens.Settings.MyInterests.InterestItem;

import com.carmel.guestjini.Networking.Users.Interest;
import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

public interface InterestItemViewMVC extends ObservableViewMvc<InterestItemViewMVC.Listener> {

    public interface Listener{
        void onInterestClicked(Interest interest, int isInterested);
    }

    void bindInterest(Interest interest);

    void setInterest(int isInterested);
}
