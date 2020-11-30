package com.carmel.guestjini.Screens.Support.AssignTicketToAgentSheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.carmel.guestjini.Screens.Common.Controllers.BaseBottomSheetFragment;

public class AssignTicketToAgentSheetFragment extends BaseBottomSheetFragment {
    private static final String ARG_TICKET_ID = "ARG_TICKET_ID";
    private static final String ARG_GROUP_ID = "ARG_GROUP_ID";

    public static AssignTicketToAgentSheetFragment createFragment(String groupId, String ticketId) {
        Bundle args = new Bundle();
        args.putString(ARG_TICKET_ID, ticketId);
        args.putString(ARG_GROUP_ID, groupId);
        AssignTicketToAgentSheetFragment fragment = new AssignTicketToAgentSheetFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private static final String SAVED_STATE_ASSIGN_TICKET_FRAGMENT = "SAVED_STATE_ASSIGN_TICKET_FRAGMENT";

    private AssignTicketToAgentSheetController assignTicketSheetController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AssignTicketToAgentSheetViewMVC viewMvc = getCompositionRoot().getViewMVCFactory().getAssignTicketToAgentSheetViewMVC(container);
        assignTicketSheetController = getCompositionRoot().getAssignTicketToAgentSheetController();
        if (savedInstanceState != null) {
            restoreControllerState(savedInstanceState);
        }
        assignTicketSheetController.bindView(viewMvc);

        return viewMvc.getRootView();
    }

    private void restoreControllerState(Bundle savedInstanceState) {
        assignTicketSheetController.restoreSavedState(
                (AssignTicketToAgentSheetController.SavedState)
                        savedInstanceState.getSerializable(SAVED_STATE_ASSIGN_TICKET_FRAGMENT)
        );
    }

    @Override
    public void onStart() {
        super.onStart();
        assignTicketSheetController.onStart(getGroupId(),getTicketId());
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

    private String getGroupId() {
        return getArguments().getString(ARG_GROUP_ID);
    }
}
