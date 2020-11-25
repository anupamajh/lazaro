package com.carmel.guestjini.Screens.Support.InboxTicketDetail.InboxTicketCategoryItem;

import com.carmel.guestjini.Networking.Tickets.TicketCategory;
import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

public interface InboxTicketCategoryItemViewMVC  extends ObservableViewMvc<InboxTicketCategoryItemViewMVC.Listener> {
    public interface Listener {
    }

    void bindTicketCategory(TicketCategory ticketCategory);
    void hideSeparator(boolean hide);
}
