package com.carmel.guestjini.InterestGroups;


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

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.carmel.guestjini.GroupsActivity;
import com.carmel.guestjini.R;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;


import Adapter.InterestGroupsAdpater;
import Model.InterestGroupsModel;

public class InterestGroupsFragment extends Fragment implements InterestGroupsAdpater.OnItemClickListener {

   private RecyclerView interestGroupsRecyclerView;
    ArrayList<InterestGroupsModel> interestGroupsList=new ArrayList<>();
    private ImageView filterIcon,backArrow;
    MaterialCardView filterPopup;
    private EditText search;
    private TextView showingSubscribedTitle,subscribedTitle,ClearTitle,newTitle,searchResultCount,filterTitle;
    private ConstraintLayout searchLayout,noResultFoundLayout,recyclerViewLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_interest_groups, container, false);
        interestGroupsRecyclerView=view.findViewById(R.id.interestGroupsRecyclerView);
        searchLayout=view.findViewById(R.id.searchLayout);
        noResultFoundLayout=view.findViewById(R.id.noResultFoundLayout);
        recyclerViewLayout=view.findViewById(R.id.recyclerViewLayout);
        search=view.findViewById(R.id.search);
        searchResultCount=view.findViewById(R.id.searchResultCount);
        backArrow=view.findViewById(R.id.backArrow);
        filterIcon=view.findViewById(R.id.filterIcon);
        filterPopup=view.findViewById(R.id.filterPopup);
        showingSubscribedTitle=view.findViewById(R.id.showingSubscribedTitle);
        subscribedTitle=view.findViewById(R.id.subscribedTitle);
        newTitle=view.findViewById(R.id.newTitle);
        ClearTitle=view.findViewById(R.id.ClearTitle);
//        filterTitle=view.findViewById(R.id.filterTitle1);


        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), GroupsActivity.class);
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
//
//        filterIcon.setOnClickListener(new View.OnClickListener() {
////            private boolean flag = true;
//            @Override
//            public void onClick(View v) {
////                if () {
////                    flag = false;
//                    final View layout = LayoutInflater.from(getContext()).inflate(R.layout.interest_groups_filter, null);
//                    final PopupWindow window = new PopupWindow(layout, 380, 400, true);
//                    window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                    window.setOutsideTouchable(true);
//                    window.showAtLocation(layout, Gravity.TOP, 274, 210);
//                    final TextView clearTitle = (TextView) layout.findViewById(R.id.ClearTitle);
//                    final TextView subscribedTitle = (TextView) layout.findViewById(R.id.subscribedTitle);
//                    final TextView newTitle = (TextView) layout.findViewById(R.id.newTitle);
//
//                    subscribedTitle.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//
//                            showingSubscribedTitle.setVisibility(View.VISIBLE);
//                            window.dismiss();
//                            clearTitle.setTextColor(ColorStateList.valueOf(Color
//                                    .parseColor("#32BDD2")));
//                        }
//                    });
//                } if(flag){
//                    flag = false;
//                    final View layout = LayoutInflater.from(getContext()).inflate(R.layout.interest_groups_filter, null);
//                    final PopupWindow window = new PopupWindow(layout, 380, 400, true);
//                    window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                    window.setOutsideTouchable(true);
//                    window.showAtLocation(layout, Gravity.TOP, 274, 210);
//                    final TextView clearTitle = (TextView) layout.findViewById(R.id.ClearTitle);
//                    final TextView subscribedTitle = (TextView) layout.findViewById(R.id.subscribedTitle);
//                    final TextView newTitle = (TextView) layout.findViewById(R.id.newTitle);
//                    clearTitle.setTextColor(ColorStateList.valueOf(Color
//                            .parseColor("#32BDD2")));
//                    subscribedTitle.setTextColor(ColorStateList.valueOf(Color
//                            .parseColor("#B5B5B5")));
//                    newTitle.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            showingSubscribedTitle.setVisibility(View.VISIBLE);
//                            window.dismiss();
//                        }
//                    });
//                }else {
//                    flag = true;
//                    final View layout = LayoutInflater.from(getContext()).inflate(R.layout.interest_groups_filter, null);
//                    final PopupWindow window = new PopupWindow(layout, 380, 400, true);
//                    window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                    window.setOutsideTouchable(true);
//                    window.showAtLocation(layout, Gravity.TOP, 274, 210);
//                    final TextView clearTitle = (TextView) layout.findViewById(R.id.ClearTitle);
//                    final TextView subscribedTitle = (TextView) layout.findViewById(R.id.subscribedTitle);
//                    final TextView newTitle = (TextView) layout.findViewById(R.id.newTitle);
//                    clearTitle.setTextColor(ColorStateList.valueOf(Color
//                            .parseColor("#32BDD2")));
//                    subscribedTitle.setTextColor(ColorStateList.valueOf(Color
//                            .parseColor("#B5B5B5")));
//                    newTitle.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            showingSubscribedTitle.setVisibility(View.GONE);
//                            window.dismiss();
//                        }
//                    });
//                }
//            }
//        });

