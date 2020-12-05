package com.carmel.guestjini.Screens.Support.AssignTicketSheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.carmel.guestjini.Screens.Common.Controllers.BaseBottomSheetFragment;

public class AssignTicketSheetFragment extends BaseBottomSheetFragment {
    private static final String ARG_TICKET_ID = "ARG_TICKET_ID";

    public static AssignTicketSheetFragment createFragment(String ticketId) {
        Bundle args = new Bundle();
        args.putString(ARG_TICKET_ID, ticketId);
        AssignTicketSheetFragment fragment = new AssignTicketSheetFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private static final String SAVED_STATE_ASSIGN_TICKET_FRAGMENT = "SAVED_STATE_ASSIGN_TICKET_FRAGMENT";

    private AssignTicketSheetController assignTicketSheetController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AssignTicketSheetViewMVC viewMvc = getCompositionRoot().getViewMVCFactory().getAssignTicketSheetViewMVC(container);
        assignTicketSheetController = getCompositionRoot().getAssignTicketSheetController();
        if (savedInstanceState != null) {
            restoreControllerState(savedInstanceState);
        }
        assignTicketSheetController.bindView(viewMvc);

        return viewMvc.getRootView();
    }

    private void restoreControllerState(Bundle savedInstanceState) {
        assignTicketSheetController.restoreSavedState(
                (AssignTicketSheetController.SavedState)
                        savedInstanceState.getSerializable(SAVED_STATE_ASSIGN_TICKET_FRAGMENT)
        );
    }

    @Override
    public void onStart() {
        super.onStart();
        assignTicketSheetController.onStart(getTicketId());
    }

    @Override
    public void onStop() {
        super.onStop();
        assignTicketSheetController.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_STATE_ASSIGN_TICKET_FRAGMENT, assignTicketSheetController.getSavedState());
    }


    private String getTicketId() {
        return getArguments().getString(ARG_TICKET_ID);
    }
}
