package com.carmel.guestjini.Screens.Support.TicketList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.carmel.guestjini.Screens.Common.Controllers.BaseFragment;
import com.carmel.guestjini.Screens.Support.TicketCategory.TicketCategoryListFragment;

public class TicketListFragment
        extends BaseFragment {

    private static final String ARG_TICKET_STATUS = "ARG_TICKET_STATUS";

    public static Fragment createFragment(int ticketStatus) {
        Bundle args = new Bundle();
        args.putInt(ARG_TICKET_STATUS, ticketStatus);
        TicketListFragment fragment = new TicketListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private static final String SAVED_STATE_TICKET_LIST_FRAGMENT = "SAVED_STATE_TICKET_LIST_FRAGMENT";

    private TicketListController ticketListController;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TicketListViewMVC viewMvc = getCompositionRoot().getViewMVCFactory().getTicketListViewMVC(container);
        ticketListController = getCompositionRoot().getTicketListController();
        if (savedInstanceState != null) {
            restoreControllerState(savedInstanceState);
        }
        ticketListController.bindView(viewMvc);

        return viewMvc.getRootView();
    }

    private void restoreControllerState(Bundle savedInstanceState) {
        ticketListController.restoreSavedState(
                (TicketListController.SavedState)
                        savedInstanceState.getSerializable(SAVED_STATE_TICKET_LIST_FRAGMENT)
        );
    }

    @Override
    public void onStart() {
        super.onStart();
        ticketListController.onStart(getTicketStatus());
    }

    @Override
    public void onStop() {
        super.onStop();
        ticketListController.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_STATE_TICKET_LIST_FRAGMENT, ticketListController.getSavedState());
    }

    private int getTicketStatus() {
        return getArguments().getInt(ARG_TICKET_STATUS);
    }
}
