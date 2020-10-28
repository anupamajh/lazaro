package com.carmel.guestjini.service.common.HelpDesk;

public abstract class TicketStatus {
    public final static int  DRAFT = 0; // Draft
    public final static int  COMPLETED = 1; // Active
    public final static int  WORK_IN_PROGRESS = 2; // Active
    public final static int  NOT_STARTED = 3; // Active
    public final static int ON_HOLD = 4; // Active
    public final static int CLOSED = 5; // Archive

}
