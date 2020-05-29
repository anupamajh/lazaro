package com.carmel.guestjini.People;


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
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Common.EndPoints;
import com.carmel.guestjini.CommunityActivity;
import com.carmel.guestjini.Components.OkHttpClientInstance;
import com.carmel.guestjini.Models.User.PeopleResponse;
import com.carmel.guestjini.Models.User.Person;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Services.Authentication.AuthService;
import com.carmel.guestjini.Services.Authentication.AuthServiceHolder;
import com.carmel.guestjini.Services.User.PeopleService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import Adapter.PeopleAdapter;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PeopleLandingFragment extends Fragment implements PeopleAdapter.OnItemClickListener {
    private RecyclerView peopleRecylerView;
    private ImageView filterIcon, backArrow;
    private RelativeLayout filterPopup;
    private EditText search;
    private ArrayList<Person> personArrayList = new ArrayList<>();
    private TextView showingYourFavourites, searchResultCount;
    private ConstraintLayout searchLayout, noResultFoundLayout, recyclerViewLayout;
    private PeopleAdapter peopleAdapter;
    private PeopleResponse peopleResponse;
    AlertDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_people_landing, container, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false); // if you want user to wait for some process to finish,
        builder.setView(R.layout.layout_loading_dialog);
        progressDialog = builder.create();
        peopleRecylerView = rootView.findViewById(R.id.peopleRecyclerView);
        searchLayout = rootView.findViewById(R.id.searchLayout);
        noResultFoundLayout = rootView.findViewById(R.id.noResultFoundLayout);
        recyclerViewLayout = rootView.findViewById(R.id.recyclerViewLayout);
        filterIcon = rootView.findViewById(R.id.filterIcon);
        filterPopup = rootView.findViewById(R.id.filterPopup);
        backArrow = rootView.findViewById(R.id.backArrow);
        showingYourFavourites = rootView.findViewById(R.id.showingYourFavourites);
        search = rootView.findViewById(R.id.search);
        searchResultCount = rootView.findViewById(R.id.searchResultCount);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        peopleRecylerView.setLayoutManager(linearLayoutManager);
        peopleRecylerView.setHasFixedSize(true);
        peopleAdapter = new PeopleAdapter(personArrayList, this, getContext());
        peopleRecylerView.setAdapter(peopleAdapter);
        loadPeopleList();

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
                Intent intent = new Intent(getContext(), CommunityActivity.class);
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
        PeopleDetailsFragment peopleDetailsFragment = new PeopleDetailsFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.CommunityPlaceHolder, peopleDetailsFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        Bundle bundle = new Bundle();
        bundle.putString("personId", personArrayList.get(position).getAddressBook().getUserId());
        peopleDetailsFragment.setArguments(bundle);
    }

    private void loadPeopleList() {
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

        PeopleService peopleService = retrofit.create(PeopleService.class);
        Call<PeopleResponse> peopleResponseCall = peopleService.getPeopleList();
        peopleResponseCall.enqueue(new Callback<PeopleResponse>() {
            @Override
            public void onResponse(Call<PeopleResponse> call, Response<PeopleResponse> response) {
                progressDialog.dismiss();
                try {
                    peopleResponse = response.body();
                    if (peopleResponse.isSuccess()) {
                        personArrayList = new ArrayList<>();
                        personArrayList.addAll(peopleResponse.getPersonList());
                        peopleAdapter.filterList(personArrayList, peopleResponse);
                    } else {
                        showDialog(false, "There was a problem fetching people list! Please try after sometime");
                    }
                } catch (Exception ex) {
                    showDialog(false, "There was a problem fetching people list! Please try after sometime");
                }
            }

            @Override
            public void onFailure(Call<PeopleResponse> call, Throwable t) {
                progressDialog.dismiss();
                showDialog(false, "There was a problem fetching people list! Please try after sometime");
            }
        });
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
