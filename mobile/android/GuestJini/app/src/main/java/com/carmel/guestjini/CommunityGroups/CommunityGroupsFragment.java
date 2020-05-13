package com.carmel.guestjini.CommunityGroups;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Common.EndPoints;
import com.carmel.guestjini.Components.OkHttpClientInstance;
import com.carmel.guestjini.GroupsActivity;
import com.carmel.guestjini.InterestGroups.SubscribedGroupChatFragment;
import com.carmel.guestjini.InterestGroups.SubscribedGroupDetailedFragment;
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

import Adapter.CommunityGroupsAdapter;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommunityGroupsFragment extends Fragment implements CommunityGroupsAdapter.OnItemClickListener {
    private ArrayList<Group> groupArrayList=new ArrayList<>();
    private RecyclerView communityGroupsRecyclerView;
    private ImageView communityGroupFilterIcon,backArrow;
    private ConstraintLayout searchLayout,noResultFoundLayout,recyclerViewLayout;
    private TextView searchResultCount;
    private EditText search;

    CommunityGroupsAdapter communityGroupsAdapter;

    AlertDialog progressDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_community_groups, container, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false); // if you want user to wait for some process to finish,
        builder.setView(R.layout.layout_loading_dialog);
        progressDialog = builder.create();
        communityGroupsRecyclerView=rootView.findViewById(R.id.RecyclerViewCommunityGroups);
        backArrow=rootView.findViewById(R.id.backArrow);
        searchLayout=rootView.findViewById(R.id.searchLayout);
        noResultFoundLayout=rootView.findViewById(R.id.noResultFoundLayout);
        recyclerViewLayout=rootView.findViewById(R.id.recyclerViewLayout);
        searchResultCount=rootView.findViewById(R.id.searchResultCount);
        search=rootView.findViewById(R.id.search);
        communityGroupFilterIcon=rootView.findViewById(R.id.filterIcon);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        communityGroupsRecyclerView.setLayoutManager(linearLayoutManager);
        communityGroupsRecyclerView.setHasFixedSize(true);
        communityGroupsAdapter=new CommunityGroupsAdapter(getContext(),groupArrayList,this);
        communityGroupsAdapter.setHasStableIds(true);
        communityGroupsRecyclerView.setAdapter(communityGroupsAdapter);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), GroupsActivity.class);
                startActivity(intent);
            }
        });
        communityGroupFilterIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View layout = LayoutInflater.from(getContext()).inflate(R.layout.community_groups_filter, null);
                final PopupWindow window = new PopupWindow(layout, 300, 460, true);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                window.setOutsideTouchable(true);
                window.showAtLocation(layout, Gravity.TOP, 310, 210);
            }
        });


        searchLayout.setOnClickListener(new View.OnClickListener() {
            private boolean flag=true;
            @Override
            public void onClick(View v) {
                if(flag) {
                    flag=false;
                    noResultFoundLayout.setVisibility(View.VISIBLE);
                    recyclerViewLayout.setVisibility(View.GONE);
                    search.setText("Boxing");
                }else {
                    flag=true;
                    searchResultCount.setVisibility(View.VISIBLE);
                }
            }
        });
        getCommunityGroups();
        return rootView;
    }

    @Override
    public void onClikInvitedUnreadGroup(int position) {
        groupArrayList.get(position);
        InvitedGroupViewFragment invitedGroupViewFragment=new InvitedGroupViewFragment();
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.communityGroupsPlaceHolder,invitedGroupViewFragment);
        fragmentTransaction.addToBackStack(null);
        Bundle bundle=new Bundle();
