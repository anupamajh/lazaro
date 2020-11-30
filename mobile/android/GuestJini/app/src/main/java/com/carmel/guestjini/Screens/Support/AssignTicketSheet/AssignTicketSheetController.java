package com.carmel.guestjini.Screens.Support.AssignTicketSheet;

import com.carmel.guestjini.Networking.Tickets.TaskAssignee;
import com.carmel.guestjini.Networking.Tickets.TaskAssigneeResponse;
import com.carmel.guestjini.Networking.Tickets.TaskRunnerResponse;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsEventBus;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsManager;
import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;
import com.carmel.guestjini.Tickets.AssignTaskTicketUseCase;
import com.carmel.guestjini.Tickets.FetchTaskAssigneeUseCase;

import java.io.Serializable;

public class AssignTicketSheetController
        implements AssignTicketSheetViewMVC.Listener,
        FetchTaskAssigneeUseCase.Listener,
        AssignTaskTicketUseCase.Listener {

    private String ticketId;

    private enum ScreenState {
        IDLE, FETCHING_TICKET_ASSIGNEE, TICKET_SHOWN, SAVING_TICKET_NOTES, TICKET_NOTES_SAVED, FETCHING_TICKET_NOTES, TICKET_NOTES_SHOWN, NETWORK_ERROR
    }

    private final FetchTaskAssigneeUseCase fetchTaskAssigneeUseCase;
    private final AssignTaskTicketUseCase assignTaskTicketUseCase;
    private final ScreensNavigator screensNavigator;
    private final DialogsManager dialogsManager;
    private final DialogsEventBus dialogsEventBus;

    private AssignTicketSheetViewMVC viewMvc;
    private ScreenState mScreenState = ScreenState.IDLE;

    public AssignTicketSheetController(
            FetchTaskAssigneeUseCase fetchTaskAssigneeUseCase,
            AssignTaskTicketUseCase assignTaskTicketUseCase,
            ScreensNavigator screensNavigator,
            DialogsManager dialogsManager,
            DialogsEventBus dialogsEventBus
    ) {
        this.fetchTaskAssigneeUseCase = fetchTaskAssigneeUseCase;
        this.assignTaskTicketUseCase = assignTaskTicketUseCase;
        this.screensNavigator = screensNavigator;
        this.dialogsManager = dialogsManager;
        this.dialogsEventBus = dialogsEventBus;
    }

    public SavedState getSavedState() {
        return new SavedState(mScreenState);
    }

    public void restoreSavedState(SavedState savedState) {
        mScreenState = savedState.mScreenState;
    }

    public static class SavedState implements Serializable {
        private final ScreenState mScreenState;

        public SavedState(ScreenState screenState) {
            mScreenState = screenState;
        }
    }

    public void onStart(String ticketId) {
        this.ticketId = ticketId;
        viewMvc.registerListener(this);
        fetchTaskAssigneeUseCase.registerListener(this);
        assignTaskTicketUseCase.registerListener(this);
        if (mScreenState == ScreenState.IDLE) {
            fetchTaskAssigneeAndNotify();
        }

    }

    private void fetchTaskAssigneeAndNotify() {
        viewMvc.showProgressIndication();
        fetchTaskAssigneeUseCase.fetchAssigneeAndNotify();

    }

    public void onStop() {
        viewMvc.unregisterListener(this);
        assignTaskTicketUseCase.unregisterListener(this);
        fetchTaskAssigneeUseCase.unregisterListener(this);

    }

    public void bindView(AssignTicketSheetViewMVC viewMvc) {
        this.viewMvc = viewMvc;
    }

    @Override
    public void onAssignTicketClicked(TaskAssignee taskAssignee) {
        viewMvc.showProgressIndication();
        assignTaskTicketUseCase
                .saveTaskNoteAndNotify(
                        taskAssignee.getId(),
                        ticketId,
                        taskAssignee.getIsGroup()
                );

    }

    @Override
    public void onTaskAssigneeFetched(TaskAssigneeResponse taskAssigneeResponse) {
        viewMvc.hideProgressIndication();
        viewMvc.bindTaskAssignees(taskAssigneeResponse.getTaskAssigneeList());

    }

    @Override
    public void onTaskAssigneeFetchFailed() {
        viewMvc.hideProgressIndication();
    }

    @Override
    public void onNetworkFailed() {
        viewMvc.hideProgressIndication();
    }

    @Override
    public void onTicketAssigned(TaskRunnerResponse taskAssigneeResponse) {
        viewMvc.hideProgressIndication();
        viewMvc.showTicketAssigned();
    }

    @Override
    public void onAssignTicketFailed() {
        viewMvc.showTicketAssignFailed();
    }
}
