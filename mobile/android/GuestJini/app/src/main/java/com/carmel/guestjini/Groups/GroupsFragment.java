package com.carmel.guestjini.Groups;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.carmel.guestjini.CommunityActivity;
import com.carmel.guestjini.R;

import java.util.ArrayList;

import Adapter.GroupsAdapter;
import Model.CommunityModel;

public class GroupsFragment extends Fragment {
    private RecyclerView groupsRecyclerView;
    private ArrayList<CommunityModel> groupsModelArrayList=new ArrayList<>();
    private ImageView leftArrowMark;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_groups_landing, container, false);
        groupsRecyclerView=view.findViewById(R.id.groupsRecyclerView);
        leftArrowMark=view.findViewById(R.id.leftArrowMark);

        leftArrowMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), CommunityActivity.class);
                startActivity(intent);
            }
        });

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        groupsRecyclerView.setLayoutManager(linearLayoutManager);
        groupsRecyclerView.setHasFixedSize(true);
        GroupsAdapter groupsAdapter=new GroupsAdapter(getContext(),groupsModelArrayList);
        groupsRecyclerView.setAdapter(groupsAdapter);
        groupsModelArrayList.add(new CommunityModel(null,"INTEREST GROUPS","There is a group for every activity, hobby or topic. Connect with like minded people."));
        groupsModelArrayList.add(new CommunityModel(null,"COMMUNITY GROUPS","Explore groups created by the community members. Participate and catch all the action."));
        groupsModelArrayList.add(new CommunityModel(null,"MY GROUPS","Create and manage your own groups. Host parties, events or simply bond together."));
        return view;
    }


}
