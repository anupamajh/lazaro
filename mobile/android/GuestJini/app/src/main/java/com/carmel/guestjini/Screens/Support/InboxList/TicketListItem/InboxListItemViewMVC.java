package com.carmel.guestjini.Screens.Support.InboxList.TicketListItem;

import com.carmel.guestjini.Networking.Tickets.Ticket;
import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

public interface InboxListItemViewMVC extends ObservableViewMvc<InboxListItemViewMVC.Listener> {


    public interface Listener {
        void onTicketClicked(Ticket ticket);
    }

    void bindTicket(Ticket ticket);
}
