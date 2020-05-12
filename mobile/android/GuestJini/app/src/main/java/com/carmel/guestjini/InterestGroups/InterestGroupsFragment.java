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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
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
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Adapter.InterestGroupsAdpater;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InterestGroupsFragment extends Fragment implements InterestGroupsAdpater.OnItemClickListener {

    private RecyclerView interestGroupsRecyclerView;
    ArrayList<Group> groupArrayList = new ArrayList<>();
    private ImageView filterIcon, backArrow;
    MaterialCardView filterPopup;
    private EditText search;
    private TextView showingSubscribedTitle, subscribedTitle, ClearTitle, newTitle, searchResultCount, filterTitle;
    private ConstraintLayout searchLayout, noResultFoundLayout, recyclerViewLayout;

    AlertDialog progressDialog;

    InterestGroupsAdpater interestGroupsAdpater;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_interest_groups, container, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false); // if you want user to wait for some process to finish,
        builder.setView(R.layout.layout_loading_dialog);
        progressDialog = builder.create();
        interestGroupsRecyclerView = view.findViewById(R.id.interestGroupsRecyclerView);
        searchLayout = view.findViewById(R.id.searchLayout);
        noResultFoundLayout = view.findViewById(R.id.noResultFoundLayout);
        recyclerViewLayout = view.findViewById(R.id.recyclerViewLayout);
        search = view.findViewById(R.id.search);
        searchResultCount = view.findViewById(R.id.searchResultCount);
        backArrow = view.findViewById(R.id.backArrow);
        filterIcon = view.findViewById(R.id.filterIcon);
        filterPopup = view.findViewById(R.id.filterPopup);
        showingSubscribedTitle = view.findViewById(R.id.showingSubscribedTitle);
        subscribedTitle = view.findViewById(R.id.subscribedTitle);
        newTitle = view.findViewById(R.id.newTitle);
        ClearTitle = view.findViewById(R.id.ClearTitle);
//        filterTitle=view.findViewById(R.id.filterTitle1);


        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), GroupsActivity.class);
                startActivity(intent);
            }
        });
        filterIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View layout = LayoutInflater.from(getContext()).inflate(R.layout.interest_groups_filter, null);
                final PopupWindow window = new PopupWindow(layout, 380, 400, true);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                window.setOutsideTouchable(true);
                window.showAtLocation(layout, Gravity.TOP, 274, 210);
                final TextView clearTitle = (TextView) layout.findViewById(R.id.ClearTitle);
                final TextView subscribedTitle = (TextView) layout.findViewById(R.id.subscribedTitle);
                final TextView newTitle = (TextView) layout.findViewById(R.id.newTitle);

            }
        });
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

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        interestGroupsRecyclerView.setLayoutManager(linearLayoutManager);
        interestGroupsRecyclerView.setHasFixedSize(true);
        interestGroupsAdpater = new InterestGroupsAdpater(groupArrayList, this);
        interestGroupsRecyclerView.setAdapter(interestGroupsAdpater);

//        interestGroupsList.add(new InterestGroupsModel("OutDoor Adventure",
//                "CYCLING",
//                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam erat sapien. ultricies.",
//                "This group matches your interest.", R.drawable.indicator_icon, R.drawable.information_icon, InterestGroupsModel.ONE_TYPE));
//
//        interestGroupsList.add(new InterestGroupsModel("Tech",
//                "ROBOTICS",
//                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam erat sapien. ultricies.",
//                "This group matches your interest.", R.drawable.indicator_icon, R.drawable.information_icon, InterestGroupsModel.TWO_TYPE));
//
//        interestGroupsList.add(new InterestGroupsModel("OutDoor Adventure",
//                "HIKING",
//                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam erat sapien. ultricies.",
//                "This group matches your interest.", R.drawable.indicator_icon, R.drawable.information_icon, InterestGroupsModel.THIRD_TYPE));
//
//        interestGroupsList.add(new InterestGroupsModel("OutDoor Adventure",
//                "SKY DIVING",
//                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam erat sapien. ultricies.",
//                "This group matches your interest.", R.drawable.indicator_icon, R.drawable.information_icon, InterestGroupsModel.FOURTH_TYPE));
        getInterestGroups();
        return view;
    }

    @Override
    public void onclickUnsubscribeGroup(int position) {
        SubscribedGroupDetailedFragment subscribedGroupDetailedFragment = new SubscribedGroupDetailedFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.interestGroupsPlaceHolder, subscribedGroupDetailedFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        Bundle bundle = new Bundle();
//        bundle.putString("Interest Group Type", interestGroupsList.get(position).getAddInterestCategoryTitle());
//        bundle.putString("Interest Group Name", interestGroupsList.get(position).getAddInterestGroupTitle());
//        bundle.putString("interestGroupDescription", interestGroupsList.get(position).getAddInterestGroupDescription());
        subscribedGroupDetailedFragment.setArguments(bundle);
    }

    @Override
    public void onclickSubscribeGroup(int position) {
        SubscribedGroupChatFragment subscribedGroupChatFragment = new SubscribedGroupChatFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.interestGroupsPlaceHolder, subscribedGroupChatFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        Bundle bundle = new Bundle();
//        bundle.putString("Interest Group Type", interestGroupsList.get(position).getAddInterestCategoryTitle());
//        bundle.putString("Interest Group Name", interestGroupsList.get(position).getAddInterestGroupTitle());
//        bundle.putString("interestGroupDescription", interestGroupsList.get(position).getAddInterestGroupDescription());
        subscribedGroupChatFragment.setArguments(bundle);

    }

    private void getInterestGroups() {
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
            postData.put("groupType", "1");

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
                            interestGroupsAdpater.update(groupArrayList);

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
