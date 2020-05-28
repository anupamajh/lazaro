package com.carmel.guestjini.Screens.Community.GroupDetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.carmel.guestjini.Screens.Common.Controllers.BaseFragment;

public class GroupDetailsFragment
        extends BaseFragment {
    private static final String ARG_GROUP_ID_STRING = "ARG_GROUP_ID_STRING";
    private static final String SAVED_STATE_GROUP_DETAIL_FRAGMENT = "SAVED_STATE_GROUP_DETAIL_FRAGMENT";

    private GroupDetailsController groupDetailsController;

    public static Fragment createFragment(String groupId) {
        Bundle args = new Bundle();
        args.putString(ARG_GROUP_ID_STRING, groupId);
        GroupDetailsFragment fragment = new GroupDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        GroupDetailsViewMVC viewMvc = getCompositionRoot().getViewMVCFactory().getGroupDetailsViewMVC(container);
        groupDetailsController = getCompositionRoot().getGroupDetailsController();
        if (savedInstanceState != null) {
            restoreControllerState(savedInstanceState);
        }
        groupDetailsController.bindView(viewMvc);
        return viewMvc.getRootView();
    }

    private void restoreControllerState(Bundle savedInstanceState) {
        groupDetailsController.restoreSavedState(
                (GroupDetailsController.SavedState)
                        savedInstanceState.getSerializable(SAVED_STATE_GROUP_DETAIL_FRAGMENT)
        );
    }

    @Override
    public void onStart() {
        super.onStart();
        groupDetailsController.onStart(getGroupId());
    }

    @Override
    public void onStop() {
        super.onStop();
        groupDetailsController.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_STATE_GROUP_DETAIL_FRAGMENT, groupDetailsController.getSavedState());
    }

    private String getGroupId() {
        return getArguments().getString(ARG_GROUP_ID_STRING);
    }
}
