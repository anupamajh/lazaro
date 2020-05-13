package com.carmel.guestjini.MyGroups;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Common.EndPoints;
import com.carmel.guestjini.Components.OkHttpClientInstance;
import com.carmel.guestjini.InterestGroups.SubscribedGroupChatFragment;
import com.carmel.guestjini.Models.Group.Group;
import com.carmel.guestjini.Models.Group.GroupResponse;
import com.carmel.guestjini.Models.User.AddressBook;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Services.Authentication.AuthService;
import com.carmel.guestjini.Services.Authentication.AuthServiceHolder;
import com.carmel.guestjini.Services.Group.GroupService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Adapter.JoinedMembersAdapter;
import Adapter.PendingRequestsAdapter;
import Model.InvitingMembersModel;
import Model.JoinedMemberModel;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyGroupsDetailsFragment extends Fragment implements JoinedMembersAdapter.OnItemClickListener, PendingRequestsAdapter.PendingRequestDelegate {
    private TextView groupName, groupDescription, groupCreationDateAndTime;
    String GroupName, GroupDescription, GroupCreationDateAndTime;
    private ImageView backArrow, dropDownImage, dropDownIcon;
    private Button inviteButton, deleteButton;
    private ArrayList<JoinedMemberModel> joinedMembersArrayList = new ArrayList<>();
    private ArrayList<InvitingMembersModel> pendingRequestsArrayList = new ArrayList<>();
    private RecyclerView joinedMembersRecyclerView, pendingRequestsRecyclerView;
    private RelativeLayout joinedLayout, pendingRequests, conversation, requestsLayout;
    private ConstraintLayout noInvitedLayout, invitationsLayout;

    private Button btnMessage;


    PendingRequestsAdapter pendingRequestsAdapter;

    AlertDialog progressDialog;

    private String groupId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_my_groups_details, container, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false); // if you want user to wait for some process to finish,
        builder.setView(R.layout.layout_loading_dialog);
        progressDialog = builder.create();
        groupName = rootView.findViewById(R.id.myGroupName);
        groupCreationDateAndTime = rootView.findViewById(R.id.myGroupCreationDate);
        groupDescription = rootView.findViewById(R.id.myGroupDescription);
        backArrow = rootView.findViewById(R.id.backArrow);
        inviteButton = rootView.findViewById(R.id.inviteButton);
        deleteButton = rootView.findViewById(R.id.deleteButton);
        pendingRequestsRecyclerView = rootView.findViewById(R.id.pendingRequestsRecyclerView);
        joinedMembersRecyclerView = rootView.findViewById(R.id.joinedMembersRecyclerView);
        joinedLayout = rootView.findViewById(R.id.joinedLayout);
        dropDownImage = rootView.findViewById(R.id.dropDownImage);
        pendingRequests = rootView.findViewById(R.id.pendingRequests);
        dropDownIcon = rootView.findViewById(R.id.dropDownIcon);
        conversation = rootView.findViewById(R.id.conversation);
        requestsLayout = rootView.findViewById(R.id.requestsLayout);
        noInvitedLayout = rootView.findViewById(R.id.noInvitedLayout);
        invitationsLayout = rootView.findViewById(R.id.invitationsLayout);
        btnMessage = rootView.findViewById(R.id.messageButton);

        Bundle bundle = getArguments();
        if (bundle != null) {
            groupId = bundle.getString("groupId");
            getGroupById();

        }

        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SubscribedGroupChatFragment subscribedGroupChatFragment = new SubscribedGroupChatFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.myGroupsPlaceHolder, subscribedGroupChatFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                Bundle bundle = new Bundle();
                bundle.putString("groupId", groupId);
                bundle.putString("groupType", "3");
                subscribedGroupChatFragment.setArguments(bundle);
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

