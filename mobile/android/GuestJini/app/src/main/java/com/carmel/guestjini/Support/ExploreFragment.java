package com.carmel.guestjini.Support;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
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
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.carmel.guestjini.Common.EndPoints;
import com.carmel.guestjini.Components.OkHttpClientInstance;
import com.carmel.guestjini.Models.Ticket.KB;
import com.carmel.guestjini.Models.Ticket.KBResponse;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Services.Authentication.AuthService;
import com.carmel.guestjini.Services.Authentication.AuthServiceHolder;
import com.carmel.guestjini.Services.Ticket.KBService;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import Adapter.ExploreTicketsAdapter;
import Model.TicketsModel;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ExploreFragment extends Fragment implements ExploreTicketsAdapter.OnItemClickListener {
    private RecyclerView recyclerView;
    private ArrayList<KB> kbArrayList = new ArrayList<>();
    private ImageView backArrow;
    private MaterialButton exploreFilterIcon;
    private TextView showingCategories, clearFilter, articlesDetails, showing, dialogClearFilter, elevators;
    private LinearLayout elevatorLayout;
    private ConstraintLayout filterPopup;
    private ExploreTicketsAdapter exploreTicketsAdapter;
    AlertDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_explore, container, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false); // if you want user to wait for some process to finish,
        builder.setView(R.layout.layout_loading_dialog);
        progressDialog = builder.create();
        recyclerView = rootView.findViewById(R.id.recyclerView);
        backArrow = rootView.findViewById(R.id.leftArrowMark);
        exploreFilterIcon = rootView.findViewById(R.id.exploreFilterIcon);
        showingCategories = rootView.findViewById(R.id.showingCategories);
        clearFilter = rootView.findViewById(R.id.clearFilter);
        elevatorLayout = rootView.findViewById(R.id.elevatorLayout);
        showing = rootView.findViewById(R.id.showing);
        filterPopup = rootView.findViewById(R.id.filterPopup);
        dialogClearFilter = rootView.findViewById(R.id.clearFilterId);
        elevators = (TextView) rootView.findViewById(R.id.elevators);
        articlesDetails = (TextView) rootView.findViewById(R.id.articlesDetails);

        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        exploreTicketsAdapter = new ExploreTicketsAdapter(rootView.getContext(), kbArrayList, this);
        recyclerView.setAdapter(exploreTicketsAdapter);
        recyclerView.setHasFixedSize(true);

//        TicketsModel ticketsModel=new TicketsModel();
//        ticketsModel.setTicketsName("Elevator is not working?");
//        ticketsModel.setTicketsAuthorName("Author - John Doe");
//        ticketsModel.setTicketsDate("10 Jan 2019");
//        ticketsModel.setTicketsDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit sodales.");
//        ticketsModel.setNavigationIcon(R.drawable.ic_navigate_next_black_24dp);
//        ticketsModelsList.add(ticketsModel);
//
//
//        ticketsModel=new TicketsModel();
//        ticketsModel.setTicketsName("Coffee machine is not working?");
//        ticketsModel.setTicketsAuthorName("Author - Jaret Quartz");
//        ticketsModel.setTicketsDate("03 Jan 2019");
//        ticketsModel.setTicketsDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit sodales.");
//        ticketsModel.setNavigationIcon(R.drawable.ic_navigate_next_black_24dp);
//        ticketsModelsList.add(ticketsModel);
//
//
//        ticketsModel=new TicketsModel();
//        ticketsModel.setTicketsName("What is the speed of Wifi?");
//        ticketsModel.setTicketsAuthorName("Author - John Doe");
//        ticketsModel.setTicketsDate("29 Dec 2018");
//        ticketsModel.setTicketsDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit sodales.");
//        ticketsModel.setNavigationIcon(R.drawable.ic_navigate_next_black_24dp);
//        ticketsModelsList.add(ticketsModel);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SupportLandingFragment supportLandingFragment = new SupportLandingFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.SuppotPlaceHolder, supportLandingFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        exploreFilterIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterPopup.setVisibility(View.VISIBLE);
            }
        });
        elevators.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                articlesDetails.setText("15 articles found in Elevator category.");
                showingCategories.setVisibility(View.GONE);
                clearFilter.setVisibility(View.VISIBLE);
                elevatorLayout.setVisibility(View.VISIBLE);
                showing.setText("Showing 03 of 15");
                dialogClearFilter.setTextColor(Color.parseColor("#32BDD2"));
                elevators.setTypeface(Typeface.defaultFromStyle((Typeface.BOLD)));
                filterPopup.setVisibility(View.GONE);
            }
        });
        clearFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showingCategories.setVisibility(View.VISIBLE);
                clearFilter.setVisibility(View.GONE);
                elevatorLayout.setVisibility(View.GONE);
                articlesDetails.setText(getText(R.string.filer_description));
                dialogClearFilter.setTextColor(Color.parseColor("#B5B5B5"));
                elevators.setTypeface(Typeface.defaultFromStyle((Typeface.NORMAL)));
                showing.setText(getText(R.string.show));
                filterPopup.setVisibility(View.GONE);
            }
        });
        dialogClearFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterPopup.setVisibility(View.GONE);
                showingCategories.setVisibility(View.VISIBLE);
                clearFilter.setVisibility(View.GONE);
                elevatorLayout.setVisibility(View.GONE);
                articlesDetails.setText(getText(R.string.filer_description));
                showing.setText(getText(R.string.show));
                dialogClearFilter.setTextColor(Color.parseColor("#B5B5B5"));
                elevators.setTypeface(Typeface.defaultFromStyle((Typeface.NORMAL)));
            }
        });

        filterPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterPopup.setVisibility(View.GONE);
            }
        });
