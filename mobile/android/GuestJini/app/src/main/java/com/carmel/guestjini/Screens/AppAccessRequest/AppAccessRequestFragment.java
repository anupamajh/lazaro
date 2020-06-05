package com.carmel.guestjini.Screens.AppAccessRequest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.carmel.guestjini.Screens.Common.Controllers.BaseFragment;

public class AppAccessRequestFragment extends BaseFragment {

    public static Fragment createFragment() {
        return new AppAccessRequestFragment();
    }

    private static final String SAVED_STATE_APP_ACCESS_FRAGMENT = "SAVED_STATE_APP_ACCESS_FRAGMENT";
    private AppAccessRequestController appAccessRequestController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AppAccessRequestViewMVC viewMVC = getCompositionRoot().getViewMVCFactory().getAppAccessRequestViewMVC(container);
        appAccessRequestController = getCompositionRoot().getAppAccessRequestController();
        appAccessRequestController.bindView(viewMVC);
        return viewMVC.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        appAccessRequestController.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        appAccessRequestController.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_STATE_APP_ACCESS_FRAGMENT, appAccessRequestController.getSavedState());
    }

    private void restoreControllerState(Bundle savedInstanceState) {
        appAccessRequestController.restoreSavedState(
                (AppAccessRequestController.SavedState)
                        savedInstanceState.getSerializable(SAVED_STATE_APP_ACCESS_FRAGMENT)
        );
    }
}