package com.carmel.guestjini.MyGroups;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Common.EndPoints;
import com.carmel.guestjini.Components.OkHttpClientInstance;
import com.carmel.guestjini.GroupsActivity;
import com.carmel.guestjini.Models.Group.Group;
import com.carmel.guestjini.Models.Group.GroupResponse;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Services.Authentication.AuthService;
import com.carmel.guestjini.Services.Authentication.AuthServiceHolder;
import com.carmel.guestjini.Services.Group.GroupService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Adapter.MyGroupsAdapter;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyGroupsFragment extends Fragment implements MyGroupsAdapter.OnItemClickListener {
    private ImageView createIcon, backArrow;
    private TextView noCreatedGroupText, later;
    private Button inviteButton, createButton;
    private RecyclerView myGroupsRecyclerView;
    private ArrayList<Group> groupArrayList = new ArrayList<>();
    private ConstraintLayout newGroupMainLayout;
    private RelativeLayout inviteAndLaterLayout;
    String GroupName1, GroupDescription1;
    private EditText groupNameEdit, groupDescrptionEdit;

    MyGroupsAdapter myGroupsAdapter;
    AlertDialog progressDialog;

    private ConstraintLayout myGroupsListLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_my_groups, container, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false); // if you want user to wait for some process to finish,
        builder.setView(R.layout.layout_loading_dialog);
        progressDialog = builder.create();
        createIcon = rootView.findViewById(R.id.createIcon);
        inviteButton = rootView.findViewById(R.id.inviteButton);
        myGroupsRecyclerView = rootView.findViewById(R.id.myGroupsRecyclerView);
        myGroupsListLayout = rootView.findViewById(R.id.myGroupsListLayout);
        backArrow = rootView.findViewById(R.id.backArrow);
        createButton = rootView.findViewById(R.id.createButton);
        newGroupMainLayout = rootView.findViewById(R.id.newGroupMainLayout);
        inviteAndLaterLayout = rootView.findViewById(R.id.inviteAndLaterLayout);
        noCreatedGroupText = rootView.findViewById(R.id.noCreatedGroupText);
        groupDescrptionEdit = rootView.findViewById(R.id.groupDescrptionEdit);
        groupNameEdit = rootView.findViewById(R.id.groupNameEdit);
        later = rootView.findViewById(R.id.later);
        myGroupsListLayout.setVisibility(View.VISIBLE);
        ;
        newGroupMainLayout.setVisibility(View.GONE);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        myGroupsRecyclerView.setLayoutManager(linearLayoutManager);
        myGroupsRecyclerView.setHasFixedSize(true);
        myGroupsAdapter = new MyGroupsAdapter(getContext(), groupArrayList, this);
        myGroupsRecyclerView.setAdapter(myGroupsAdapter);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), GroupsActivity.class);
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
                myGroupsListLayout.setVisibility(View.GONE);

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
                createMyGroup();
            }
        });
        inviteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InvitingMembersFragment invitingMembersFragment = new InvitingMembersFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.myGroupsPlaceHolder, invitingMembersFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        later.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyGroupsDetailsFragment myGroupsDetailsFragment = new MyGroupsDetailsFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.myGroupsPlaceHolder, myGroupsDetailsFragment);
                fragmentTransaction.addToBackStack(null);
                Bundle bundle = new Bundle();
                bundle.putString("GroupName", GroupName1);
                bundle.putString("GroupDescription", GroupDescription1);
                myGroupsDetailsFragment.setArguments(bundle);
                fragmentTransaction.commit();
            }
        });
        geMyGroups();
        return rootView;
    }

    @Override
    public void onClikJoinedGroup(int position) {
        MyGroupsDetailsFragment myGroupsDetailsFragment = new MyGroupsDetailsFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.myGroupsPlaceHolder, myGroupsDetailsFragment);
        fragmentTransaction.addToBackStack(null);
        Bundle bundle = new Bundle();
        bundle.putString("groupId",groupArrayList.get(position).getId());
