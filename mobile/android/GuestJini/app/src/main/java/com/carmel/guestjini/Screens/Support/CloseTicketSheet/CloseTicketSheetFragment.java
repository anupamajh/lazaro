package com.carmel.guestjini.Screens.Support.CloseTicketSheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.carmel.guestjini.Screens.Common.Controllers.BaseBottomSheetFragment;

public class CloseTicketSheetFragment  extends BaseBottomSheetFragment {
    private static final String ARG_TICKET_ID = "ARG_TICKET_ID";

    public static CloseTicketSheetFragment createFragment(String ticketId) {
        Bundle args = new Bundle();
        args.putString(ARG_TICKET_ID, ticketId);
        CloseTicketSheetFragment fragment = new CloseTicketSheetFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private static final String SAVED_STATE_CLOSE_TICKET_FRAGMENT = "SAVED_STATE_CLOSE_TICKET_FRAGMENT";

    private CloseTicketSheetController closeTicketSheetController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        CloseTicketSheetViewMVC viewMvc = getCompositionRoot().getViewMVCFactory().getCloseTicketSheetViewMVC(container);
        closeTicketSheetController = getCompositionRoot().getCloseTicketSheetController();
        if (savedInstanceState != null) {
            restoreControllerState(savedInstanceState);
        }
        closeTicketSheetController.bindView(viewMvc);

        return viewMvc.getRootView();
    }

    private void restoreControllerState(Bundle savedInstanceState) {
        closeTicketSheetController.restoreSavedState(
                (CloseTicketSheetController.SavedState)
                        savedInstanceState.getSerializable(SAVED_STATE_CLOSE_TICKET_FRAGMENT)
        );
    }

    @Override
    public void onStart() {
        super.onStart();
        closeTicketSheetController.onStart(getTicketId());
    }

    @Override
    public void onStop() {
        super.onStop();
        closeTicketSheetController.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_STATE_CLOSE_TICKET_FRAGMENT, closeTicketSheetController.getSavedState());
    }


    private String getTicketId() {
        return getArguments().getString(ARG_TICKET_ID);
    }
}
