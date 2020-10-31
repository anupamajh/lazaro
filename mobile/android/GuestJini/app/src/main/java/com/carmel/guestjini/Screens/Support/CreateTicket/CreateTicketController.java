package com.carmel.guestjini.Screens.Support.CreateTicket;

import com.carmel.guestjini.Networking.Tickets.Ticket;
import com.carmel.guestjini.Networking.Tickets.TicketCategory;
import com.carmel.guestjini.Networking.Tickets.TicketResponse;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsEventBus;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsManager;
import com.carmel.guestjini.Screens.Common.Dialogs.PromptDialog.PromptDialogEvent;
import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;
import com.carmel.guestjini.Tickets.DeleteTicketUseCase;
import com.carmel.guestjini.Tickets.FetchTicketUseCase;
import com.carmel.guestjini.Tickets.SaveTicketUseCase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CreateTicketController
        implements CreateTicketViewMVC.Listener,
        DeleteTicketUseCase.Listener,
        FetchTicketUseCase.Listener,
        SaveTicketUseCase.Listener,
        DialogsEventBus.Listener {
    private enum ScreenState {
        IDLE, TICKET_SAVED, TICKET_SAVE_FAILED, TICKET_DELETED, TICKET_DELETE_FAILED
    }

    private final ScreensNavigator screensNavigator;
    private final SaveTicketUseCase saveTicketUseCase;
    private final DeleteTicketUseCase deleteTicketUseCase;
    private final FetchTicketUseCase fetchTicketUseCase;
    private final DialogsManager dialogsManager;
    private final DialogsEventBus dialogsEventBus;
    private TicketCategory parentTicketCategory = null;
    private String draftTicketId;

    private CreateTicketViewMVC viewMVC;
    private ScreenState mScreenState = ScreenState.IDLE;

    private String strSubject;
    private String strNarration;
    private String ticketCategoryId;
    private int saveStatus = 0;

    private TicketCategory ticketCategory;
    private List<TicketCategory> ticketCategories;

    public CreateTicketController
            (
                    ScreensNavigator screensNavigator,
                    SaveTicketUseCase saveTicketUseCase,
                    DeleteTicketUseCase deleteTicketUseCase,
                    FetchTicketUseCase fetchTicketUseCase,
                    DialogsManager dialogsManager,
                    DialogsEventBus dialogsEventBus
            ) {
        this.screensNavigator = screensNavigator;
        this.saveTicketUseCase = saveTicketUseCase;
        this.fetchTicketUseCase = fetchTicketUseCase;
        this.dialogsManager = dialogsManager;
        this.dialogsEventBus = dialogsEventBus;
        this.draftTicketId = "";
        this.deleteTicketUseCase = deleteTicketUseCase;
    }

    public void onStart(String ticketCategoryData, String ticketId) {
        viewMVC.registerListener(this);
        fetchTicketUseCase.registerListener(this);
        saveTicketUseCase.registerListener(this);
        deleteTicketUseCase.registerListener(this);
        viewMVC.showProgressIndication();
        this.draftTicketId = ticketId;
        if (mScreenState == ScreenState.IDLE) {
            fetchTicketUseCase.fetchKTicketByIdAndNotify(ticketId);
        }
    }


    @Override
    public void onTicketFetched(TicketResponse ticketResponse) {
        viewMVC.hideProgressIndication();
        ticketCategories = ticketResponse.getTaskTicketCategories();
        if (ticketCategories.size() > 0) {
            this.ticketCategoryId = ticketCategories.get(ticketCategories.size() - 1).getId();
        }
        viewMVC.bindTicket(ticketResponse.getTaskTicket());
        viewMVC.bindTicketCategoryData(this.ticketCategories);
    }

    @Override
    public void onTicketFetchFailed() {
        viewMVC.hideProgressIndication();
        viewMVC.showTicketFetchFailed();
    }

    public void onStart(String ticketCategoryData) {
        Type listType = new TypeToken<ArrayList<TicketCategory>>() {
        }.getType();
        List<TicketCategory> ticketCategoryList = new Gson().fromJson(ticketCategoryData, listType);
        ticketCategories = ticketCategoryList;
        if (ticketCategories.size() > 0) {
            this.ticketCategoryId = ticketCategories.get(ticketCategories.size() - 1).getId();
        }
        viewMVC.registerListener(this);
        saveTicketUseCase.registerListener(this);
        viewMVC.bindTicketCategoryData(ticketCategoryList);
        deleteTicketUseCase.registerListener(this);
        this.draftTicketId = "";
    }

    public void onStop() {
        viewMVC.unregisterListener(this);
        saveTicketUseCase.unregisterListener(this);
        deleteTicketUseCase.unregisterListener(this);
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
//            if (parentTicketCategory != null) {
//                ticketCategoryId = parentTicketCategory.getId();
//                subject = parentTicketCategory.getCategoryDescription();
//                if (parentTicketCategory.getChild() != null) {
//                    subject = parentTicketCategory.getChild().getCategoryDescription();
//                    ticketCategoryId = parentTicketCategory.getChild().getId();
//                    //narration = parentTicketCategory.getChild().getCategoryDescription() + "\n" + narration;
//                }
//            }
            if (ticketCategories.size() > 0) {
                ticketCategoryId = ticketCategories.get(ticketCategories.size() - 1).getId();
                subject = ticketCategories.get(ticketCategories.size() - 1).getCategoryDescription();
            }
            this.ticketCategoryId = ticketCategoryId;
            this.strSubject = subject;
            this.strNarration = narration;
            this.saveStatus = 3;
            saveTicketUseCase.saveTicketAndNotify(subject, narration, ticketCategoryId, saveStatus, draftTicketId);
        }
    }

    @Override
    public void onSaveDraftClicked(String subject, String narration) {
        if (viewMVC.isValid()) {
            viewMVC.showProgressIndication();
            String ticketCategoryId = "";
//            if (parentTicketCategory != null) {
//                ticketCategoryId = parentTicketCategory.getId();
//                subject = parentTicketCategory.getCategoryDescription();
//                if (parentTicketCategory.getChild() != null) {
//                    subject = parentTicketCategory.getChild().getCategoryDescription();
//                    ticketCategoryId = parentTicketCategory.getChild().getId();
//                    //narration = parentTicketCategory.getChild().getCategoryDescription();
//                }
//            }
            if (ticketCategories.size() > 0) {
                ticketCategoryId = ticketCategories.get(ticketCategories.size() - 1).getId();
                subject = ticketCategories.get(ticketCategories.size() - 1).getCategoryDescription();
            }
            this.ticketCategoryId = ticketCategoryId;
            this.strSubject = subject;
            this.strNarration = narration;
            this.saveStatus = 0;
            saveTicketUseCase.saveTicketAndNotify(subject, narration, ticketCategoryId, saveStatus, draftTicketId);
        }
    }

    @Override
    public void onBackToCategoryClicked() {
        if (ticketCategories.size() == 0) {
            screensNavigator.toSupportHome();
        } else {
            String parentId = ticketCategories.get(ticketCategories.size() - 1).getParentId();
            ticketCategories.remove(ticketCategories.size() - 1);
            String ticketCategoryData = new GsonBuilder().create().toJson(ticketCategories);
            screensNavigator.toTicketCategoryList(parentId, ticketCategoryData);
        }
    }

    @Override
    public void onBackClicked() {
        screensNavigator.toSupportHome();
    }

    @Override
    public void onTicketSaved(Ticket ticket) {
        viewMVC.hideProgressIndication();
        this.draftTicketId = ticket.getId();
        if (this.saveStatus != 0) {
            screensNavigator.toTicketList(this.saveStatus);
            viewMVC.showTicketSaved();
        } else {
            viewMVC.showDraftSaved();
        }
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
                    saveTicketUseCase.saveTicketAndNotify(strSubject, strNarration, ticketCategoryId, saveStatus, draftTicketId);
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

    @Override
    public void onDeleteDraftClicked() {
        if (!draftTicketId.equals("")) {
            viewMVC.showProgressIndication();
            deleteTicketUseCase.deleteTicketAndNotify(draftTicketId);
        }
    }

    @Override
    public void onTicketDeleted(Ticket ticket) {
        viewMVC.showTicketDeleted();
        screensNavigator.toSupportHome();
    }

    @Override
    public void onTicketDeleteFailed() {
        viewMVC.hideProgressIndication();
        dialogsManager.showNetworkFailedInfoDialog(null, "Delete Ticket");
    }

}
