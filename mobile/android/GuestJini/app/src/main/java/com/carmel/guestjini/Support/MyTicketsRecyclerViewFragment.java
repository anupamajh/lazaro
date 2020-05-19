package com.carmel.guestjini.Support;


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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Common.EndPoints;
import com.carmel.guestjini.Components.OkHttpClientInstance;
import com.carmel.guestjini.Models.Ticket.Ticket;
import com.carmel.guestjini.Models.Ticket.TicketResponse;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Services.Authentication.AuthService;
import com.carmel.guestjini.Services.Authentication.AuthServiceHolder;
import com.carmel.guestjini.Services.Ticket.TicketService;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Adapter.TicketAdapter;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MyTicketsRecyclerViewFragment extends Fragment implements TicketAdapter.OnItemClickListener {

    RecyclerView ticketsRecyclerView;
    DrawerLayout drawerLayout;
    MaterialButton ticketsFilterIcon, ascendingButton, descendingButton;
    FloatingActionButton addIcon;
    ImageView backArrow;
    Spinner spinner;
    MaterialCheckBox openCheckBox;
    ConstraintLayout clearAllLayout, ascendingLayout, todayLayout, yesterdayLayout;
    TextView ascending, clearAll, clearDescription;
    LinearLayout openLayout;
    private EditText txtSearch;
    private ImageView searchIcon;
    public ArrayList<Ticket> tickets = new ArrayList<>();
    AlertDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_my_tickets_recycler_view, container, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false); // if you want user to wait for some process to finish,
        builder.setView(R.layout.layout_loading_dialog);
        progressDialog = builder.create();
        ticketsRecyclerView = rootView.findViewById(R.id.myTicketsRecyclerView);
        drawerLayout = rootView.findViewById(R.id.drawerLayout);
        ticketsFilterIcon = rootView.findViewById(R.id.filterIcon);
        addIcon = rootView.findViewById(R.id.addIcon);
        todayLayout = rootView.findViewById(R.id.todayLayout);
        yesterdayLayout = rootView.findViewById(R.id.yesterdayLayout);
        backArrow = rootView.findViewById(R.id.leftArrowMark);
        openCheckBox = rootView.findViewById(R.id.openCheckBox);
        clearAllLayout = rootView.findViewById(R.id.clearAllLayout);
        ascendingButton = rootView.findViewById(R.id.ascendingButton);
        descendingButton = rootView.findViewById(R.id.descendingButton);
        ascendingLayout = rootView.findViewById(R.id.ascendingLayout);
        ascending = rootView.findViewById(R.id.ascending);
        clearAll = rootView.findViewById(R.id.clearAll);
        clearDescription = rootView.findViewById(R.id.clearDescription);
        openLayout = rootView.findViewById(R.id.openLayout);
        txtSearch = rootView.findViewById(R.id.search);
        searchIcon = rootView.findViewById(R.id.searchIcon);



