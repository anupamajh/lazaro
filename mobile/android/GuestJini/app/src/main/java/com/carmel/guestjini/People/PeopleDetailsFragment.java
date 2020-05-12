package com.carmel.guestjini.People;


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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.carmel.guestjini.Common.EndPoints;
import com.carmel.guestjini.Components.OkHttpClientInstance;
import com.carmel.guestjini.Models.User.InterestMap;
import com.carmel.guestjini.Models.User.PeopleResponse;
import com.carmel.guestjini.Models.User.UserInterests;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Services.Authentication.AuthService;
import com.carmel.guestjini.Services.Authentication.AuthServiceHolder;
import com.carmel.guestjini.Services.User.PeopleService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PeopleDetailsFragment extends Fragment {
    ImageView backButton, favouriteUnselectedIcon;
    LinearLayout unlikeIconLinearLayout;
    TextView addFafouriteText, profileName, genderName, mobileNo, emailId, commonInterestTitle, otherInterestsTitle;
    private String profile_name, profile_gender;
    private ConstraintLayout scrollViewLayout, commonInterestsSubLayout, otherInterestsSubLayout;

    private int isFavourite = 0;
    private String personId = "";

    AlertDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_people_details, container, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false); // if you want user to wait for some process to finish,
        builder.setView(R.layout.layout_loading_dialog);
        progressDialog = builder.create();
        unlikeIconLinearLayout = rootView.findViewById(R.id.addToFavouriteLinearLayout);
        backButton = rootView.findViewById(R.id.backButton);
        favouriteUnselectedIcon = rootView.findViewById(R.id.favouriteUnselectedIcon);
        addFafouriteText = rootView.findViewById(R.id.addToFavouriteTitle);
        profileName = rootView.findViewById(R.id.profileName);
        genderName = rootView.findViewById(R.id.genderName);
        scrollViewLayout = rootView.findViewById(R.id.scrollViewLayout);
        commonInterestsSubLayout = rootView.findViewById(R.id.commonInterestsSubLayout);
        otherInterestsSubLayout = rootView.findViewById(R.id.otherInterestsSubLayout);
        commonInterestTitle = rootView.findViewById(R.id.commonInterestsTitle);
        otherInterestsTitle = rootView.findViewById(R.id.otherInterests);
        mobileNo = rootView.findViewById(R.id.mobileNo);
        emailId = rootView.findViewById(R.id.emailId);
        final Bundle bundle = this.getArguments();
        if (bundle != null) {
            personId = bundle.getString("personId");
            getPersonDetails(personId);

        }

        scrollViewLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // commonInterestsSubLayout.setVisibility(View.GONE);
            }
        });
        unlikeIconLinearLayout.setOnClickListener(new View.OnClickListener() {
            private boolean flag = true;

            @Override
            public void onClick(View v) {
                if (isFavourite == 0) {
                    isFavourite = 1;
                    flag = false;
                    favouriteUnselectedIcon.setImageResource(R.drawable.like_button_xhdpi);
                    addFafouriteText.setText("Remove from favourites");

                } else {
                    isFavourite = 0;
                    flag = true;
                    favouriteUnselectedIcon.setImageResource(R.drawable.unlike_button_xhdpi);
                    addFafouriteText.setText("Add to favourites");
                }
                updateIsFavourite(isFavourite);
            }
        });
        backButton.setOnClickListener(v -> {
            PeopleLandingFragment peopleLandingFragment = new PeopleLandingFragment();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.CommunityPlaceHolder, peopleLandingFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        });

        return rootView;
    }

    private void getPersonDetails(String personId) {
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
            Map<String, String> requestData = new HashMap<>();
            requestData.put("userId", personId);

            PeopleService peopleService = retrofit.create(PeopleService.class);
            Call<PeopleResponse> peopleResponseCall = peopleService.getPersonDetails(requestData);
            peopleResponseCall.enqueue(new Callback<PeopleResponse>() {
                @Override
                public void onResponse(Call<PeopleResponse> call, Response<PeopleResponse> response) {
                    progressDialog.dismiss();
                    try {
                        PeopleResponse peopleResponse = response.body();
                        if (peopleResponse.isSuccess()) {
                            String genderText = "Male";
                            if (peopleResponse.getMyUserInfo().getGender() == 1) {
                                genderText = "Male";
                            } else if (peopleResponse.getMyUserInfo().getGender() == 2) {
                                genderText = "Female";
                            }
                            profileName.setText(peopleResponse.getOthersAddressBook().getDisplayName());
                            genderName.setText(genderText);
                            mobileNo.setText(peopleResponse.getOthersAddressBook().getPhone1());
                            emailId.setText(peopleResponse.getOthersAddressBook().getEmail1());
                            Integer interestTitleId = null;
                            if(peopleResponse.getIsFavourite() == 0){
                                favouriteUnselectedIcon.setImageResource(R.drawable.unlike_button_xhdpi);
                                addFafouriteText.setText("Add to favourites");
                            }else{
                                favouriteUnselectedIcon.setImageResource(R.drawable.like_button_xhdpi);
                                addFafouriteText.setText("Remove from favourites");
                            }
                            isFavourite = peopleResponse.getIsFavourite();
                            for (InterestMap interestMap : peopleResponse.getMyInterestMap()) {
                                interestTitleId = addInterestHeading(
                                        interestMap.getInterestCategory().getName(),
                                        interestTitleId,
                                        interestMap);
                            }
                            Integer otherInterestTitleId = null;
                            for (InterestMap interestMap : peopleResponse.getOtherInterestMap()) {
                                otherInterestTitleId = addOtherInterestHeading(
                                        interestMap.getInterestCategory().getName(),
                                        otherInterestTitleId,
                                        interestMap);
                            }

                        } else {
                            progressDialog.dismiss();
                            showDialog(false, "There was problem fetching person details! Please Try after sometime");
                        }

                    } catch (Exception ex) {
                        progressDialog.dismiss();
                        showDialog(false, "There was problem fetching person details! Please Try after sometime");
                    }
                }

                @Override
                public void onFailure(Call<PeopleResponse> call, Throwable t) {
                    progressDialog.dismiss();
                    showDialog(false, "There was problem fetching person details! Please Try after sometime");
                }
            });
        } catch (Exception ex) {
            progressDialog.dismiss();
            showDialog(false, "There was problem fetching person details! Please Try after sometime");
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

    private Integer addInterestHeading(String interestCategoryName, Integer interestTitleId, InterestMap interestMap) {
       // Typeface typeface = getResources().getFont(R.font.roboto_bold);
        TextView previousView = commonInterestTitle;
        if (interestTitleId != null) {
            previousView = commonInterestsSubLayout.findViewById(interestTitleId);
        }
        TextView interestHeading = new TextView(getContext());
        ConstraintSet constraintSet = new ConstraintSet();
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(24, 38, 0, 0);
        interestHeading.setTextColor(ContextCompat.getColor(getContext(), R.color.colorSummerSky));
        interestHeading.setTextSize(16);
        interestHeading.setText(interestCategoryName);
        interestHeading.setId(View.generateViewId());
        interestHeading.setVisibility(View.VISIBLE);
        //interestHeading.setTypeface(typeface);
        commonInterestsSubLayout.addView(interestHeading, layoutParams);
        constraintSet.clone(commonInterestsSubLayout);
        constraintSet.connect(interestHeading.getId(), ConstraintSet.START, commonInterestTitle.getId(), ConstraintSet.START);
        constraintSet.connect(interestHeading.getId(), ConstraintSet.TOP, previousView.getId(), ConstraintSet.BOTTOM);
        constraintSet.applyTo(commonInterestsSubLayout);
        Integer interestViewId = interestHeading.getId();
        for(UserInterests userInterests: interestMap.getInterestList()){
            interestViewId = addInterests(userInterests, interestViewId);
        }
        TextView deviderLine = new TextView(getContext());
        layoutParams = new ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(24, 8, 0, 0);
        deviderLine.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.divider_line));
        deviderLine.setId(View.generateViewId());
        commonInterestsSubLayout.addView(deviderLine, layoutParams);
        constraintSet = new ConstraintSet();
        constraintSet.clone(commonInterestsSubLayout);
        constraintSet.connect(deviderLine.getId(), ConstraintSet.START, commonInterestTitle.getId(), ConstraintSet.START);
        constraintSet.connect(deviderLine.getId(), ConstraintSet.TOP, interestViewId, ConstraintSet.BOTTOM);
        constraintSet.applyTo(commonInterestsSubLayout);
        return deviderLine.getId();
    }

    private Integer addInterests(UserInterests userInterests, Integer interestViewId){
        TextView previousView = commonInterestTitle;
        if(interestViewId != null){
            previousView = commonInterestsSubLayout.findViewById(interestViewId);
        }
        TextView interestView = new TextView(getContext());
        ConstraintSet constraintSet = new ConstraintSet();
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(24, 16, 0, 0);
        interestView.setTextColor(ContextCompat.getColor(getContext(), R.color.colorSuvaGray));
        interestView.setTextSize(14);
        interestView.setText(userInterests.getInterestName());
        interestView.setId(View.generateViewId());
        interestView.setVisibility(View.VISIBLE);
        //interestHeading.setTypeface(typeface);
        commonInterestsSubLayout.addView(interestView, layoutParams);
        constraintSet.clone(commonInterestsSubLayout);
        constraintSet.connect(interestView.getId(), ConstraintSet.START, commonInterestTitle.getId(), ConstraintSet.START);
        constraintSet.connect(interestView.getId(), ConstraintSet.TOP, previousView.getId(), ConstraintSet.BOTTOM);
        constraintSet.applyTo(commonInterestsSubLayout);
        return interestView.getId();
    }

    private Integer addOtherInterestHeading(String interestCategoryName, Integer interestTitleId, InterestMap interestMap) {
        // Typeface typeface = getResources().getFont(R.font.roboto_bold);
        TextView previousView = otherInterestsTitle;
        if (interestTitleId != null) {
            previousView = otherInterestsSubLayout.findViewById(interestTitleId);
        }
        TextView interestHeading = new TextView(getContext());
        ConstraintSet constraintSet = new ConstraintSet();
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(24, 38, 0, 0);
        interestHeading.setTextColor(ContextCompat.getColor(getContext(), R.color.otherInterestTitle));
        interestHeading.setTextSize(16);
        interestHeading.setText(interestCategoryName);
        interestHeading.setId(View.generateViewId());
        interestHeading.setVisibility(View.VISIBLE);
        //interestHeading.setTypeface(typeface);
        otherInterestsSubLayout.addView(interestHeading, layoutParams);
        constraintSet.clone(otherInterestsSubLayout);
        constraintSet.connect(interestHeading.getId(), ConstraintSet.START, otherInterestsTitle.getId(), ConstraintSet.START);
        constraintSet.connect(interestHeading.getId(), ConstraintSet.TOP, previousView.getId(), ConstraintSet.BOTTOM);
        constraintSet.applyTo(otherInterestsSubLayout);
        Integer interestViewId = interestHeading.getId();
        for(UserInterests userInterests: interestMap.getInterestList()){
            interestViewId = addOtherInterests(userInterests, interestViewId);
        }
        TextView deviderLine = new TextView(getContext());
        layoutParams = new ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(24, 8, 0, 0);
        deviderLine.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.divider_line));
        deviderLine.setId(View.generateViewId());
        otherInterestsSubLayout.addView(deviderLine, layoutParams);
        constraintSet = new ConstraintSet();
        constraintSet.clone(otherInterestsSubLayout);
        constraintSet.connect(deviderLine.getId(), ConstraintSet.START, otherInterestsTitle.getId(), ConstraintSet.START);
        constraintSet.connect(deviderLine.getId(), ConstraintSet.TOP, interestViewId, ConstraintSet.BOTTOM);
        constraintSet.applyTo(otherInterestsSubLayout);
        return deviderLine.getId();
    }

    private Integer addOtherInterests(UserInterests userInterests, Integer interestViewId){
        TextView previousView = otherInterestsTitle;
        if(interestViewId != null){
            previousView = otherInterestsSubLayout.findViewById(interestViewId);
        }
        TextView interestView = new TextView(getContext());
        ConstraintSet constraintSet = new ConstraintSet();
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(24, 16, 0, 0);
        interestView.setTextColor(ContextCompat.getColor(getContext(), R.color.otherInterestName));
        interestView.setTextSize(14);
        interestView.setText(userInterests.getInterestName());
        interestView.setId(View.generateViewId());
        interestView.setVisibility(View.VISIBLE);
        //interestHeading.setTypeface(typeface);
        otherInterestsSubLayout.addView(interestView, layoutParams);
        constraintSet.clone(otherInterestsSubLayout);
        constraintSet.connect(interestView.getId(), ConstraintSet.START, otherInterestsTitle.getId(), ConstraintSet.START);
        constraintSet.connect(interestView.getId(), ConstraintSet.TOP, previousView.getId(), ConstraintSet.BOTTOM);
        constraintSet.applyTo(otherInterestsSubLayout);
        return interestView.getId();
    }

    private void updateIsFavourite(Integer isFavourite){
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
            Map<String, String> requestData = new HashMap<>();
            requestData.put("userId", personId);
            requestData.put("isFavourite", String.valueOf(isFavourite));

            PeopleService peopleService = retrofit.create(PeopleService.class);
            Call<PeopleResponse> peopleResponseCall = peopleService.addFavourite(requestData);
            peopleResponseCall.enqueue(new Callback<PeopleResponse>() {
                @Override
                public void onResponse(Call<PeopleResponse> call, Response<PeopleResponse> response) {
                    progressDialog.dismiss();
                    try {
                        PeopleResponse peopleResponse = response.body();
                        if (peopleResponse.isSuccess()) {

                        } else {
                            progressDialog.dismiss();
                            showDialog(false, "There was problem fetching person details! Please Try after sometime");
                        }

                    } catch (Exception ex) {
                        progressDialog.dismiss();
                        showDialog(false, "There was problem fetching person details! Please Try after sometime");
                    }
                }

                @Override
                public void onFailure(Call<PeopleResponse> call, Throwable t) {
                    progressDialog.dismiss();
                    showDialog(false, "There was problem fetching person details! Please Try after sometime");
                }
            });
        } catch (Exception ex) {
            progressDialog.dismiss();
            showDialog(false, "There was problem fetching person details! Please Try after sometime");
        }
    }
}
