package com.carmel.guestjini.Screens.Settings.MyProfile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.carmel.guestjini.Common.AgeCalculator;
import com.carmel.guestjini.Common.DateUtil;
import com.carmel.guestjini.Networking.Users.UserInfo;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyProfileViewMVCImpl extends BaseObservableViewMvc<MyProfileViewMVC.Listener>
        implements MyProfileViewMVC {

    private static final int USER_PREFERENCE_SHOW_PROFILE_PIC = 1;
    private static final int USER_PREFERENCE_SHOW_GENDER = 2;
    private static final int USER_PREFERENCE_SHOW_AGE = 3;
    private static final int USER_PREFERENCE_SHOW_EMAIL = 4;
    private static final int USER_PREFERENCE_SHOW_PLACE_OF_ORIGIN = 5;
    private static final int USER_PREFERENCE_SHOW_MOBILE_NUMBER = 6;

    private final ProgressBar progressBar;
    private final ImageView btnProfileToggle;
    private final TextView txtFullName;
    private final TextView txtGender;
    private final TextView txtAge;
    private final TextView txtMobileNumber;
    private final TextView txtEmail;
    private final TextView txtPlaceOfOrigin;


    public MyProfileViewMVCImpl(
            LayoutInflater inflater,
            @Nullable ViewGroup parent
    ) {
        setRootView(inflater.inflate(R.layout.layout_settings_my_profile, parent, false));
        progressBar = findViewById(R.id.progress);
        ImageView btnBack = findViewById(R.id.btnBack);
        CircleImageView imgProfile = findViewById(R.id.imgProfile);
        FloatingActionButton btnChangeProfileImage = findViewById(R.id.btnChangeProfileImage);
        btnProfileToggle = findViewById(R.id.btnProfileToggle);
        txtFullName = findViewById(R.id.txtFullName);
        txtGender = findViewById(R.id.txtGender);
        ImageView btnGenderToggle = findViewById(R.id.btnGenderToggle);
        txtAge = findViewById(R.id.txtAge);
        ImageView btnAgeToggle = findViewById(R.id.btnAgeToggle);
        txtMobileNumber = findViewById(R.id.txtMobileNumber);
        ImageView btnMobileNumberToggle = findViewById(R.id.btnMobileNumberToggle);
        txtEmail = findViewById(R.id.txtEmail);
        ImageView btnEmailToggle = findViewById(R.id.btnEmailToggle);
        txtPlaceOfOrigin = findViewById(R.id.txtPlaceOfOrigin);
        ImageView btnPlaceOfOriginToggle = findViewById(R.id.btnPlaceOfOriginToggle);
        MaterialButton btnSetupInterest = findViewById(R.id.btnSetupInterest);
        btnBack.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onBackClicked();
            }
        });

        btnProfileToggle.setOnClickListener(new View.OnClickListener() {
            private boolean flag = true;
            boolean isVisible = false;

            @Override
            public void onClick(View view) {
                if (flag) {
                    flag = false;
                    btnProfileToggle.setImageResource(R.drawable.toggle_icon_on_xxhdpi);
                    isVisible = true;
                } else {
                    flag = true;
                    btnProfileToggle.setImageResource(R.drawable.toggle_icon_off_xxhdpi);
                    isVisible = false;
                }
                for (Listener listener : getListeners()) {
                    listener.onUserPreferenceChange(USER_PREFERENCE_SHOW_PROFILE_PIC, isVisible);
                }
            }
        });

        btnGenderToggle.setOnClickListener(new View.OnClickListener() {
            private boolean flag = true;
            boolean isVisible = false;

            @Override
            public void onClick(View view) {
                if (flag) {
                    flag = false;
                    btnProfileToggle.setImageResource(R.drawable.toggle_icon_on_xxhdpi);
                    isVisible = true;
                } else {
                    flag = true;
                    btnProfileToggle.setImageResource(R.drawable.toggle_icon_off_xxhdpi);
                    isVisible = false;
                }
                for (Listener listener : getListeners()) {
                    listener.onUserPreferenceChange(USER_PREFERENCE_SHOW_GENDER, isVisible);
                }
            }
        });

        btnAgeToggle.setOnClickListener(new View.OnClickListener() {
            private boolean flag = true;
            boolean isVisible = false;

            @Override
            public void onClick(View view) {
                if (flag) {
                    flag = false;
                    btnProfileToggle.setImageResource(R.drawable.toggle_icon_on_xxhdpi);
                    isVisible = true;
                } else {
                    flag = true;
                    btnProfileToggle.setImageResource(R.drawable.toggle_icon_off_xxhdpi);
                    isVisible = false;
                }
                for (Listener listener : getListeners()) {
                    listener.onUserPreferenceChange(USER_PREFERENCE_SHOW_AGE, isVisible);
                }
            }
        });

        btnMobileNumberToggle.setOnClickListener(new View.OnClickListener() {
            private boolean flag = true;
            boolean isVisible = false;

            @Override
            public void onClick(View view) {
                if (flag) {
                    flag = false;
                    btnProfileToggle.setImageResource(R.drawable.toggle_icon_on_xxhdpi);
                    isVisible = true;
                } else {
                    flag = true;
                    btnProfileToggle.setImageResource(R.drawable.toggle_icon_off_xxhdpi);
                    isVisible = false;
                }
                for (Listener listener : getListeners()) {
                    listener.onUserPreferenceChange(USER_PREFERENCE_SHOW_MOBILE_NUMBER, isVisible);
                }
            }
        });

        btnEmailToggle.setOnClickListener(new View.OnClickListener() {
            private boolean flag = true;
            boolean isVisible = false;

            @Override
            public void onClick(View view) {
                if (flag) {
                    flag = false;
                    btnProfileToggle.setImageResource(R.drawable.toggle_icon_on_xxhdpi);
                    isVisible = true;
                } else {
                    flag = true;
                    btnProfileToggle.setImageResource(R.drawable.toggle_icon_off_xxhdpi);
                    isVisible = false;
                }
                for (Listener listener : getListeners()) {
                    listener.onUserPreferenceChange(USER_PREFERENCE_SHOW_EMAIL, isVisible);
                }
            }
        });

        btnPlaceOfOriginToggle.setOnClickListener(new View.OnClickListener() {
            private boolean flag = true;
            boolean isVisible = false;

            @Override
            public void onClick(View view) {
                if (flag) {
                    flag = false;
                    btnProfileToggle.setImageResource(R.drawable.toggle_icon_on_xxhdpi);
                    isVisible = true;
                } else {
                    flag = true;
                    btnProfileToggle.setImageResource(R.drawable.toggle_icon_off_xxhdpi);
                    isVisible = false;
                }
                for (Listener listener : getListeners()) {
                    listener.onUserPreferenceChange(USER_PREFERENCE_SHOW_PLACE_OF_ORIGIN, isVisible);
                }
            }
        });
        btnSetupInterest.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onShowInterestsClicked();
            }
        });

    }

    @Override
    public void bindMyProfile(UserInfo userInfo) {
        String strGenderText = "";
        switch (userInfo.getGender()) {
            case 1:
                strGenderText = "Male";
                break;
            case 2:
                strGenderText = "Female";
                break;
            default:
                strGenderText = "Other";
                break;
        }
        txtFullName.setText(userInfo.getFullName());
        txtGender.setText(strGenderText);
        if (userInfo.getAddressBook().getDateOfBirth() != null) {
            Date birthDate = DateUtil.convertToDate(userInfo.getAddressBook().getDateOfBirth());
            if (birthDate != null) {
                Date today = new Date();
                LocalDate localeBirthDate = Instant.ofEpochMilli(birthDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate localeToday = Instant.ofEpochMilli(today.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
                txtAge.setText(String.valueOf(AgeCalculator.calculateAge(localeBirthDate, localeToday)));
            }
        }
        txtMobileNumber.setText(userInfo.getPhone());
        txtEmail.setText(userInfo.getAddressBook().getEmail1());
        txtPlaceOfOrigin.setText("");

    }

    @Override
    public void showProgressIndication() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressIndication() {
        progressBar.setVisibility(View.GONE);
    }
}
