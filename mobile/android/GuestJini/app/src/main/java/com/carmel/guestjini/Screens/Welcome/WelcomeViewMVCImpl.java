package com.carmel.guestjini.Screens.Welcome;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class WelcomeViewMVCImpl extends BaseObservableViewMvc<WelcomeViewMVC.Listener> implements WelcomeViewMVC {

    public WelcomeViewMVCImpl(LayoutInflater inflater,
                              @Nullable ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.layout_base_welcome, parent, false));
        final int[] images = new int[]{
                R.drawable.landing_image, R.drawable.orange_plain_image, R.drawable.purple_plain_image, R.drawable.yellow_plain_image

        };
        CarouselView carouselView = findViewById(R.id.carouselView);
        carouselView.setPageCount(images.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(images[position]);
            }
        });
        Button brnNext = findViewById(R.id.btnNext);
        brnNext.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onNextClicked();
            }
        });
    }
}
