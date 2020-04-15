package com.carmel.guestjini.CommunityGroups;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.carmel.guestjini.InterestGroups.InterestGroupsFragment;
import com.carmel.guestjini.InterestGroups.SubscribedGroupDetailedFragment;
import com.carmel.guestjini.R;

import java.util.ArrayList;

import Adapter.GroupChatAdapter;
import Model.GroupChatModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommunityGroupChatFragment extends Fragment {
    private ImageView backArrow,informationIcon;
    private TextView communityGroupName,communityGroupDescription;
    String CommunityGroupName,CommunityGroupDescription,GroupAdminName,GroupCreationDateAndTime;;
    private RecyclerView groupChatRecyclerView;
    private ArrayList<GroupChatModel> groupChatList=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_community_group_chat, container, false);
        communityGroupName=rootView.findViewById(R.id.communityGroupName);
        communityGroupDescription=rootView.findViewById(R.id.communityGroupDescription);
        groupChatRecyclerView=rootView.findViewById(R.id.communityGroupChatRecyclerView);
        backArrow=rootView.findViewById(R.id.backButton);
        informationIcon=rootView.findViewById(R.id.informationButton);

        Bundle bundle=getArguments();
        if(bundle!=null){
            CommunityGroupName=bundle.getString("GroupName");
            CommunityGroupDescription=bundle.getString("GroupDescription");

            communityGroupName.setText(CommunityGroupName);
            communityGroupDescription.setText(CommunityGroupDescription);
        }

        informationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InvitedGroupViewFragment invitedGroupViewFragment=new InvitedGroupViewFragment();
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.communityGroupsPlaceHolder,invitedGroupViewFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                Bundle bundle=new Bundle();
                bundle.putString("GroupName",CommunityGroupName);
                bundle.putString("GroupDescription",CommunityGroupDescription);
                bundle.putString("GroupAdminName",GroupAdminName);
                bundle.putString("GroupCreationDateAndTime",GroupCreationDateAndTime);
                invitedGroupViewFragment.setArguments(bundle);
            }
        });

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommunityGroupsFragment communityGroupsFragment=new CommunityGroupsFragment();
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.communityGroupsPlaceHolder,communityGroupsFragment);
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
