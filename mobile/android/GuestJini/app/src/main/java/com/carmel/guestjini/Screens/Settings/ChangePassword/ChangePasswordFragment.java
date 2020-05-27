package com.carmel.guestjini.Screens.Settings.ChangePassword;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.carmel.guestjini.Screens.Common.Controllers.BaseFragment;

public class ChangePasswordFragment extends BaseFragment {
    public static Fragment createFragment() {
        return new ChangePasswordFragment();
    }

    private static final String SAVED_STATE_CHANGE_PASSWORD_FRAGMENT = "SAVED_STATE_CHANGE_PASSWORD_FRAGMENT";

    private ChangePasswordController changePasswordController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ChangePasswordViewMVC viewMVC = getCompositionRoot().getViewMVCFactory().getChangePasswordViewMVC(container);
        changePasswordController = getCompositionRoot().getChangePasswordController();
        changePasswordController.bindView(viewMVC);
        return viewMVC.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        changePasswordController.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        changePasswordController.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_STATE_CHANGE_PASSWORD_FRAGMENT, changePasswordController.getSavedState());
    }

    private void restoreControllerState(Bundle savedInstanceState){
        changePasswordController.restoreSavedState(
                (ChangePasswordController.SavedState)
                        savedInstanceState.getSerializable(SAVED_STATE_CHANGE_PASSWORD_FRAGMENT)
        );
    }
}
