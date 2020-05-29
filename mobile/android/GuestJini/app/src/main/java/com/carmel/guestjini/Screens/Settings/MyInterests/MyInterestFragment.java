package com.carmel.guestjini.Screens.Settings.MyInterests;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.carmel.guestjini.Screens.Common.Controllers.BaseFragment;

public class MyInterestFragment
        extends BaseFragment {
    public static Fragment createFragment() {
        return new MyInterestFragment();
    }

    private static final String SAVED_STATE_MY_INTEREST_FRAGMENT = "SAVED_STATE_MY_INTEREST_FRAGMENT";

    private MyInterestController myInterestController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MyInterestViewMVC viewMvc = getCompositionRoot().getViewMVCFactory().getMyInterestViewMVC(container);
        myInterestController = getCompositionRoot().getMyInterestController();
        if (savedInstanceState != null) {
            restoreControllerState(savedInstanceState);
        }
        myInterestController.bindView(viewMvc);

        return viewMvc.getRootView();
    }

    private void restoreControllerState(Bundle savedInstanceState) {
        myInterestController.restoreSavedState(
                (MyInterestController.SavedState)
                        savedInstanceState.getSerializable(SAVED_STATE_MY_INTEREST_FRAGMENT)
        );
    }

    @Override
    public void onStart() {
        super.onStart();
        myInterestController.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        myInterestController.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_STATE_MY_INTEREST_FRAGMENT, myInterestController.getSavedState());
    }
}