//        filterIcon.setOnClickListener(new View.OnClickListener() {
//            private boolean flag=true;
//            @Override
//            public void onClick(View v) {
//                if(flag){
//                    flag=false;
//                    filterPopup.setVisibility(View.VISIBLE);
//                }else {
//                    flag=true;
//                    filterPopup.setVisibility(View.GONE);
//                }
//            }
//        });
//        subscribedTitle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                filterPopup.setVisibility(View.GONE);
//                filterTitle.setVisibility(View.VISIBLE);
//                filterTitle.setText("Showing subscribed groups.");
//                ClearTitle.setTextColor(ColorStateList.valueOf(Color
//                        .parseColor("#32BDD2")));
//                subscribedTitle.setTextColor(ColorStateList.valueOf(Color
//                        .parseColor("#B5B5B5")));
//                newTitle.setTextColor(ColorStateList.valueOf(Color.parseColor("#505050")));
//            }
//        });
//        ClearTitle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                filterPopup.setVisibility(View.GONE);
//                filterTitle.setVisibility(View.GONE);
//                ClearTitle.setTextColor(ColorStateList.valueOf(Color
//                        .parseColor("#B5B5B5")));
//                filterTitle.setTextColor(ColorStateList.valueOf(Color
//                        .parseColor("#505050")));
//
//            }
//        });
//        newTitle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                filterPopup.setVisibility(View.GONE);
//                filterTitle.setVisibility(View.VISIBLE);
//                filterTitle.setText("Showing new groups.");
//                ClearTitle.setTextColor(ColorStateList.valueOf(Color
//                        .parseColor("#32BDD2")));
//                newTitle.setTextColor(ColorStateList.valueOf(Color
//                        .parseColor("#B5B5B5")));
//
//            }
//        });
        searchLayout.setOnClickListener(new View.OnClickListener() {
            private boolean flag=true;
            @Override
            public void onClick(View v) {
                if(flag) {
                    flag=false;
                    noResultFoundLayout.setVisibility(View.VISIBLE);
                    recyclerViewLayout.setVisibility(View.GONE);
                    search.setText("John");
                }else {
                    flag=true;
                    searchResultCount.setVisibility(View.VISIBLE);
                }

            }
        });

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        interestGroupsRecyclerView.setLayoutManager(linearLayoutManager);
        interestGroupsRecyclerView.setHasFixedSize(true);
        InterestGroupsAdpater interestGroupsAdpater=new InterestGroupsAdpater(interestGroupsList,this);
        interestGroupsRecyclerView.setAdapter(interestGroupsAdpater);

        interestGroupsList.add(new InterestGroupsModel("OutDoor Adventure",
                "CYCLING",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam erat sapien. ultricies.",
                "This group matches your interest.",R.drawable.indicator_icon,R.drawable.information_icon,InterestGroupsModel.ONE_TYPE));

        interestGroupsList.add(new InterestGroupsModel("Tech",
                "ROBOTICS",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam erat sapien. ultricies.",
                "This group matches your interest.",R.drawable.indicator_icon,R.drawable.information_icon,InterestGroupsModel.TWO_TYPE));

        interestGroupsList.add(new InterestGroupsModel("OutDoor Adventure",
                "HIKING",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam erat sapien. ultricies.",
                "This group matches your interest.",R.drawable.indicator_icon,R.drawable.information_icon,InterestGroupsModel.THIRD_TYPE));

        interestGroupsList.add(new InterestGroupsModel("OutDoor Adventure",
                "SKY DIVING",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam erat sapien. ultricies.",
                "This group matches your interest.",R.drawable.indicator_icon,R.drawable.information_icon,InterestGroupsModel.FOURTH_TYPE));
        return view;
    }

    @Override
    public void onclickUnsubscribeGroup(int position) {
        SubscribedGroupDetailedFragment subscribedGroupDetailedFragment=new SubscribedGroupDetailedFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.interestGroupsPlaceHolder, subscribedGroupDetailedFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        Bundle bundle=new Bundle();
        bundle.putString("Interest Group Type",interestGroupsList.get(position).getAddInterestCategoryTitle());
        bundle.putString("Interest Group Name",interestGroupsList.get(position).getAddInterestGroupTitle());
        bundle.putString("interestGroupDescription",interestGroupsList.get(position).getAddInterestGroupDescription());
        subscribedGroupDetailedFragment.setArguments(bundle);
    }

    @Override
    public void onclickSubscribeGroup(int position) {
        SubscribedGroupChatFragment subscribedGroupChatFragment=new SubscribedGroupChatFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.interestGroupsPlaceHolder, subscribedGroupChatFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        Bundle bundle=new Bundle();
        bundle.putString("Interest Group Type",interestGroupsList.get(position).getAddInterestCategoryTitle());
        bundle.putString("Interest Group Name",interestGroupsList.get(position).getAddInterestGroupTitle());
        bundle.putString("interestGroupDescription",interestGroupsList.get(position).getAddInterestGroupDescription());
        subscribedGroupChatFragment.setArguments(bundle);

    }
}
