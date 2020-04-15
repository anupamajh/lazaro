package com.carmel.guestjini.Profile;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.R;

import java.util.ArrayList;

import Adapter.ProfileInterestsRecyclerAdapter;
import Model.ProfileInterestsModel;


public class ProfileInterestsFragment extends Fragment implements ProfileInterestsRecyclerAdapter.OnItemClickListener {

    RecyclerView recyclerView;
    ArrayList<ProfileInterestsModel> profileInterestsList =new ArrayList<>();
    TextView viewMyInterestsText;
    ImageView backButton;
    ConstraintLayout myInterestsMainLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     final  View rootView= inflater.inflate(R.layout.fragment_profile_interests, container, false);
       recyclerView=rootView.findViewById(R.id.recyclerViewMyInterest1);
       viewMyInterestsText=rootView.findViewById(R.id.viewMyInterestsTilte);
        myInterestsMainLayout=rootView.findViewById(R.id.myInterestsMainLayout);
        backButton =rootView.findViewById(R.id.leftArrowMarkMyInterests);

//         demo purpose
       viewMyInterestsText.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               final Dialog dialog = new Dialog(getContext());
               dialog.setContentView(R.layout.selected_interest_list_dailog_box);
               dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
               WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();

               wmlp.gravity = Gravity.TOP | Gravity.RIGHT;
               wmlp.x = 50;   //x position
               wmlp.y = 510;   //y position

               ConstraintLayout constraintLayout=(ConstraintLayout) dialog.findViewById(R.id.selectedInterestListDailogBox);
                constraintLayout.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       dialog.dismiss();
                   }
               });
               dialog.show();
           }
       });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfileFragment profileFragment=new ProfileFragment();
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.add(R.id.profilePlaceHolder,profileFragment).commit();

            }
        });
        myInterestsMainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfileFragment profileFragment=new ProfileFragment();
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.add(R.id.profilePlaceHolder,profileFragment).commit();
                Toast toast=new Toast(getContext());
                ViewGroup viewGroup = rootView.findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.toast_layout, viewGroup, false);
                TextView text = (TextView) dialogView.findViewById(R.id.visibleToast);
                text.setText("NOT SAVED                                                            " +
                        " Lorem ipsum doler sit amet.");
                toast.setView(dialogView);
                toast.makeText(getActivity(),"", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.getAbsoluteGravity(0,0),0,520);
                toast.show();
            }
        });

        // demo purpose

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        ProfileInterestsRecyclerAdapter myInterests1RecyclerAdapter=new ProfileInterestsRecyclerAdapter(rootView.getContext(), profileInterestsList,this);
        recyclerView.setAdapter(myInterests1RecyclerAdapter);

        profileInterestsList.add(new ProfileInterestsModel(
                "Adventure - Outdoor",
                R.drawable.dropdown_icon_mdpi));

        profileInterestsList.add(new ProfileInterestsModel(
                "Adventure - Indoor (02)",
                R.drawable.dropdown_icon_mdpi));

        profileInterestsList.add(new ProfileInterestsModel(
                "Fitness (02)",
                R.drawable.dropdown_icon_mdpi));

        profileInterestsList.add(new ProfileInterestsModel(
                "Photography (05)",
                R.drawable.dropdown_icon_mdpi));

        profileInterestsList.add(new ProfileInterestsModel(
                "Writing (05)",
                R.drawable.dropdown_icon_mdpi));

        profileInterestsList.add(new ProfileInterestsModel(
                "Film (05)",
                R.drawable.dropdown_icon_mdpi));

        profileInterestsList.add(new ProfileInterestsModel(
                "Reading",
                R.drawable.dropdown_icon_mdpi));

        profileInterestsList.add(new ProfileInterestsModel(
                "Dance (05)",
                R.drawable.dropdown_icon_mdpi));

        profileInterestsList.add(new ProfileInterestsModel(
                "Craft",
                R.drawable.dropdown_icon_mdpi));

        profileInterestsList.add(new ProfileInterestsModel(
                "Career and Business (05)",
                R.drawable.dropdown_icon_mdpi));

         return rootView;
     }

    @Override
    public void onItemClick(int position) {

    }
}
