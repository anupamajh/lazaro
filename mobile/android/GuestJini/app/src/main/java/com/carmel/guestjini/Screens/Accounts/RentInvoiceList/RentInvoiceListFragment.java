package com.carmel.guestjini.Screens.Accounts.RentInvoiceList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.carmel.guestjini.Screens.Common.Controllers.BaseFragment;

public class RentInvoiceListFragment
        extends BaseFragment {
    public static Fragment createFragment(){
        return new RentInvoiceListFragment();
    }
    private static final String SAVED_STATE_RENT_INVOICE_LIST_FRAGMENT = "SAVED_STATE_RENT_INVOICE_LIST_FRAGMENT";

    private RentInvoiceListController rentInvoiceListController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RentInvoiceListViewMVC viewMvc = getCompositionRoot().getViewMVCFactory().getRentInvoiceListViewMVC(container);
        rentInvoiceListController = getCompositionRoot().getRentInvoiceListController();
        if (savedInstanceState != null) {
            restoreControllerState(savedInstanceState);
        }
        rentInvoiceListController.bindView(viewMvc);

        return viewMvc.getRootView();
    }

    private void restoreControllerState(Bundle savedInstanceState) {
        rentInvoiceListController.restoreSavedState(
                (RentInvoiceListController.SavedState)
                        savedInstanceState.getSerializable(SAVED_STATE_RENT_INVOICE_LIST_FRAGMENT)
        );
    }

    @Override
    public void onStart() {
        super.onStart();
        rentInvoiceListController.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        rentInvoiceListController.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_STATE_RENT_INVOICE_LIST_FRAGMENT, rentInvoiceListController.getSavedState());
    }

}
