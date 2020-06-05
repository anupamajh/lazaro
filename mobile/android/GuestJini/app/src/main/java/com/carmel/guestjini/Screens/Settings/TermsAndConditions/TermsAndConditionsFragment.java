package com.carmel.guestjini.Screens.Settings.TermsAndConditions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.carmel.guestjini.Screens.Common.Controllers.BaseFragment;

public class TermsAndConditionsFragment extends BaseFragment {
    public static Fragment createFragment() {
        return new TermsAndConditionsFragment();
    }

    private static final String SAVED_STATE_SETTINGS_TERMS_CONDITIONS_FRAGMENT = "SAVED_STATE_SETTINGS_TERMS_CONDITIONS_FRAGMENT";
    private TermsAndConditionsController termsAndConditionsController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TermsAndConditionsViewMVC viewMVC = getCompositionRoot().getViewMVCFactory().getTermsAndConditionsViewMVC(container);
        termsAndConditionsController = getCompositionRoot().getTermsAndConditionsController();
        termsAndConditionsController.bindView(viewMVC);
        return viewMVC.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        termsAndConditionsController.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        termsAndConditionsController.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_STATE_SETTINGS_TERMS_CONDITIONS_FRAGMENT, termsAndConditionsController.getSavedState());
    }

    private void restoreControllerState(Bundle savedInstanceState) {
        termsAndConditionsController.restoreSavedState(
                (TermsAndConditionsController.SavedState)
                        savedInstanceState.getSerializable(SAVED_STATE_SETTINGS_TERMS_CONDITIONS_FRAGMENT)
        );
    }
}