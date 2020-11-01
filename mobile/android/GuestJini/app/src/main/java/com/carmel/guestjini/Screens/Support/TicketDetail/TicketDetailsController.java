package com.carmel.guestjini.Screens.Support.TicketDetail;

import com.carmel.guestjini.Networking.Tickets.TaskNote;
import com.carmel.guestjini.Networking.Tickets.Ticket;
import com.carmel.guestjini.Networking.Tickets.TicketFeedBackResponse;
import com.carmel.guestjini.Networking.Tickets.TicketResponse;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsEventBus;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsManager;
import com.carmel.guestjini.Screens.Common.Dialogs.PromptDialog.PromptDialogEvent;
import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;
import com.carmel.guestjini.Tickets.FetchTicketTaskNoteListUseCase;
import com.carmel.guestjini.Tickets.FetchTicketUseCase;
import com.carmel.guestjini.Tickets.GetTicketFeedBackUseCase;
import com.carmel.guestjini.Tickets.SaveTaskNoteUseCase;
import com.carmel.guestjini.Tickets.SaveTicketFeedbackUseCase;

import java.io.Serializable;
import java.util.List;

public class TicketDetailsController
        implements TicketDetailsViewMVC.Listener,
        FetchTicketUseCase.Listener,
        FetchTicketTaskNoteListUseCase.Listener,
        GetTicketFeedBackUseCase.Listener,
        SaveTicketFeedbackUseCase.Listener,
        SaveTaskNoteUseCase.Listener,
        DialogsEventBus.Listener {

    private enum ScreenState {
        IDLE, FETCHING_TICKET, TICKET_SHOWN, SAVING_TICKET_NOTES, TICKET_NOTES_SAVED, FETCHING_TICKET_NOTES, TICKET_NOTES_SHOWN, NETWORK_ERROR
    }


    private final FetchTicketUseCase fetchTicket;
    private final FetchTicketTaskNoteListUseCase fetchTicketTaskNoteListUseCase;
    private final SaveTaskNoteUseCase saveTaskNoteUseCase;
    private final GetTicketFeedBackUseCase getTicketFeedBackUseCase;
    private final SaveTicketFeedbackUseCase saveTicketFeedbackUseCase;
    private final ScreensNavigator screensNavigator;
    private final DialogsManager dialogsManager;
    private final DialogsEventBus dialogsEventBus;

    private String ticketId;

    private TicketDetailsViewMVC viewMvc;
    private ScreenState mScreenState = ScreenState.IDLE;

    public TicketDetailsController(
            FetchTicketUseCase fetchTicket,
            FetchTicketTaskNoteListUseCase fetchTicketTaskNoteListUseCase,
            SaveTaskNoteUseCase saveTaskNoteUseCase,
            GetTicketFeedBackUseCase getTicketFeedBackUseCase,
            SaveTicketFeedbackUseCase saveTicketFeedbackUseCase,
            ScreensNavigator screensNavigator,
            DialogsManager dialogsManager,
            DialogsEventBus dialogsEventBus
    ) {
        this.fetchTicket = fetchTicket;
        this.fetchTicketTaskNoteListUseCase = fetchTicketTaskNoteListUseCase;
        this.saveTaskNoteUseCase = saveTaskNoteUseCase;
        this.getTicketFeedBackUseCase = getTicketFeedBackUseCase;
        this.saveTicketFeedbackUseCase = saveTicketFeedbackUseCase;
        this.screensNavigator = screensNavigator;
        this.dialogsManager = dialogsManager;
        this.dialogsEventBus = dialogsEventBus;
    }

    public void bindView(TicketDetailsViewMVC viewMvc) {
        this.viewMvc = viewMvc;
    }

    public SavedState getSavedState() {
        return new SavedState(mScreenState);
    }

    public void restoreSavedState(SavedState savedState) {
        mScreenState = savedState.mScreenState;
    }

    public void onStart(String ticketId) {
        this.ticketId = ticketId;
        viewMvc.registerListener(this);
        fetchTicket.registerListener(this);
        fetchTicketTaskNoteListUseCase.registerListener(this);
        saveTaskNoteUseCase.registerListener(this);
        getTicketFeedBackUseCase.registerListener(this);
        saveTicketFeedbackUseCase.registerListener(this);
        dialogsEventBus.registerListener(this);

        if (mScreenState != ScreenState.NETWORK_ERROR) {
            fetchTicket(ticketId);
        }
    }


    public void onStop() {
        viewMvc.unregisterListener(this);
        fetchTicket.unregisterListener(this);
        fetchTicketTaskNoteListUseCase.unregisterListener(this);
        saveTaskNoteUseCase.unregisterListener(this);
        getTicketFeedBackUseCase.unregisterListener(this);
        saveTicketFeedbackUseCase.unregisterListener(this);
        dialogsEventBus.unregisterListener(this);
    }

    private void fetchTicket(String ticketId) {
        mScreenState = ScreenState.FETCHING_TICKET;
        viewMvc.showProgressIndication();
        fetchTicket.fetchKTicketByIdAndNotify(ticketId);
    }

    private void fetchTicketComments(String ticketId) {
        mScreenState = ScreenState.FETCHING_TICKET_NOTES;
        viewMvc.showProgressIndication();
        fetchTicketTaskNoteListUseCase.fetchTicketNotesListAndNotify(ticketId);
    }


    @Override
    public void onDialogEvent(Object event) {
        if (event instanceof PromptDialogEvent) {
            switch (((PromptDialogEvent) event).getClickedButton()) {
                case POSITIVE:
                    fetchTicket(ticketId);
                    break;
                case NEGATIVE:
                    mScreenState = ScreenState.IDLE;
                    break;
            }
        }
    }

    @Override
    public void onBackClicked() {
        screensNavigator.navigateUp();
    }

    @Override
    public void onSubmitClicked(String ticketId, String comment) {
        mScreenState = ScreenState.SAVING_TICKET_NOTES;
        viewMvc.showProgressIndication();
        saveTaskNoteUseCase.saveTaskNoteAndNotify(ticketId, comment);
    }

    @Override
    public void onTicketFetched(TicketResponse ticketResponse) {
        mScreenState = ScreenState.IDLE;
        this.ticketId = ticketResponse.getTaskTicket().getId();
        viewMvc.bindTicket(ticketResponse.getTaskTicket());
        viewMvc.bindTicketCategories(ticketResponse.getTaskTicketCategories());
        viewMvc.hideProgressIndication();
        fetchTicketComments(ticketResponse.getTaskTicket().getId());
    }

    @Override
    public void onTicketFetchFailed() {
        mScreenState = ScreenState.NETWORK_ERROR;
        viewMvc.hideProgressIndication();
        dialogsManager.showUseCaseFailedDialog("Ticket", null);
    }

    @Override
    public void onTaskNoteListFetched(List<TaskNote> taskNoteList) {
        viewMvc.bindTaskNotes(taskNoteList);
        getTicketFeedBackUseCase.fetchKTicketFeedbackByIdAndNotify(this.ticketId);
    }

    @Override
    public void onTaskNoteListFetchFailed() {
        mScreenState = ScreenState.NETWORK_ERROR;
        viewMvc.hideProgressIndication();
        dialogsManager.showUseCaseFailedDialog("Ticket Notes", null);
    }

    @Override
    public void onNetworkFailed() {
        dialogsManager.showNetworkFailedInfoDialog(null, "Ticket");
    }

    @Override
    public void onTaskNoteSaved(TaskNote taskNote) {
        mScreenState = ScreenState.IDLE;
        viewMvc.hideProgressIndication();
        viewMvc.clearComment();
        viewMvc.showTaskNotesSaved();
        fetchTicketComments(ticketId);
    }

    @Override
    public void onTaskNoteSaveFailed() {
        mScreenState = ScreenState.NETWORK_ERROR;
        viewMvc.hideProgressIndication();
        viewMvc.showTaskNotesSaveFailed();
        dialogsManager.showUseCaseFailedDialog("Ticket Notes", null);
    }

    public static class SavedState implements Serializable {
        private final ScreenState mScreenState;

        public SavedState(ScreenState screenState) {
            mScreenState = screenState;
        }
    }

    @Override
    public void onSubmitFeedbackClicked(int rating, String feedback) {
        viewMvc.showProgressIndication();
        saveTicketFeedbackUseCase.saveTicketFeedbackAndNotify(
                ticketId,
                rating,
                feedback
        );
    }

    @Override
    public void onTicketFeedbackFetched(TicketFeedBackResponse ticketFeedBackResponse) {
        viewMvc.hideProgressIndication();
        viewMvc.bindTicketFeedback(ticketFeedBackResponse);
    }

    @Override
    public void onTicketFeedbackFetchFailed() {
        viewMvc.hideProgressIndication();

    }

    @Override
    public void onTicketFeedbackSaved(TicketFeedBackResponse ticketFeedBackResponse) {
        viewMvc.hideProgressIndication();
        if(ticketFeedBackResponse.isSuccess()) {
            viewMvc.showFeedbackSaved();
            viewMvc.bindTicketFeedback(ticketFeedBackResponse);
            screensNavigator.toSupportHome();
        }else{
            viewMvc.showFeedbackSaveFailed();
        }
    }

    @Override
    public void onTicketFeedbackSaveFailed() {
        viewMvc.hideProgressIndication();

    }
}
