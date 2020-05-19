package com.carmel.guestjini.InterestGroups;


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
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Common.EndPoints;
import com.carmel.guestjini.CommunityGroupsActivity;
import com.carmel.guestjini.Components.OkHttpClientInstance;
import com.carmel.guestjini.Models.Group.Group;
import com.carmel.guestjini.Models.Group.GroupResponse;
import com.carmel.guestjini.Models.User.AddressBook;
import com.carmel.guestjini.MyGroupsActivity;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Services.Authentication.AuthService;
import com.carmel.guestjini.Services.Authentication.AuthServiceHolder;
import com.carmel.guestjini.Services.Group.GroupService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Adapter.InterestGroupMemberAdapter;
import Model.InterestGroupMembersModel;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SubscribedGroupDetailedFragment extends Fragment {
    private RecyclerView groupInformationRecyclerView;
    private ArrayList<InterestGroupMembersModel> interestGroupMemberslist = new ArrayList<>();
    private Button subscribeButton, exitButton;
    private TextView txtGroupType, groupName, groupDescription, txtPageTitle, memberText;
    private ImageView backArrow;
    String group_Type, group_Name, group_Description;
    String name;

    private String groupId;
    private int groupType;

    AlertDialog progressDialog;

    InterestGroupMemberAdapter interestGroupMemmberRecyclerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_subscribed_group_detailed, container, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false); // if you want user to wait for some process to finish,
        builder.setView(R.layout.layout_loading_dialog);
        progressDialog = builder.create();

        subscribeButton = rootView.findViewById(R.id.subscribeButton1);
        exitButton = rootView.findViewById(R.id.exitButton);
        groupInformationRecyclerView = rootView.findViewById(R.id.groupInformationRecyclerView);
        txtGroupType = rootView.findViewById(R.id.interestGroupType);
        txtPageTitle = rootView.findViewById(R.id.interestGroupsHeading);
        groupName = rootView.findViewById(R.id.interestGroupName);
        backArrow = rootView.findViewById(R.id.backButton);
        groupDescription = rootView.findViewById(R.id.interestsGroupDescription);
        memberText = rootView.findViewById(R.id.memberText);
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
                        InterestGroupsFragment interestGroupsFragment = new InterestGroupsFragment();
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.interestGroupsPlaceHolder, interestGroupsFragment);
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
                subscribe();
            }
        });

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
        groupInformationRecyclerView.setLayoutManager(linearLayoutManager1);
        groupInformationRecyclerView.setHasFixedSize(true);
        interestGroupMemmberRecyclerAdapter = new InterestGroupMemberAdapter(rootView.getContext(), interestGroupMemberslist);
        groupInformationRecyclerView.setAdapter(interestGroupMemmberRecyclerAdapter);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(groupType == 1) {
                    InterestGroupsFragment interestGroupsFragment = new InterestGroupsFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.interestGroupsPlaceHolder, interestGroupsFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }else if(groupType == 2){
                    final Intent intent;
                    intent =  new Intent(getContext(), CommunityGroupsActivity.class);
                    getContext().startActivity(intent);
                }else if(groupType == 3){
                    final Intent intent;
                    intent =  new Intent(getContext(), MyGroupsActivity.class);
                    getContext().startActivity(intent);
                }
            }
        });
        final Bundle bundle = getArguments();
        if (bundle != null) {
            groupId = bundle.getString("groupId");
            groupType = Integer.parseInt(bundle.getString("groupType"));
            if (groupType == 1) {
                txtPageTitle.setText("INTEREST GROUPS");
            } else if (groupType == 2) {
                txtPageTitle.setText("COMMUNITY GROUPS");
            } else if (groupType == 3) {
                txtPageTitle.setText("MY GROUPS");
            }
            getGroupById();

        }
        return rootView;
    }

    private void getGroupById() {
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
            postData.put("id", groupId);
            GroupService groupService = retrofit.create(GroupService.class);
            Call<GroupResponse> groupResponseCall = groupService.getGroupById(postData);
            groupResponseCall.enqueue(new Callback<GroupResponse>() {
                @Override
                public void onResponse(Call<GroupResponse> call, Response<GroupResponse> response) {
                    try {
                        progressDialog.dismiss();
                        GroupResponse groupResponse = response.body();
                        if (groupResponse.getSuccess()) {
                            Group myGroup = groupResponse.getGroup();
                            txtGroupType.setText(myGroup.getInterestCategoryName());
                            groupName.setText(myGroup.getName());
                            groupDescription.setText(myGroup.getDescription());
                            interestGroupMemberslist = new ArrayList<>();
                            boolean amIMember = false;
                            if (myGroup.getIsSubscribed() == 1) {
                                InterestGroupMembersModel interestGroupMembersModel = new InterestGroupMembersModel();
                                interestGroupMembersModel.setGroupMemberProfileName("YOU");
                                interestGroupMembersModel.setGroupId(groupId);
                                interestGroupMembersModel.setGroupProfileIcon(R.drawable.profile);
                                interestGroupMembersModel.setGroupMemberProfileCreatedDate(myGroup.getSubscribedDate());
                                interestGroupMemberslist.add(0, interestGroupMembersModel);
                                amIMember = true;
                            }
                            for (AddressBook addressBook : groupResponse.getGroupPeople()) {
                                if (addressBook.getHasAcceptedInvitation() == 1 && addressBook.getIsMe() == 0) {
                                    InterestGroupMembersModel interestGroupMembersModel = new InterestGroupMembersModel(addressBook, myGroup.getId());
                                    interestGroupMemberslist.add(interestGroupMembersModel);
                                }
                            }
                            if (amIMember) {
                                exitButton.setVisibility(View.GONE);
                                subscribeButton.setVisibility(View.GONE);
                            } else {
                                exitButton.setVisibility(View.GONE);
                                subscribeButton.setVisibility(View.VISIBLE);
                            }
                            memberText.setText(String.valueOf(interestGroupMemberslist.size()) + " Members");
                            interestGroupMemmberRecyclerAdapter.update(interestGroupMemberslist);
                        } else {
                            progressDialog.dismiss();
                            showDialog(false, "There was a problem fetching group! Please try after sometime");
                        }
                    } catch (Exception ex) {
                        progressDialog.dismiss();
                        showDialog(false, "There was a problem fetching group! Please try after sometime");
                    }

                }

                @Override
                public void onFailure(Call<GroupResponse> call, Throwable t) {
                    progressDialog.dismiss();
                    showDialog(false, "There was a problem fetching group! Please try after sometime");

                }
            });
        } catch (Exception ex) {
            progressDialog.dismiss();
            showDialog(false, "There was a problem fetching group! Please try after sometime");
        }
    }

    private void subscribe(){
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
            postData.put("groupId", groupId);
            GroupService groupService = retrofit.create(GroupService.class);
            Call<GroupResponse> groupResponseCall = groupService.subscribe(postData);
            groupResponseCall.enqueue(new Callback<GroupResponse>() {
                @Override
                public void onResponse(Call<GroupResponse> call, Response<GroupResponse> response) {
                    try {
                        progressDialog.dismiss();
                        GroupResponse groupResponse = response.body();
                        if (groupResponse.getSuccess()) {
                            getGroupById();
                        } else {
                            progressDialog.dismiss();
                            showDialog(false, "There was a problem subscribing to group! Please try after sometime");
                        }
                    } catch (Exception ex) {
                        progressDialog.dismiss();
                        showDialog(false, "There was a problem subscribing to group! Please try after sometime");
                    }

                }

                @Override
                public void onFailure(Call<GroupResponse> call, Throwable t) {
                    progressDialog.dismiss();
                    showDialog(false, "There was a problem subscribing to group! Please try after sometime");

                }
            });
        } catch (Exception ex) {
            progressDialog.dismiss();
            showDialog(false, "There was a problem subscribing to group! Please try after sometime");
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
