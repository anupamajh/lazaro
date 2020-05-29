package com.carmel.guestjini.Screens.Community.CommunityHome;

import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

public interface CommunityHomeViewMVC extends ObservableViewMvc<CommunityHomeViewMVC.Listener> {
    public interface Listener {
        void onMyProfileClicked();

        void onPeopleClicked();

        void onGroupClicked();
    }
}