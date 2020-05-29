package com.carmel.guestjini.Screens.Community.PeopleList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.carmel.guestjini.Screens.Common.Controllers.BaseFragment;

public class PeopleListFragment
        extends BaseFragment {
    public static Fragment createFragment() {
        return new PeopleListFragment();
    }

    private static final String SAVED_STATE_PEOPLE_LIST_FRAGMENT = "SAVED_STATE_PEOPLE_LIST_FRAGMENT";

    private PeopleListController peopleListController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        PeopleListViewMVC viewMvc = getCompositionRoot().getViewMVCFactory().getPeopleListViewMVC(container);
        peopleListController = getCompositionRoot().getPeopleListController();
        if (savedInstanceState != null) {
            restoreControllerState(savedInstanceState);
        }
        peopleListController.bindView(viewMvc);

        return viewMvc.getRootView();
    }

    private void restoreControllerState(Bundle savedInstanceState) {
        peopleListController.restoreSavedState(
                (PeopleListController.SavedState)
                        savedInstanceState.getSerializable(SAVED_STATE_PEOPLE_LIST_FRAGMENT)
        );
    }

    @Override
    public void onStart() {
        super.onStart();
        peopleListController.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        peopleListController.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_STATE_PEOPLE_LIST_FRAGMENT, peopleListController.getSavedState());
    }
}
