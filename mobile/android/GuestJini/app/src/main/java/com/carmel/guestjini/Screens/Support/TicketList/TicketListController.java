package com.carmel.guestjini.Screens.Support.TicketList;

import com.carmel.guestjini.Networking.Tickets.Ticket;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsEventBus;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsManager;
import com.carmel.guestjini.Screens.Common.Dialogs.PromptDialog.PromptDialogEvent;
import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;
import com.carmel.guestjini.Tickets.FetchTicketListUseCase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TicketListController implements
        TicketListViewMVC.Listener,
        FetchTicketListUseCase.Listener,
        DialogsEventBus.Listener {

    private enum ScreenState {
        IDLE, FETCHING_TICKET_LIST, TICKET_LIST_SHOWN, NETWORK_ERROR
    }

    private FetchTicketListUseCase fetchTicketListUseCase;
    private final ScreensNavigator screensNavigator;
    private final DialogsManager dialogsManager;
    private final DialogsEventBus dialogsEventBus;
    private List<Ticket> ticketList = new ArrayList<>();

    private TicketListViewMVC viewMVC;
    private ScreenState mScreenState = ScreenState.IDLE;

    public TicketListController
            (
                    FetchTicketListUseCase fetchTicketListUseCase,
                    ScreensNavigator screensNavigator,
                    DialogsManager dialogsManager,
                    DialogsEventBus dialogsEventBus
            ) {
        this.fetchTicketListUseCase = fetchTicketListUseCase;
        this.screensNavigator = screensNavigator;
        this.dialogsManager = dialogsManager;
        this.dialogsEventBus = dialogsEventBus;
    }

    public void bindView(TicketListViewMVC ticketListViewMVC) {
        this.viewMVC = ticketListViewMVC;
    }

    public void onStart() {
        viewMVC.registerListener(this);
        fetchTicketListUseCase.registerListener(this);
        dialogsEventBus.registerListener(this);
        if (mScreenState != ScreenState.NETWORK_ERROR) {
            fetchTicketListAndNotify();
        }
    }

    public void onStop() {
        viewMVC.unregisterListener(this);
        dialogsEventBus.unregisterListener(this);
        fetchTicketListUseCase.unregisterListener(this);
    }

    public SavedState getSavedState() {
        return new SavedState(mScreenState);
    }

    public void restoreSavedState(SavedState savedState) {
        mScreenState = savedState.mScreenState;
    }


    private void fetchTicketListAndNotify() {
        mScreenState = ScreenState.FETCHING_TICKET_LIST;
        viewMVC.showProgressIndication();
        fetchTicketListUseCase.fetchTicketListAndNotify();
    }

    @Override
    public void onDialogEvent(Object event) {
        if (event instanceof PromptDialogEvent) {
            switch (((PromptDialogEvent) event).getClickedButton()) {
                case POSITIVE:
                    fetchTicketListAndNotify();
                    break;
                case NEGATIVE:
                    mScreenState = ScreenState.IDLE;
                    break;
            }
        }
    }

    @Override
    public void onTicketClicked(Ticket ticket) {
        screensNavigator.toTicketDetails(ticket.getId());
    }

    @Override
    public void onSearchClicked(String searchText) {
        if (!searchText.equals("")) {
            List<Ticket> collection = ticketList.stream()
                    .filter(ticket -> (ticket.getTicketNarration() + " " +
                            ticket.getTicketTitle()).toLowerCase().contains(searchText.toLowerCase()))
                    .collect(Collectors.toList());
            viewMVC.bindTickets(collection, ticketList.size());
        } else {
            viewMVC.bindTickets(ticketList, ticketList.size());
        }
    }

    @Override
    public void onBackClicked() {
        screensNavigator.navigateUp();
    }

    @Override
    public void onTicketListFetched(List<Ticket> ticketList) {
        this.ticketList = ticketList;
        mScreenState = ScreenState.TICKET_LIST_SHOWN;
        viewMVC.hideProgressIndication();
        viewMVC.bindTickets(ticketList, ticketList.size());
    }

    @Override
    public void onTicketListFetchFailed() {
        mScreenState = ScreenState.NETWORK_ERROR;
        viewMVC.hideProgressIndication();
        dialogsManager.showUseCaseFailedDialog("Tickets", null);

    }

    @Override
    public void onNetworkFailed() {
        dialogsManager.showNetworkFailedInfoDialog(null, "Tickets");
    }

    public static class SavedState implements Serializable {
        private final ScreenState mScreenState;

        public SavedState(ScreenState screenState) {
            mScreenState = screenState;
        }
    }
}
