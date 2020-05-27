package com.carmel.guestjini.Screens.Support.SupportHome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.carmel.guestjini.Screens.Common.Controllers.BaseFragment;

public class SupportHomeFragment extends BaseFragment {
    public static Fragment createFragment(){
        return new SupportHomeFragment();
    }
    private static final String SAVED_STATE_SUPPORT_HOME_FRAGMENT = "SAVED_STATE_SUPPORT_HOME_FRAGMENT";
    private SupportHomeController supportHomeController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        SupportHomeViewMVC viewMVC = getCompositionRoot().getViewMVCFactory().getSupportHomeViewMVC(container);
        supportHomeController = getCompositionRoot().getSupportHomeController();
        supportHomeController.bindView(viewMVC);
        return viewMVC.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        supportHomeController.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        supportHomeController.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_STATE_SUPPORT_HOME_FRAGMENT, supportHomeController.getSavedState());
    }

    private void restoreControllerState(Bundle savedInstanceState){
        supportHomeController.restoreSavedState(
                (SupportHomeController.SavedState)
                        savedInstanceState.getSerializable(SAVED_STATE_SUPPORT_HOME_FRAGMENT)
        );
    }
}
