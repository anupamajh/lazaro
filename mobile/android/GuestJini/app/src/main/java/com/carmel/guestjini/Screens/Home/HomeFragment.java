package com.carmel.guestjini.Screens.Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.carmel.guestjini.Screens.Common.Controllers.BaseFragment;

public class HomeFragment  extends BaseFragment {
    public static Fragment createFragment() {
        return new HomeFragment();
    }

    private static final String SAVED_STATE_HOME_FRAGMENT = "SAVED_STATE_HOME_FRAGMENT";
    private HomeController homeController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        HomeViewMVC viewMVC = getCompositionRoot().getViewMVCFactory().getHomeViewMVC(container);
        homeController = getCompositionRoot().getHomeController();
        homeController.bindView(viewMVC);
        return viewMVC.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        homeController.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        homeController.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_STATE_HOME_FRAGMENT, homeController.getSavedState());
    }

    private void restoreControllerState(Bundle savedInstanceState) {
        homeController.restoreSavedState(
                (HomeController.SavedState)
                        savedInstanceState.getSerializable(SAVED_STATE_HOME_FRAGMENT)
        );
    }
}
