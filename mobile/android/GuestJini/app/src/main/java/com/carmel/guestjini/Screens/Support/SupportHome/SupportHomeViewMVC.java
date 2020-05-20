package com.carmel.guestjini.Screens.Support.SupportHome;

import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

public interface SupportHomeViewMVC extends ObservableViewMvc<SupportHomeViewMVC.Listener> {
    public interface Listener {
        void onKBSearchClicked(String searchText);
        void onKBExploreClicked();
        void onCreateTicketClicked();
        void onGotoTicketsClicked();
    }
}
