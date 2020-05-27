package com.carmel.guestjini.Screens.Accounts.AccountsHome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.carmel.guestjini.Screens.Common.Controllers.BaseFragment;

public class AccountsHomeFragment  extends BaseFragment {
    public static Fragment createFragment(){
        return new AccountsHomeFragment();
    }
    private static final String SAVED_STATE_ACCOUNTS_HOME_FRAGMENT = "SAVED_STATE_ACCOUNTS_HOME_FRAGMENT";
    private AccountsHomeController accountsHomeController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AccountsHomeViewMVC viewMVC = getCompositionRoot().getViewMVCFactory().getAccountsHomeViewMVC(container);
        accountsHomeController = getCompositionRoot().getAccountsHomeController();
        accountsHomeController.bindView(viewMVC);
        return viewMVC.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        accountsHomeController.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        accountsHomeController.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_STATE_ACCOUNTS_HOME_FRAGMENT, accountsHomeController.getSavedState());
    }

    private void restoreControllerState(Bundle savedInstanceState){
        accountsHomeController.restoreSavedState(
                (AccountsHomeController.SavedState)
                        savedInstanceState.getSerializable(SAVED_STATE_ACCOUNTS_HOME_FRAGMENT)
        );
    }
}