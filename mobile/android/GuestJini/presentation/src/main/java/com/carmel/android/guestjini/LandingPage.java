package com.carmel.android.guestjini;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LandingPage extends AppCompatActivity {

    CarouselView carouselView;
    int[] sampleImages = {R.drawable.image1, R.drawable.image2};

    @BindView(R.id.btnShowLoginScreen)
    Button btnLoginScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_landing_page);
        carouselView = findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);
        ButterKnife.bind(this);
        SharedPreferences preferences = getSharedPreferences("GuestJini", Context.MODE_PRIVATE);
        if (preferences.getBoolean("isLoggedIn", false)) {
            Intent intent = new Intent(LandingPage.this, HomePage.class);
            LandingPage.this.startActivity(intent);
        }

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

    @OnClick(R.id.btnShowLoginScreen)
    public void showLogin(View view) {
        Intent intent = new Intent(LandingPage.this, LoginPage.class);
        LandingPage.this.startActivity(intent);
    }

}
