package com.carmel.guestjini.Community;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.carmel.guestjini.R;

import java.util.ArrayList;

import Adapter.CommunityAdapter;
import Model.CommunityModel;


public class CommunityFragment extends Fragment {
    private RecyclerView communityRecyclerView;
    private ArrayList<CommunityModel> communityModelArrayList =new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_community_landing, container, false);
        communityRecyclerView=rootView.findViewById(R.id.communityRecyclerView);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        communityRecyclerView.setLayoutManager(linearLayoutManager);
        communityRecyclerView.setHasFixedSize(true);
        CommunityAdapter communityAdapter=new CommunityAdapter(getContext(),communityModelArrayList);
        communityRecyclerView.setAdapter(communityAdapter);
        communityModelArrayList.add(new CommunityModel(null,"MY PROFILE","Based on your profile, you will be able to connect with the best matches who share your interests. Go ahead and setup your profile."));
        communityModelArrayList.add(new CommunityModel(null,"PEOPLE","Meet the community members and make new friends."));
        communityModelArrayList.add(new CommunityModel(null,"GROUPS","Join the groups and catch all the action. You will get suggestions based on your profile."));

        return rootView;
    }
}
