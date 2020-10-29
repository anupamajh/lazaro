package com.carmel.guestjini.Screens.OTP;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.carmel.guestjini.Screens.AppAccessRequest.AppAccessRequestController;
import com.carmel.guestjini.Screens.AppAccessRequest.AppAccessRequestFragment;
import com.carmel.guestjini.Screens.AppAccessRequest.AppAccessRequestViewMVC;
import com.carmel.guestjini.Screens.Common.Controllers.BaseFragment;

public class OTPFragment  extends BaseFragment {

    public static Fragment createFragment() {
        return new OTPFragment();
    }

    private static final String SAVED_STATE_OTP_FRAGMENT = "SAVED_STATE_OTP_FRAGMENT";
    private OTPController otpController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        OTPViewMVC viewMVC = getCompositionRoot().getViewMVCFactory().getOTPViewMVC(container);
        otpController = getCompositionRoot().getOTPController();
        otpController.bindView(viewMVC);
        return viewMVC.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        otpController.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        otpController.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_STATE_OTP_FRAGMENT, otpController.getSavedState());
    }

    private void restoreControllerState(Bundle savedInstanceState) {
        otpController.restoreSavedState(
                (OTPController.SavedState)
                        savedInstanceState.getSerializable(SAVED_STATE_OTP_FRAGMENT)
        );
    }
}