package com.carmel.guestjini.Screens.Support.InboxTicketDetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.carmel.guestjini.Screens.Common.Controllers.BaseFragment;

public class InboxTicketDetailFragment
        extends BaseFragment {
    private static final String ARG_TICKET_ID = "ARG_TICKET_ID";
    private static final String ARG_INBOX_TYPE = "ARG_INBOX_TYPE";

    public static Fragment createFragment(String ticketId, int inboxType) {
        Bundle args = new Bundle();
        args.putString(ARG_TICKET_ID, ticketId);
        args.putInt(ARG_INBOX_TYPE, inboxType);
        InboxTicketDetailFragment fragment = new InboxTicketDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private static final String SAVED_STATE_INBOX_TICKET_DETAIL_FRAGMENT = "SAVED_STATE_INBOX_TICKET_DETAIL_FRAGMENT";

    private InboxTicketDetailController inboxTicketDetailController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        InboxTicketDetailViewMVC viewMvc = getCompositionRoot().getViewMVCFactory().getInboxTicketDetailViewMVC(container);
        inboxTicketDetailController = getCompositionRoot().getInboxTicketDetailController();
        if (savedInstanceState != null) {
            restoreControllerState(savedInstanceState);
        }
        inboxTicketDetailController.bindView(viewMvc);

        return viewMvc.getRootView();
    }

    private void restoreControllerState(Bundle savedInstanceState) {
        inboxTicketDetailController.restoreSavedState(
                (InboxTicketDetailController.SavedState)
                        savedInstanceState.getSerializable(SAVED_STATE_INBOX_TICKET_DETAIL_FRAGMENT)
        );
    }

    @Override
    public void onStart() {
        super.onStart();
        inboxTicketDetailController.onStart(getTicketId(), getInboxType());
        inboxTicketDetailController.setFragmentManager(getFragmentManager());
    }

    @Override
    public void onStop() {
        super.onStop();
        inboxTicketDetailController.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_STATE_INBOX_TICKET_DETAIL_FRAGMENT, inboxTicketDetailController.getSavedState());
    }


    private String getTicketId() {
        return getArguments().getString(ARG_TICKET_ID);
    }

    private int getInboxType() {
        return getArguments().getInt(ARG_INBOX_TYPE);
    }
}
