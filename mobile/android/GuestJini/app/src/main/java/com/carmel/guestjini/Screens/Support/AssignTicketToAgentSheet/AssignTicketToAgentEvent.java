package com.carmel.guestjini.Screens.Support.AssignTicketToAgentSheet;

public class AssignTicketToAgentEvent {
    public enum Status {
        ASSIGNING, ASSIGNED, FAILED, FETCHING_ASSIGNEE, ASSIGNEE_FETCHED
    }

    private final Status mStatus;

    public AssignTicketToAgentEvent(Status currentStatus) {
        mStatus = currentStatus;
    }

    public Status getCurrentStatus() {
        return mStatus;
    }
}
