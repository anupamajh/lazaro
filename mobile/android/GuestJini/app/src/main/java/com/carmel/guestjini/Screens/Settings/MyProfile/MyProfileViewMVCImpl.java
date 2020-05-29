package com.carmel.guestjini.Screens.Settings.MyProfile;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.carmel.guestjini.Common.AgeCalculator;
import com.carmel.guestjini.Common.DateUtil;
import com.carmel.guestjini.Networking.Users.UserInfo;
import com.carmel.guestjini.Networking.Users.UserPreference;
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
    private final ImageView btnGenderToggle;
    private final ImageView btnAgeToggle;
    private final ImageView btnMobileNumberToggle;
    private final ImageView btnPlaceOfOriginToggle;
    private final ImageView btnEmailToggle;
    private final CircleImageView imgProfile;
    private final DrawerLayout profileDrawerLayout;
    private final FloatingActionButton btnChangeProfileImage;

    private final RelativeLayout cameraIconLayout;
    private final RelativeLayout galleryIconLayout;
    private final FloatingActionButton cameraIcon;
    private final FloatingActionButton galleryIcon;


    private boolean isLoading = true;


    public MyProfileViewMVCImpl(
            LayoutInflater inflater,
            @Nullable ViewGroup parent
    ) {
        setRootView(inflater.inflate(R.layout.layout_settings_my_profile, parent, false));
        progressBar = findViewById(R.id.progress);
        ImageView btnBack = findViewById(R.id.btnBack);
        imgProfile = findViewById(R.id.imgProfile);
        btnChangeProfileImage = findViewById(R.id.btnChangeProfileImage);
        btnProfileToggle = findViewById(R.id.btnProfileToggle);
        txtFullName = findViewById(R.id.txtFullName);
        txtGender = findViewById(R.id.txtGender);
        btnGenderToggle = findViewById(R.id.btnGenderToggle);
        txtAge = findViewById(R.id.txtAge);
        btnAgeToggle = findViewById(R.id.btnAgeToggle);
        txtMobileNumber = findViewById(R.id.txtMobileNumber);
        btnMobileNumberToggle = findViewById(R.id.btnMobileNumberToggle);
        txtEmail = findViewById(R.id.txtEmail);
        btnEmailToggle = findViewById(R.id.btnEmailToggle);
        txtPlaceOfOrigin = findViewById(R.id.txtPlaceOfOrigin);
        btnPlaceOfOriginToggle = findViewById(R.id.btnPlaceOfOriginToggle);
        MaterialButton btnSetupInterest = findViewById(R.id.btnSetupInterest);
        profileDrawerLayout = findViewById(R.id.profileDrawerLayout);
        cameraIconLayout = findViewById(R.id.cameraLayout);
        galleryIconLayout = findViewById(R.id.galleryLayout);

        cameraIcon = findViewById(R.id.cameraIcon);
        galleryIcon = findViewById(R.id.galleryIcon);

        btnChangeProfileImage.setOnClickListener(view -> {
            profileDrawerLayout.openDrawer(GravityCompat.START);
        });

        btnBack.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onBackClicked();
            }
        });

        cameraIconLayout.setOnClickListener(view -> {
            profileDrawerLayout.closeDrawers();
            for (Listener listener : getListeners()) {
                listener.onCameraClicked();
            }
        });

        galleryIconLayout.setOnClickListener(view -> {
            profileDrawerLayout.closeDrawers();
            for (Listener listener : getListeners()) {
                listener.onGalleryClicked();
            }
        });

        cameraIcon.setOnClickListener(view -> {
            profileDrawerLayout.closeDrawers();
            for (Listener listener : getListeners()) {
                listener.onCameraClicked();
            }
        });

        galleryIcon.setOnClickListener(view -> {
            profileDrawerLayout.closeDrawers();
            for (Listener listener : getListeners()) {
                listener.onGalleryClicked();
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
                if (!isLoading) {
                    for (Listener listener : getListeners()) {
                        listener.onUserPreferenceChange(USER_PREFERENCE_SHOW_PROFILE_PIC, isVisible);
                    }
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
                    btnGenderToggle.setImageResource(R.drawable.toggle_icon_on_xxhdpi);
                    isVisible = true;
                } else {
                    flag = true;
                    btnGenderToggle.setImageResource(R.drawable.toggle_icon_off_xxhdpi);
                    isVisible = false;
                }
                if (!isLoading) {
                    for (Listener listener : getListeners()) {
                        listener.onUserPreferenceChange(USER_PREFERENCE_SHOW_GENDER, isVisible);
                    }
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
                    btnAgeToggle.setImageResource(R.drawable.toggle_icon_on_xxhdpi);
                    isVisible = true;
                } else {
                    flag = true;
                    btnAgeToggle.setImageResource(R.drawable.toggle_icon_off_xxhdpi);
                    isVisible = false;
                }
                if (!isLoading) {
                    for (Listener listener : getListeners()) {
                        listener.onUserPreferenceChange(USER_PREFERENCE_SHOW_AGE, isVisible);
                    }
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
                    btnMobileNumberToggle.setImageResource(R.drawable.toggle_icon_on_xxhdpi);
                    isVisible = true;
                } else {
                    flag = true;
                    btnMobileNumberToggle.setImageResource(R.drawable.toggle_icon_off_xxhdpi);
                    isVisible = false;
                }
                if (!isLoading) {
                    for (Listener listener : getListeners()) {
                        listener.onUserPreferenceChange(USER_PREFERENCE_SHOW_MOBILE_NUMBER, isVisible);
                    }
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
                    btnEmailToggle.setImageResource(R.drawable.toggle_icon_on_xxhdpi);
                    isVisible = true;
                } else {
                    flag = true;
                    btnEmailToggle.setImageResource(R.drawable.toggle_icon_off_xxhdpi);
                    isVisible = false;
                }
                if (!isLoading) {
                    for (Listener listener : getListeners()) {
                        listener.onUserPreferenceChange(USER_PREFERENCE_SHOW_EMAIL, isVisible);
                    }
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
                    btnPlaceOfOriginToggle.setImageResource(R.drawable.toggle_icon_on_xxhdpi);
                    isVisible = true;
                } else {
                    flag = true;
                    btnPlaceOfOriginToggle.setImageResource(R.drawable.toggle_icon_off_xxhdpi);
                    isVisible = false;
                }
                if (!isLoading) {
                    for (Listener listener : getListeners()) {
                        listener.onUserPreferenceChange(USER_PREFERENCE_SHOW_PLACE_OF_ORIGIN, isVisible);
                    }
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
        for (UserPreference userPreference : userInfo.getUserPreferences()) {
            switch (userPreference.getPreferenceType()) {
                case USER_PREFERENCE_SHOW_PROFILE_PIC: {
                    btnProfileToggle.callOnClick();
                }
                break;
                case USER_PREFERENCE_SHOW_GENDER: {
                    btnGenderToggle.callOnClick();
                }
                break;
                case USER_PREFERENCE_SHOW_AGE: {
                    btnAgeToggle.callOnClick();
                }
                break;
                case USER_PREFERENCE_SHOW_EMAIL: {
                    btnEmailToggle.callOnClick();
                }
                break;
                case USER_PREFERENCE_SHOW_PLACE_OF_ORIGIN: {
                    btnPlaceOfOriginToggle.callOnClick();
                }
                break;
                case USER_PREFERENCE_SHOW_MOBILE_NUMBER: {
                    btnMobileNumberToggle.callOnClick();
                }
                break;
            }
        }
        isLoading = false;

    }

    @Override
    public void enableProfileIcon(boolean enable) {
        btnChangeProfileImage.setEnabled(enable);
    }

    @Override
    public void setImageURI(Uri data) {
        imgProfile.setImageURI(data);
    }

    @Override
    public BitmapDrawable getProfilePicDrawable() {
        return (BitmapDrawable) imgProfile.getDrawable();
    }

    @Override
    public void bindProfilePic(String response) {
        byte[] decodedString = Base64.decode(response, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        imgProfile.setImageBitmap(decodedByte);

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
