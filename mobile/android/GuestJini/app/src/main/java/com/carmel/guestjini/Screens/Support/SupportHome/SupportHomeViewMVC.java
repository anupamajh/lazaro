package com.carmel.guestjini.Screens.Support.SupportHome;

import com.carmel.guestjini.Networking.Tickets.TicketCategory;
import com.carmel.guestjini.Networking.Tickets.TicketCountDTO;
import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

import java.util.List;

public interface SupportHomeViewMVC extends ObservableViewMvc<SupportHomeViewMVC.Listener> {

    public interface Listener {

        void onActiveTicketClicked();

        void onDraftTicketClicked();

        void onArchivedTicketsClicked();

        void onTicketCategoryItemClicked(TicketCategory ticketCategory);

        void onKBSearchClicked(String searchText);

        void onKBExploreClicked();

        void onCreateTicketClicked();

        void onGotoTicketsClicked();
    }

    void bindTicketCategories(List<TicketCategory> ticketCategoryList);

    void showProgressIndication();

    void hideProgressIndication();

    void showActiveTicketLayout(boolean show);

    void showDraftTicketLayout(boolean show);

    void showArchiveTicketLayout(boolean show);

    void bindTicketCounts(TicketCountDTO ticketCountDTO);

}
