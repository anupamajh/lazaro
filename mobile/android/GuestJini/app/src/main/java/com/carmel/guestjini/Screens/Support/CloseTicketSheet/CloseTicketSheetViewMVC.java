package com.carmel.guestjini.Screens.Support.CloseTicketSheet;

import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

public interface CloseTicketSheetViewMVC
        extends ObservableViewMvc<CloseTicketSheetViewMVC.Listener> {
    public interface Listener {
        void onCloseTicketClicked(String message);
    }

    void showProgressIndication();

    void hideProgressIndication();

}
