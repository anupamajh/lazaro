package com.carmel.guestjini.Screens.Support.TicketDetail.TaskTicketCategory;

import com.carmel.guestjini.Networking.Tickets.TicketCategory;
import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

public interface TaskTicketCategoryViewMVC extends ObservableViewMvc<TaskTicketCategoryViewMVC.Listener> {
    public interface Listener {
    }

    void bindTicketCategory(TicketCategory ticketCategory);
    void hideSeparator(boolean hide);
}
