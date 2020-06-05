package com.carmel.guestjini.Screens.Settings.SettingsHome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.carmel.guestjini.Screens.Common.Controllers.BaseFragment;

public class SettingsHomeFragment extends BaseFragment {
    public static Fragment createFragment() {
        return new SettingsHomeFragment();
    }

    private static final String SAVED_STATE_SETTINGS_HOME_FRAGMENT = "SAVED_STATE_SETTINGS_HOME_FRAGMENT";
    private SettingsHomeController settingsHomeController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        SettingsHomeViewMVC viewMVC = getCompositionRoot().getViewMVCFactory().getSettingsHomeViewMVC(container);
        settingsHomeController = getCompositionRoot().getSettingsHomeController();
        settingsHomeController.bindView(viewMVC);
        return viewMVC.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        settingsHomeController.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        settingsHomeController.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_STATE_SETTINGS_HOME_FRAGMENT, settingsHomeController.getSavedState());
    }

    private void restoreControllerState(Bundle savedInstanceState) {
        settingsHomeController.restoreSavedState(
                (SettingsHomeController.SavedState)
                        savedInstanceState.getSerializable(SAVED_STATE_SETTINGS_HOME_FRAGMENT)
        );
    }
}