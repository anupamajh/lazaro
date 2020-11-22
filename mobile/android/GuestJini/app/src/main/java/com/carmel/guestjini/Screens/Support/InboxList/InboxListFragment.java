package com.carmel.guestjini.Screens.Support.InboxList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.carmel.guestjini.Screens.Common.Controllers.BaseFragment;

public class InboxListFragment
        extends BaseFragment {

    private static final String ARG_INBOX_TYPE = "ARG_INBOX_TYPE";

    public static Fragment createFragment(int inboxType) {
        Bundle args = new Bundle();
        args.putInt(ARG_INBOX_TYPE, inboxType);
        InboxListFragment fragment = new InboxListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private static final String SAVED_STATE_TICKET_LIST_FRAGMENT = "SAVED_STATE_TICKET_LIST_FRAGMENT";

    private InboxListController inboxListController;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        InboxListViewMVC viewMvc = getCompositionRoot().getViewMVCFactory().getInboxListViewMVC(container);
        inboxListController = getCompositionRoot().getInboxListController();
        if (savedInstanceState != null) {
            restoreControllerState(savedInstanceState);
        }
        inboxListController.bindView(viewMvc);

        return viewMvc.getRootView();
    }

    private void restoreControllerState(Bundle savedInstanceState) {
        inboxListController.restoreSavedState(
                (InboxListController.SavedState)
                        savedInstanceState.getSerializable(SAVED_STATE_TICKET_LIST_FRAGMENT)
        );
    }

    @Override
    public void onStart() {
        super.onStart();
        inboxListController.onStart(getTicketStatus());
    }

    @Override
    public void onStop() {
        super.onStop();
        inboxListController.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_STATE_TICKET_LIST_FRAGMENT, inboxListController.getSavedState());
    }

    private int getTicketStatus() {
        return getArguments().getInt(ARG_INBOX_TYPE);
    }
}
