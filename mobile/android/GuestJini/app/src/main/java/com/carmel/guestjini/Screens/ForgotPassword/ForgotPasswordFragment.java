package com.carmel.guestjini.Screens.ForgotPassword;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.carmel.guestjini.Screens.Common.Controllers.BaseFragment;

public class ForgotPasswordFragment extends BaseFragment {

    public static Fragment createFragment() {
        return new ForgotPasswordFragment();
    }

    private static final String SAVED_STATE_FORGOT_PASSWORD_FRAGMENT = "SAVED_STATE_FORGOT_PASSWORD_FRAGMENT";
    private ForgotPasswordController forgotPasswordController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ForgotPasswordViewMVC viewMVC = getCompositionRoot().getViewMVCFactory().getForgotPasswordViewMVC(container);
        forgotPasswordController = getCompositionRoot().getForgotPasswordController();
        forgotPasswordController.bindView(viewMVC);
        return viewMVC.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        forgotPasswordController.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        forgotPasswordController.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_STATE_FORGOT_PASSWORD_FRAGMENT, forgotPasswordController.getSavedState());
    }

    private void restoreControllerState(Bundle savedInstanceState) {
        forgotPasswordController.restoreSavedState(
                (ForgotPasswordController.SavedState)
                        savedInstanceState.getSerializable(SAVED_STATE_FORGOT_PASSWORD_FRAGMENT)
        );
    }
}