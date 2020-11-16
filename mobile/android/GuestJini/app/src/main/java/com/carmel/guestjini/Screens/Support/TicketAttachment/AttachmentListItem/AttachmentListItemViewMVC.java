package com.carmel.guestjini.Screens.Support.TicketAttachment.AttachmentListItem;

import com.carmel.guestjini.Networking.Tickets.TaskAttachment;
import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

public interface AttachmentListItemViewMVC extends ObservableViewMvc<AttachmentListItemViewMVC.Listener> {
    public interface Listener {
        void onDeleteClicked(TaskAttachment taskAttachment);

        void onAttachmentClicked(TaskAttachment taskAttachment);
    }

    void bindAttachment(TaskAttachment taskAttachment);
}
