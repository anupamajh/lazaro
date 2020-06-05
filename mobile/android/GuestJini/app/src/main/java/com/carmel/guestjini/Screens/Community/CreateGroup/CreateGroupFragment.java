package com.carmel.guestjini.Screens.Community.CreateGroup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.carmel.guestjini.Screens.Common.Controllers.BaseFragment;

public class CreateGroupFragment extends BaseFragment {

    private static final String SAVED_STATE_CREATE_GROUP_FRAGMENT = "SAVED_STATE_CREATE_GROUP_FRAGMENT";

    public static Fragment createFragment() {
        return new CreateGroupFragment();
    }

    private CreateGroupController createGroupController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        CreateGroupViewMVC viewMVC = getCompositionRoot().getViewMVCFactory().getCreateGroupViewMVC(container);
        createGroupController = getCompositionRoot().getCreateGroupController();
        if (savedInstanceState != null) {
            restoreControllerState(savedInstanceState);
        }
        createGroupController.bindView(viewMVC);

        return viewMVC.getRootView();
    }

    private void restoreControllerState(Bundle savedInstanceState) {
        createGroupController.restoreSavedState(
                (CreateGroupController.SavedState)
                        savedInstanceState.getSerializable(SAVED_STATE_CREATE_GROUP_FRAGMENT)
        );
    }

    @Override
    public void onStart() {
        super.onStart();
        createGroupController.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        createGroupController.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_STATE_CREATE_GROUP_FRAGMENT, createGroupController.getSavedState());
    }
}