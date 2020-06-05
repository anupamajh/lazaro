package com.carmel.guestjini.Screens.Settings.MyProfile;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.carmel.guestjini.Screens.Common.Controllers.ActivityResultListener;
import com.carmel.guestjini.Screens.Common.Controllers.BaseFragment;

public class MyProfileFragment extends BaseFragment
        implements ActivityResultListener {
    public static Fragment createFragment() {
        return new MyProfileFragment();
    }

    private static final String SAVED_STATE_MY_PROFILE_FRAGMENT = "SAVED_STATE_MY_PROFILE_FRAGMENT";

    private MyProfileController myProfileController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MyProfileViewMVC viewMvc = getCompositionRoot().getViewMVCFactory().getMyProfileViewMVC(container);
        myProfileController = getCompositionRoot().getMyProfileController();
        getCompositionRoot().getActivityResultDispatcher().registerListener(this);
        if (savedInstanceState != null) {
            restoreControllerState(savedInstanceState);
        }
        myProfileController.bindView(viewMvc);
        myProfileController.bindActivity(this.getActivity());
        //viewMvc.enableProfileIcon(false);
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            viewMvc.enableProfileIcon(true);
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        myProfileController.onActivityResult(requestCode, resultCode, data);
    }
}
