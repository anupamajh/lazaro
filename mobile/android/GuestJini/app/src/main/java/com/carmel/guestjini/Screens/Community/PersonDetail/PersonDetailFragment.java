package com.carmel.guestjini.Screens.Community.PersonDetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.carmel.guestjini.Screens.Common.Controllers.BaseFragment;

public class PersonDetailFragment extends BaseFragment {
    private static final String ARG_PERSON_ID = "ARG_PERSON_ID";
    private static final String SAVED_STATE_PERSON_DETAIL_FRAGMENT = "SAVED_STATE_PERSON_DETAIL_FRAGMENT";

    public static Fragment createFragment(String personId) {
        Bundle args = new Bundle();
        args.putString(ARG_PERSON_ID, personId);
        PersonDetailFragment fragment = new PersonDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private PersonDetailController personDetailController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        PersonDetailViewMVC viewMvc = getCompositionRoot().getViewMVCFactory().getPersonDetailViewMVC(container);
        personDetailController = getCompositionRoot().getPersonDetailController();
        if (savedInstanceState != null) {
            restoreControllerState(savedInstanceState);
        }
        personDetailController.bindView(viewMvc);

        return viewMvc.getRootView();
    }

    private void restoreControllerState(Bundle savedInstanceState) {
        personDetailController.restoreSavedState(
                (PersonDetailController.SavedState)
                        savedInstanceState.getSerializable(SAVED_STATE_PERSON_DETAIL_FRAGMENT)
        );
    }

    @Override
    public void onStart() {
        super.onStart();
        personDetailController.onStart(getPersonId());
    }

    @Override
    public void onStop() {
        super.onStop();
        personDetailController.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_STATE_PERSON_DETAIL_FRAGMENT, personDetailController.getSavedState());
    }

    private String getPersonId() {
        return getArguments().getString(ARG_PERSON_ID);
    }

}