//        exploreFilterIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Dialog dialog = new Dialog(getContext());
//                dialog.setContentView(R.layout.explore_filter_list);
//                WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                wmlp.gravity = Gravity.TOP | Gravity.LEFT;
//                wmlp.x = 50;   //x position
//                wmlp.y = 350;   //y position
//                TextView elevators = (TextView) dialog.findViewById(R.id.elevators);
//                elevators.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog.dismiss();
//                        articlesDetails.setText("15 articles found in Elevator category.");
//                        showingCategories.setVisibility(View.GONE);
//                        clearFilter.setVisibility(View.VISIBLE);
//                        elevatorLayout.setVisibility(View.VISIBLE);
//                        showing.setText("Showing 03 of 15");
//                        dialogClearFilter=dialog.findViewById(R.id.clearFilterId);
//                        dialogClearFilter.setTextColor(Color.parseColor("#32BDD2"));
//                        dialogClearFilter.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                showingCategories.setVisibility(View.VISIBLE);
//                                clearFilter.setVisibility(View.GONE);
//                                elevatorLayout.setVisibility(View.GONE);
//                                articlesDetails.setText(getText(R.string.filer_description));
//                                showing.setText(getText(R.string.show));
//                            }
//                        });
//
//                        clearFilter.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                showingCategories.setVisibility(View.VISIBLE);
//                                clearFilter.setVisibility(View.GONE);
//                                elevatorLayout.setVisibility(View.GONE);
//                                articlesDetails.setText(getText(R.string.filer_description));
//                                showing.setText(getText(R.string.show));
//                            }
//                        });
//                    }
//                });
//                dialog.show();
//            }
//        });

        this.getKbList();

        return rootView;
    }

    @Override
    public void onItemClick(int position) {
        ArticlesDetailsFragment articlesDetailsFragment = new ArticlesDetailsFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.SuppotPlaceHolder, articlesDetailsFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        Bundle bundle = new Bundle();
        bundle.putString("kbId", kbArrayList.get(position).getId());
        articlesDetailsFragment.setArguments(bundle);
    }

    private void getKbList() {
        try {
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
            KBService kbService = retrofit.create(KBService.class);
            Call<KBResponse> kbResponseCall = kbService.getAll();
            kbResponseCall.enqueue(new Callback<KBResponse>() {
                @Override
                public void onResponse(Call<KBResponse> call, Response<KBResponse> response) {
                    try {
                        progressDialog.dismiss();
                        KBResponse kbResponse = response.body();
                        if (kbResponse.getSuccess()) {
                            exploreTicketsAdapter.update(kbResponse.getKbList());
                            kbArrayList = new ArrayList<>();
                            kbArrayList.addAll(kbResponse.getKbList());
                        } else {
                            showDialog(false,"There was a problem fetching articles! Please try after sometime");
                        }
                    } catch (Exception ex) {
                        showDialog(false,"There was a problem fetching articles! Please try after sometime");
                    }
                }

                @Override
                public void onFailure(Call<KBResponse> call, Throwable t) {
                    progressDialog.dismiss();
                    showDialog(false,"There was a problem fetching articles! Please try after sometime");
                }
            });
        } catch (Exception ex) {
            showDialog(false,"There was a problem fetching articles! Please try after sometime");
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

