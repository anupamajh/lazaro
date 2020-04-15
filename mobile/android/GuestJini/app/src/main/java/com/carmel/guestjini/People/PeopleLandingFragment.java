package com.carmel.guestjini.People;


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

import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.carmel.guestjini.CommunityActivity;
import com.carmel.guestjini.R;

import java.util.ArrayList;

import Adapter.PeopleAdapter;
import Model.PeopleModel;

public class PeopleLandingFragment extends Fragment implements PeopleAdapter.OnItemClickListener {
    private RecyclerView peopleRecylerView;
    private ImageView filterIcon,backArrow;
    private RelativeLayout filterPopup;
    private EditText search;
    private ArrayList<PeopleModel> peopleModelArrayList=new ArrayList<>();
    private TextView showingYourFavourites,searchResultCount;
    private ConstraintLayout searchLayout,noResultFoundLayout,recyclerViewLayout;
    private PeopleAdapter peopleAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_people_landing, container, false);
        peopleRecylerView=rootView.findViewById(R.id.peopleRecyclerView);
        searchLayout=rootView.findViewById(R.id.searchLayout);
        noResultFoundLayout=rootView.findViewById(R.id.noResultFoundLayout);
        recyclerViewLayout=rootView.findViewById(R.id.recyclerViewLayout);
        filterIcon=rootView.findViewById(R.id.filterIcon);
        filterPopup=rootView.findViewById(R.id.filterPopup);
        backArrow=rootView.findViewById(R.id.backArrow);
        showingYourFavourites=rootView.findViewById(R.id.showingYourFavourites);
        search=rootView.findViewById(R.id.search);
        searchResultCount=rootView.findViewById(R.id.searchResultCount);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        peopleRecylerView.setLayoutManager(linearLayoutManager);
        peopleRecylerView.setHasFixedSize(true);
        peopleAdapter=new PeopleAdapter(peopleModelArrayList,this);
        peopleRecylerView.setAdapter(peopleAdapter);

        PeopleModel peopleModel=new PeopleModel();
        peopleModel.setAddPeopleName("John Doe");
        peopleModel.setAddPeopleGender("Male");
        peopleModel.setCompatibilityCount("12/12");
        peopleModel.setNotificationIndicator(R.drawable.red_small_circle);
        peopleModel.setProfilePicture(R.drawable.profile_image);
        peopleModelArrayList.add(peopleModel);

        peopleModel=new PeopleModel();
        peopleModel.setAddPeopleName("Peter Law");
        peopleModel.setAddPeopleGender("Male");
        peopleModel.setCompatibilityCount("08/12");
        peopleModel.setProfilePicture(R.drawable.profile_image);
        peopleModelArrayList.add(peopleModel);

        peopleModel=new PeopleModel();
        peopleModel.setAddPeopleName("Luke Ray");
        peopleModel.setCompatibilityCount("05/12");
        peopleModel.setProfilePicture(R.drawable.profile_image);
        peopleModelArrayList.add(peopleModel);

        peopleModel=new PeopleModel();
        peopleModel.setAddPeopleName("Daisy Lake");
        peopleModel.setAddPeopleGender("Female");
        peopleModel.setCompatibilityCount("02/12");
        peopleModel.setProfilePicture(R.drawable.profile_image);
        peopleModelArrayList.add(peopleModel);

        peopleModel=new PeopleModel();
        peopleModel.setAddPeopleName("Jeret Quartz");
        peopleModel.setAddPeopleGender("Female");
        peopleModel.setCompatibilityCount("01/12");
        peopleModel.setProfilePicture(R.drawable.profile_image);
        peopleModelArrayList.add(peopleModel);

        peopleModel=new PeopleModel();
        peopleModel.setAddPeopleName("Dan Quartz");
        peopleModel.setAddPeopleGender("Male");
        peopleModel.setCompatibilityCount("00/12");
        peopleModel.setProfilePicture(R.drawable.profile_image);
        peopleModelArrayList.add(peopleModel);



//        search.addTextChangedListener(new TextWatcher() {
//            private boolean flag=true;
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//            @Override
//            public void afterTextChanged(Editable s) {
//                filter(s.toString());
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
                            .parseColor("#32BDD2")));
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

//        filterIcon.setOnClickListener(new View.OnClickListener() {
//            private boolean flag=true;
//            @Override
//            public void onClick(View v) {
//                if(flag){
//                    flag=false;
//                    filterPopup.setVisibility(View.VISIBLE);
//
//                }else {
//                    flag=true;
//                    filterPopup.setVisibility(View.GONE);
//                }
//            }
//        });
//
//        favouritesTitle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                    showingYourFavourites.setVisibility(View.VISIBLE);
//                    clearTitle.setTextColor(ColorStateList.valueOf(Color
//                            .parseColor("#32BDD2")));
//                favouritesTitle.setTextColor(ColorStateList.valueOf(Color
//                        .parseColor("#B5B5B5")));
//            }
//        });
//
//        clearTitle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                filterPopup.setVisibility(View.GONE);
//                showingYourFavourites.setVisibility(View.GONE);
//                clearTitle.setTextColor(ColorStateList.valueOf(Color
//                        .parseColor("#B5B5B5")));
//                favouritesTitle.setTextColor(ColorStateList.valueOf(Color
//                        .parseColor("#747474")));
//
//            }
//        });

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), CommunityActivity.class);
                startActivity(intent);
            }
        });
        return rootView;

    }
//    private void filter(String text){
//        ArrayList<PeopleModel> filterList=new ArrayList<>();
//        for(PeopleModel item:peopleModelArrayList){
//            if(item.getAddPeopleName().toLowerCase().contains(text.toLowerCase())){
//                filterList.add(item);
//            }
//        }
//        peopleAdapter.filterList(filterList);
//    }

    @Override
    public void onItemClick(int position) {
        PeopleDetailsFragment peopleDetailsFragment=new PeopleDetailsFragment();
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.peoplePlaceHolder,peopleDetailsFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        Bundle bundle=new Bundle();
        bundle.putString("profile name",peopleModelArrayList.get(position).getAddPeopleName());
        bundle.putString("profile gender",peopleModelArrayList.get(position).getAddPeopleGender());
        peopleDetailsFragment.setArguments(bundle);
    }
}
