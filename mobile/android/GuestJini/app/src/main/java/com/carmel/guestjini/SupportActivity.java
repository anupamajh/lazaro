package com.carmel.guestjini;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.carmel.guestjini.Components.BottomNavigationListener;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Support.MyTicketDetailsFragment;
import com.carmel.guestjini.Support.SupportLandingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SupportActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private long ACTIVITY_ID = R.id.suppotIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() != ACTIVITY_ID){
                    switch (item.getItemId()){
                        case R.id.suppotIcon:
                        {
                            Intent intent=new Intent(getApplicationContext(), SupportActivity.class);
                            startActivity(intent);
                        }
                        break;
                        case R.id.communityIcon:
                        {
                            Intent intent=new Intent(getApplicationContext(), CommunityActivity.class);
                            startActivity(intent);
                        }
                        break;
                        case R.id.accountsIcon:
                        {
                            Intent intent=new Intent(getApplicationContext(), AccountsActivity.class);
                            startActivity(intent);
                        }
                        break;
                        case R.id.rewardsIcon:
                        {
                            return false;
                        }
                        //break;
                        case R.id.settingsIcon:
                        {
                            Intent intent=new Intent(getApplicationContext(), SettingsActivity.class);
                            startActivity(intent);
                        }
                        break;
                    }
                }

                return true;
            }
        });
        SupportLandingFragment supportLandingFragment=new SupportLandingFragment();
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.SuppotPlaceHolder,supportLandingFragment);
        fragmentTransaction.commit();

    }
}
