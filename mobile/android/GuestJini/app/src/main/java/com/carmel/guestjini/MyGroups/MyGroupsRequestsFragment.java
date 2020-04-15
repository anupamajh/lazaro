package com.carmel.guestjini.MyGroups;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.carmel.guestjini.R;

import java.util.ArrayList;
import Adapter.MyGroupsRequestsAdpter;
import Model.MyGroupsRequestsModel;


public class MyGroupsRequestsFragment extends Fragment {
    private RecyclerView myGroupsRequestsRecyclerView;
    private ImageView backArrow;
    private ArrayList<MyGroupsRequestsModel> myGroupsRequestsArrayList=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_my_groups_requests, container, false);
        myGroupsRequestsRecyclerView=rootView.findViewById(R.id.myGroupsRequestsRecyclerView);
        backArrow=rootView.findViewById(R.id.backButton);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyGroupsDetailsFragment myGroupsDetailsFragment=new MyGroupsDetailsFragment();
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.myGroupsPlaceHolder,myGroupsDetailsFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        myGroupsRequestsRecyclerView.setLayoutManager(linearLayoutManager);
        myGroupsRequestsRecyclerView.setHasFixedSize(true);
        MyGroupsRequestsAdpter myGroupsRequestsAdpter=new MyGroupsRequestsAdpter(getContext(),myGroupsRequestsArrayList);
        myGroupsRequestsRecyclerView.setAdapter(myGroupsRequestsAdpter);
        myGroupsRequestsRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

        myGroupsRequestsArrayList.add(new MyGroupsRequestsModel(R.drawable.profile2,
                "Nora Bravos",
                "Today 11:48 AM (2 Hours ago)",
                R.drawable.round_right_icon_xxhdpi,"ACCEPT"));

        myGroupsRequestsArrayList.add(new MyGroupsRequestsModel(R.drawable.profile2,
                "Luke Ray",
                "29 Jun 2019 10:05 AM (1 Day ago)",
                R.drawable.round_right_icon_xxhdpi,"ACCEPT"));

        myGroupsRequestsArrayList.add(new MyGroupsRequestsModel(R.drawable.profile2,
                "Daisy Lake",
                "30 Jun 2019 06:32 PM (2 Days ago)",
                R.drawable.right_icon_xxhdpi,"ACCEPTED"));

        return rootView;
    }

}
