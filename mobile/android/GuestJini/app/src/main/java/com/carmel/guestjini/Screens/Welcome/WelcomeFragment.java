package com.carmel.guestjini.Screens.Welcome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.carmel.guestjini.Screens.Common.Controllers.BaseFragment;

public class WelcomeFragment extends BaseFragment {

    public static Fragment createFragment() {
        return new WelcomeFragment();
    }

    private static final String SAVED_STATE_WELCOME_FRAGMENT = "SAVED_STATE_WELCOME_FRAGMENT";
    private WelcomeController welcomeController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        WelcomeViewMVC welcomeViewMVC = getCompositionRoot().getViewMVCFactory().getWelcomeViewMVC(container);
        welcomeController = getCompositionRoot().getWelcomeController();
        welcomeController.bindView(welcomeViewMVC);
        return welcomeViewMVC.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        welcomeController.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        welcomeController.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_STATE_WELCOME_FRAGMENT, welcomeController.getSavedState());
    }

    private void restoreControllerState(Bundle savedInstanceState) {
        welcomeController.restoreSavedState(
                (WelcomeController.SavedState)
                        savedInstanceState.getSerializable(SAVED_STATE_WELCOME_FRAGMENT)
        );
    }
}
