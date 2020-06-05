package com.carmel.guestjini.Screens.Common.Controllers;

import androidx.appcompat.app.AppCompatActivity;

import com.carmel.guestjini.Common.DependencyInjection.ControllerCompositionRoot;
import com.carmel.guestjini.Common.GuestJiniApplication;

public class BaseActivity extends AppCompatActivity {
    private ControllerCompositionRoot mControllerCompositionRoot;

    protected ControllerCompositionRoot getCompositionRoot() {
        if (mControllerCompositionRoot == null) {
            mControllerCompositionRoot = new ControllerCompositionRoot(
                    ((GuestJiniApplication) getApplication()).getCompositionRoot(),
                    this
            );
        }
        return mControllerCompositionRoot;
    }
}
