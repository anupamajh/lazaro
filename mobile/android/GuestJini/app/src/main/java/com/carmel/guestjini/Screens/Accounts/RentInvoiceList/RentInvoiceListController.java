package com.carmel.guestjini.Screens.Accounts.RentInvoiceList;

import com.carmel.guestjini.Accounts.FetchMyRentInvoiceListUseCase;
import com.carmel.guestjini.Networking.Accounts.AccountTicket;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsEventBus;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsManager;
import com.carmel.guestjini.Screens.Common.Dialogs.PromptDialog.PromptDialogEvent;
import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RentInvoiceListController implements
        RentInvoiceListViewMVC.Listener,
        FetchMyRentInvoiceListUseCase.Listener,
        DialogsEventBus.Listener {
    private enum ScreenState {
        IDLE, FETCHING_RENT_INVOICE_LIST, RENT_INVOICE_LIST_SHOWN, NETWORK_ERROR
    }

    private final FetchMyRentInvoiceListUseCase fetchMyRentInvoiceListUseCase;
    private final ScreensNavigator screensNavigator;
    private final DialogsManager dialogsManager;
    private final DialogsEventBus dialogsEventBus;
    private List<AccountTicket> accountTickets = new ArrayList<>();

    private RentInvoiceListViewMVC viewMvc;

    private ScreenState mScreenState = ScreenState.IDLE;


    public RentInvoiceListController
            (
                    FetchMyRentInvoiceListUseCase fetchMyRentInvoiceListUseCase,
                    ScreensNavigator screensNavigator,
                    DialogsManager dialogsManager,
                    DialogsEventBus dialogsEventBus
            ) {
        this.fetchMyRentInvoiceListUseCase = fetchMyRentInvoiceListUseCase;
        this.screensNavigator = screensNavigator;
        this.dialogsManager = dialogsManager;
        this.dialogsEventBus = dialogsEventBus;
    }


    public void bindView(RentInvoiceListViewMVC viewMvc) {
        this.viewMvc = viewMvc;
    }

    public SavedState getSavedState() {
        return new SavedState(mScreenState);
    }

    public void restoreSavedState(SavedState savedState) {
        mScreenState = savedState.mScreenState;
    }

    public void onStart() {
        viewMvc.registerListener(this);
        fetchMyRentInvoiceListUseCase.registerListener(this);
        dialogsEventBus.registerListener(this);

        if (mScreenState != ScreenState.NETWORK_ERROR) {
            fetchRentInvoiceListAndNotify();
        }
    }

    public void onStop() {
        viewMvc.unregisterListener(this);
        dialogsEventBus.unregisterListener(this);
        fetchMyRentInvoiceListUseCase.unregisterListener(this);
    }

    private void fetchRentInvoiceListAndNotify() {
        mScreenState = ScreenState.FETCHING_RENT_INVOICE_LIST;
        viewMvc.showProgressIndication();
        fetchMyRentInvoiceListUseCase.fetchRentInvoiceListAndNotify();
    }


    @Override
    public void onRentInvoiceListFetched(List<AccountTicket> accountTickets) {
        this.accountTickets = accountTickets;
        mScreenState = ScreenState.RENT_INVOICE_LIST_SHOWN;
        viewMvc.hideProgressIndication();
        viewMvc.bindAccountTickets(accountTickets);
    }

    @Override
    public void onRentInvoiceListFetchFailed() {
        mScreenState = ScreenState.NETWORK_ERROR;
        viewMvc.hideProgressIndication();
        dialogsManager.showUseCaseFailedDialog("Rent Invoice", null);
    }

    @Override
    public void onNetworkFailed() {
        dialogsManager.showNetworkFailedInfoDialog(null, "Rent Invoice");
    }

    @Override
    public void onAccountTicketItem(AccountTicket accountTicket) {
        screensNavigator.toRentInvoiceDetailsScreen(accountTicket.getId());
    }

    @Override
    public void onBackClicked() {
        screensNavigator.navigateUp();
    }

    @Override
    public void onDialogEvent(Object event) {
        if (event instanceof PromptDialogEvent) {
            switch (((PromptDialogEvent) event).getClickedButton()) {
                case POSITIVE:
                    fetchRentInvoiceListAndNotify();
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
