package com.carmel.guestjini.MyGroups;


import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.carmel.guestjini.CommunityGroups.InvitedGroupViewFragment;
import com.carmel.guestjini.GroupsActivity;
import com.carmel.guestjini.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import Adapter.MyGroupsAdapter;
import Model.CommunityGroupsModel;

public class MyGroupsFragment extends Fragment implements MyGroupsAdapter.OnItemClickListener{
    private  ImageView createIcon,backArrow;
    private TextView noCreatedGroupText,later;
    private Button inviteButton,createButton;
    private RecyclerView myGroupsRecyclerView;
    private ArrayList<CommunityGroupsModel> myGroupsArrayList=new ArrayList<>();
    private ConstraintLayout newGroupMainLayout;
    private RelativeLayout inviteAndLaterLayout;
    String GroupName1,GroupDescription1;
    private EditText groupNameEdit,groupDescrptionEdit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_my_groups, container, false);
        createIcon=rootView.findViewById(R.id.createIcon);
        inviteButton=rootView.findViewById(R.id.inviteButton);
        myGroupsRecyclerView=rootView.findViewById(R.id.myGroupsRecyclerView);
        backArrow=rootView.findViewById(R.id.backArrow);
        createButton=rootView.findViewById(R.id.createButton);
        newGroupMainLayout=rootView.findViewById(R.id.newGroupMainLayout);
        inviteAndLaterLayout=rootView.findViewById(R.id.inviteAndLaterLayout);
        noCreatedGroupText=rootView.findViewById(R.id.noCreatedGroupText);
        groupDescrptionEdit=rootView.findViewById(R.id.groupDescrptionEdit);
        groupNameEdit=rootView.findViewById(R.id.groupNameEdit);
        later=rootView.findViewById(R.id.later);


        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        myGroupsRecyclerView.setLayoutManager(linearLayoutManager);
        myGroupsRecyclerView.setHasFixedSize(true);
        MyGroupsAdapter myGroupsAdapter=new MyGroupsAdapter(getContext(),myGroupsArrayList,this);
        myGroupsRecyclerView.setAdapter(myGroupsAdapter);


        myGroupsArrayList.add(new CommunityGroupsModel("Apache Riders",
                "John Doe",
                "2nd July 2019 06:21 PM (4 Days ago)",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                        "Donec dignissim felis et magna mattis finibus." +
                        " Nulla elit ligula, placerat tincidunt ipsum eu, ornare semper felis. " +
                        "Duis arcu massa, venenatis eget ante sodales, posuere volutpat risus." +
                        " Aenean et justo eu massa sodales posuere.Duis arcu massa, venenatis eget ante sodales, posuere volutpat risus.",
                null,
                R.drawable.profile2,
                0,
                CommunityGroupsModel.JoinedGroupsCell));

        myGroupsArrayList.add(new CommunityGroupsModel("STAND-UP COMEDY",
                "YOU",
                "2nd July 2019 06:21 PM (4 Days ago)",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam erat sapien, ultricies.",
                null,
                R.drawable.profile2,
                0,
                CommunityGroupsModel.RemovedCell));

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), GroupsActivity.class);
                startActivity(intent);
            }
        });

        createIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newGroupMainLayout.setVisibility(View.VISIBLE);
                noCreatedGroupText.setVisibility(View.GONE);
                createIcon.setVisibility(View.GONE);
                inviteAndLaterLayout.setVisibility(View.GONE);

//                NewGroupFragment newGroupFragment=new NewGroupFragment();
//                FragmentManager fragmentManager=getFragmentManager();
//                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.myGroupsPlaceHolder,newGroupFragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();

            }
        });
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GroupName1=groupNameEdit.getText().toString();
                GroupDescription1=groupDescrptionEdit.getText().toString();
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
                        newGroupMainLayout.setVisibility(View.GONE);
                        noCreatedGroupText.setVisibility(View.VISIBLE);
                        createIcon.setVisibility(View.VISIBLE);
                        inviteAndLaterLayout.setVisibility(View.VISIBLE);
                        noCreatedGroupText.setText(null);
                        noCreatedGroupText.setText(getText(R.string.no_group_invited));
//                        MyGroupsFragment myGroupsFragment=new MyGroupsFragment();
//                        FragmentManager fragmentManager=getFragmentManager();
//                        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
//                        fragmentTransaction.replace(R.id.myGroupsPlaceHolder,myGroupsFragment);
//                        fragmentTransaction.addToBackStack(null);
//                        fragmentTransaction.commit();
                    }
                });

                dialog.show();
            }
        });
        inviteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InvitingMembersFragment invitingMembersFragment=new InvitingMembersFragment();
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.myGroupsPlaceHolder,invitingMembersFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        later.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyGroupsDetailsFragment myGroupsDetailsFragment=new MyGroupsDetailsFragment();
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.myGroupsPlaceHolder,myGroupsDetailsFragment);
                fragmentTransaction.addToBackStack(null);
                Bundle bundle=new Bundle();
                bundle.putString("GroupName",GroupName1);
                bundle.putString("GroupDescription",GroupDescription1);
                myGroupsDetailsFragment.setArguments(bundle);
                fragmentTransaction.commit();
            }
        });

        return rootView;
    }

    @Override
    public void onClikJoinedGroup(int position) {
        myGroupsArrayList.get(position);
        MyGroupsDetailsFragment myGroupsDetailsFragment=new MyGroupsDetailsFragment();
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.myGroupsPlaceHolder,myGroupsDetailsFragment);
        fragmentTransaction.addToBackStack(null);
        Bundle bundle=new Bundle();
        bundle.putString("GroupName",myGroupsArrayList.get(position).getCommunityGroupTitle());
        bundle.putString("GroupDescription",myGroupsArrayList.get(position).getCommunityGroupDescription());
        bundle.putString("GroupCreationDateAndTime",myGroupsArrayList.get(position).getCommunityGroupCreationDateAndTime());
        bundle.putInt("GroupIcon",myGroupsArrayList.get(position).getAdminProfileIcon());
        myGroupsDetailsFragment.setArguments(bundle);
        fragmentTransaction.commit();
    }

    @Override
    public void onClikRemovedGroup(int position) {

    }
}
