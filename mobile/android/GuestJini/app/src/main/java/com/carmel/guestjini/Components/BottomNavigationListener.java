package com.carmel.guestjini.Components;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.carmel.guestjini.AccountsActivity;
import com.carmel.guestjini.CommunityActivity;
import com.carmel.guestjini.R;
import com.carmel.guestjini.SettingsActivity;
import com.carmel.guestjini.SupportActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationListener implements BottomNavigationView.OnNavigationItemSelectedListener {
    private long currentMenu;
    private Context context;

    public BottomNavigationListener(long currentMenu, Context context) {
        this.currentMenu = currentMenu;
        this.context = context;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() != this.currentMenu){
            switch (item.getItemId()){
                case R.id.suppotIcon:
                {
                    Intent intent=new Intent(context, SupportActivity.class);
                    context.startActivity(intent);
                }
                break;
                case R.id.communityIcon:
                {
                    Intent intent=new Intent(context, CommunityActivity.class);
                    context.startActivity(intent);
                }
                break;
                case R.id.accountsIcon:
                {
                    Intent intent=new Intent(context, AccountsActivity.class);
                    context.startActivity(intent);
                }
                break;
                case R.id.rewardsIcon:
                {
                    return false;
                }
                //break;
                case R.id.settingsIcon:
                {
                    Intent intent=new Intent(context, SettingsActivity.class);
                    context.startActivity(intent);
                }
                break;
            }
        }

        return true;
    }
}
