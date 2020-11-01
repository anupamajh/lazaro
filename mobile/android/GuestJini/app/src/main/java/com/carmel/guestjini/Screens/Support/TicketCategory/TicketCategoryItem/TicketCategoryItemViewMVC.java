package com.carmel.guestjini.Screens.Support.TicketCategory.TicketCategoryItem;

import com.carmel.guestjini.Networking.Tickets.TicketCategory;
import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

public interface TicketCategoryItemViewMVC  extends ObservableViewMvc<TicketCategoryItemViewMVC.Listener> {


    public interface Listener {
        void onTicketCategoryItemClicked(TicketCategory ticketCategory);
    }

    void bindTicketCategory(TicketCategory ticketCategory);
    void removeBorder(boolean b);

}
