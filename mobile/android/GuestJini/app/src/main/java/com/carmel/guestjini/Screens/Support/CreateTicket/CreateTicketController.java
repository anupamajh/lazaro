package com.carmel.guestjini.Screens.Support.CreateTicket;

import com.carmel.guestjini.Networking.Tickets.Ticket;
import com.carmel.guestjini.Networking.Tickets.TicketCategory;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsEventBus;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsManager;
import com.carmel.guestjini.Screens.Common.Dialogs.PromptDialog.PromptDialogEvent;
import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;
import com.carmel.guestjini.Tickets.SaveTicketUseCase;
import com.google.gson.GsonBuilder;

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
    private TicketCategory parentTicketCategory = null;

    private CreateTicketViewMVC viewMVC;
    private ScreenState mScreenState = ScreenState.IDLE;

    private String strSubject;
    private String strNarration;
    private String ticketCategoryId;
    private int saveStatus = 0;

    private TicketCategory ticketCategory;

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

    public void onStart(String ticketCategoryData) {
        parentTicketCategory = new GsonBuilder().create().fromJson(ticketCategoryData, TicketCategory.class);
        viewMVC.registerListener(this);
        saveTicketUseCase.registerListener(this);
        viewMVC.bindTicketCategoryData(parentTicketCategory);
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
            viewMVC.showProgressIndication();
            String ticketCategoryId = "";
            if (parentTicketCategory != null) {
                ticketCategoryId = parentTicketCategory.getId();
                subject = parentTicketCategory.getCategoryDescription();
                if (parentTicketCategory.getChild() != null) {
                    ticketCategoryId = parentTicketCategory.getChild().getId();
                    narration = parentTicketCategory.getChild().getCategoryDescription() + "\n" + narration;
                }
            }
            this.ticketCategoryId = ticketCategoryId;
            this.strSubject = subject;
            this.strNarration = narration;
            this.saveStatus = 3;
            saveTicketUseCase.saveTicketAndNotify(subject, narration, ticketCategoryId, saveStatus);
        }
    }

    @Override
    public void onSaveDraftClicked(String subject, String narration) {
        if (viewMVC.isValid()) {
            viewMVC.showProgressIndication();
            String ticketCategoryId = "";
            if (parentTicketCategory != null) {
                ticketCategoryId = parentTicketCategory.getId();
                subject = parentTicketCategory.getCategoryDescription();
                if (parentTicketCategory.getChild() != null) {
                    ticketCategoryId = parentTicketCategory.getChild().getId();
                    narration = parentTicketCategory.getChild().getCategoryDescription() + "\n" + narration;
                }
            }
            this.ticketCategoryId = ticketCategoryId;
            this.strSubject = subject;
            this.strNarration = narration;
            this.saveStatus = 0;
            saveTicketUseCase.saveTicketAndNotify(subject, narration, ticketCategoryId, saveStatus);
        }
    }

    @Override
    public void onBackToCategoryClicked() {
        String ticketCategoryData = new GsonBuilder().create().toJson(parentTicketCategory, TicketCategory.class);
        screensNavigator.toTicketCategoryList(parentTicketCategory.getId(), ticketCategoryData);
    }

    @Override
    public void onBackClicked() {
        screensNavigator.navigateUp();
    }

    @Override
    public void onTicketSaved(Ticket ticket) {
        viewMVC.hideProgressIndication();
        screensNavigator.toTicketList(this.saveStatus);
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
                    saveTicketUseCase.saveTicketAndNotify(strSubject, strNarration, ticketCategoryId, saveStatus);
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
