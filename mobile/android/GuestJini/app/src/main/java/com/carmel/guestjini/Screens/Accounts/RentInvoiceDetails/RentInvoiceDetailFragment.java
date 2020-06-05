package com.carmel.guestjini.Screens.Accounts.RentInvoiceDetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.carmel.guestjini.Screens.Common.Controllers.BaseFragment;

public class RentInvoiceDetailFragment extends BaseFragment {
    private static final String ARG_ACCOUNT_TICKET_ID = "ARG_ACCOUNT_TICKET_ID";
    private static final String SAVED_STATE_RENT_INVOICE_DETAIL_FRAGMENT = "SAVED_STATE_RENT_INVOICE_DETAIL_FRAGMENT";

    public static Fragment createFragment(String kbId) {
        Bundle args = new Bundle();
        args.putString(ARG_ACCOUNT_TICKET_ID, kbId);
        RentInvoiceDetailFragment fragment = new RentInvoiceDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private RentInvoiceDetailController rentInvoiceDetailController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RentInvoiceDetailViewMVC viewMvc = getCompositionRoot().getViewMVCFactory().getRentInvoiceDetailViewMVC(container);
        rentInvoiceDetailController = getCompositionRoot().getRentInvoiceDetailController();
        if (savedInstanceState != null) {
            restoreControllerState(savedInstanceState);
        }
        rentInvoiceDetailController.bindView(viewMvc);

        return viewMvc.getRootView();
    }

    private void restoreControllerState(Bundle savedInstanceState) {
        rentInvoiceDetailController.restoreSavedState(
                (RentInvoiceDetailController.SavedState)
                        savedInstanceState.getSerializable(SAVED_STATE_RENT_INVOICE_DETAIL_FRAGMENT)
        );
    }

    @Override
    public void onStart() {
        super.onStart();
        rentInvoiceDetailController.onStart(getAccountTicketId());
    }

    @Override
    public void onStop() {
        super.onStop();
        rentInvoiceDetailController.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_STATE_RENT_INVOICE_DETAIL_FRAGMENT, rentInvoiceDetailController.getSavedState());
    }

    private String getAccountTicketId() {
        return getArguments().getString(ARG_ACCOUNT_TICKET_ID);
    }
}