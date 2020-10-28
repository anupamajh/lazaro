package com.carmel.guestjini.Screens.Support.CreateTicket;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.carmel.guestjini.Screens.Common.Controllers.BaseFragment;
import com.carmel.guestjini.Screens.Support.TicketCategory.TicketCategoryListFragment;

public class CreateTicketFragment extends BaseFragment {

    private static final String SAVED_STATE_CREATE_TICKET_FRAGMENT = "SAVED_STATE_CREATE_TICKET_FRAGMENT";
    private static final String ARG_TICKET_CATEGORY_DATA = "ARG_TICKET_CATEGORY_DATA";

    public static Fragment createFragment(String ticketCategoryData) {
        Bundle args = new Bundle();
        args.putString(ARG_TICKET_CATEGORY_DATA, ticketCategoryData);
        CreateTicketFragment fragment = new CreateTicketFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private CreateTicketController createTicketController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        CreateTicketViewMVC viewMVC = getCompositionRoot().getViewMVCFactory().getCreateTicketViewMVC(container);
        createTicketController = getCompositionRoot().getCreateTicketController();
        if (savedInstanceState != null) {
            restoreControllerState(savedInstanceState);
        }
        createTicketController.bindView(viewMVC);

        return viewMVC.getRootView();
    }

    private void restoreControllerState(Bundle savedInstanceState) {
        createTicketController.restoreSavedState(
                (CreateTicketController.SavedState)
                        savedInstanceState.getSerializable(SAVED_STATE_CREATE_TICKET_FRAGMENT)
        );
    }

    @Override
    public void onStart() {
        super.onStart();
        createTicketController.onStart(getTicketCategoryData());
    }

    @Override
    public void onStop() {
        super.onStop();
        createTicketController.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_STATE_CREATE_TICKET_FRAGMENT, createTicketController.getSavedState());
    }

    private String getTicketCategoryData() {
        return getArguments().getString(ARG_TICKET_CATEGORY_DATA);
    }
}