//        bundle.putString("GroupName",communityGroupsList.get(position).getCommunityGroupTitle());
//        bundle.putString("GroupDescription",communityGroupsList.get(position).getCommunityGroupDescription());
//        bundle.putString("GroupAdminName",communityGroupsList.get(position).getCommunityGroupAdmin());
//        bundle.putString("GroupCreationDateAndTime",communityGroupsList.get(position).getCommunityGroupCreationDateAndTime());
//        bundle.putInt("GroupIcon",communityGroupsList.get(position).getAdminProfileIcon());
        fragmentTransaction.commit();
        invitedGroupViewFragment.setArguments(bundle);

    }

    @Override
    public void onClikRequestAcceptedGroup(int position) {
        Group group = groupArrayList.get(position);
        SubscribedGroupChatFragment subscribedGroupChatFragment = new SubscribedGroupChatFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.communityGroupsPlaceHolder, subscribedGroupChatFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        Bundle bundle = new Bundle();
        bundle.putString("groupId", group.getId());
        bundle.putString("groupType", "2");
        subscribedGroupChatFragment.setArguments(bundle);
//
//        groupArrayList.get(position);
//        CommunityGroupChatFragment communityGroupChatFragment=new CommunityGroupChatFragment();
//        FragmentManager fragmentManager=getFragmentManager();
//        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.communityGroupsPlaceHolder,communityGroupChatFragment);
//        fragmentTransaction.addToBackStack(null);
//        Bundle bundle=new Bundle();
////        bundle.putString("GroupName",communityGroupsList.get(position).getCommunityGroupTitle());
////        bundle.putString("GroupDescription",communityGroupsList.get(position).getCommunityGroupDescription());
////        bundle.putString("GroupAdminName",communityGroupsList.get(position).getCommunityGroupAdmin());
////        bundle.putString("GroupCreationDateAndTime",communityGroupsList.get(position).getCommunityGroupCreationDateAndTime());
////        bundle.putInt("GroupIcon",communityGroupsList.get(position).getAdminProfileIcon());
//        communityGroupChatFragment.setArguments(bundle);
//        fragmentTransaction.commit();
    }

    @Override
    public void onClikRemovedGroup(int position) {
        Toast toast=new Toast(getContext());
        ViewGroup viewGroup = null;
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.toast_layout, viewGroup, false);
        TextView text = (TextView) dialogView.findViewById(R.id.visibleToast);
        text.setText(getText(R.string.groupRemovedMessage));
        toast.setView(dialogView);
//        toast.makeText(getActivity(),"", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.getAbsoluteGravity(0,0),0,520);
        toast.show();
    }

    @Override
    public void onClikRequestedGroup(int position) {
        groupArrayList.get(position);
        InvitedGroupViewFragment invitedGroupViewFragment=new InvitedGroupViewFragment();
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.communityGroupsPlaceHolder,invitedGroupViewFragment);
        fragmentTransaction.addToBackStack(null);
        Bundle bundle=new Bundle();
//        bundle.putString("GroupName",communityGroupsList.get(position).getCommunityGroupTitle());
//        bundle.putString("GroupDescription",communityGroupsList.get(position).getCommunityGroupDescription());
//        bundle.putString("GroupAdminName",communityGroupsList.get(position).getCommunityGroupAdmin());
//        bundle.putString("GroupCreationDateAndTime",communityGroupsList.get(position).getCommunityGroupCreationDateAndTime());
//        bundle.putInt("GroupIcon",communityGroupsList.get(position).getAdminProfileIcon());
        invitedGroupViewFragment.setArguments(bundle);
        fragmentTransaction.commit();
    }

    @Override
    public void onClikUnsubscribedGroup(int position) {

        Group group = groupArrayList.get(position);
        SubscribedGroupDetailedFragment subscribedGroupDetailedFragment = new SubscribedGroupDetailedFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.communityGroupsPlaceHolder, subscribedGroupDetailedFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        Bundle bundle = new Bundle();
        bundle.putString("groupId", group.getId());
        bundle.putString("groupType", "2");
        subscribedGroupDetailedFragment.setArguments(bundle);
//        groupArrayList.get(position);
//        InvitedGroupViewFragment invitedGroupViewFragment=new InvitedGroupViewFragment();
//        FragmentManager fragmentManager=getFragmentManager();
//        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.communityGroupsPlaceHolder,invitedGroupViewFragment);
//        fragmentTransaction.addToBackStack(null);
//        Bundle bundle=new Bundle();
////        bundle.putString("GroupName",communityGroupsList.get(position).getCommunityGroupTitle());
////        bundle.putString("GroupDescription",communityGroupsList.get(position).getCommunityGroupDescription());
////        bundle.putString("GroupAdminName",communityGroupsList.get(position).getCommunityGroupAdmin());
////        bundle.putString("GroupCreationDateAndTime",communityGroupsList.get(position).getCommunityGroupCreationDateAndTime());
////        bundle.putInt("GroupIcon",communityGroupsList.get(position).getAdminProfileIcon());
//        invitedGroupViewFragment.setArguments(bundle);
//        fragmentTransaction.commit();
    }

    private void getCommunityGroups() {
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
            postData.put("groupType", "2");

            GroupService groupService = retrofit.create(GroupService.class);
            Call<GroupResponse> groupResponseCall = groupService.getGroupByType(postData);
            groupResponseCall.enqueue(new Callback<GroupResponse>() {
                @Override
                public void onResponse(Call<GroupResponse> call, Response<GroupResponse> response) {
                    progressDialog.dismiss();
                    try {
                        GroupResponse groupResponse = response.body();
                        if(groupResponse.getSuccess()){
                            groupArrayList = new ArrayList<>();
                            groupArrayList.addAll(groupResponse.getGroupList());
                            communityGroupsAdapter.update(groupArrayList);

                        }else{
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
