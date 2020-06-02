package com.carmel.guestjini.Screens.Community.PersonDetail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;

import com.carmel.guestjini.Networking.Users.InterestMap;
import com.carmel.guestjini.Networking.Users.PeopleResponse;
import com.carmel.guestjini.Networking.Users.UserInterests;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

public class PersonDetailViewMVCImpl extends
        BaseObservableViewMvc<PersonDetailViewMVC.Listener>
        implements PersonDetailViewMVC {

    private final ProgressBar progressBar;
    private final ImageView btnAddFavourite;
    private final ImageView imgProfilePicture;
    private final TextView txtFullName;
    private final TextView commonInterestTitle;
    private final TextView otherInterestsTitle;
    private final TextView addToFavouriteTitle;
    private final TextView txtGender;
    private final TextView txtMobileNumber;
    private final TextView txtEmail;
    private final ConstraintLayout commonInterestsSubLayout;
    private final ConstraintLayout otherInterestsSubLayout;

    private PeopleResponse peopleResponse;

    public PersonDetailViewMVCImpl(
            LayoutInflater inflater,
            @Nullable ViewGroup parent
    ) {
        setRootView(inflater.inflate(R.layout.layout_community_person_detail, parent, false));
        progressBar = findViewById(R.id.progress);
        ImageView btnBack = findViewById(R.id.btnBack);
        btnAddFavourite = findViewById(R.id.btnAddFavourite);
        imgProfilePicture = findViewById(R.id.imgProfilePicture);
        txtFullName = findViewById(R.id.txtFullName);
        addToFavouriteTitle = findViewById(R.id.addToFavouriteTitle);
        txtGender = findViewById(R.id.txtGender);
        txtMobileNumber = findViewById(R.id.txtMobileNumber);
        txtEmail = findViewById(R.id.txtEmail);
        commonInterestsSubLayout = findViewById(R.id.commonInterestsSubLayout);
        otherInterestsSubLayout = findViewById(R.id.otherInterestsSubLayout);
        commonInterestTitle = findViewById(R.id.commonInterestsTitle);
        otherInterestsTitle = findViewById(R.id.otherInterests);

        btnBack.setOnClickListener(v -> {
            for (Listener listener : getListeners()) {
                listener.onBackClicked();
            }
        });

        btnAddFavourite.setOnClickListener(v -> {
            int isFavourite = 0;
            if (peopleResponse.getIsFavourite() != 0) {
                btnAddFavourite.setImageResource(R.drawable.favourite_select_icon);
                addToFavouriteTitle.setText("Add to favourites");
                isFavourite = 0;
            } else {
                btnAddFavourite.setImageResource(R.drawable.favourite_selected_icon);
                addToFavouriteTitle.setText("Remove from favourites");
                isFavourite = 1;
            }
            peopleResponse.setIsFavourite(isFavourite);
            for (Listener listener : getListeners()) {
                listener.onIsFavouriteClicked(peopleResponse.getOthersAddressBook().getUserId(), isFavourite);
            }
        });

    }

    @Override
    public void bindPerson(PeopleResponse peopleResponse) {
        this.peopleResponse = peopleResponse;
        String genderText = "Male";
        if (this.peopleResponse.getMyUserInfo().getGender() == 1) {
            genderText = "Male";
        } else if (this.peopleResponse.getMyUserInfo().getGender() == 2) {
            genderText = "Female";
        }
        txtFullName.setText(this.peopleResponse.getOthersAddressBook().getDisplayName());
        txtGender.setText(genderText);
        txtMobileNumber.setText(this.peopleResponse.getOthersAddressBook().getPhone1());
        txtEmail.setText(this.peopleResponse.getOthersAddressBook().getEmail1());
        if (this.peopleResponse.getIsFavourite() == 0) {
            btnAddFavourite.setImageResource(R.drawable.favourite_select_icon);
            addToFavouriteTitle.setText("Add to favourites");
        } else {
            btnAddFavourite.setImageResource(R.drawable.favourite_selected_icon);
            addToFavouriteTitle.setText("Remove from favourites");
        }

        Integer interestTitleId = null;
        Integer otherInterestTitleId = null;
        for (InterestMap interestMap : this.peopleResponse.getMyInterestMap()) {
            interestTitleId = addInterestHeading(
                    interestMap.getInterestCategory().getName(),
                    interestTitleId,
                    interestMap);
        }
        for (InterestMap interestMap : this.peopleResponse.getOtherInterestMap()) {
            otherInterestTitleId = addOtherInterestHeading(
                    interestMap.getInterestCategory().getName(),
                    otherInterestTitleId,
                    interestMap);
        }
    }

    @Override
    public void showProgressIndication() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressIndication() {
        progressBar.setVisibility(View.GONE);
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
        for (UserInterests userInterests : interestMap.getInterestList()) {
            interestViewId = addInterests(userInterests, interestViewId);
        }
        TextView deviderLine = new TextView(getContext());
        layoutParams = new ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(24, 8, 0, 0);
        deviderLine.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.divider_line));
        deviderLine.setId(View.generateViewId());
        commonInterestsSubLayout.addView(deviderLine, layoutParams);
        constraintSet = new ConstraintSet();
        constraintSet.clone(commonInterestsSubLayout);
        constraintSet.connect(deviderLine.getId(), ConstraintSet.START, commonInterestTitle.getId(), ConstraintSet.START);
        constraintSet.connect(deviderLine.getId(), ConstraintSet.TOP, interestViewId, ConstraintSet.BOTTOM);
        constraintSet.applyTo(commonInterestsSubLayout);
        return deviderLine.getId();
    }

    private Integer addInterests(UserInterests userInterests, Integer interestViewId) {
        TextView previousView = commonInterestTitle;
        if (interestViewId != null) {
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
        for (UserInterests userInterests : interestMap.getInterestList()) {
            interestViewId = addOtherInterests(userInterests, interestViewId);
        }
        TextView deviderLine = new TextView(getContext());
        layoutParams = new ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(24, 8, 0, 0);
        deviderLine.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.divider_line));
        deviderLine.setId(View.generateViewId());
        otherInterestsSubLayout.addView(deviderLine, layoutParams);
        constraintSet = new ConstraintSet();
        constraintSet.clone(otherInterestsSubLayout);
        constraintSet.connect(deviderLine.getId(), ConstraintSet.START, otherInterestsTitle.getId(), ConstraintSet.START);
        constraintSet.connect(deviderLine.getId(), ConstraintSet.TOP, interestViewId, ConstraintSet.BOTTOM);
        constraintSet.applyTo(otherInterestsSubLayout);
        return deviderLine.getId();
    }

    private Integer addOtherInterests(UserInterests userInterests, Integer interestViewId) {
        TextView previousView = otherInterestsTitle;
        if (interestViewId != null) {
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


}