//        deleteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Dialog dialog = new Dialog(getContext());
//                dialog.setContentView(R.layout.alert_dailogbox);
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//
//
//                TextView alertDailogTitle = (TextView) dialog.findViewById(R.id.alertDailogTitle);
//                alertDailogTitle.setText(getText(R.string.delete_group));
//                alertDailogTitle.setTextColor(ColorStateList.valueOf(Color.parseColor("#E65959")));
//
//                TextView alertDailogMessage = (TextView) dialog.findViewById(R.id.alertDailogDescription);
//                alertDailogMessage.setText(getText(R.string.feedback_success));
//
//                FloatingActionButton doneButton = (FloatingActionButton) dialog.findViewById(R.id.done_button);
//                doneButton.setBackgroundTintList(ColorStateList.valueOf(Color
//                        .parseColor("#E65959")));
//                doneButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog.dismiss();
//                        MyGroupsFragment myGroupsFragment = new MyGroupsFragment();
//                        FragmentManager fragmentManager = getFragmentManager();
//                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                        fragmentTransaction.replace(R.id.myGroupsPlaceHolder, myGroupsFragment);
//                        fragmentTransaction.addToBackStack(null);
//                        fragmentTransaction.commit();
//                    }
//                });
//
//                dialog.show();
//            }
//        });

        conversation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyGroupsChatFragment myGroupChatFragment = new MyGroupsChatFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.myGroupsPlaceHolder, myGroupChatFragment);
                fragmentTransaction.addToBackStack(null);
                Bundle bundle = new Bundle();
                bundle.putString("MyGroupName", GroupName);
                bundle.putString("MyGroupDescription", GroupDescription);
                fragmentTransaction.commit();
                myGroupChatFragment.setArguments(bundle);
            }
        });

        requestsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyGroupsRequestsFragment myGroupsRequestsFragment = new MyGroupsRequestsFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.myGroupsPlaceHolder, myGroupsRequestsFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        joinedMembersRecyclerView.setLayoutManager(linearLayoutManager);
        joinedMembersRecyclerView.setHasFixedSize(true);
        JoinedMembersAdapter joinedMembersAdapter = new JoinedMembersAdapter(getContext(), joinedMembersArrayList, this);
        joinedMembersRecyclerView.setAdapter(joinedMembersAdapter);
        joinedMembersRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));


        joinedMembersArrayList.add(new JoinedMemberModel(R.drawable.profile2, "Soloman Jakes", "Today (Just now)"));
        joinedMembersArrayList.add(new JoinedMemberModel(R.drawable.profile, "Luke Ray", "Since 29 Jun 2019 (2 Hours ago"));
        joinedMembersArrayList.add(new JoinedMemberModel(R.drawable.profile1, "Daisy Lake", "Since 29 Jun 2019 (1 Day ago"));
        joinedMembersArrayList.add(new JoinedMemberModel(R.drawable.profile2, "Mark Smith", "Since 29 Jun 2019 (3 Days ago"));
        joinedMembersArrayList.add(new JoinedMemberModel(R.drawable.profile, "Lucy Lake", "Since 29 Jun 2019 (1 Day ago"));
        joinedMembersArrayList.add(new JoinedMemberModel(R.drawable.profile2, "Dan Smith", "Since 29 Jun 2019 (3 Days ago"));

        joinedLayout.setOnClickListener(new View.OnClickListener() {
            private boolean flag = true;

            @Override
            public void onClick(View v) {
                if (flag) {
                    flag = false;
                    dropDownImage.setImageResource(R.drawable.dropup_icon_mdpi);
                    joinedMembersRecyclerView.setVisibility(View.VISIBLE);
                } else {
                    flag = true;
                    dropDownImage.setImageResource(R.drawable.dropdown_icon_mdpi);
                    joinedMembersRecyclerView.setVisibility(View.GONE);

                }
            }
        });


        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
        pendingRequestsRecyclerView.setLayoutManager(linearLayoutManager1);
        pendingRequestsRecyclerView.setHasFixedSize(true);
        pendingRequestsAdapter = new PendingRequestsAdapter(getContext(), pendingRequestsArrayList);
        pendingRequestsAdapter.setPendingRequestDelegate(this);
        pendingRequestsRecyclerView.setAdapter(pendingRequestsAdapter);
        pendingRequestsRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

        pendingRequests.setOnClickListener(new View.OnClickListener() {
            private boolean flag = true;

            @Override
            public void onClick(View v) {
                if (flag) {
                    flag = false;
                    dropDownIcon.setImageResource(R.drawable.dropup_icon_mdpi);
                    pendingRequestsRecyclerView.setVisibility(View.VISIBLE);
                } else {
                    flag = true;
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
                            groupName.setText(myGroup.getName());
                            groupCreationDateAndTime.setText(myGroup.getCreationTime());
                            groupDescription.setText(myGroup.getDescription());
                            pendingRequestsArrayList = new ArrayList<>();
                            for (AddressBook addressBook : groupResponse.getGroupPeople()) {
                                InvitingMembersModel invitingMembersModel = new InvitingMembersModel(addressBook, myGroup.getId());
                                pendingRequestsArrayList.add(invitingMembersModel);
                            }
                            pendingRequestsAdapter.update(pendingRequestsArrayList);

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

    @Override
    public void onInviteClicked(String userId, String groupId) {
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
            postData.put("userId", userId);
            GroupService groupService = retrofit.create(GroupService.class);
            Call<GroupResponse> groupResponseCall = groupService.inviteToGroup(postData);

            groupResponseCall.enqueue(new Callback<GroupResponse>() {
                @Override
                public void onResponse(Call<GroupResponse> call, Response<GroupResponse> response) {
                    try {
                        progressDialog.dismiss();
                        GroupResponse groupResponse = response.body();
                        if (groupResponse.getSuccess()) {
                            showDialog(true, "Invitation has been sent successfully");
                            getGroupById();
                        } else {
                            progressDialog.dismiss();
                            showDialog(false, "There was a problem inviting member to group! Please try after sometime");
                        }
                    } catch (Exception ex) {
                        progressDialog.dismiss();
                        showDialog(false, "There was a problem inviting member to group! Please try after sometime");
                    }

                }

                @Override
                public void onFailure(Call<GroupResponse> call, Throwable t) {
                    progressDialog.dismiss();
                    showDialog(false, "There was a problem inviting member to group! Please try after sometime");

                }
            });
        } catch (Exception ex) {
            progressDialog.dismiss();
            showDialog(false, "There was a problem inviting member to group! Please try after sometime");
        }
    }
}
