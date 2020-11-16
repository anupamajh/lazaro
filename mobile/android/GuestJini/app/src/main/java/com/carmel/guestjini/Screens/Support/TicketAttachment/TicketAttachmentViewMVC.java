package com.carmel.guestjini.Screens.Support.TicketAttachment;

import com.carmel.guestjini.Networking.Tickets.TaskAttachment;
import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

import java.util.List;

public interface TicketAttachmentViewMVC
        extends ObservableViewMvc<TicketAttachmentViewMVC.Listener> {
    public interface Listener {
        void onBackClicked();

        void onShowAttachmentOptionClicked();

        void onAttachmentClicked(TaskAttachment taskAttachment);

        void onDeleteClicked(TaskAttachment taskAttachment);

        void onCameraClicked();

        void onGalleryClicked();

        void onDocumentsClicked();
    }

    void bindAttachments(List<TaskAttachment> taskAttachmentList);

    void showProgressIndication();

    void hideProgressIndication();
}
