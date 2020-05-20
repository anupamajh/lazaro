package com.carmel.guestjini.Screens.Support.KBList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.carmel.guestjini.Screens.Common.Controllers.BaseFragment;

public class KBListFragment
        extends BaseFragment {
    private static final String ARG_SEARCH_STRING = "ARG_SEARCH_STRING";
    public static Fragment createFragment(){
        return new KBListFragment();
    }
    private static final String SAVED_STATE_KB_LIST_FRAGMENT = "SAVED_STATE_KB_LIST_FRAGMENT";

    private KBListController kbListController;

    public static Fragment createFragment(String searchText) {
        Bundle args = new Bundle();
        args.putString(ARG_SEARCH_STRING, searchText);
        KBListFragment fragment = new KBListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        KBListViewMVC viewMvc = getCompositionRoot().getViewMVCFactory().getKBListMVC(container);
        kbListController = getCompositionRoot().getKBListController();
        if (savedInstanceState != null) {
            restoreControllerState(savedInstanceState);
        }
        kbListController.bindView(viewMvc);

        return viewMvc.getRootView();
    }

    private void restoreControllerState(Bundle savedInstanceState) {
        kbListController.restoreSavedState(
                (KBListController.SavedState)
                        savedInstanceState.getSerializable(SAVED_STATE_KB_LIST_FRAGMENT)
        );
    }

    @Override
    public void onStart() {
        super.onStart();
        kbListController.onStart(getSearchString());
    }

    @Override
    public void onStop() {
        super.onStop();
        kbListController.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_STATE_KB_LIST_FRAGMENT, kbListController.getSavedState());
    }

    private String getSearchString() {
        return getArguments().getString(ARG_SEARCH_STRING);
    }
}
