package com.carmel.guestjini.Screens.Support.TicketCategory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.carmel.guestjini.Screens.Common.Controllers.BaseFragment;
import com.carmel.guestjini.Screens.Support.KBList.KBListFragment;

public class TicketCategoryListFragment
        extends BaseFragment {
    private static final String ARG_PARENT_ID = "ARG_PARENT_ID";
    private static final String ARG_TICKET_CATEGORY_DATA = "ARG_TICKET_CATEGORY_DATA";

    private static final String SAVED_STATE_TICKET_CATEGORY_LIST_FRAGMENT = "SAVED_STATE_TICKET_CATEGORY_LIST_FRAGMENT";

    private TicketCategoryListController ticketCategoryListController;

    public static Fragment createFragment(String parentId, String ticketCategoryData) {
        Bundle args = new Bundle();
        args.putString(ARG_PARENT_ID, parentId);
        args.putString(ARG_TICKET_CATEGORY_DATA, ticketCategoryData);
        TicketCategoryListFragment fragment = new TicketCategoryListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TicketCategoryListViewMVC viewMvc = getCompositionRoot().getViewMVCFactory().getTicketCategoryListViewMVC(container);
        ticketCategoryListController = getCompositionRoot().getTicketCategoryListController();
        if (savedInstanceState != null) {
            restoreControllerState(savedInstanceState);
        }
        ticketCategoryListController.bindView(viewMvc);

        return viewMvc.getRootView();
    }

    private void restoreControllerState(Bundle savedInstanceState) {
        ticketCategoryListController.restoreSavedState(
                (TicketCategoryListController.SavedState)
                        savedInstanceState.getSerializable(SAVED_STATE_TICKET_CATEGORY_LIST_FRAGMENT)
        );
    }

    @Override
    public void onStart() {
        super.onStart();
        ticketCategoryListController.onStart(getParentId(), getTicketCategoryData());
    }

    @Override
    public void onStop() {
        super.onStop();
        ticketCategoryListController.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_STATE_TICKET_CATEGORY_LIST_FRAGMENT, ticketCategoryListController.getSavedState());
    }

    private String getParentId() {
        return getArguments().getString(ARG_PARENT_ID);
    }

    private String getTicketCategoryData() {
        return getArguments().getString(ARG_TICKET_CATEGORY_DATA);
    }
}
