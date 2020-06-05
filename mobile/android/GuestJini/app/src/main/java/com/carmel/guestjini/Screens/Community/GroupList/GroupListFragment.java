package com.carmel.guestjini.Screens.Community.GroupList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.carmel.guestjini.Screens.Common.Controllers.BaseFragment;

public class GroupListFragment
        extends BaseFragment {
    private static final String ARG_GROUP_TYPE_INT = "ARG_GROUP_TYPE_INT";
    private static final String SAVED_STATE_GROUP_LIST_FRAGMENT = "SAVED_STATE_GROUP_LIST_FRAGMENT";

    private GroupListController groupListController;

    public static Fragment createFragment(int groupType) {
        Bundle args = new Bundle();
        args.putInt(ARG_GROUP_TYPE_INT, groupType);
        GroupListFragment fragment = new GroupListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        GroupListViewMVC viewMvc = getCompositionRoot().getViewMVCFactory().getGroupListViewMVC(container);
        groupListController = getCompositionRoot().getGroupListController();
        if (savedInstanceState != null) {
            restoreControllerState(savedInstanceState);
        }
        groupListController.bindView(viewMvc);

        return viewMvc.getRootView();
    }

    private void restoreControllerState(Bundle savedInstanceState) {
        groupListController.restoreSavedState(
                (GroupListController.SavedState)
                        savedInstanceState.getSerializable(SAVED_STATE_GROUP_LIST_FRAGMENT)
        );
    }

    @Override
    public void onStart() {
        super.onStart();
        groupListController.onStart(getGroupType());
    }

    @Override
    public void onStop() {
        super.onStop();
        groupListController.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_STATE_GROUP_LIST_FRAGMENT, groupListController.getSavedState());
    }

    private int getGroupType() {
        return getArguments().getInt(ARG_GROUP_TYPE_INT);
    }
}
