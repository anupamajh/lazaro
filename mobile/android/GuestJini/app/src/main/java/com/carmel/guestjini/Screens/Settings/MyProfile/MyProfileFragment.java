package com.carmel.guestjini.Screens.Settings.MyProfile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.carmel.guestjini.Screens.Common.Controllers.BaseFragment;

public class MyProfileFragment  extends BaseFragment

{
    public static Fragment createFragment(){
        return new MyProfileFragment();
    }
    private static final String SAVED_STATE_MY_PROFILE_FRAGMENT = "SAVED_STATE_MY_PROFILE_FRAGMENT";

    private MyProfileController myProfileController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MyProfileViewMVC viewMvc = getCompositionRoot().getViewMVCFactory().getMyProfileViewMVC(container);
        myProfileController = getCompositionRoot().getMyProfileController();
        if (savedInstanceState != null) {
            restoreControllerState(savedInstanceState);
        }
        myProfileController.bindView(viewMvc);

        return viewMvc.getRootView();
    }

    private void restoreControllerState(Bundle savedInstanceState) {
        myProfileController.restoreSavedState(
                (MyProfileController.SavedState)
                        savedInstanceState.getSerializable(SAVED_STATE_MY_PROFILE_FRAGMENT)
        );
    }

    @Override
    public void onStart() {
        super.onStart();
        myProfileController.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        myProfileController.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_STATE_MY_PROFILE_FRAGMENT, myProfileController.getSavedState());
    }
}
