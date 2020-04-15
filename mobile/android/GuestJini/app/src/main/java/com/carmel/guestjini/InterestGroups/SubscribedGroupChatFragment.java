package com.carmel.guestjini.InterestGroups;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.R;

import java.util.ArrayList;

import Adapter.GroupChatAdapter;
import Adapter.InterestGroupMemberAdapter;
import Model.GroupChatModel;
import Model.InterestGroupMembersModel;


public class SubscribedGroupChatFragment extends Fragment {
    ImageView backArrow,informationIcon;
    RecyclerView groupChatRecyclerView,groupInformationRecyclerView;
    ArrayList<GroupChatModel> groupChatList=new ArrayList<>();
    private TextView groupType,groupName,groupDescription;
    String group_Type,group_Name,group_Description;
    String group_Type1,group_Name1,group_Description1;

//    RelativeLayout recyclerViewMainLayout,unsubscribedLayout;

    ImageView informationButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_subscribed_group_chat, container, false);
        backArrow=rootView.findViewById(R.id.backButton);
        informationIcon=rootView.findViewById(R.id.informationButton);
        groupChatRecyclerView=rootView.findViewById(R.id.groupChatRecyclerView);
        groupInformationRecyclerView=rootView.findViewById(R.id.groupInformationRecyclerView);
//        recyclerViewMainLayout=rootView.findViewById(R.id.recyclerViewMainLayout);
//        unsubscribedLayout=rootView.findViewById(R.id.unsubscribedLayout);

        groupType=rootView.findViewById(R.id.interestGroupType);
        groupName=rootView.findViewById(R.id.interestGroupName);
        groupDescription=rootView.findViewById(R.id.interestsGroupDescription);
        Bundle bundle=this.getArguments();

        if(bundle!=null){
            group_Type=bundle.getString("Interest Group Type");
            group_Name=bundle.getString("Interest Group Name");
            group_Description=bundle.getString("interestGroupDescription");

            groupType.setText(group_Type);
            groupName.setText(group_Name);
            groupDescription.setText(group_Description);

        }
        Bundle bundle1=this.getArguments();
        if(bundle1!=null){
            group_Type=bundle.getString("Interest Group Type");
            group_Name=bundle.getString("Interest Group Name");
            group_Description=bundle.getString("interestGroupDescription");

            groupType.setText(group_Type);
            groupName.setText(group_Name);
            groupDescription.setText(group_Description);

        }
        informationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubscribedGroupDetailedFragment subscribedGroupDetailedFragment=new SubscribedGroupDetailedFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.interestGroupsPlaceHolder, subscribedGroupDetailedFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                Bundle bundle=new Bundle();
                bundle.putString("Interest Group Type",group_Type);
                bundle.putString("Interest Group Name",group_Name);
                bundle.putString("interestGroupDescription",group_Description);
                subscribedGroupDetailedFragment.setArguments(bundle);
            }
        });


        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InterestGroupsFragment interestGroupsFragment=new InterestGroupsFragment();
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.interestGroupsPlaceHolder,interestGroupsFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        groupChatRecyclerView.setLayoutManager(linearLayoutManager);
        GroupChatAdapter groupChatAdapter=new GroupChatAdapter(rootView.getContext(),groupChatList);
        groupChatRecyclerView.setAdapter(groupChatAdapter);

        groupChatList.add(new GroupChatModel(
                "26 Jul 2019, 02:25 pm",
                "Peter Law",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam erat sapien, ultricies.",GroupChatModel.ONE_TYPE));

        groupChatList.add(new GroupChatModel(
                "26 Jul 2019, 04:25 pm",
                "Luke Ray ",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam erat sapien, ultricies.",GroupChatModel.ONE_TYPE));

        groupChatList.add(new GroupChatModel(
                "26 Jul 2019, 02:25 pm",
                "Andrew Paul ",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam erat sapien, ultricies.",GroupChatModel.ONE_TYPE));

        groupChatList.add(new GroupChatModel(
                "26 Jul 2019, 06:25 pm",
                "You ",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam erat sapien, ultricies.",GroupChatModel.TWO_TYPE));
        return rootView;
    }

}
