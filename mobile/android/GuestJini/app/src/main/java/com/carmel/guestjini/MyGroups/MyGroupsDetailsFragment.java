package com.carmel.guestjini.MyGroups;


import android.app.Dialog;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.carmel.guestjini.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import Adapter.JoinedMembersAdapter;
import Adapter.PendingRequestsAdapter;
import Model.InvitingMembersModel;
import Model.JoinedMemberModel;

public class MyGroupsDetailsFragment extends Fragment implements JoinedMembersAdapter.OnItemClickListener{
    private TextView groupName,groupDescription,groupCreationDateAndTime;
    String GroupName,GroupDescription,GroupCreationDateAndTime;
    private ImageView backArrow,dropDownImage,dropDownIcon;
    private Button inviteButton,deleteButton;
    private ArrayList<JoinedMemberModel> joinedMembersArrayList=new ArrayList<>();
    private ArrayList<InvitingMembersModel> pendingRequestsArrayList=new ArrayList<>();
    private RecyclerView joinedMembersRecyclerView,pendingRequestsRecyclerView;
    private RelativeLayout joinedLayout,pendingRequests,conversation,requestsLayout;
    private ConstraintLayout noInvitedLayout,invitationsLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_my_groups_details, container, false);
        groupName = rootView.findViewById(R.id.myGroupName);
        groupCreationDateAndTime = rootView.findViewById(R.id.myGroupCreationDate);
        groupDescription = rootView.findViewById(R.id.myGroupDescription);
        backArrow=rootView.findViewById(R.id.backArrow);
        inviteButton=rootView.findViewById(R.id.inviteButton);
        deleteButton=rootView.findViewById(R.id.deleteButton);
        pendingRequestsRecyclerView=rootView.findViewById(R.id.pendingRequestsRecyclerView);
        joinedMembersRecyclerView=rootView.findViewById(R.id.joinedMembersRecyclerView);
        joinedLayout=rootView.findViewById(R.id.joinedLayout);
        dropDownImage=rootView.findViewById(R.id.dropDownImage);
        pendingRequests=rootView.findViewById(R.id.pendingRequests);
        dropDownIcon=rootView.findViewById(R.id.dropDownIcon);
        conversation=rootView.findViewById(R.id.conversation);
        requestsLayout=rootView.findViewById(R.id.requestsLayout);
        noInvitedLayout=rootView.findViewById(R.id.noInvitedLayout);
        invitationsLayout=rootView.findViewById(R.id.invitationsLayout);

        Bundle bundle = getArguments();
        if (bundle != null) {
            GroupName = bundle.getString("GroupName");
            GroupCreationDateAndTime = bundle.getString("GroupCreationDateAndTime");
            GroupDescription = bundle.getString("GroupDescription");

            groupName.setText(GroupName);
            groupCreationDateAndTime.setText(GroupCreationDateAndTime);
            groupDescription.setText(GroupDescription);
            noInvitedLayout.setVisibility(View.VISIBLE);
            invitationsLayout.setVisibility(View.GONE);

        }

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyGroupsFragment myGroupsFragment=new MyGroupsFragment();
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.myGroupsPlaceHolder,myGroupsFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

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

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.alert_dailogbox);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


                TextView alertDailogTitle = (TextView) dialog.findViewById(R.id.alertDailogTitle);
                alertDailogTitle.setText(getText(R.string.delete_group));
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
                        MyGroupsFragment myGroupsFragment=new MyGroupsFragment();
                        FragmentManager fragmentManager=getFragmentManager();
                        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.myGroupsPlaceHolder,myGroupsFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                });

                dialog.show();
            }
        });

        conversation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyGroupsChatFragment myGroupChatFragment=new MyGroupsChatFragment();
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.myGroupsPlaceHolder,myGroupChatFragment);
                fragmentTransaction.addToBackStack(null);
                Bundle bundle=new Bundle();
                bundle.putString("MyGroupName",GroupName);
                bundle.putString("MyGroupDescription",GroupDescription);
                fragmentTransaction.commit();
                myGroupChatFragment.setArguments(bundle);
            }
        });

        requestsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyGroupsRequestsFragment myGroupsRequestsFragment=new MyGroupsRequestsFragment();
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.myGroupsPlaceHolder,myGroupsRequestsFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        joinedMembersRecyclerView.setLayoutManager(linearLayoutManager);
        joinedMembersRecyclerView.setHasFixedSize(true);
        JoinedMembersAdapter joinedMembersAdapter=new JoinedMembersAdapter(getContext(),joinedMembersArrayList,this);
        joinedMembersRecyclerView.setAdapter(joinedMembersAdapter);
        joinedMembersRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));


        joinedMembersArrayList.add(new JoinedMemberModel(R.drawable.profile2,"Soloman Jakes","Today (Just now)"));
        joinedMembersArrayList.add(new JoinedMemberModel(R.drawable.profile,"Luke Ray","Since 29 Jun 2019 (2 Hours ago"));
        joinedMembersArrayList.add(new JoinedMemberModel(R.drawable.profile1,"Daisy Lake","Since 29 Jun 2019 (1 Day ago"));
        joinedMembersArrayList.add(new JoinedMemberModel(R.drawable.profile2,"Mark Smith","Since 29 Jun 2019 (3 Days ago"));
        joinedMembersArrayList.add(new JoinedMemberModel(R.drawable.profile,"Lucy Lake","Since 29 Jun 2019 (1 Day ago"));
        joinedMembersArrayList.add(new JoinedMemberModel(R.drawable.profile2,"Dan Smith","Since 29 Jun 2019 (3 Days ago"));

        joinedLayout.setOnClickListener(new View.OnClickListener() {
            private boolean flag=true;
            @Override
            public void onClick(View v) {
                if (flag) {
                    flag = false;
                    dropDownImage.setImageResource(R.drawable.dropup_icon_mdpi);
                    joinedMembersRecyclerView.setVisibility(View.VISIBLE);
                }else {
                    flag=true;
                    dropDownImage.setImageResource(R.drawable.dropdown_icon_mdpi);
                    joinedMembersRecyclerView.setVisibility(View.GONE);

                }
            }
        });




        LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(getContext());
        pendingRequestsRecyclerView.setLayoutManager(linearLayoutManager1);
        pendingRequestsRecyclerView.setHasFixedSize(true);
        PendingRequestsAdapter pendingRequestsAdapter=new PendingRequestsAdapter(getContext(),pendingRequestsArrayList);
        pendingRequestsRecyclerView.setAdapter(pendingRequestsAdapter);
        pendingRequestsRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

        InvitingMembersModel invitingMembersModel=new InvitingMembersModel();
        invitingMembersModel.setProfilePicture(R.drawable.profile_image);
        invitingMembersModel.setFavouritesIndicator(0);
        invitingMembersModel.setProfileName("Solomon Jakes");
        invitingMembersModel.setProfileGender("Male,21 Years");
        invitingMembersModel.setInvitationSentMessage("Invitation Sent");
        invitingMembersModel.setInvitationSentDate(" 21 July 2019");
        pendingRequestsArrayList.add(invitingMembersModel);

        invitingMembersModel=new InvitingMembersModel();
        invitingMembersModel.setProfilePicture(R.drawable.profile1);
        invitingMembersModel.setFavouritesIndicator(R.drawable.favourite_icon);
        invitingMembersModel.setProfileName("Linda Raymond");
        invitingMembersModel.setProfileGender("Female,23 Years");
        invitingMembersModel.setInvitationSentMessage("Invitation Sent");
        invitingMembersModel.setInvitationSentDate(" 21 July 2019");
        pendingRequestsArrayList.add(invitingMembersModel);

        invitingMembersModel=new InvitingMembersModel();
        invitingMembersModel.setProfilePicture(R.drawable.profile_image);
        invitingMembersModel.setFavouritesIndicator(0);
        invitingMembersModel.setProfileName("Daisy Lake");
        invitingMembersModel.setProfileGender("Female");
        invitingMembersModel.setInvitationSentMessage("Invitation Sent");
        invitingMembersModel.setInvitationSentDate(" 21 July 2019");
        pendingRequestsArrayList.add(invitingMembersModel);

        invitingMembersModel=new InvitingMembersModel();
        invitingMembersModel.setProfilePicture(R.drawable.profile);
        invitingMembersModel.setFavouritesIndicator(R.drawable.favourite_icon);
        invitingMembersModel.setProfileName("Diana Smith");
        invitingMembersModel.setInvitationSentMessage("Invitation Sent");
        invitingMembersModel.setInvitationSentDate(" 21 July 2019");
        pendingRequestsArrayList.add(invitingMembersModel);

        invitingMembersModel=new InvitingMembersModel();
        invitingMembersModel.setProfilePicture(R.drawable.no_profile_picture);
        invitingMembersModel.setFavouritesIndicator(0);
        invitingMembersModel.setProfileName("Dan Park");
        invitingMembersModel.setProfileGender("Female,27 Years");
        invitingMembersModel.setInvitationSentMessage("Invitation Sent");
        invitingMembersModel.setInvitationSentDate(" 21 July 2019");
        pendingRequestsArrayList.add(invitingMembersModel);

        invitingMembersModel=new InvitingMembersModel();
        invitingMembersModel.setProfilePicture(R.drawable.no_profile_picture);
        invitingMembersModel.setFavouritesIndicator(0);
        invitingMembersModel.setProfileName("Nora Bravos");
        invitingMembersModel.setProfileGender("64 Years");
        invitingMembersModel.setInvitationSentMessage("Invitation Sent");
        invitingMembersModel.setInvitationSentDate(" 21 July 2019");
        pendingRequestsArrayList.add(invitingMembersModel);

        invitingMembersModel=new InvitingMembersModel();
        invitingMembersModel.setProfilePicture(R.drawable.profile2);
        invitingMembersModel.setFavouritesIndicator(0);
        invitingMembersModel.setProfileName("Sandor Jakes");
        invitingMembersModel.setInvitationSentMessage("Invitation Sent");
        invitingMembersModel.setInvitationSentDate(" 21 July 2019");
        pendingRequestsArrayList.add(invitingMembersModel);

        invitingMembersModel=new InvitingMembersModel();
        invitingMembersModel.setProfilePicture(R.drawable.profile1);
        invitingMembersModel.setFavouritesIndicator(R.drawable.favourite_icon);
        invitingMembersModel.setProfileName("Rocy Ray");
        invitingMembersModel.setProfileGender("Female,26 Years");
        invitingMembersModel.setInvitationSentMessage("Invitation Sent");
        invitingMembersModel.setInvitationSentDate(" 21 July 2019");
        pendingRequestsArrayList.add(invitingMembersModel);

        invitingMembersModel=new InvitingMembersModel();
        invitingMembersModel.setProfilePicture(R.drawable.profile_image);
        invitingMembersModel.setFavouritesIndicator(0);
        invitingMembersModel.setProfileName("Alita Lake");
        invitingMembersModel.setProfileGender("Female,26 Years");
        invitingMembersModel.setInvitationSentMessage("Invitation Sent");
        invitingMembersModel.setInvitationSentDate(" 21 July 2019");
        pendingRequestsArrayList.add(invitingMembersModel);

        invitingMembersModel=new InvitingMembersModel();
        invitingMembersModel.setProfilePicture(R.drawable.profile);
        invitingMembersModel.setFavouritesIndicator(R.drawable.favourite_icon);
        invitingMembersModel.setProfileName("Margret Smith");
        invitingMembersModel.setProfileGender("Female,26 Years");
        invitingMembersModel.setInvitationSentMessage("Invitation Sent");
        invitingMembersModel.setInvitationSentDate(" 21 July 2019");
        pendingRequestsArrayList.add(invitingMembersModel);

        invitingMembersModel=new InvitingMembersModel();
        invitingMembersModel.setProfilePicture(R.drawable.no_profile_picture);
        invitingMembersModel.setFavouritesIndicator(0);
        invitingMembersModel.setProfileName("Lucy Rain");
        invitingMembersModel.setProfileGender("Female,26 Years");
        invitingMembersModel.setInvitationSentMessage("Invitation Sent");
        invitingMembersModel.setInvitationSentDate(" 21 July 2019");
        pendingRequestsArrayList.add(invitingMembersModel);

        invitingMembersModel=new InvitingMembersModel();
        invitingMembersModel.setProfilePicture(R.drawable.no_profile_picture);
        invitingMembersModel.setFavouritesIndicator(0);
        invitingMembersModel.setProfileName("Oneil Smith");
        invitingMembersModel.setInvitationSentMessage("Invitation Sent");
        invitingMembersModel.setInvitationSentDate(" 21 July 2019");
        pendingRequestsArrayList.add(invitingMembersModel);

        pendingRequests.setOnClickListener(new View.OnClickListener() {
            private boolean flag=true;
            @Override
            public void onClick(View v) {
                if (flag) {
                    flag = false;
                    dropDownIcon.setImageResource(R.drawable.dropup_icon_mdpi);
                    pendingRequestsRecyclerView.setVisibility(View.VISIBLE);
                }else {
                    flag=true;
                    dropDownIcon.setImageResource(R.drawable.dropdown_icon_mdpi);
                    pendingRequestsRecyclerView.setVisibility(View.GONE);

                }
            }
        });

        return rootView;

    }

    @Override
    public void onClick(int position) {

    }
}
