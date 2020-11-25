package com.carmel.guestjini.Screens.Support.InboxTicketDetail.InboxTicketComments;

import com.carmel.guestjini.Networking.Tickets.TaskNote;
import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

public interface InboxTicketCommentViewMVC
        extends ObservableViewMvc<InboxTicketCommentViewMVC.Listener> {

    public interface Listener {
    }

    void bindTaskNote(TaskNote taskNote);
}
