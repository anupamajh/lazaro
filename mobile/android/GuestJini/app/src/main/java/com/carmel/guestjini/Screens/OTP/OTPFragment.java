package com.carmel.guestjini.Screens.OTP;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.carmel.guestjini.Screens.Common.Controllers.BaseFragment;
import com.carmel.guestjini.Screens.Support.TicketCategory.TicketCategoryListFragment;

public class OTPFragment  extends BaseFragment {
    private static final String ARG_SCREEN_TYPE = "ARG_SCREEN_TYPE";

    public static Fragment createFragment(int screen) {
        Bundle args = new Bundle();
        args.putInt(ARG_SCREEN_TYPE, screen);
        OTPFragment fragment = new OTPFragment();
        fragment.setArguments(args);
        return fragment;
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
        otpController.onStart(getScreenType());
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
    private int getScreenType() {
        return getArguments().getInt(ARG_SCREEN_TYPE);
    }
}