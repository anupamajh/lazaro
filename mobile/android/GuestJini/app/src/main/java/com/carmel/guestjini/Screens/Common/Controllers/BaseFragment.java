package com.carmel.guestjini.Screens.Common.Controllers;

import androidx.fragment.app.Fragment;

import com.carmel.guestjini.Common.DependencyInjection.ControllerCompositionRoot;
import com.carmel.guestjini.Common.GuestJiniApplication;

public class BaseFragment extends Fragment {
    private ControllerCompositionRoot mControllerCompositionRoot;

    protected ControllerCompositionRoot getCompositionRoot() {
        if (mControllerCompositionRoot == null) {
            mControllerCompositionRoot = new ControllerCompositionRoot(
                    ((GuestJiniApplication) requireActivity().getApplication()).getCompositionRoot(),
                    requireActivity()
            );
        }
        return mControllerCompositionRoot;
    }
}
