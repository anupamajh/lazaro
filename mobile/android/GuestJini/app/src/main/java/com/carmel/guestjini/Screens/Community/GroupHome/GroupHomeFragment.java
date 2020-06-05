package com.carmel.guestjini.Screens.Community.GroupHome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.carmel.guestjini.Screens.Common.Controllers.BaseFragment;

public class GroupHomeFragment extends BaseFragment {
    public static Fragment createFragment() {
        return new GroupHomeFragment();
    }

    private static final String SAVED_STATE_GROUP_HOME_FRAGMENT = "SAVED_STATE_GROUP_HOME_FRAGMENT";
    private GroupHomeController groupHomeController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        GroupHomeViewMVC viewMVC = getCompositionRoot().getViewMVCFactory().getGroupHomeViewMVC(container);
        groupHomeController = getCompositionRoot().getGroupHomeController();
        groupHomeController.bindView(viewMVC);
        return viewMVC.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        groupHomeController.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        groupHomeController.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_STATE_GROUP_HOME_FRAGMENT, groupHomeController.getSavedState());
    }

    private void restoreControllerState(Bundle savedInstanceState) {
        groupHomeController.restoreSavedState(
                (GroupHomeController.SavedState)
                        savedInstanceState.getSerializable(SAVED_STATE_GROUP_HOME_FRAGMENT)
        );
    }
}