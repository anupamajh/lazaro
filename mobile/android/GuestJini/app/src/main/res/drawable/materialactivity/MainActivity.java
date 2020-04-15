package com.carmel.materialactivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    MaterialButton login;
    TextInputEditText textInputEditText;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login=findViewById(R.id.loginButton);
        fab=findViewById(R.id.cameraIcon);
        textInputEditText=findViewById(R.id.userName);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(getApplicationContext(),SupportMaterialsActivity.class);
//                startActivity(intent);
                fab.setBackgroundTintList(ColorStateList.valueOf(Color
                        .parseColor("#33691E")));
            }
        });
    }
}
