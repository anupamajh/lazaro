package com.carmel.guestjini.Screens.SetPassword;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.carmel.guestjini.Screens.Common.Controllers.BaseFragment;

public class SetPasswordFragment   extends BaseFragment {

    public static Fragment createFragment() {
        return new SetPasswordFragment();
    }

    private static final String SAVED_STATE_SET_PASSWORD_FRAGMENT = "SAVED_STATE_SET_PASSWORD_FRAGMENT";
    private SetPasswordController setPasswordController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        SetPasswordMVC viewMVC = getCompositionRoot().getViewMVCFactory().getSetPasswordMVC(container);
        setPasswordController = getCompositionRoot().getSetPasswordController();
        setPasswordController.bindView(viewMVC);
        return viewMVC.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        setPasswordController.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        setPasswordController.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_STATE_SET_PASSWORD_FRAGMENT, setPasswordController.getSavedState());
    }

    private void restoreControllerState(Bundle savedInstanceState) {
        setPasswordController.restoreSavedState(
                (SetPasswordController.SavedState)
                        savedInstanceState.getSerializable(SAVED_STATE_SET_PASSWORD_FRAGMENT)
        );
    }
}