//        bundle.putString("GroupDescription",myGroupsArrayList.get(position).getCommunityGroupDescription());
//        bundle.putString("GroupCreationDateAndTime",myGroupsArrayList.get(position).getCommunityGroupCreationDateAndTime());
//        bundle.putInt("GroupIcon",myGroupsArrayList.get(position).getAdminProfileIcon());
        myGroupsDetailsFragment.setArguments(bundle);
        fragmentTransaction.commit();
    }

    @Override
    public void onClikRemovedGroup(int position) {

    }


    private void geMyGroups() {
        try {
            progressDialog.show();
            AuthServiceHolder authServiceHolder = new AuthServiceHolder();
            SharedPreferences preferences = getContext().getSharedPreferences("GuestJini", Context.MODE_PRIVATE);
            String accessToken = preferences.getString("access_token", "");
            OkHttpClient okHttpClient = new OkHttpClientInstance.Builder(getActivity(), authServiceHolder)
                    .addHeader("Authorization", "Bearer " + accessToken)
                    .build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(EndPoints.END_POINT_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
            AuthService authService = retrofit.create(AuthService.class);
            authServiceHolder.set(authService);

            Map<String, String> postData = new HashMap<>();
            postData.put("groupType", "3");

            GroupService groupService = retrofit.create(GroupService.class);
            Call<GroupResponse> groupResponseCall = groupService.getGroupByType(postData);
            groupResponseCall.enqueue(new Callback<GroupResponse>() {
                @Override
                public void onResponse(Call<GroupResponse> call, Response<GroupResponse> response) {
                    progressDialog.dismiss();
                    try {
                        GroupResponse groupResponse = response.body();
                        if (groupResponse.getSuccess()) {
                            groupArrayList = new ArrayList<>();
                            groupArrayList.addAll(groupResponse.getGroupList());
                            myGroupsAdapter.update(groupArrayList);

                        } else {
                            showDialog(false, "There was a problem fetching people list! Please try after sometime");
                        }
                    } catch (Exception ex) {
                        showDialog(false, "There was a problem fetching people list! Please try after sometime");
                    }
                }

                @Override
                public void onFailure(Call<GroupResponse> call, Throwable t) {
                    progressDialog.dismiss();
                    showDialog(false, "There was a problem fetching group list! Please try after sometime");
                }
            });

        } catch (Exception ex) {
            progressDialog.dismiss();
            showDialog(false, "There was a problem fetching group list! Please try after sometime");
        }
    }

    private void createMyGroup() {
        try {
            Group group = new Group();
            group.setName(groupNameEdit.getText().toString());
            group.setDescription(groupDescrptionEdit.getText().toString());
            group.setGroupType(2);
            if (group.getName().trim().equals("")) {
                showDialog(false, "Group name is required!");
            }else{
                progressDialog.show();
                AuthServiceHolder authServiceHolder = new AuthServiceHolder();
                SharedPreferences preferences = getContext().getSharedPreferences("GuestJini", Context.MODE_PRIVATE);
                String accessToken = preferences.getString("access_token", "");
                OkHttpClient okHttpClient = new OkHttpClientInstance.Builder(getActivity(), authServiceHolder)
                        .addHeader("Authorization", accessToken)
                        .build();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(EndPoints.END_POINT_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(okHttpClient)
                        .build();
                AuthService authService = retrofit.create(AuthService.class);
                authServiceHolder.set(authService);
                GroupService groupService = retrofit.create(GroupService.class);
                Call<GroupResponse> groupResponseCall = groupService.saveGroup(group);
                groupResponseCall.enqueue(new Callback<GroupResponse>() {
                    @Override
                    public void onResponse(Call<GroupResponse> call, Response<GroupResponse> response) {
                        progressDialog.dismiss();
                        try {
                            GroupResponse groupResponse = response.body();
                            if (groupResponse.getSuccess()) {
                                newGroupMainLayout.setVisibility(View.GONE);
                                noCreatedGroupText.setVisibility(View.GONE);
                                createIcon.setVisibility(View.VISIBLE);
                                inviteAndLaterLayout.setVisibility(View.GONE);
                                myGroupsListLayout.setVisibility(View.VISIBLE);
                                showDialog(true, "Group created successfully!");
                                geMyGroups();
                            } else {
                                showDialog(false, "There was a problem creating group! Please try after sometime");
                            }
                        } catch (Exception ex) {
                            showDialog(false, "There was a problem creating group! Please try after sometime");
                        }
                    }

                    @Override
                    public void onFailure(Call<GroupResponse> call, Throwable t) {
                        progressDialog.dismiss();
                        showDialog(false, "There was a problem creating group! Please try after sometime");
                    }
                });

            }
        } catch (Exception ex) {
            progressDialog.dismiss();
            showDialog(false, "There was a problem creating group! Please try after sometime");

        }
    }

    private void showDialog(boolean isSuccess, String message) {
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.alert_dailogbox);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView alertDailogTitle = (TextView) dialog.findViewById(R.id.alertDailogTitle);
        if (isSuccess) {
            alertDailogTitle.setText(getText(R.string.success));
        } else {
            alertDailogTitle.setText(getText(R.string.failed));
            alertDailogTitle.setTextColor(Color.parseColor("#E65959"));
        }
        TextView alertDailogMessage = (TextView) dialog.findViewById(R.id.alertDailogDescription);
        alertDailogMessage.setText(message);
        FloatingActionButton doneButton = (FloatingActionButton) dialog.findViewById(R.id.done_button);
        if (isSuccess) {
            doneButton.setBackgroundTintList(ColorStateList.valueOf(Color
                    .parseColor("#32BDD2")));
        } else {
            doneButton.setBackgroundTintList(ColorStateList.valueOf(Color
                    .parseColor("#E65959")));
        }


        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
