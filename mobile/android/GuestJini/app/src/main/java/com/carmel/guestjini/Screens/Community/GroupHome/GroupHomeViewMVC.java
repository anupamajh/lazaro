package com.carmel.guestjini.Screens.Community.GroupHome;

import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

public interface GroupHomeViewMVC extends ObservableViewMvc<GroupHomeViewMVC.Listener> {
    public interface Listener {
        void onInterestGroupClicked();

        void onCommunityGroupClicked();

        void onMyGroupClicked();
    }
}
