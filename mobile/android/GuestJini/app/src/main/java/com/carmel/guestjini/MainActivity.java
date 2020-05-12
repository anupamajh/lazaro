package com.carmel.guestjini;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class MainActivity extends AppCompatActivity {
    CarouselView carouselView;
    MaterialButton rightArrowIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rightArrowIcon=findViewById(R.id.landingButton);
        carouselView=findViewById(R.id.carouselView);
        final  int[] images=new int[]{
                R.drawable.landing_image,R.drawable.orange_plain_image,R.drawable.purple_plain_image,R.drawable.yellow_plain_image

        };
        SharedPreferences preferences = getSharedPreferences("GuestJini", Context.MODE_PRIVATE);
        if (preferences.getBoolean("isLoggedIn", false)) {
            Intent intent = new Intent(MainActivity.this, SupportActivity.class);
            MainActivity.this.startActivity(intent);
        }
        carouselView.setPageCount(images.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(images[position]);
            }
        });

        rightArrowIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });
    }
}
