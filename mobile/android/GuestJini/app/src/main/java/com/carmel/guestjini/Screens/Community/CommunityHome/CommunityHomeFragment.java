package com.carmel.guestjini.Screens.Community.CommunityHome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.carmel.guestjini.Screens.Common.Controllers.BaseFragment;

public class CommunityHomeFragment extends BaseFragment {
    public static Fragment createFragment() {
        return new CommunityHomeFragment();
    }

    private static final String SAVED_STATE_COMMUNITY_HOME_FRAGMENT = "SAVED_STATE_COMMUNITY_HOME_FRAGMENT";
    private CommunityHomeController communityHomeController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        CommunityHomeViewMVC viewMVC = getCompositionRoot().getViewMVCFactory().getCommunityHomeViewMVC(container);
        communityHomeController = getCompositionRoot().getCommunityHomeController();
        communityHomeController.bindView(viewMVC);
        return viewMVC.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        communityHomeController.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        communityHomeController.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_STATE_COMMUNITY_HOME_FRAGMENT, communityHomeController.getSavedState());
    }

    private void restoreControllerState(Bundle savedInstanceState) {
        communityHomeController.restoreSavedState(
                (CommunityHomeController.SavedState)
                        savedInstanceState.getSerializable(SAVED_STATE_COMMUNITY_HOME_FRAGMENT)
        );
    }
}