package com.carmel.guestjini.Screens.Support.TicketDetail.TicketComments;

import com.carmel.guestjini.Networking.Tickets.TaskNote;
import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

public interface TicketCommentsViewMVC extends ObservableViewMvc<TicketCommentsViewMVC.Listener> {

    public interface Listener {
    }

    void bindTaskNote(TaskNote taskNote);
}
