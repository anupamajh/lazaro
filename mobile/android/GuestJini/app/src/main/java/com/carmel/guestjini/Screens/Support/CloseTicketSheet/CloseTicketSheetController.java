package com.carmel.guestjini.Screens.Support.CloseTicketSheet;

import com.carmel.guestjini.Networking.Tickets.TicketResponse;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsEventBus;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsManager;
import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;
import com.carmel.guestjini.Tickets.CloseTicketUseCase;

import java.io.Serializable;

public class CloseTicketSheetController
        implements CloseTicketSheetViewMVC.Listener,
        CloseTicketUseCase.Listener {
    private String ticketId;

    private enum ScreenState {
        IDLE, FETCHING_TICKET_ASSIGNEE, TICKET_SHOWN, SAVING_TICKET_NOTES, TICKET_NOTES_SAVED, FETCHING_TICKET_NOTES, TICKET_NOTES_SHOWN, NETWORK_ERROR
    }

    private final CloseTicketUseCase closeTicketUseCase;
    private final ScreensNavigator screensNavigator;
    private final DialogsManager dialogsManager;
    private final DialogsEventBus dialogsEventBus;

    private CloseTicketSheetViewMVC viewMvc;
    private ScreenState mScreenState = ScreenState.IDLE;

    public CloseTicketSheetController(
            CloseTicketUseCase closeTicketUseCase,
            ScreensNavigator screensNavigator,
            DialogsManager dialogsManager,
            DialogsEventBus dialogsEventBus
    ) {
        this.closeTicketUseCase = closeTicketUseCase;
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
        closeTicketUseCase.registerListener(this);
        viewMvc.registerListener(this);
    }

    public void onStop() {
        closeTicketUseCase.unregisterListener(this);
        viewMvc.unregisterListener(this);
    }

    public void bindView(CloseTicketSheetViewMVC viewMvc) {
        this.viewMvc = viewMvc;
    }

    @Override
    public void onCloseTicketClicked(String message) {
        viewMvc.showProgressIndication();
        closeTicketUseCase.closeTaskNoteAndNotify(this.ticketId, null, message);
    }

    @Override
    public void onTicketClosed(TicketResponse ticketResponse) {
        viewMvc.hideProgressIndication();
        viewMvc.showTicketClosed();
    }

    @Override
    public void onCloseTicketFailed() {
        viewMvc.showTicketCloseFailed();
    }

    @Override
    public void onNetworkFailed() {
        viewMvc.showTicketCloseFailed();
    }
}
