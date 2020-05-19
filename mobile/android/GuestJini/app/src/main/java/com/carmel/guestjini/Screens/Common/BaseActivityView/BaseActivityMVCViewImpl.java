package com.carmel.guestjini.Screens.Common.BaseActivityView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

public class BaseActivityMVCViewImpl extends BaseObservableViewMvc<BaseActivityMVCView.Listener>
implements BaseActivityMVCView
{
    private final FrameLayout baseLayout;
    private final FrameLayout frameLayout;

    public BaseActivityMVCViewImpl(LayoutInflater inflater, @Nullable ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.layout_base_base_activity, parent, false));
        baseLayout = findViewById(R.layout.layout_base_base_activity);
        frameLayout = findViewById(R.id.frame_content);

    }

    @Override
    public FrameLayout getFragmentFrame() {
        return frameLayout;
    }
}
