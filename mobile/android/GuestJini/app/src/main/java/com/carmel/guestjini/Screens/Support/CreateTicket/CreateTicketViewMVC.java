package com.carmel.guestjini.Screens.Support.CreateTicket;

import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

public interface CreateTicketViewMVC extends ObservableViewMvc<CreateTicketViewMVC.Listener> {
    public interface Listener {
        void onCreateTicketClicked(String subject, String narration);

        void onBackClicked();
    }

    boolean isValid();

    void showProgressIndication();

    void hideProgressIndication();


}
