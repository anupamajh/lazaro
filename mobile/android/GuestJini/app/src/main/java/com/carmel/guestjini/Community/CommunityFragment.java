package com.carmel.guestjini.Community;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Delegates.CommunityMainNavigationListener;
import com.carmel.guestjini.Groups.GroupsFragment;
import com.carmel.guestjini.People.PeopleLandingFragment;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Settings.MyProfileFragment;

import java.util.ArrayList;

import Adapter.CommunityAdapter;
import Model.CommunityModel;


public class CommunityFragment extends Fragment implements CommunityMainNavigationListener {
    private RecyclerView communityRecyclerView;
    private ArrayList<CommunityModel> communityModelArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_community_landing, container, false);
        communityRecyclerView = rootView.findViewById(R.id.communityRecyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        communityRecyclerView.setLayoutManager(linearLayoutManager);
        communityRecyclerView.setHasFixedSize(true);
        CommunityAdapter communityAdapter = new CommunityAdapter(getContext(), communityModelArrayList);
        communityAdapter.setCommunityMainNavigationListener(this);
        communityRecyclerView.setAdapter(communityAdapter);
        communityModelArrayList.add(new CommunityModel(null, "MY PROFILE", "Based on your profile, you will be able to connect with the best matches who share your interests. Go ahead and setup your profile."));
        communityModelArrayList.add(new CommunityModel(null, "PEOPLE", "Meet the community members and make new friends."));
        communityModelArrayList.add(new CommunityModel(null, "GROUPS", "Join the groups and catch all the action. You will get suggestions based on your profile."));

        return rootView;
    }

    @Override
    public void onClickNavigation(int clickedItem) {
        switch (clickedItem) {
            case 1: { //Load Profile
                loadProfileFragment();
            }
            break;
            case 2: { //Load People
                loadPeopleFragment();
            }
            break;
            case 3: { //Load Groups
                loadGroupFragment();
            }
            break;
        }
    }

    private void loadProfileFragment() {
        Bundle bundle = new Bundle();
        bundle.putInt("back_constant", 1);
        MyProfileFragment myProfileFragment = new MyProfileFragment();
        FragmentManager fragmentManager = getFragmentManager();
        myProfileFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.CommunityPlaceHolder, myProfileFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    private void loadPeopleFragment() {
        PeopleLandingFragment peopleLandingFragment = new PeopleLandingFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.CommunityPlaceHolder, peopleLandingFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void loadGroupFragment() {
        GroupsFragment groupsFragment = new GroupsFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.CommunityPlaceHolder, groupsFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
