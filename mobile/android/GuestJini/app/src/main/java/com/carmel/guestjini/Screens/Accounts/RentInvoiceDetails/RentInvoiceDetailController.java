package com.carmel.guestjini.Screens.Accounts.RentInvoiceDetails;

import com.carmel.guestjini.Accounts.FetchMyRentInvoiceDetailsUseCase;
import com.carmel.guestjini.Networking.Accounts.AccountTicket;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsEventBus;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsManager;
import com.carmel.guestjini.Screens.Common.Dialogs.PromptDialog.PromptDialogEvent;
import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;

import java.io.Serializable;

public class RentInvoiceDetailController
        implements RentInvoiceDetailViewMVC.Listener,
        FetchMyRentInvoiceDetailsUseCase.Listener,
        DialogsEventBus.Listener {

    private enum ScreenState {
        IDLE, FETCHING_RENT_INVOICE_DETAIL, RENT_INVOICE_DETAIL_SHOWN, NETWORK_ERROR
    }

    private final FetchMyRentInvoiceDetailsUseCase fetchMyRentInvoiceDetailsUseCase;
    private final ScreensNavigator screensNavigator;
    private final DialogsManager dialogsManager;
    private final DialogsEventBus dialogsEventBus;

    private RentInvoiceDetailViewMVC viewMVC;

    private ScreenState mScreenState = ScreenState.IDLE;

    private String accountTicketId;

    public RentInvoiceDetailController
            (
                    FetchMyRentInvoiceDetailsUseCase fetchMyRentInvoiceDetailsUseCase,
                    ScreensNavigator screensNavigator,
                    DialogsManager dialogsManager,
                    DialogsEventBus dialogsEventBus
            ) {
        this.fetchMyRentInvoiceDetailsUseCase = fetchMyRentInvoiceDetailsUseCase;
        this.screensNavigator = screensNavigator;
        this.dialogsManager = dialogsManager;
        this.dialogsEventBus = dialogsEventBus;
    }

    public void bindView(RentInvoiceDetailViewMVC viewMvc) {
        this.viewMVC = viewMvc;
    }

    public SavedState getSavedState() {
        return new SavedState(mScreenState);
    }

    public void restoreSavedState(SavedState savedState) {
        mScreenState = savedState.mScreenState;
    }

    public void onStart(String accountTicketId) {
        viewMVC.registerListener(this);
        fetchMyRentInvoiceDetailsUseCase.registerListener(this);
        dialogsEventBus.registerListener(this);
        this.accountTicketId = accountTicketId;

        if (mScreenState != ScreenState.NETWORK_ERROR) {
            fetchRentInvoiceDetailsAndNotify();
        }
    }

    public void onStop() {
        viewMVC.unregisterListener(this);
        dialogsEventBus.unregisterListener(this);
        fetchMyRentInvoiceDetailsUseCase.unregisterListener(this);

    }

    private void fetchRentInvoiceDetailsAndNotify() {
        mScreenState = ScreenState.FETCHING_RENT_INVOICE_DETAIL;
        viewMVC.showProgressIndication();
        fetchMyRentInvoiceDetailsUseCase.fetchRentInvoiceDetailAndNotify(accountTicketId);
    }


    @Override
    public void onRentInvoiceDetailFetched(AccountTicket accountTicket) {
        viewMVC.bindAccountTicket(accountTicket);
    }

    @Override
    public void onRentInvoiceDetailFetchFailed() {
        mScreenState = ScreenState.NETWORK_ERROR;
        viewMVC.hideProgressIndication();
        dialogsManager.showUseCaseFailedDialog("Rent Invoice Details", null);
    }

    @Override
    public void onNetworkFailed() {
        dialogsManager.showNetworkFailedInfoDialog(null, "Rent Invoice Details");
    }

    @Override
    public void onBackClicked() {
        screensNavigator.navigateUp();
    }

    @Override
    public void onPayNowClicked(
            String accountTicketId,
            String guestId,
            String email,
            String transactionAmount) {
        screensNavigator.toPaymentScreen(
                accountTicketId,
                guestId,
                email,
                transactionAmount
        );
    }

    @Override
    public void onDialogEvent(Object event) {
        if (event instanceof PromptDialogEvent) {
            switch (((PromptDialogEvent) event).getClickedButton()) {
                case POSITIVE:
                    fetchRentInvoiceDetailsAndNotify();
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
