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
import com.carmel.guestjini.Models.Group.GroupConversation;
import com.carmel.guestjini.Models.Group.GroupConversationResponse;
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

import Adapter.GroupChatAdapter;
import Model.GroupChatModel;
import Model.InvitingMembersModel;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SubscribedGroupChatFragment extends Fragment {
    ImageView backArrow, informationIcon;
    RecyclerView groupChatRecyclerView, groupInformationRecyclerView;
    ArrayList<GroupChatModel> groupChatList = new ArrayList<>();
    private TextView txtGroupType, groupName, groupDescription, txtPageTitle, txtMessage;
    String group_Type, group_Name, group_Description;
    String group_Type1, group_Name1, group_Description1;
    Button btnSend;

    private String groupId;
    private int groupType;

    AlertDialog progressDialog;
    GroupChatAdapter groupChatAdapter;


//    RelativeLayout recyclerViewMainLayout,unsubscribedLayout;

    ImageView informationButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_subscribed_group_chat, container, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false); // if you want user to wait for some process to finish,
        builder.setView(R.layout.layout_loading_dialog);
        progressDialog = builder.create();
        backArrow = rootView.findViewById(R.id.backButton);
        informationIcon = rootView.findViewById(R.id.informationButton);
        groupChatRecyclerView = rootView.findViewById(R.id.groupChatRecyclerView);
        groupInformationRecyclerView = rootView.findViewById(R.id.groupInformationRecyclerView);
        btnSend = rootView.findViewById(R.id.sendButton);
        txtMessage = rootView.findViewById(R.id.messageEditBox);
//        recyclerViewMainLayout=rootView.findViewById(R.id.recyclerViewMainLayout);
//        unsubscribedLayout=rootView.findViewById(R.id.unsubscribedLayout);

        txtPageTitle = rootView.findViewById(R.id.interestGroupsHeading);
        txtGroupType = rootView.findViewById(R.id.interestGroupType);
        groupName = rootView.findViewById(R.id.interestGroupName);
        groupDescription = rootView.findViewById(R.id.interestsGroupDescription);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtMessage.getText().toString().trim().equals("")) {

                } else {
                    saveConversation();
                }
            }
        });


        informationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(groupType == 1) {
                    SubscribedGroupDetailedFragment subscribedGroupDetailedFragment = new SubscribedGroupDetailedFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.interestGroupsPlaceHolder, subscribedGroupDetailedFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    Bundle bundle = new Bundle();
                    bundle.putString("groupId", groupId);
                    bundle.putString("groupType", String.valueOf(groupType));
                    subscribedGroupDetailedFragment.setArguments(bundle);
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


        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (groupType == 1) {
                    InterestGroupsFragment interestGroupsFragment = new InterestGroupsFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.interestGroupsPlaceHolder, interestGroupsFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                } else if (groupType == 2) {
                    final Intent intent;
                    intent = new Intent(getContext(), CommunityGroupsActivity.class);
                    getContext().startActivity(intent);
                }else if (groupType == 3) {
                    final Intent intent;
                    intent = new Intent(getContext(), MyGroupsActivity.class);
                    getContext().startActivity(intent);
                }
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        groupChatRecyclerView.setLayoutManager(linearLayoutManager);
        groupChatAdapter = new

                GroupChatAdapter(rootView.getContext(), groupChatList);
        groupChatRecyclerView.setAdapter(groupChatAdapter);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            groupId = bundle.getString("groupId");
            groupType = Integer.parseInt(bundle.getString("groupType"));
            if (groupType == 1) {
                txtPageTitle.setText("INTEREST GROUPS");
            } else if (groupType == 2) {
                txtPageTitle.setText("COMMUNITY GROUPS");
            } else if (groupType == 3) {
                txtPageTitle.setText("MY GROUPS");
                informationIcon.setVisibility(View.GONE);
            }
            getGroupById();
            getConversationByGroup();
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

    private void getConversationByGroup() {
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
            Call<GroupConversationResponse> groupConversationResponseCall = groupService.getGroupConversationById(postData);
            groupConversationResponseCall.enqueue(new Callback<GroupConversationResponse>() {
                @Override
                public void onResponse(Call<GroupConversationResponse> call, Response<GroupConversationResponse> response) {
                    progressDialog.dismiss();
                    try {
                        GroupConversationResponse groupConversationResponse = response.body();
                        groupChatList = new ArrayList<>();
                        if (groupConversationResponse.getSuccess()) {
                            for (GroupConversation groupConversation : groupConversationResponse.getGroupConversationList()) {
                                GroupChatModel groupChatModel = new GroupChatModel(groupConversation);
                                groupChatList.add(groupChatModel);
                            }
                            groupChatAdapter.update(groupChatList);
                            groupChatRecyclerView.scrollToPosition(groupChatRecyclerView.getAdapter().getItemCount() - 1);
                        } else {
                            showDialog(false, "There was a problem fetching group conversation! Please try after sometime");
                        }
                    } catch (Exception ex) {
                        showDialog(false, "There was a problem fetching group conversation! Please try after sometime");
                    }
                }

                @Override
                public void onFailure(Call<GroupConversationResponse> call, Throwable t) {
                    progressDialog.dismiss();
                    showDialog(false, "There was a problem fetching group conversation! Please try after sometime");
                }
            });

        } catch (Exception ex) {
            progressDialog.dismiss();
            showDialog(false, "There was a problem fetching group conversation! Please try after sometime");
        }
    }

    private void saveConversation() {
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
            postData.put("message", txtMessage.getText().toString().trim());
            GroupService groupService = retrofit.create(GroupService.class);
            Call<GroupConversationResponse> groupConversationResponseCall = groupService.saveGroupConversation(postData);
            groupConversationResponseCall.enqueue(new Callback<GroupConversationResponse>() {
                @Override
                public void onResponse(Call<GroupConversationResponse> call, Response<GroupConversationResponse> response) {
                    progressDialog.dismiss();
                    try {
                        GroupConversationResponse groupConversationResponse = response.body();
                        if (groupConversationResponse.getSuccess()) {
                            txtMessage.setText("");
                            getConversationByGroup();
                        } else {
                            showDialog(false, "There was a problem saving conversation! Please try after sometime");
                        }
                    } catch (Exception ex) {
                        showDialog(false, "There was a problem saving conversation! Please try after sometime");
                    }
                }

                @Override
                public void onFailure(Call<GroupConversationResponse> call, Throwable t) {
                    progressDialog.dismiss();
                    showDialog(false, "There was a problem saving conversation! Please try after sometime");

                }
            });
        } catch (Exception ex) {
            progressDialog.dismiss();
            showDialog(false, "There was a problem saving conversation! Please try after sometime");
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
