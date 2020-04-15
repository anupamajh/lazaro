package com.carmel.guestjini.MyGroups;


import android.content.Context;
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

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.carmel.guestjini.R;

import java.util.ArrayList;

import Adapter.InvitingMembersAdapter;
import Model.InvitingMembersModel;

public class InvitingMembersFragment extends Fragment implements InvitingMembersAdapter.OnItemClickListener {
    private RecyclerView inviteGroupsRecyclerView;
    private ArrayList<InvitingMembersModel> invitingMembersArrayList = new ArrayList<>();
    private EditText search;
    private TextView searchResultCount,showingYourFavourites;
    private ImageView filterIcon, backArrow;
    private ConstraintLayout searchLayout, noResultFoundLayout, recyclerViewLayout;
    ListPopupWindow  listPopupWindow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_inviting_members, container, false);
        inviteGroupsRecyclerView = rootView.findViewById(R.id.inviteGroupsRecyclerView);
        searchLayout = rootView.findViewById(R.id.searchLayout);
        noResultFoundLayout = rootView.findViewById(R.id.noResultFoundLayout);
        recyclerViewLayout = rootView.findViewById(R.id.recyclerViewLayout);
        search = rootView.findViewById(R.id.search);
        searchResultCount = rootView.findViewById(R.id.searchResultCount);
        backArrow = rootView.findViewById(R.id.backArrow);
        filterIcon = rootView.findViewById(R.id.filterIcon);
        showingYourFavourites=rootView.findViewById(R.id.showingFavourites);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        inviteGroupsRecyclerView.setLayoutManager(linearLayoutManager);
        inviteGroupsRecyclerView.setHasFixedSize(true);
        InvitingMembersAdapter invitingMembersAdapter = new InvitingMembersAdapter(getContext(), invitingMembersArrayList, this);
        inviteGroupsRecyclerView.setAdapter(invitingMembersAdapter);

        InvitingMembersModel invitingMembersModel = new InvitingMembersModel();
        invitingMembersModel.setProfilePicture(R.drawable.no_profile_picture);
        invitingMembersModel.setFavouritesIndicator(0);
        invitingMembersModel.setProfileName("Solomon Jakes");
        invitingMembersModel.setProfileGender("Male,21 Years");
        invitingMembersModel.setInvitationSentMessage("Invitation Sent");
        invitingMembersModel.setInvitationSentDate(" 21 July 2019");
        invitingMembersArrayList.add(invitingMembersModel);

        invitingMembersModel = new InvitingMembersModel();
        invitingMembersModel.setProfilePicture(R.drawable.profile1);
        invitingMembersModel.setFavouritesIndicator(R.drawable.favourite_icon);
        invitingMembersModel.setProfileName("Linda Raymond");
        invitingMembersModel.setProfileGender("Female,23 Years");
        invitingMembersModel.setInvitationSentMessage("Invitation Sent");
        invitingMembersModel.setInvitationSentDate(" 21 July 2019");
        invitingMembersArrayList.add(invitingMembersModel);

        invitingMembersModel = new InvitingMembersModel();
        invitingMembersModel.setProfilePicture(R.drawable.profile_image);
        invitingMembersModel.setFavouritesIndicator(0);
        invitingMembersModel.setProfileName("Daisy Lake");
        invitingMembersModel.setProfileGender("Female");
        invitingMembersModel.setInvitationSentMessage("Invitation Sent");
        invitingMembersModel.setInvitationSentDate(" 21 July 2019");
        invitingMembersArrayList.add(invitingMembersModel);

        invitingMembersModel = new InvitingMembersModel();
        invitingMembersModel.setProfilePicture(R.drawable.profile);
        invitingMembersModel.setFavouritesIndicator(R.drawable.favourite_icon);
        invitingMembersModel.setProfileName("Diana Smith");
        invitingMembersModel.setInvitationSentMessage("Invitation Sent");
        invitingMembersModel.setInvitationSentDate(" 21 July 2019");
        invitingMembersArrayList.add(invitingMembersModel);

        invitingMembersModel = new InvitingMembersModel();
        invitingMembersModel.setProfilePicture(R.drawable.profile1);
        invitingMembersModel.setFavouritesIndicator(0);
        invitingMembersModel.setProfileName("Dan Park");
        invitingMembersModel.setProfileGender("Female,27 Years");
        invitingMembersModel.setInvitationSentMessage("Invitation Sent");
        invitingMembersModel.setInvitationSentDate(" 21 July 2019");
        invitingMembersArrayList.add(invitingMembersModel);

        invitingMembersModel = new InvitingMembersModel();
        invitingMembersModel.setProfilePicture(R.drawable.profile2);
        invitingMembersModel.setFavouritesIndicator(0);
        invitingMembersModel.setProfileName("Nora Bravos");
        invitingMembersModel.setProfileGender("64 Years");
        invitingMembersModel.setInvitationSentMessage("Invitation Sent");
        invitingMembersModel.setInvitationSentDate(" 21 July 2019");
        invitingMembersArrayList.add(invitingMembersModel);

        searchLayout.setOnClickListener(new View.OnClickListener() {
            private boolean flag = true;

            @Override
            public void onClick(View v) {
                if (flag) {
                    flag = false;
                    noResultFoundLayout.setVisibility(View.VISIBLE);
                    recyclerViewLayout.setVisibility(View.GONE);
                    search.setText("John");
                } else {
                    flag = true;
                    searchResultCount.setVisibility(View.VISIBLE);
                }

            }
        });
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyGroupsFragment myGroupsFragment = new MyGroupsFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.myGroupsPlaceHolder, myGroupsFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });



        filterIcon.setOnClickListener(new View.OnClickListener() {
            private boolean flag = true;
            @Override
            public void onClick(View v) {
                if (flag) {
                    flag = false;
                    final View layout = LayoutInflater.from(getContext()).inflate(R.layout.my_groups_filter, null);
                    final PopupWindow window = new PopupWindow(layout, 300, 400, true);
                    window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    window.setOutsideTouchable(true);
                    window.showAtLocation(layout, Gravity.TOP, 315, 210);
                    final TextView clearTitle = (TextView) layout.findViewById(R.id.clearTitle);
                    final TextView favouritesTitle = (TextView) layout.findViewById(R.id.favouritesTitle);

                    favouritesTitle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showingYourFavourites.setVisibility(View.VISIBLE);
                            window.dismiss();
                        }
                    });
                } else {
                    flag = true;
                    final View layout = LayoutInflater.from(getContext()).inflate(R.layout.my_groups_filter, null);
                    final PopupWindow window = new PopupWindow(layout, 300, 400, true);
                    window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    window.setOutsideTouchable(true);
                    window.showAtLocation(layout, Gravity.TOP, 315, 210);
                    final TextView clearTitle = (TextView) layout.findViewById(R.id.clearTitle);
                    final TextView favouritesTitle = (TextView) layout.findViewById(R.id.favouritesTitle);
                    clearTitle.setTextColor(ColorStateList.valueOf(Color
                            .parseColor("#747474")));
                    favouritesTitle.setTextColor(ColorStateList.valueOf(Color
                            .parseColor("#B5B5B5")));
                    clearTitle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showingYourFavourites.setVisibility(View.GONE);
                            window.dismiss();
                        }
                    });
                }
            }
        });

        return rootView;
    }

    @Override
    public void onItemClick(int position) {

    }
//
//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//
//        listPopupWindow.dismiss();
//    }
}
