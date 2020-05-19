package com.carmel.guestjini.Common;

import android.app.Application;

import com.carmel.guestjini.Common.DependencyInjection.CompositionRoot;

public class GuestJiniApplication  extends Application {
    private CompositionRoot compositionRoot;

    @Override
    public void onCreate() {
        super.onCreate();
        compositionRoot = new CompositionRoot();
    }

    public CompositionRoot getCompositionRoot(){
        return compositionRoot;
    }
}
