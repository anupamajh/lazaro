package com.carmel.guestjini.People;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.carmel.guestjini.People.PeopleLandingFragment;
import com.carmel.guestjini.R;


public class PeopleDetailsFragment extends Fragment {
    ImageView backButton,favouriteUnselectedIcon;
    LinearLayout unlikeIconLinearLayout;
    TextView addFafouriteText,profileName,genderName;
    private String profile_name,profile_gender;
    private ConstraintLayout scrollViewLayout,commonInterestsSubLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_people_details, container, false);
        unlikeIconLinearLayout=rootView.findViewById(R.id.addToFavouriteLinearLayout);
        backButton =rootView.findViewById(R.id.backButton);
        favouriteUnselectedIcon=rootView.findViewById(R.id.favouriteUnselectedIcon);
        addFafouriteText=rootView.findViewById(R.id.addToFavouriteTitle);
        profileName=rootView.findViewById(R.id.profileName);
        genderName=rootView.findViewById(R.id.genderName);
        scrollViewLayout=rootView.findViewById(R.id.scrollViewLayout);
        commonInterestsSubLayout=rootView.findViewById(R.id.commonInterestsSubLayout);

        final Bundle bundle = this.getArguments();
        if (bundle != null) {
            profile_name  = bundle.getString("profile name");
            profile_gender  = bundle.getString("profile gender");

            profileName.setText(profile_name);
            genderName.setText(profile_gender);
        }

        scrollViewLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commonInterestsSubLayout.setVisibility(View.GONE);
            }
        });
        unlikeIconLinearLayout.setOnClickListener(new View.OnClickListener() {
            private boolean flag=true;
            @Override
            public void onClick(View v) {
                if(flag){
                    flag=false;
                    favouriteUnselectedIcon.setImageResource(R.drawable.like_button_xhdpi);
                    addFafouriteText.setText("Remove from favourites");

                }else {
                    flag = true;
                    favouriteUnselectedIcon.setImageResource(R.drawable.unlike_button_xhdpi);
                    addFafouriteText.setText("Add to favourites");
                }
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PeopleLandingFragment peopleLandingFragment=new PeopleLandingFragment();
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.peoplePlaceHolder,peopleLandingFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        return rootView;
    }

}
