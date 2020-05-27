package com.carmel.guestjini.Screens.Accounts.Payments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.carmel.guestjini.Screens.Common.Controllers.BaseFragment;

public class PaymentsFragment  extends BaseFragment {
    private static final String ARG_ACCOUNT_TICKET_ID = "ARG_ACCOUNT_TICKET_ID";
    private static final String ARG_GUEST_ID = "ARG_GUEST_ID";
    private static final String ARG_EMAIL = "ARG_EMAIL";
    private static final String ARG_TRANSACTION_AMOUNT = "ARG_TRANSACTION_AMOUNT";
    private static final String SAVED_STATE_PAYMENTS_FRAGMENT = "SAVED_STATE_PAYMENTS_FRAGMENT";
    private PaymentsController paymentsController;

    public static Fragment createFragment(
            String accountTicketId,
            String guestId,
            String email,
            String transactionAmount
    ){
        Bundle args = new Bundle();
        args.putString(ARG_ACCOUNT_TICKET_ID, accountTicketId);
        args.putString(ARG_GUEST_ID, guestId);
        args.putString(ARG_EMAIL, email);
        args.putString(ARG_TRANSACTION_AMOUNT, transactionAmount);
        PaymentsFragment fragment = new PaymentsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        PaymentsViewMVC viewMVC = getCompositionRoot().getViewMVCFactory().getPaymentsViewMVC(container);
        paymentsController = getCompositionRoot().getPaymentsController();
        paymentsController.bindView(viewMVC);
        return viewMVC.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        paymentsController.onStart(
                getAccountTicketId(),
                getGuestId(),
                getEmail(),
                getTransactionAmount()
        );
    }

    @Override
    public void onStop() {
        super.onStop();
        paymentsController.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_STATE_PAYMENTS_FRAGMENT, paymentsController.getSavedState());
    }

    private void restoreControllerState(Bundle savedInstanceState){
        paymentsController.restoreSavedState(
                (PaymentsController.SavedState)
                        savedInstanceState.getSerializable(SAVED_STATE_PAYMENTS_FRAGMENT)
        );
    }

    private String getAccountTicketId() {
        return getArguments().getString(ARG_ACCOUNT_TICKET_ID);
    }
    private String getGuestId() {
        return getArguments().getString(ARG_GUEST_ID);
    }
    private String getEmail() {
        return getArguments().getString(ARG_EMAIL);
    }
    private String getTransactionAmount() {
        return getArguments().getString(ARG_TRANSACTION_AMOUNT);
    }
}
