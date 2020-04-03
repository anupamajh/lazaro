package com.carmel.guestjini.service.request.HelpDesk;



import com.carmel.guestjini.service.model.HelpDesk.TaskAttachment;
import com.carmel.guestjini.service.model.HelpDesk.TaskTicket;

import java.util.List;

public class TicketRequest {

    private TaskTicket taskTicket;
    private List<TaskAttachment> taskAttachmentList;

    public TaskTicket getTaskTicket() {
        return taskTicket;
    }

    public void setTaskTicket(TaskTicket taskTicket) {
        this.taskTicket = taskTicket;
    }

    public List<TaskAttachment> getTaskAttachmentList() {
        return taskAttachmentList;
    }

    public void setTaskAttachmentList(List<TaskAttachment> taskAttachmentList) {
        this.taskAttachmentList = taskAttachmentList;
    }
}
