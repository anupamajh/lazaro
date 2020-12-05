package com.carmel.guestjini.Screens.Support.AssignTicketSheet;

import com.carmel.guestjini.Screens.Common.Dialogs.PromptDialog.PromptDialogEvent;

public class AssignTicketEvent {
    public enum Status {
        ASSIGNING, ASSIGNED, FAILED, FETCHING_ASSIGNEE, ASSIGNEE_FETCHED
    }

    private final Status mStatus;

    public AssignTicketEvent(Status currentStatus) {
        mStatus = currentStatus;
    }

    public Status getCurrentStatus() {
        return mStatus;
    }
}
