package com.carmel.guestjini.Screens.Common.Controllers;

import com.carmel.guestjini.Common.DependencyInjection.ControllerCompositionRoot;
import com.carmel.guestjini.Common.GuestJiniApplication;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BaseBottomSheetFragment extends BottomSheetDialogFragment {

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
