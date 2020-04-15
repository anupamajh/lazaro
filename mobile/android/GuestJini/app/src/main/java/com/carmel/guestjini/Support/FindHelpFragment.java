package com.carmel.guestjini.Support;


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

import com.carmel.guestjini.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import Adapter.ExploreTicketsAdapter;
import Model.TicketsModel;

public class FindHelpFragment extends Fragment implements ExploreTicketsAdapter.OnItemClickListener {
    private ImageView burgerMenu;
    private DrawerLayout drawerLayout;
    private ImageView leftArrowMark;
    private RecyclerView findHelpRecyclerView;
    private ArrayList<TicketsModel> ticketsModelsList=new ArrayList<>();
    private ConstraintLayout findHelpSearchLayout,resultFoundLayout,noResultFoundLayout,popularSearchesLayout;
    private EditText search;
    private TextView popularSerachText;
    private RelativeLayout drawerExploreLayout;
    private MaterialButton createTicket;
    private FloatingActionButton gotoTicketsIcon;
    String popularSearch;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_find_help, container, false);
        burgerMenu=rootView.findViewById(R.id.burgerMenu);
        drawerLayout=rootView.findViewById(R.id.findHelpDrawerLayout);
        leftArrowMark=rootView.findViewById(R.id.leftArrowMark);
        findHelpSearchLayout=rootView.findViewById(R.id.findHelpSearchLayout);
        noResultFoundLayout=rootView.findViewById(R.id.noResultFoundLayout);
        search=rootView.findViewById(R.id.search);
        popularSerachText=rootView.findViewById(R.id.showing);
        popularSearchesLayout=rootView.findViewById(R.id.popularSearches);
        resultFoundLayout=rootView.findViewById(R.id.resultFoundLayout);
        drawerExploreLayout=rootView.findViewById(R.id.exploreSubLayout);
        gotoTicketsIcon=rootView.findViewById(R.id.gotoTicketsIcon);
        findHelpRecyclerView=rootView.findViewById(R.id.findHelpRecyclerView);
        createTicket=rootView.findViewById(R.id.createTicket);
        findHelpRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        findHelpRecyclerView.setLayoutManager(linearLayoutManager);
        ExploreTicketsAdapter exploreTicketsAdapter=new ExploreTicketsAdapter(rootView.getContext(),ticketsModelsList,this);
        findHelpRecyclerView.setAdapter(exploreTicketsAdapter);
        findHelpRecyclerView.setHasFixedSize(true);

        TicketsModel ticketsModel=new TicketsModel();
        ticketsModel.setTicketsName("Elevator is not working?");
        ticketsModel.setTicketsAuthorName("Author - John Doe");
        ticketsModel.setTicketsDate("10 Jan 2019");
        ticketsModel.setTicketsDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit sodales.");
        ticketsModel.setNavigationIcon(R.drawable.ic_navigate_next_black_24dp);
        ticketsModelsList.add(ticketsModel);


        ticketsModel=new TicketsModel();
        ticketsModel.setTicketsName("Coffee machine is not working?");
        ticketsModel.setTicketsAuthorName("Author - Jaret Quartz");
        ticketsModel.setTicketsDate("03 Jan 2019");
        ticketsModel.setTicketsDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit sodales.");
        ticketsModel.setNavigationIcon(R.drawable.ic_navigate_next_black_24dp);
        ticketsModelsList.add(ticketsModel);


        ticketsModel=new TicketsModel();
        ticketsModel.setTicketsName("Where are tha elevators");
        ticketsModel.setTicketsAuthorName("Author - John Doe");
        ticketsModel.setTicketsDate("29 Dec 2018");
        ticketsModel.setTicketsDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit sodales.");
        ticketsModel.setNavigationIcon(R.drawable.ic_navigate_next_black_24dp);
        ticketsModelsList.add(ticketsModel);


        final Bundle bundle = this.getArguments();
        if (bundle != null) {
            popularSearch=bundle.getString("popular Search");
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
                SupportLandingFragment supportLandingFragment=new SupportLandingFragment();
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.SuppotPlaceHolder,supportLandingFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        drawerExploreLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExploreFragment exploreFragment=new ExploreFragment();
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.SuppotPlaceHolder,exploreFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        createTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewTicketFragment newTicketFragment=new NewTicketFragment();
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.SuppotPlaceHolder,newTicketFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        gotoTicketsIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyTicketsRecyclerViewFragment MyTicketsRecyclerViewFragment=new MyTicketsRecyclerViewFragment();
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.SuppotPlaceHolder,MyTicketsRecyclerViewFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return rootView;
    }

    @Override
    public void onItemClick(int position) {
        ticketsModelsList.get(position);
        ArticlesDetailsFragment articlesDetailsFragment=new ArticlesDetailsFragment();
        FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.SuppotPlaceHolder,articlesDetailsFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        Bundle bundle = new Bundle();
        bundle.putString("ticket_name", ticketsModelsList.get(position).getTicketsName());
        bundle.putString("ticket_author_name",ticketsModelsList.get(position).getTicketsAuthorName());
        bundle.putString("ticket_date",ticketsModelsList.get(position).getTicketsDate());
        articlesDetailsFragment.setArguments(bundle);
    }
}
