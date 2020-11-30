package com.carmel.guestjini.Screens.Support.AssignTicketSheet;

import com.carmel.guestjini.Networking.Tickets.TaskAssignee;
import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

import java.util.List;

public interface AssignTicketSheetViewMVC
        extends ObservableViewMvc<AssignTicketSheetViewMVC.Listener> {
    public interface Listener {
        void onAssignTicketClicked(TaskAssignee taskAssignee);
    }


    void showProgressIndication();

    void hideProgressIndication();

    void bindTaskAssignees(List<TaskAssignee> taskAssigneeList);

    void showTicketAssigned();

    void showTicketAssignFailed();

}
