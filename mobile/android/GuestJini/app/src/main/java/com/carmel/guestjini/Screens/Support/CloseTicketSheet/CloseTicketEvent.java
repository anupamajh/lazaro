package com.carmel.guestjini.Screens.Support.CloseTicketSheet;

public class CloseTicketEvent {
    public enum Status {
        CLOSING, CLOSED, FAILED
    }

    private final Status mStatus;

    public CloseTicketEvent(Status currentStatus) {
        mStatus = currentStatus;
    }

    public Status getCurrentStatus() {
        return mStatus;
    }
}
