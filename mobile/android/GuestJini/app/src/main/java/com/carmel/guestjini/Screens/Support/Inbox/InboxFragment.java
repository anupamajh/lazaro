package com.carmel.guestjini.Screens.Support.Inbox;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.carmel.guestjini.Screens.Common.Controllers.BaseFragment;

public class InboxFragment extends BaseFragment {

    private static final String SAVED_STATE_INBOX_FRAGMENT = "SAVED_STATE_INBOX_FRAGMENT";
    private static final String ARG_TICKET_CATEGORY_DATA = "ARG_TICKET_CATEGORY_DATA";
    private static final String ARG_TICKET_ID = "ARG_TICKET_ID";

    public static Fragment createFragment() {
        Bundle args = new Bundle();
        InboxFragment fragment = new InboxFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private InboxController inboxController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        InboxViewMVC viewMVC = getCompositionRoot().getViewMVCFactory().getInboxViewMVC(container);
        inboxController = getCompositionRoot().getInboxController();
        if (savedInstanceState != null) {
            restoreControllerState(savedInstanceState);
        }
        inboxController.bindView(viewMVC);

        return viewMVC.getRootView();
    }

    private void restoreControllerState(Bundle savedInstanceState) {
        inboxController.restoreSavedState(
                (InboxController.SavedState)
                        savedInstanceState.getSerializable(SAVED_STATE_INBOX_FRAGMENT)
        );
    }

    @Override
    public void onStart() {
        super.onStart();
        inboxController.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        inboxController.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_STATE_INBOX_FRAGMENT, inboxController.getSavedState());
    }

    private String getTicketCategoryData() {
        return getArguments().getString(ARG_TICKET_CATEGORY_DATA);
    }

    private String getTicketId() {
        return getArguments().getString(ARG_TICKET_ID);
    }
}