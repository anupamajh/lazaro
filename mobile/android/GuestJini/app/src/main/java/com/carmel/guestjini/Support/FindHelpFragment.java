package com.carmel.guestjini.Support;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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

public class FindHelpFragment extends Fragment implements ExploreTicketsAdapter.OnItemClickListener {
    private ImageView burgerMenu;
    private DrawerLayout drawerLayout;
    private ImageView leftArrowMark;
    private RecyclerView findHelpRecyclerView;
    private ArrayList<KB> kbArrayList = new ArrayList<>();
    private ConstraintLayout findHelpSearchLayout, resultFoundLayout, noResultFoundLayout, popularSearchesLayout;
    private EditText search;
    private TextView popularSerachText;
    private RelativeLayout drawerExploreLayout;
    private MaterialButton createTicket;
    private FloatingActionButton gotoTicketsIcon;
    String popularSearch;
    private ExploreTicketsAdapter exploreTicketsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_find_help, container, false);
        burgerMenu = rootView.findViewById(R.id.burgerMenu);
        drawerLayout = rootView.findViewById(R.id.findHelpDrawerLayout);
        leftArrowMark = rootView.findViewById(R.id.leftArrowMark);
        findHelpSearchLayout = rootView.findViewById(R.id.findHelpSearchLayout);
        noResultFoundLayout = rootView.findViewById(R.id.noResultFoundLayout);
        search = rootView.findViewById(R.id.search);
        popularSerachText = rootView.findViewById(R.id.showing);
        popularSearchesLayout = rootView.findViewById(R.id.popularSearches);
        resultFoundLayout = rootView.findViewById(R.id.resultFoundLayout);
        drawerExploreLayout = rootView.findViewById(R.id.exploreSubLayout);
        gotoTicketsIcon = rootView.findViewById(R.id.gotoTicketsIcon);
        findHelpRecyclerView = rootView.findViewById(R.id.findHelpRecyclerView);
        createTicket = rootView.findViewById(R.id.createTicket);
        findHelpRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        findHelpRecyclerView.setLayoutManager(linearLayoutManager);
        exploreTicketsAdapter = new ExploreTicketsAdapter(rootView.getContext(), kbArrayList, this);
        findHelpRecyclerView.setAdapter(exploreTicketsAdapter);
        findHelpRecyclerView.setHasFixedSize(true);


        final Bundle bundle = this.getArguments();
        if (bundle != null) {
            popularSearch = bundle.getString("popular Search");
            resultFoundLayout.setVisibility(View.VISIBLE);
            noResultFoundLayout.setVisibility(View.GONE);
            search.setText("Search");
            popularSerachText.setText("Popular Searches");
        }

        findHelpSearchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultFoundLayout.setVisibility(View.VISIBLE);
                noResultFoundLayout.setVisibility(View.GONE);
                search.setText("Elevators");
            }
        });

        popularSearchesLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultFoundLayout.setVisibility(View.VISIBLE);
                noResultFoundLayout.setVisibility(View.GONE);
                search.setHint("Search");
                popularSerachText.setText("Popular Searches");
            }
        });

        burgerMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        leftArrowMark.setOnClickListener(new View.OnClickListener() {
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
        drawerExploreLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExploreFragment exploreFragment = new ExploreFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.SuppotPlaceHolder, exploreFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        createTicket.setOnClickListener(new View.OnClickListener() {
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
        gotoTicketsIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyTicketsRecyclerViewFragment MyTicketsRecyclerViewFragment = new MyTicketsRecyclerViewFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.SuppotPlaceHolder, MyTicketsRecyclerViewFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        this.getKbList();
        return rootView;
    }

    @Override
    public void onItemClick(int position) {
//        kbArrayList.get(position);
//        ArticlesDetailsFragment articlesDetailsFragment = new ArticlesDetailsFragment();
//        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.SuppotPlaceHolder, articlesDetailsFragment);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
//
//        Bundle bundle = new Bundle();
//        bundle.putString("ticket_name", ticketsModelsList.get(position).getTicketsName());
//        bundle.putString("ticket_author_name", ticketsModelsList.get(position).getTicketsAuthorName());
//        bundle.putString("ticket_date", ticketsModelsList.get(position).getTicketsDate());
//        articlesDetailsFragment.setArguments(bundle);
    }

    private void getKbList() {
        try {
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
                        KBResponse kbResponse = response.body();
                        if (kbResponse.getSuccess()) {
                            exploreTicketsAdapter.update(kbResponse.getKbList());
                        } else {
                            //TODO: Show appropriate alert
                        }
                    } catch (Exception ex) {
                        //TODO: Show appropriate alert
                    }
                }

                @Override
                public void onFailure(Call<KBResponse> call, Throwable t) {
                    //TODO: Show appropriate alert
                }
            });
        } catch (Exception ex) {
            //TODO: Show appropriate message
        }

    }
}
