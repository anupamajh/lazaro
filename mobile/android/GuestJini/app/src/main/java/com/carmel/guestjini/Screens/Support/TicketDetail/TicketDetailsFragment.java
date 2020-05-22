package com.carmel.guestjini.Screens.Support.TicketDetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.carmel.guestjini.Screens.Common.Controllers.BaseFragment;

public class TicketDetailsFragment
        extends BaseFragment {
    private static final String ARG_TICKET_ID = "ARG_TICKET_ID";
    public static Fragment createFragment(String ticketId){
        Bundle args = new Bundle();
        args.putString(ARG_TICKET_ID, ticketId);
        TicketDetailsFragment fragment = new TicketDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }
    private static final String SAVED_STATE_TICKET_DETAIL_FRAGMENT = "SAVED_STATE_TICKET_DETAIL_FRAGMENT";

    private TicketDetailsController ticketDetailsController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TicketDetailsViewMVC viewMvc = getCompositionRoot().getViewMVCFactory().getTicketDetailsViewMVC(container);
        ticketDetailsController = getCompositionRoot().getTicketDetailsController();
        if (savedInstanceState != null) {
            restoreControllerState(savedInstanceState);
        }
        ticketDetailsController.bindView(viewMvc);

        return viewMvc.getRootView();
    }

    private void restoreControllerState(Bundle savedInstanceState) {
        ticketDetailsController.restoreSavedState(
                (TicketDetailsController.SavedState)
                        savedInstanceState.getSerializable(SAVED_STATE_TICKET_DETAIL_FRAGMENT)
        );
    }

    @Override
    public void onStart() {
        super.onStart();
        ticketDetailsController.onStart(getTicketId());
    }

    @Override
    public void onStop() {
        super.onStop();
        ticketDetailsController.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_STATE_TICKET_DETAIL_FRAGMENT, ticketDetailsController.getSavedState());
    }


    private String getTicketId() {
        return getArguments().getString(ARG_TICKET_ID);
    }

}
