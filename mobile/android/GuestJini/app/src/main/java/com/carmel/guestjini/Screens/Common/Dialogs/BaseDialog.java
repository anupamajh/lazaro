package com.carmel.guestjini.Screens.Common.Dialogs;


import androidx.fragment.app.DialogFragment;

import com.carmel.guestjini.Common.DependencyInjection.ControllerCompositionRoot;
import com.carmel.guestjini.Common.GuestJiniApplication;

public abstract class BaseDialog extends DialogFragment {


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
