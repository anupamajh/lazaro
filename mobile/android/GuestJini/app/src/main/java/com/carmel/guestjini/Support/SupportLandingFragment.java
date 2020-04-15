package com.carmel.guestjini.Support;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.carmel.guestjini.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class SupportLandingFragment extends Fragment {

    private FloatingActionButton gotoTicketsIcon,exploreNavigationIcon;
    private MaterialButton createTicket;
    private ConstraintLayout searchLayout,popularSearches;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView=inflater.inflate(R.layout.fragment_support_landing, container, false);
        gotoTicketsIcon=rootView.findViewById(R.id.gotoTicketsIcon);
        exploreNavigationIcon=rootView.findViewById(R.id.exploreNavigationIcon);
        popularSearches=rootView.findViewById(R.id.popularSearches);
        createTicket=rootView.findViewById(R.id.createTicket);
        searchLayout=rootView.findViewById(R.id.searchLayout);


        searchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FindHelpFragment findHelpFragment=new FindHelpFragment();
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.SuppotPlaceHolder,findHelpFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        popularSearches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("popular Search", String.valueOf(popularSearches));
                FindHelpFragment findHelpFragment=new FindHelpFragment();
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.SuppotPlaceHolder,findHelpFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                findHelpFragment.setArguments(bundle);
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

        exploreNavigationIcon.setOnClickListener(new View.OnClickListener() {
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
        return rootView ;

    }

}
