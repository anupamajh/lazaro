package com.carmel.guestjini;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.carmel.guestjini.InterestGroups.InterestGroupsFragment;

public class InterestGroupsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest_groups);
        InterestGroupsFragment interestGroupsFragment = new InterestGroupsFragment();
//        SubscribedGroupChatFragment subscribedGroupChatFragment=new SubscribedGroupChatFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.interestGroupsPlaceHolder, interestGroupsFragment);
        fragmentTransaction.commit();
    }
}
