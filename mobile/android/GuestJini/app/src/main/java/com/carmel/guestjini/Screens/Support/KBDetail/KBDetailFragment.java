package com.carmel.guestjini.Screens.Support.KBDetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.carmel.guestjini.Screens.Common.Controllers.BaseFragment;

public class KBDetailFragment extends BaseFragment {
    private static final String ARG_KB_ID = "ARG_KB_ID";
    private static final String SAVED_STATE_KB_DETAIL_FRAGMENT = "SAVED_STATE_KB_DETAIL_FRAGMENT";

    public static Fragment createFragment(String kbId){
        Bundle args = new Bundle();
        args.putString(ARG_KB_ID, kbId);
        KBDetailFragment fragment = new KBDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private KBDetailController kbDetailController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        KBDetailViewMVC viewMvc = getCompositionRoot().getViewMVCFactory().getKBDetailsViewMVC(container);
        kbDetailController = getCompositionRoot().getKBDetailController();
        if (savedInstanceState != null) {
            restoreControllerState(savedInstanceState);
        }
        kbDetailController.bindView(viewMvc);

        return viewMvc.getRootView();
    }

    private void restoreControllerState(Bundle savedInstanceState) {
        kbDetailController.restoreSavedState(
                (KBDetailController.SavedState)
                        savedInstanceState.getSerializable(SAVED_STATE_KB_DETAIL_FRAGMENT)
        );
    }

    @Override
    public void onStart() {
        super.onStart();
        kbDetailController.onStart(getKBId());
    }

    @Override
    public void onStop() {
        super.onStop();
        kbDetailController.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_STATE_KB_DETAIL_FRAGMENT, kbDetailController.getSavedState());
    }

    private String getKBId() {
        return getArguments().getString(ARG_KB_ID);
    }

}
