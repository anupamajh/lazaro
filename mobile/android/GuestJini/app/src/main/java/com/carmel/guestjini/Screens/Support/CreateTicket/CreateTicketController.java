package com.carmel.guestjini.Screens.Support.CreateTicket;

import com.carmel.guestjini.Networking.Tickets.Ticket;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsEventBus;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsManager;
import com.carmel.guestjini.Screens.Common.Dialogs.PromptDialog.PromptDialogEvent;
import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;
import com.carmel.guestjini.Tickets.SaveTicketUseCase;

import java.io.Serializable;

public class CreateTicketController
        implements CreateTicketViewMVC.Listener,
        SaveTicketUseCase.Listener,
        DialogsEventBus.Listener {
    private enum ScreenState {
        IDLE, TICKET_SAVED, TICKET_SAVE_FAILED
    }

    private final ScreensNavigator screensNavigator;
    private final SaveTicketUseCase saveTicketUseCase;
    private final DialogsManager dialogsManager;
    private final DialogsEventBus dialogsEventBus;

    private CreateTicketViewMVC viewMVC;
    private ScreenState mScreenState = ScreenState.IDLE;

    private String strSubject;
    private String strNarration;

    public CreateTicketController
            (
                    ScreensNavigator screensNavigator,
                    SaveTicketUseCase saveTicketUseCase,
                    DialogsManager dialogsManager,
                    DialogsEventBus dialogsEventBus
            ) {
        this.screensNavigator = screensNavigator;
        this.saveTicketUseCase = saveTicketUseCase;
        this.dialogsManager = dialogsManager;
        this.dialogsEventBus = dialogsEventBus;
    }

    public void onStart() {
        viewMVC.registerListener(this);
        saveTicketUseCase.registerListener(this);
    }

    public void onStop() {
        viewMVC.unregisterListener(this);
        saveTicketUseCase.unregisterListener(this);
    }

    public void bindView(CreateTicketViewMVC createTicketViewMVC) {
        viewMVC = createTicketViewMVC;
    }


    public Serializable getSavedState() {
        return new SavedState(mScreenState);
    }

    public void restoreSavedState(SavedState savedState) {
        mScreenState = savedState.mScreenState;
    }

    @Override
    public void onCreateTicketClicked(String subject, String narration) {
        if (viewMVC.isValid()) {
            this.strSubject = subject;
            this.strNarration = narration;
            viewMVC.showProgressIndication();
            saveTicketUseCase.saveTicketAndNotify(subject, narration);
        }
    }

    @Override
    public void onBackClicked() {
        screensNavigator.navigateUp();
    }

    @Override
    public void onTicketSaved(Ticket ticket) {
        viewMVC.hideProgressIndication();
        screensNavigator.toTicketList();
    }

    @Override
    public void onTicketSaveFailed() {
        viewMVC.hideProgressIndication();
        dialogsManager.showUseCaseFailedDialog("Save Ticket", null);

    }

    @Override
    public void onNetworkFailed() {
        viewMVC.hideProgressIndication();
        dialogsManager.showNetworkFailedInfoDialog(null, "Save Ticket");
    }

    @Override
    public void onDialogEvent(Object event) {
        if (event instanceof PromptDialogEvent) {
            switch (((PromptDialogEvent) event).getClickedButton()) {
                case POSITIVE:
                    saveTicketUseCase.saveTicketAndNotify(strSubject, strNarration);
                    break;
                case NEGATIVE:
                    mScreenState = ScreenState.IDLE;
                    break;
            }
        }
    }

    public static class SavedState implements Serializable {
        private final ScreenState mScreenState;

        public SavedState(ScreenState screenState) {
            mScreenState = screenState;
        }
    }

}
