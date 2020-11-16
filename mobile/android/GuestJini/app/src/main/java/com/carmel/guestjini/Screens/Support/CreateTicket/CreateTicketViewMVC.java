package com.carmel.guestjini.Screens.Support.CreateTicket;

import com.carmel.guestjini.Networking.Tickets.Ticket;
import com.carmel.guestjini.Networking.Tickets.TicketCategory;
import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

import java.util.List;

public interface CreateTicketViewMVC extends ObservableViewMvc<CreateTicketViewMVC.Listener> {


    public interface Listener {
        void onCreateTicketClicked(String subject, String narration);

        void onSaveDraftClicked(String subject, String narration);

        void onBackToCategoryClicked();

        void onDeleteDraftClicked();

        void onBackClicked();

        void onAttachmentClicked();
    }

    boolean isValid();

    void showProgressIndication();

    void hideProgressIndication();

    void bindTicketCategoryData(List<TicketCategory> ticketCategories);

    void showDraftSaved();

    void showTicketSaved();

    void showTicketDeleted();

    void bindTicket(Ticket taskTicket);

    void showTicketFetchFailed();

}
