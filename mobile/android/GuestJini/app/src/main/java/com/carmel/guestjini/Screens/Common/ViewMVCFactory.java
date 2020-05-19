package com.carmel.guestjini.Screens.Common;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.carmel.guestjini.Screens.Common.BaseActivityView.BaseActivityMVCView;
import com.carmel.guestjini.Screens.Common.BaseActivityView.BaseActivityMVCViewImpl;
import com.carmel.guestjini.Screens.Common.Dialogs.PromptDialog.PromptViewMvc;
import com.carmel.guestjini.Screens.Common.Dialogs.PromptDialog.PromptViewMvcImpl;
import com.carmel.guestjini.Screens.Login.LoginViewMVC;
import com.carmel.guestjini.Screens.Login.LoginViewMVCImpl;
import com.carmel.guestjini.Screens.Welcome.WelcomeViewMVC;
import com.carmel.guestjini.Screens.Welcome.WelcomeViewMVCImpl;

public class ViewMVCFactory {
    private final LayoutInflater layoutInflater;

    public ViewMVCFactory(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    public BaseActivityMVCView getBaseActivityView(@Nullable ViewGroup parent) {
        return new BaseActivityMVCViewImpl(layoutInflater, parent);
    }

    public WelcomeViewMVC getWelcomeViewMVC(@Nullable ViewGroup parent) {
        return new WelcomeViewMVCImpl(layoutInflater,parent);
    }

    public LoginViewMVC getLoginViewMVC(@Nullable ViewGroup parent) {
        return new LoginViewMVCImpl(layoutInflater,parent);
    }

    public PromptViewMvc getPromptViewMvc(@Nullable ViewGroup parent) {
        return new PromptViewMvcImpl(layoutInflater, parent);
    }
}
