package com.carmel.guestjini.Screens.Support.InboxList;

import com.carmel.guestjini.Common.Search.SearchRequest;
import com.carmel.guestjini.Networking.Tickets.Ticket;
import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

import java.util.List;

public interface InboxListViewMVC extends ObservableViewMvc<InboxListViewMVC.Listener> {

    public interface Listener {
        void onTicketClicked(Ticket ticket);

        void onSearchClicked(String searchText);

        void onBackClicked();

        void onCreateTicketClicked();

        void onFilterApplied(SearchRequest searchRequest);
    }

    void bindTickets(List<Ticket> ticketList, int totalItems);

    void bindListTitle(int inboxType);

    void showProgressIndication();

    void hideProgressIndication();
}
