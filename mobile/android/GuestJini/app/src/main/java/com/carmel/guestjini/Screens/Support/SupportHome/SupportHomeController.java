package com.carmel.guestjini.Screens.Support.SupportHome;

import com.carmel.guestjini.Networking.Tickets.TicketCategory;
import com.carmel.guestjini.Networking.Tickets.TicketCountDTO;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsEventBus;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsManager;
import com.carmel.guestjini.Screens.Common.Dialogs.PromptDialog.PromptDialogEvent;
import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;
import com.carmel.guestjini.TicketCategory.FetchTicketCategoryByParentIdUseCase;
import com.carmel.guestjini.Tickets.FetchTicketCountUseCase;
import com.google.gson.GsonBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SupportHomeController implements
        SupportHomeViewMVC.Listener,
        FetchTicketCategoryByParentIdUseCase.Listener ,
        FetchTicketCountUseCase.Listener,
        DialogsEventBus.Listener
{

    private enum ScreenState {
        IDLE, NETWORK_ERROR, FETCHING_TICKET_CATEGORY_LIST, TICKET_CATEGORY_LIST_FETCHED, TICKET_CATEGORY_LIST_FETCH_FAILED
    }

    private final FetchTicketCategoryByParentIdUseCase fetchTicketCategoryByParentIdUseCase;
    private final FetchTicketCountUseCase fetchTicketCountUseCase;
    private final ScreensNavigator mScreensNavigator;
    private final DialogsManager dialogsManager;
    private final DialogsEventBus dialogsEventBus;

    private SupportHomeViewMVC viewMVC;
    private ScreenState mScreenState = ScreenState.IDLE;


    public SupportHomeController(
            ScreensNavigator mScreensNavigator,
            FetchTicketCategoryByParentIdUseCase fetchTicketCategoryByParentIdUseCase,
            FetchTicketCountUseCase fetchTicketCountUseCase,
            DialogsManager dialogsManager,
            DialogsEventBus dialogsEventBus
    ) {
        this.mScreensNavigator = mScreensNavigator;
        this.fetchTicketCategoryByParentIdUseCase = fetchTicketCategoryByParentIdUseCase;
        this.fetchTicketCountUseCase = fetchTicketCountUseCase;
        this.dialogsManager = dialogsManager;
        this.dialogsEventBus = dialogsEventBus;
    }

    public void onStart() {
        viewMVC.registerListener(this);
        fetchTicketCategoryByParentIdUseCase.registerListener(this);
        fetchTicketCountUseCase.registerListener(this);
        if (mScreenState != ScreenState.NETWORK_ERROR) {
            fetchTicketCountUseCase.fetchTicketCountAndNotify();
        }
    }

    public void onStop() {
        viewMVC.unregisterListener(this);
        fetchTicketCategoryByParentIdUseCase.unregisterListener(this);
        fetchTicketCountUseCase.unregisterListener(this);

    }

    private void fetchTicketCategoryListListAndNotify() {
        mScreenState = ScreenState.FETCHING_TICKET_CATEGORY_LIST;
        viewMVC.showProgressIndication();
        fetchTicketCategoryByParentIdUseCase.fetchTicketTicketCategoryListAndNotify(null);
    }

    public void bindView(SupportHomeViewMVC supportHomeViewMVC) {
        viewMVC = supportHomeViewMVC;
    }


    public Serializable getSavedState() {
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


    @Override
    public void onKBSearchClicked(String searchText) {
        mScreensNavigator.toKBList(searchText);
    }

    @Override
    public void onKBExploreClicked() {
        mScreensNavigator.toKBList();
    }

    @Override
    public void onCreateTicketClicked() {
        //mScreensNavigator.toCreateTicket(ticketCategoryData);
    }

    @Override
    public void onGotoTicketsClicked() {
//        mScreensNavigator.toTicketList();
    }

    @Override
    public void onActiveTicketClicked() {
        mScreensNavigator.toTicketList(3);
    }

    @Override
    public void onDraftTicketClicked() {
        mScreensNavigator.toTicketList(0);
    }

    @Override
    public void onArchivedTicketsClicked() {
        mScreensNavigator.toTicketList(5);
    }

    @Override
    public void onTicketCategoryItemClicked(TicketCategory ticketCategory) {
        List<TicketCategory> ticketCategories = new ArrayList<>();
        ticketCategories.add(ticketCategory);
        String ticketCategoryData = new GsonBuilder().create().toJson(ticketCategories);
        mScreensNavigator.toTicketCategoryList(ticketCategory.getId(), ticketCategoryData);
    }

    @Override
    public void onTicketCategoryListFetched(List<TicketCategory> ticketCategoryList) {
        viewMVC.hideProgressIndication();
        viewMVC.bindTicketCategories(ticketCategoryList);
    }

    @Override
    public void onTicketCategoryListFetchFailed() {
        viewMVC.hideProgressIndication();
        dialogsManager.showUseCaseFailedDialog("Ticket Category", null);
    }

    @Override
    public void onNetworkFailed() {
        viewMVC.hideProgressIndication();
        dialogsManager.showNetworkFailedInfoDialog(null, "Ticket Category");
    }

    @Override
    public void onTicketCountFetched(TicketCountDTO ticketCountDTO) {
        viewMVC.hideProgressIndication();
        if(ticketCountDTO.isSuccess()){
            viewMVC.bindTicketCounts(ticketCountDTO);
        }
        fetchTicketCategoryListListAndNotify();
    }

    @Override
    public void onTicketCountFetchFailed() {
        viewMVC.hideProgressIndication();
        fetchTicketCategoryListListAndNotify();
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
}
