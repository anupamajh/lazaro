package com.carmel.guestjini.Screens.Support.TicketList.TicketListItem;

import com.carmel.guestjini.Networking.Tickets.Ticket;
import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

public interface TicketListItemViewMVC extends ObservableViewMvc<TicketListItemViewMVC.Listener> {


    public interface Listener {
        void onTicketClicked(Ticket ticket);
    }

    void bindTicket(Ticket ticket);
}
