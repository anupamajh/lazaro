package com.carmel.guestjini.MyGroups;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.CommunityGroups.CommunityGroupsFragment;
import com.carmel.guestjini.CommunityGroups.InvitedGroupViewFragment;
import com.carmel.guestjini.R;

import java.util.ArrayList;

import Adapter.GroupChatAdapter;
import Model.GroupChatModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyGroupsChatFragment extends Fragment {
    private ImageView backArrow;
    private TextView myGroupName,myGroupDescription;
    String MyGroupName,MyGroupDescription;
    private RecyclerView groupChatRecyclerView;
    private ArrayList<GroupChatModel> groupChatList=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_my_group_chat, container, false);
        myGroupName=rootView.findViewById(R.id.myGroupName);
        myGroupDescription=rootView.findViewById(R.id.myGroupDescription);
        groupChatRecyclerView=rootView.findViewById(R.id.myGroupChatRecyclerView);
        backArrow=rootView.findViewById(R.id.backButton);

        Bundle bundle=getArguments();
        if(bundle!=null) {
            MyGroupName = bundle.getString("MyGroupName");
            MyGroupDescription = bundle.getString("MyGroupDescription");

            myGroupName.setText(MyGroupName);
            myGroupDescription.setText(MyGroupDescription);
        }

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
