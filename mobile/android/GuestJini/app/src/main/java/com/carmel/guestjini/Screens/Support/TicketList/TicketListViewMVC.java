package com.carmel.guestjini.Screens.Support.TicketList;

import com.carmel.guestjini.Networking.Tickets.Ticket;
import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

import java.util.List;

public interface TicketListViewMVC  extends ObservableViewMvc<TicketListViewMVC.Listener> {
    public interface Listener {
        void onTicketClicked(Ticket ticket);
        void onSearchClicked(String searchText);
        void onBackClicked();
        void onCreateTicketClicked();
    }

    void bindTickets(List<Ticket> ticketList, int totalItems);

    void showProgressIndication();

    void hideProgressIndication();
}
