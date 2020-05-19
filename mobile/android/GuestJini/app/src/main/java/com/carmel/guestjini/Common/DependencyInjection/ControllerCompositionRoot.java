package com.carmel.guestjini.Common.DependencyInjection;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.carmel.guestjini.Authentication.AttemptLoginUseCase;
import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsEventBus;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsManager;
import com.carmel.guestjini.Screens.Common.FragmentHelper.FragmentFrameHelper;
import com.carmel.guestjini.Screens.Common.FragmentHelper.FragmentFrameWrapper;
import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;
import com.carmel.guestjini.Screens.Common.SharedPreference.SharedPreferenceHelper;
import com.carmel.guestjini.Screens.Common.ViewMVCFactory;
import com.carmel.guestjini.Screens.Login.LoginController;
import com.carmel.guestjini.Screens.Welcome.WelcomeController;

public class ControllerCompositionRoot {
    private final CompositionRoot compositionRoot;
    private final FragmentActivity fragmentActivity;
    private final SharedPreferences preferences;
    private final SharedPreferences.Editor editor;


    public ControllerCompositionRoot(
            CompositionRoot compositionRoot,
            FragmentActivity fragmentActivity
    ) {
        this.compositionRoot = compositionRoot;
        this.fragmentActivity = fragmentActivity;
        this.preferences = fragmentActivity.getSharedPreferences("GuestJini", Context.MODE_PRIVATE);
        editor = this.preferences.edit();
    }

    private FragmentActivity getActivity() {
        return fragmentActivity;
    }

    private Context getContext() {
        return fragmentActivity;
    }

    private FragmentManager getFragmentManager() {
        return getActivity().getSupportFragmentManager();
    }

    private LayoutInflater getLayoutInflater() {
        return LayoutInflater.from(getContext());
    }

    public ScreensNavigator getScreensNavigator() {
        return new ScreensNavigator(getFragmentFrameHelper());
    }

    private FragmentFrameHelper getFragmentFrameHelper() {
        return new FragmentFrameHelper(getActivity(), getFragmentFrameWrapper(), getFragmentManager());
    }

    private FragmentFrameWrapper getFragmentFrameWrapper() {
        return (FragmentFrameWrapper) getActivity();
    }

    public ViewMVCFactory getViewMVCFactory() {
        return new ViewMVCFactory(getLayoutInflater());
    }

    public WelcomeController getWelcomeController() {
        return new WelcomeController(
                getScreensNavigator()
        );
    }

    private GuestJiniAPI getGuestJiniAPI() {
        return compositionRoot.getGuestJiniAPI();
    }

    public AttemptLoginUseCase getAttemptLoginUseCase(){
        return new AttemptLoginUseCase(getGuestJiniAPI());
    }

    public SharedPreferenceHelper getSharedPreferenceHelper(){
        return new SharedPreferenceHelper(preferences, editor);

    }
    public LoginController getLoginController() {
        return new LoginController(
                getScreensNavigator(),
                getAttemptLoginUseCase(),
                getSharedPreferenceHelper(),
                getDialogsManager()
        );
    }

    public DialogsManager getDialogsManager() {
        return new DialogsManager(getContext(), getFragmentManager());
    }

    public DialogsEventBus getDialogsEventBus() {
        return compositionRoot.getDialogsEventBus();
    }
}
