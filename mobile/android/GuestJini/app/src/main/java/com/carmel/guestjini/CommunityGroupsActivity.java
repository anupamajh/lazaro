package com.carmel.guestjini;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.carmel.guestjini.CommunityGroups.CommunityGroupsFragment;

public class CommunityGroupsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_groups);
        CommunityGroupsFragment communityGroupsFragment = new CommunityGroupsFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.communityGroupsPlaceHolder, communityGroupsFragment);
        fragmentTransaction.commit();
    }
}
