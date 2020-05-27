package com.carmel.guestjini.Screens.Settings.PrivacyPolicy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.carmel.guestjini.Screens.Common.Controllers.BaseFragment;

public class PrivacyPolicyFragment  extends BaseFragment {
    public static Fragment createFragment(){
        return new PrivacyPolicyFragment();
    }
    private static final String SAVED_STATE_SETTINGS_PRIVACY_POLICY_FRAGMENT = "SAVED_STATE_SETTINGS_PRIVACY_POLICY_FRAGMENT";
    private PrivacyPolicyController privacyPolicyController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        PrivacyPolicyViewMVC viewMVC = getCompositionRoot().getViewMVCFactory().getPrivacyPolicyViewMVC(container);
        privacyPolicyController = getCompositionRoot().getPrivacyPolicyController();
        privacyPolicyController.bindView(viewMVC);
        return viewMVC.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        privacyPolicyController.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        privacyPolicyController.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_STATE_SETTINGS_PRIVACY_POLICY_FRAGMENT, privacyPolicyController.getSavedState());
    }

    private void restoreControllerState(Bundle savedInstanceState){
        privacyPolicyController.restoreSavedState(
                (PrivacyPolicyController.SavedState)
                        savedInstanceState.getSerializable(SAVED_STATE_SETTINGS_PRIVACY_POLICY_FRAGMENT)
        );
    }
}