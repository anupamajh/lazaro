package com.carmel.guestjini.Screens.Support.TicketCategory;


import com.carmel.guestjini.KnowledgeBase.FetchKBListUseCase;
import com.carmel.guestjini.Networking.KnowledgeBase.KB;
import com.carmel.guestjini.Networking.Tickets.TicketCategory;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsEventBus;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsManager;
import com.carmel.guestjini.Screens.Common.Dialogs.PromptDialog.PromptDialogEvent;
import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;
import com.carmel.guestjini.Screens.Support.KBList.KBListController;
import com.carmel.guestjini.Screens.Support.KBList.KBListViewMVC;
import com.carmel.guestjini.Screens.Support.SupportHome.SupportHomeController;
import com.carmel.guestjini.TicketCategory.FetchTicketCategoryByParentIdUseCase;
import com.google.gson.GsonBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TicketCategoryListController
        implements TicketCategoryListViewMVC.Listener,
        FetchTicketCategoryByParentIdUseCase.Listener,
        DialogsEventBus.Listener {
    private enum ScreenState {
        IDLE, NETWORK_ERROR, FETCHING_TICKET_CATEGORY_LIST, TICKET_CATEGORY_LIST_FETCHED, TICKET_CATEGORY_LIST_FETCH_FAILED
    }

    private final FetchTicketCategoryByParentIdUseCase fetchTicketCategoryByParentIdUseCase;
    private final ScreensNavigator screensNavigator;
    private final DialogsManager dialogsManager;
    private final DialogsEventBus dialogsEventBus;
    private List<TicketCategory> ticketCategories = new ArrayList<>();

    private String parentId = null;
    private TicketCategory parentTicketCategory = null;

    private TicketCategoryListViewMVC viewMvc;

    private ScreenState mScreenState = ScreenState.IDLE;

    public TicketCategoryListController(
            FetchTicketCategoryByParentIdUseCase fetchTicketCategoryByParentIdUseCase,
            ScreensNavigator screensNavigator,
            DialogsManager dialogsManager,
            DialogsEventBus dialogsEventBus
    ) {
        this.fetchTicketCategoryByParentIdUseCase = fetchTicketCategoryByParentIdUseCase;
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

    public void onStart(String parentId, String ticketCategoryData) {
        this.parentId = parentId;
        parentTicketCategory = new GsonBuilder().create().fromJson(ticketCategoryData, TicketCategory.class);
        viewMvc.registerListener(this);
        fetchTicketCategoryByParentIdUseCase.registerListener(this);
        dialogsEventBus.registerListener(this);
        viewMvc.bindTicketCategoryTitle(parentTicketCategory);
        if (mScreenState != ScreenState.NETWORK_ERROR) {
            fetchTicketCategoryListListAndNotify();
        }
    }

    public void onStop() {
        viewMvc.unregisterListener(this);
        dialogsEventBus.unregisterListener(this);
        fetchTicketCategoryByParentIdUseCase.unregisterListener(this);
    }

    private void fetchTicketCategoryListListAndNotify() {
        mScreenState = ScreenState.FETCHING_TICKET_CATEGORY_LIST;
        viewMvc.showProgressIndication();
        fetchTicketCategoryByParentIdUseCase.fetchTicketTicketCategoryListAndNotify(parentId);
    }

    @Override
    public void onDialogEvent(Object event) {
        if (event instanceof PromptDialogEvent) {
            switch (((PromptDialogEvent) event).getClickedButton()) {
                case POSITIVE:
                    fetchTicketCategoryListListAndNotify();
                    break;
                case NEGATIVE:
                    mScreenState = ScreenState.IDLE;
                    break;
            }
        }
    }

    public void bindView(TicketCategoryListViewMVC ticketCategoryListViewMVC) {
        viewMvc = ticketCategoryListViewMVC;
    }

    @Override
    public void onTicketCategoryItemClicked(TicketCategory ticketCategory) {
        parentTicketCategory.setChild(ticketCategory);
        String ticketCategoryData = new GsonBuilder().create().toJson(parentTicketCategory, TicketCategory.class);
        screensNavigator.toCreateTicket(ticketCategoryData);
    }

    @Override
    public void onTicketCategoryListFetched(List<TicketCategory> ticketCategoryList) {
        if(ticketCategoryList.size() == 0){
            String ticketCategoryData = new GsonBuilder().create().toJson(parentTicketCategory, TicketCategory.class);
            screensNavigator.toCreateTicket(ticketCategoryData);
        }else {
            viewMvc.bindTicketCategories(ticketCategoryList);
            viewMvc.hideProgressIndication();
        }
    }

    @Override
    public void onTicketCategoryListFetchFailed() {
        viewMvc.hideProgressIndication();
        dialogsManager.showUseCaseFailedDialog("Ticket Category", null);
    }

    @Override
    public void onNetworkFailed() {
        viewMvc.hideProgressIndication();
        dialogsManager.showNetworkFailedInfoDialog(null, "Ticket Category");
    }

    public static class SavedState implements Serializable {
        private final ScreenState mScreenState;

        public SavedState(ScreenState screenState) {
            mScreenState = screenState;
        }
    }

    @Override
    public void onBackClicked() {
        screensNavigator.toSupportHome();
    }
}
