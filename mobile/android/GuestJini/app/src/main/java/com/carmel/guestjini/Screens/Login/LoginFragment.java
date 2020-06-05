package com.carmel.guestjini.Screens.Login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.carmel.guestjini.Screens.Common.Controllers.BaseFragment;

public class LoginFragment extends BaseFragment {

    public static Fragment createFragment() {
        return new LoginFragment();
    }

    private static final String SAVED_STATE_LOGIN_FRAGMENT = "SAVED_STATE_LOGIN_FRAGMENT";
    private LoginController loginController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LoginViewMVC viewMVC = getCompositionRoot().getViewMVCFactory().getLoginViewMVC(container);
        loginController = getCompositionRoot().getLoginController();
        loginController.bindView(viewMVC);
        return viewMVC.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        loginController.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        loginController.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_STATE_LOGIN_FRAGMENT, loginController.getSavedState());
    }

    private void restoreControllerState(Bundle savedInstanceState) {
        loginController.restoreSavedState(
                (LoginController.SavedState)
                        savedInstanceState.getSerializable(SAVED_STATE_LOGIN_FRAGMENT)
        );
    }
}
