package com.carmel.guestjini.Screens.Community.GroupConversation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.carmel.guestjini.Screens.Common.Controllers.BaseFragment;

public class GroupConversationFragment
        extends BaseFragment {
    private static final String ARG_GROUP_ID_STRING = "ARG_GROUP_ID_STRING";
    private static final String ARG_GROUP_TYPE_INTEGER = "ARG_GROUP_TYPE_INTEGER";
    private static final String SAVED_STATE_GROUP_CONVERSATION_FRAGMENT = "SAVED_STATE_GROUP_CONVERSATION_FRAGMENT";

    private GroupConversationController groupConversationController;

    public static Fragment createFragment(String groupId, Integer groupType) {
        Bundle args = new Bundle();
        args.putString(ARG_GROUP_ID_STRING, groupId);
        args.putInt(ARG_GROUP_TYPE_INTEGER, groupType);
        GroupConversationFragment fragment = new GroupConversationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        GroupConversationViewMVC viewMvc = getCompositionRoot().getViewMVCFactory().getGroupConversationViewMVC(container);
        groupConversationController = getCompositionRoot().getGroupConversationController();
        if (savedInstanceState != null) {
            restoreControllerState(savedInstanceState);
        }
        groupConversationController.bindView(viewMvc);
        return viewMvc.getRootView();
    }

    private void restoreControllerState(Bundle savedInstanceState) {
        groupConversationController.restoreSavedState(
                (GroupConversationController.SavedState)
                        savedInstanceState.getSerializable(SAVED_STATE_GROUP_CONVERSATION_FRAGMENT)
        );
    }

    @Override
    public void onStart() {
        super.onStart();
        groupConversationController.onStart(getGroupId(), getGroupType());
    }

    @Override
    public void onStop() {
        super.onStop();
        groupConversationController.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_STATE_GROUP_CONVERSATION_FRAGMENT, groupConversationController.getSavedState());
    }

    private String getGroupId() {
        return getArguments().getString(ARG_GROUP_ID_STRING);
    }

    private Integer getGroupType() {
        return getArguments().getInt(ARG_GROUP_TYPE_INTEGER);
    }
}
