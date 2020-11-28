package com.carmel.guestjini.Screens.Support.AssignTicketSheet;

import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;
import com.carmel.guestjini.Screens.Support.InboxTicketDetail.InboxTicketDetailViewMVC;

public interface AssignTicketSheetViewMVC
        extends ObservableViewMvc<AssignTicketSheetViewMVC.Listener> {
    public interface Listener {
        void onAssignTicketClicked();
    }


    void showProgressIndication();

    void hideProgressIndication();
    
}
