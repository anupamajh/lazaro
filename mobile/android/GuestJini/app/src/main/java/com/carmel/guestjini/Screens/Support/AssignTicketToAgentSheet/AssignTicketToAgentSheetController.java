package com.carmel.guestjini.Screens.Support.AssignTicketToAgentSheet;

import com.carmel.guestjini.Networking.Tickets.TaskAssignee;
import com.carmel.guestjini.Networking.Tickets.TaskAssigneeResponse;
import com.carmel.guestjini.Networking.Tickets.TaskRunnerResponse;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsEventBus;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsManager;
import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;
import com.carmel.guestjini.Tickets.AssignTaskTicketUseCase;
import com.carmel.guestjini.Tickets.FetchTaskAssigneeByGroupUseCase;

import java.io.Serializable;

public class AssignTicketToAgentSheetController
        implements AssignTicketToAgentSheetViewMVC.Listener,
        FetchTaskAssigneeByGroupUseCase.Listener,
        AssignTaskTicketUseCase.Listener {

    private String ticketId;
    private String groupId;

    private enum ScreenState {
        IDLE, FETCHING_TICKET_ASSIGNEE, TICKET_SHOWN, SAVING_TICKET_NOTES, TICKET_NOTES_SAVED, FETCHING_TICKET_NOTES, TICKET_NOTES_SHOWN, NETWORK_ERROR
    }

    private final FetchTaskAssigneeByGroupUseCase fetchTaskAssigneeByGroupUseCase;
    private final AssignTaskTicketUseCase assignTaskTicketUseCase;
    private final ScreensNavigator screensNavigator;
    private final DialogsManager dialogsManager;
    private final DialogsEventBus dialogsEventBus;
    private final AssignTicketToAgentEventBus assignTicketToAgentEventBus;

    private AssignTicketToAgentSheetViewMVC viewMvc;
    private ScreenState mScreenState = ScreenState.IDLE;

    public AssignTicketToAgentSheetController(
            FetchTaskAssigneeByGroupUseCase fetchTaskAssigneeByGroupUseCase,
            AssignTaskTicketUseCase assignTaskTicketUseCase,
            ScreensNavigator screensNavigator,
            DialogsManager dialogsManager,
            DialogsEventBus dialogsEventBus,
            AssignTicketToAgentEventBus assignTicketToAgentEventBus
    ) {
        this.fetchTaskAssigneeByGroupUseCase = fetchTaskAssigneeByGroupUseCase;
        this.assignTaskTicketUseCase = assignTaskTicketUseCase;
        this.screensNavigator = screensNavigator;
        this.dialogsManager = dialogsManager;
        this.dialogsEventBus = dialogsEventBus;
        this.assignTicketToAgentEventBus = assignTicketToAgentEventBus;
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

    public void onStart(String groupId, String ticketId) {
        this.ticketId = ticketId;
        this.groupId = groupId;
        viewMvc.registerListener(this);
        fetchTaskAssigneeByGroupUseCase.registerListener(this);
        assignTaskTicketUseCase.registerListener(this);
        if (mScreenState == ScreenState.IDLE) {
            fetchTaskAssigneeAndNotify();
        }

    }

    private void fetchTaskAssigneeAndNotify() {
        viewMvc.showProgressIndication();
        assignTicketToAgentEventBus.postEvent(new AssignTicketToAgentEvent(AssignTicketToAgentEvent.Status.FETCHING_ASSIGNEE));
        fetchTaskAssigneeByGroupUseCase.fetchAssigneeAndNotify(groupId, ticketId);

    }

    public void onStop() {
        viewMvc.unregisterListener(this);
        assignTaskTicketUseCase.unregisterListener(this);
        fetchTaskAssigneeByGroupUseCase.unregisterListener(this);

    }

    public void bindView(AssignTicketToAgentSheetViewMVC viewMvc) {
        this.viewMvc = viewMvc;
    }

    @Override
    public void onAssignTicketClicked(TaskAssignee taskAssignee) {
        viewMvc.showProgressIndication();
        assignTicketToAgentEventBus.postEvent(new AssignTicketToAgentEvent(AssignTicketToAgentEvent.Status.ASSIGNING));
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
        assignTicketToAgentEventBus.postEvent(new AssignTicketToAgentEvent(AssignTicketToAgentEvent.Status.ASSIGNEE_FETCHED));

    }

    @Override
    public void onTaskAssigneeFetchFailed() {
        viewMvc.hideProgressIndication();
        assignTicketToAgentEventBus.postEvent(new AssignTicketToAgentEvent(AssignTicketToAgentEvent.Status.FAILED));

    }

    @Override
    public void onNetworkFailed() {
        viewMvc.hideProgressIndication();
        assignTicketToAgentEventBus.postEvent(new AssignTicketToAgentEvent(AssignTicketToAgentEvent.Status.FAILED));
    }

    @Override
    public void onTicketAssigned(TaskRunnerResponse taskAssigneeResponse) {
        viewMvc.hideProgressIndication();
        viewMvc.showTicketAssigned();
        assignTicketToAgentEventBus.postEvent(new AssignTicketToAgentEvent(AssignTicketToAgentEvent.Status.ASSIGNED));
    }

    @Override
    public void onAssignTicketFailed() {
        viewMvc.showTicketAssignFailed();
        assignTicketToAgentEventBus.postEvent(new AssignTicketToAgentEvent(AssignTicketToAgentEvent.Status.FAILED));
    }


}
