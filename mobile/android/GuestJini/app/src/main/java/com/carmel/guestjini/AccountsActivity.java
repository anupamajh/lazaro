package com.carmel.guestjini;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.carmel.guestjini.Accounts.AccountsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AccountsActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private long ACTIVITY_ID = R.id.accountsIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.accountsIcon);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                for (int i = 0; i < bottomNavigationView.getMenu().size(); i++) {
//                    MenuItem menuItem = bottomNavigationView.getMenu().getItem(i);
//                    boolean isChecked = menuItem.getItemId() == item.getItemId();
//                    menuItem.setChecked(isChecked);
//                }
                if (item.getItemId() != ACTIVITY_ID) {
                    switch (item.getItemId()) {
                        case R.id.suppotIcon: {
                            Intent intent = new Intent(getApplicationContext(), SupportActivity.class);
                            startActivity(intent);
                        }
                        break;
                        case R.id.communityIcon: {
                            Intent intent = new Intent(getApplicationContext(), CommunityActivity.class);
                            startActivity(intent);
                        }
                        break;
                        case R.id.accountsIcon: {
                            Intent intent = new Intent(getApplicationContext(), AccountsActivity.class);
                            startActivity(intent);
                        }
                        break;
                        case R.id.rewardsIcon: {
                            return false;
                        }
                        //break;
                        case R.id.settingsIcon: {
                            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                            startActivity(intent);
                        }
                        break;
                    }
                }

                return true;
            }
        });
        AccountsFragment accountsFragment = new AccountsFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.AccountsPlaceHolder, accountsFragment);
        fragmentTransaction.commit();
    }
}
