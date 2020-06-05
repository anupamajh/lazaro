package com.carmel.guestjini.Networking.Tickets;

import java.util.List;

public class TicketRequest {
    private Ticket taskTicket;
    private List<TaskAttachment> taskAttachmentList;

    public Ticket getTaskTicket() {
        return taskTicket;
    }

    public void setTaskTicket(Ticket taskTicket) {
        this.taskTicket = taskTicket;
    }

    public List<TaskAttachment> getTaskAttachmentList() {
        return taskAttachmentList;
    }

    public void setTaskAttachmentList(List<TaskAttachment> taskAttachmentList) {
        this.taskAttachmentList = taskAttachmentList;
    }
}
