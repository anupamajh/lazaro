package com.carmel.guestjini.Screens.Support.InboxTicketDetail;

import androidx.fragment.app.FragmentManager;

import com.carmel.guestjini.Networking.Tickets.TaskAssigneeResponse;
import com.carmel.guestjini.Networking.Tickets.TaskRunnerResponse;
import com.carmel.guestjini.Networking.Tickets.TicketResponse;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsEventBus;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsManager;
import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;
import com.carmel.guestjini.Screens.Support.AssignTicketSheet.AssignTicketSheetFragment;
import com.carmel.guestjini.Screens.Support.AssignTicketToAgentSheet.AssignTicketToAgentSheetFragment;
import com.carmel.guestjini.Screens.Support.CloseTicketSheet.CloseTicketSheetFragment;
import com.carmel.guestjini.Tickets.FetchTicketAssigneeDetailsUseCase;
import com.carmel.guestjini.Tickets.FetchTicketUseCase;
import com.carmel.guestjini.Tickets.WithdrawTicketFromAgentUseCase;
import com.carmel.guestjini.Tickets.WithdrawTicketFromGroupUseCase;

import java.io.Serializable;

public class InboxTicketDetailController
        implements InboxTicketDetailViewMVC.Listener,
        FetchTicketUseCase.Listener,
        FetchTicketAssigneeDetailsUseCase.Listener,
        WithdrawTicketFromAgentUseCase.Listener,
        WithdrawTicketFromGroupUseCase.Listener
{



    private enum ScreenState {
        IDLE, FETCHING_TICKET, TICKET_SHOWN, SAVING_TICKET_NOTES, TICKET_NOTES_SAVED, FETCHING_TICKET_NOTES, TICKET_NOTES_SHOWN, NETWORK_ERROR
    }

    private final FetchTicketUseCase fetchTicket;
    private final FetchTicketAssigneeDetailsUseCase fetchTicketAssigneeDetailsUseCase;
    private final WithdrawTicketFromAgentUseCase withdrawTicketFromAgentUseCase;
    private final WithdrawTicketFromGroupUseCase withdrawTicketFromGroupUseCase;
    private final ScreensNavigator screensNavigator;
    private final DialogsManager dialogsManager;
    private final DialogsEventBus dialogsEventBus;

    private String ticketId;
    private int inboxType;
    private InboxTicketDetailViewMVC viewMvc;
    private ScreenState mScreenState = ScreenState.IDLE;
    private FragmentManager fragmentManager;

    public InboxTicketDetailController(
            FetchTicketUseCase fetchTicket,
            FetchTicketAssigneeDetailsUseCase fetchTicketAssigneeDetailsUseCase,
            WithdrawTicketFromAgentUseCase withdrawTicketFromAgentUseCase,
            WithdrawTicketFromGroupUseCase withdrawTicketFromGroupUseCase,
            ScreensNavigator screensNavigator,
            DialogsManager dialogsManager,
            DialogsEventBus dialogsEventBus
    ) {
        this.fetchTicket = fetchTicket;
        this.fetchTicketAssigneeDetailsUseCase = fetchTicketAssigneeDetailsUseCase;
        this.withdrawTicketFromAgentUseCase = withdrawTicketFromAgentUseCase;
        this.withdrawTicketFromGroupUseCase = withdrawTicketFromGroupUseCase;
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

    public void onStart(String ticketId, int inboxType) {
        this.ticketId = ticketId;
        this.inboxType = inboxType;
        viewMvc.setupView(inboxType);
        viewMvc.registerListener(this);
        fetchTicket.registerListener(this);
        fetchTicketAssigneeDetailsUseCase.registerListener(this);
        withdrawTicketFromAgentUseCase.registerListener(this);
        withdrawTicketFromGroupUseCase.registerListener(this);
        if (mScreenState != ScreenState.NETWORK_ERROR) {
            fetchTicket(ticketId);
        }
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }


    public void onStop() {
        viewMvc.unregisterListener(this);
        fetchTicket.unregisterListener(this);
        fetchTicketAssigneeDetailsUseCase.unregisterListener(this);
        withdrawTicketFromAgentUseCase.unregisterListener(this);
        withdrawTicketFromGroupUseCase.unregisterListener(this);

    }

    private void fetchTicket(String ticketId) {
        mScreenState = ScreenState.FETCHING_TICKET;
        viewMvc.showProgressIndication();
        fetchTicket.fetchKTicketByIdAndNotify(ticketId);
    }

    public void bindView(InboxTicketDetailViewMVC viewMvc) {
        this.viewMvc = viewMvc;
    }

    @Override
    public void onBackClicked() {
        screensNavigator.navigateUp();
    }

    @Override
    public void onAssignTicketClicked() {
        AssignTicketSheetFragment assignTicketSheetFragment = screensNavigator.getAssignSheetFragment(ticketId);
        assignTicketSheetFragment.show(fragmentManager, "ASSIGN_TICKET_SHEET");
    }

    @Override
    public void onWithdrawFromGroupClicked() {
        viewMvc.showProgressIndication();
        withdrawTicketFromGroupUseCase.withdrawTicketFromGroupAndNotify(ticketId);

    }

    @Override
    public void onAssignTicketToAgentClicked(String groupId, String ticketId) {
        AssignTicketToAgentSheetFragment assignTicketSheetFragment = screensNavigator.getAssignTicketToAgentSheetFragment(groupId, ticketId);
        assignTicketSheetFragment.show(fragmentManager, "ASSIGN_TICKET_TO_AGENT_SHEET");
    }

    @Override
    public void onWithdrawFromAgentClicked() {
        viewMvc.showProgressIndication();
        withdrawTicketFromAgentUseCase.withdrawTicketFromAgentAndNotify(ticketId);
    }

    @Override
    public void onCloseTicketClicked() {
        CloseTicketSheetFragment closeTicketSheetFragment = screensNavigator.getCloseTicketSheetFragment(ticketId);
        closeTicketSheetFragment.show(fragmentManager, "CLOSE_TICKET_SHEET");
    }

    @Override
    public void onTicketFetched(TicketResponse ticketResponse) {
        mScreenState = ScreenState.IDLE;
        this.ticketId = ticketResponse.getTaskTicket().getId();
        viewMvc.bindTicket(ticketResponse.getTaskTicket());
        viewMvc.bindTicketCategories(ticketResponse.getTaskTicketCategories());
        viewMvc.hideProgressIndication();
        fetchTicketAssigneeDetailsUseCase.fetchAssigneeAndNotify(ticketId);
    }

    @Override
    public void onTicketFetchFailed() {
        mScreenState = ScreenState.NETWORK_ERROR;
        viewMvc.hideProgressIndication();
        dialogsManager.showUseCaseFailedDialog("Ticket", null);
    }

    @Override
    public void onNetworkFailed() {
        dialogsManager.showNetworkFailedInfoDialog(null, "Ticket");
    }

    @Override
    public void onTicketAssigneeDetailFetched(TaskAssigneeResponse taskAssigneeResponse) {
        viewMvc.hideProgressIndication();
        viewMvc.bindTicketAssignmentDetails(taskAssigneeResponse);
    }

    @Override
    public void onTicketAssigneeDetailFetchFailed() {
        viewMvc.hideProgressIndication();
    }

    @Override
    public void onTicketWithdrawnFromAgent(TaskRunnerResponse taskAssigneeResponse) {
        viewMvc.hideProgressIndication();
        viewMvc.showTicketWithdrawnFromAgent();
        fetchTicket(ticketId);

    }

    @Override
    public void onTicketWithdrawFromAgentFailed() {
        viewMvc.hideProgressIndication();
        viewMvc.showTicketWithdrawFromAgentFailed();

    }

    @Override
    public void onTicketWithdrawnFromGroup(TaskRunnerResponse taskAssigneeResponse) {
        viewMvc.hideProgressIndication();
        fetchTicket(ticketId);
        viewMvc.showTicketWithdrawnFromGroup();
        fetchTicket(ticketId);
    }

    @Override
    public void onTicketWithdrawFromGroupFailed() {
        viewMvc.hideProgressIndication();
        viewMvc.showTicketWithdrawFromGroupFailed();

    }
}
