package com.carmel.guestjini.InterestGroups;


import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.carmel.guestjini.ForgotPasswordOTPValidation;
import com.carmel.guestjini.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import Adapter.InterestGroupMemberAdapter;
import Model.InterestGroupMembersModel;


public class SubscribedGroupDetailedFragment extends Fragment {
    private RecyclerView groupInformationRecyclerView;
    private ArrayList<InterestGroupMembersModel> interestGroupMemberslist=new ArrayList<>();
    private Button subscribeButton,exitButton;
    private TextView groupType,groupName,groupDescription;
    private ImageView backArrow;
    String group_Type,group_Name,group_Description;
    String name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_subscribed_group_detailed, container, false);
        subscribeButton=rootView.findViewById(R.id.subscribeButton1);
        exitButton=rootView.findViewById(R.id.exitButton);
        groupInformationRecyclerView=rootView.findViewById(R.id.groupInformationRecyclerView);
        groupType=rootView.findViewById(R.id.interestGroupType);
        groupName=rootView.findViewById(R.id.interestGroupName);
        backArrow=rootView.findViewById(R.id.backButton);
        groupDescription=rootView.findViewById(R.id.interestsGroupDescription);
        final Bundle bundle=getArguments();
        if(bundle!=null){
            group_Type=bundle.getString("Interest Group Type");
            group_Name=bundle.getString("Interest Group Name");
            group_Description=bundle.getString("interestGroupDescription");

            groupType.setText(group_Type);
            groupName.setText(group_Name);
            groupDescription.setText(group_Description);

        }

        if(group_Name.equals("CYCLING")){
            exitButton.setVisibility(View.VISIBLE);
            subscribeButton.setVisibility(View.GONE);
        }else{
            exitButton.setVisibility(View.GONE);
            subscribeButton.setVisibility(View.VISIBLE);
        }
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.alert_dailogbox);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


                TextView alertDailogTitle = (TextView) dialog.findViewById(R.id.alertDailogTitle);
                alertDailogTitle.setText(getText(R.string.exit_group));
                alertDailogTitle.setTextColor(ColorStateList.valueOf(Color.parseColor("#E65959")));

                TextView alertDailogMessage = (TextView) dialog.findViewById(R.id.alertDailogDescription);
                alertDailogMessage.setText(getText(R.string.feedback_success));

                FloatingActionButton doneButton = (FloatingActionButton) dialog.findViewById(R.id.done_button);
                doneButton.setBackgroundTintList(ColorStateList.valueOf(Color
                        .parseColor("#E65959")));
                doneButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        InterestGroupsFragment interestGroupsFragment=new InterestGroupsFragment();
                        FragmentManager fragmentManager=getFragmentManager();
                        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.interestGroupsPlaceHolder,interestGroupsFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                });

                dialog.show();
            }
        });

        subscribeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.alert_dailogbox);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                TextView alertDailogTitle = (TextView) dialog.findViewById(R.id.alertDailogTitle);
                alertDailogTitle.setText(getText(R.string.success));

                TextView alertDailogMessage = (TextView) dialog.findViewById(R.id.alertDailogDescription);
                alertDailogMessage.setText(getText(R.string.feedback_success));

                FloatingActionButton doneButton = (FloatingActionButton) dialog.findViewById(R.id.done_button);
                doneButton.setBackgroundTintList(ColorStateList.valueOf(Color
                        .parseColor("#32BDD2")));
                doneButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        SubscribedGroupChatFragment subscribedGroupChatFragment=new SubscribedGroupChatFragment();
                        FragmentManager fragmentManager=getFragmentManager();
                        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.interestGroupsPlaceHolder,subscribedGroupChatFragment);
                        fragmentTransaction.addToBackStack(null);
                        Bundle bundle1=new Bundle();
                        bundle1.putString("Interest Group Type",group_Type);
                        bundle1.putString("Interest Group Name",group_Name);
                        bundle1.putString("interestGroupDescription",group_Description);
                        fragmentTransaction.commit();
                        subscribedGroupChatFragment.setArguments(bundle1);
                    }
                });

                dialog.show();
            }
        });

        LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(getContext());
        groupInformationRecyclerView.setLayoutManager(linearLayoutManager1);
        groupInformationRecyclerView.setHasFixedSize(true);
        InterestGroupMemberAdapter interestGroupMemmberRecyclerAdapter=new InterestGroupMemberAdapter(rootView.getContext(),interestGroupMemberslist);
        groupInformationRecyclerView.setAdapter(interestGroupMemmberRecyclerAdapter);

        InterestGroupMembersModel interestGroupMembersModel=new InterestGroupMembersModel();
        interestGroupMembersModel.setGroupMemberProfileName("You");
        interestGroupMembersModel.setGroupMemberProfileCreatedDate("Since 29 July 2019");
        interestGroupMembersModel.setGroupProfileIcon(R.drawable.profile);
        interestGroupMemberslist.add(interestGroupMembersModel);

        interestGroupMembersModel=new InterestGroupMembersModel();
        interestGroupMembersModel.setGroupMemberProfileName("Luke Ray");
        interestGroupMembersModel.setGroupMemberProfileCreatedDate("Since 29 July 2019");
        interestGroupMembersModel.setGroupProfileIcon(R.drawable.profile_image);
        interestGroupMemberslist.add(interestGroupMembersModel);

        interestGroupMembersModel=new InterestGroupMembersModel();
        interestGroupMembersModel.setGroupMemberProfileName("Daisy Lake");
        interestGroupMembersModel.setGroupMemberProfileCreatedDate("Since 29 July 2019");
        interestGroupMembersModel.setGroupProfileIcon(R.drawable.profile);
        interestGroupMemberslist.add(interestGroupMembersModel);

        interestGroupMembersModel=new InterestGroupMembersModel();
        interestGroupMembersModel.setGroupMemberProfileName("Mark Smith");
        interestGroupMembersModel.setGroupMemberProfileCreatedDate("Since 29 July 2019");
        interestGroupMembersModel.setGroupProfileIcon(R.drawable.profile2);
        interestGroupMemberslist.add(interestGroupMembersModel);

        interestGroupMembersModel=new InterestGroupMembersModel();
        interestGroupMembersModel.setGroupMemberProfileName("Dan Park");
        interestGroupMembersModel.setGroupMemberProfileCreatedDate("Since 29 July 2019");
        interestGroupMembersModel.setGroupProfileIcon(R.drawable.profile1);
        interestGroupMemberslist.add(interestGroupMembersModel);

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
        return rootView;
    }

}