//        spinner=rootView.findViewById(R.id.selectDateSpinner);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
//                R.array.planets_array, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//// Apply the adapter to the spinner
//        spinner.setAdapter(adapter);
        todayLayout.setOnClickListener(new View.OnClickListener() {
            private boolean flag = true;

            @Override
            public void onClick(View v) {
                yesterdayLayout.setVisibility(View.VISIBLE);
                if (flag) {
                    flag = false;
                    yesterdayLayout.setVisibility(View.VISIBLE);
                } else {
                    flag = true;
                    yesterdayLayout.setVisibility(View.GONE);
                }
            }
        });

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SupportLandingFragment supportLandingFragment = new SupportLandingFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.SuppotPlaceHolder, supportLandingFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        addIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewTicketFragment newTicketFragment = new NewTicketFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.SuppotPlaceHolder, newTicketFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        ticketsFilterIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        progressDialog.show();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        ticketsRecyclerView.setLayoutManager(linearLayoutManager);
        ticketsRecyclerView.setHasFixedSize(true);
        final TicketAdapter ticketAdapter = new TicketAdapter(tickets, this);
        ticketAdapter.setHasStableIds(true);
        ticketsRecyclerView.setAdapter(ticketAdapter);
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

        TicketService ticketService = retrofit.create(TicketService.class);

        Call<TicketResponse> myTicketsServiceCall =
                ticketService.getAll();

        myTicketsServiceCall.enqueue(new Callback<TicketResponse>() {
            @Override
            public void onResponse(Call<TicketResponse> call, Response<TicketResponse> response) {
                progressDialog.dismiss();
                boolean hasError = true;
                TicketResponse ticketResponse = response.body();
                if (ticketResponse.isSuccess()) {
                    tickets = new ArrayList<>();
                    tickets.addAll(ticketResponse.getTaskTicketList());
                    ticketAdapter.updateData(ticketResponse.getTaskTicketList());
                } else {
                    showDialog(false,"There was a problem fetching tickets! Please try after sometime");
                }
            }

            @Override
            public void onFailure(Call<TicketResponse> call, Throwable t) {
                progressDialog.dismiss();
                showDialog(false,"There was a problem fetching tickets! Please try after sometime");
            }
        });

        openCheckBox.setOnClickListener(new View.OnClickListener() {
            private boolean flag = true;

            @Override
            public void onClick(View v) {
                if (flag) {
                    flag = false;
                    clearAllLayout.setBackgroundTintList(ColorStateList.valueOf(Color
                            .parseColor("#32BDD2")));
                    clearAll.setTextColor(Color.parseColor("#FFFFFF"));
                    clearDescription.setTextColor(Color.parseColor("#FFFFFF"));
                    openLayout.setVisibility(View.VISIBLE);

                } else {
                    flag = true;
                    clearAllLayout.setBackgroundTintList(ColorStateList.valueOf(Color
                            .parseColor("#E6E6E6")));
                    clearAll.setTextColor(Color.parseColor("#B5B5B5"));
                    clearDescription.setTextColor(Color.parseColor("#909090"));
                    openLayout.setVisibility(View.GONE);
                    ticketsRecyclerView.setAdapter(null);

                }
            }
        });
        openLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLayout.setVisibility(View.GONE);

            }
        });
        ascendingLayout.setOnClickListener(new View.OnClickListener() {
            private boolean flag = true;

            @Override
            public void onClick(View v) {
                if (flag) {
                    flag = false;
                    descendingButton.setVisibility(View.VISIBLE);
                    ascendingButton.setVisibility(View.GONE);
                    ascending.setText(getText(R.string.descending));
                } else {
                    flag = true;
                    ascendingButton.setVisibility(View.VISIBLE);
                    descendingButton.setVisibility(View.GONE);
                    ascending.setText(getText(R.string.ascending));
                }
            }
        });

        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strSearch = txtSearch.getText().toString().trim();
                if(!strSearch.equals("")){
                    List<Ticket> collect = tickets.stream().filter(ticket -> (
                            (ticket.getTicketNarration() + " " + ticket.getTicketTitle())
                                    .toLowerCase().trim()
                                    .contains(strSearch.toLowerCase().trim()))
                    ).collect(Collectors.toList());
                    ticketAdapter.updateData(collect);

                }else{
                    ticketAdapter.updateData(tickets);
                }

            }
        });

        return rootView;

    }

    @Override
    public void onItemClick(int position) {
        tickets.get(position);
        MyTicketDetailsFragment myTicketDetailsFragment = new MyTicketDetailsFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.SuppotPlaceHolder, myTicketDetailsFragment);
        fragmentTransaction.commit();
        Bundle bundle = new Bundle();
        bundle.putString("ticketId", tickets.get(position).getId());
        myTicketDetailsFragment.setArguments(bundle);

    }

    @Override
    public void onClick(int position, String name) {
        tickets.get(position);
        MyTicketDetailsFragment myTicketDetailsFragment = new MyTicketDetailsFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.SuppotPlaceHolder, myTicketDetailsFragment);
        fragmentTransaction.commit();

//        Bundle bundle = new Bundle();
//        bundle.putString("ticket_status", myTicketsModelsList.get(position).getTicketsStatus());
//        bundle.putString("ticket_name", myTicketsModelsList.get(position).getTicketsName());
//        bundle.putString("ticket_status", myTicketsModelsList.get(position).getTicketsStatus());
//        bundle.putString("ticket_dateAndTime", myTicketsModelsList.get(position).getTicketsDateAndTime());
//        bundle.putString("ticket_value", myTicketsModelsList.get(position).getTicketsValue());
//        myTicketDetailsFragment.setArguments(bundle);

    }

    @Override
    public void onclickDraft(int position) {
        DraftViewFragment draftViewFragment = new DraftViewFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.SuppotPlaceHolder, draftViewFragment);
        fragmentTransaction.commit();

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
