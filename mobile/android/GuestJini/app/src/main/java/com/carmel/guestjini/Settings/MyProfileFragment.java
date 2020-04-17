package com.carmel.guestjini.Settings;


import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.carmel.guestjini.Common.AgeCalculator;
import com.carmel.guestjini.Common.DateUtil;
import com.carmel.guestjini.Common.EndPoints;
import com.carmel.guestjini.Components.OkHttpClientInstance;
import com.carmel.guestjini.Models.User.UserInfo;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Services.Authentication.AuthService;
import com.carmel.guestjini.Services.Authentication.AuthServiceHolder;
import com.carmel.guestjini.Services.User.UserService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MyProfileFragment extends Fragment {
    ImageView backIcon, profileToggleButton, gender, age, mobileNo, place, email;
    DrawerLayout profileDrawerLayout;
    FloatingActionButton profileEdit;
    CircleImageView profileIcon;

    private TextView txtMyName;
    private TextView txtGender;
    private TextView txtAge;
    private TextView txtPhoneNo;
    private TextView txtEmail;
    private TextView txtPlaceOfOrigin;
    private CircleImageView imgProfilePic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_my_profile, container, false);
        backIcon = rootView.findViewById(R.id.leftArrowMark);
        profileDrawerLayout = rootView.findViewById(R.id.profileDrawerLayout);
        profileEdit = rootView.findViewById(R.id.profileEdit);
        profileIcon = rootView.findViewById(R.id.profileIcon);
        txtMyName = rootView.findViewById(R.id.johnDeoText);
        txtGender = rootView.findViewById(R.id.maleText);
        txtAge = rootView.findViewById(R.id.AgeEditText);
        txtPhoneNo = rootView.findViewById(R.id.phoneno);
        txtEmail = rootView.findViewById(R.id.EmailText);
        txtPlaceOfOrigin = rootView.findViewById(R.id.txtPlaceOfOrigin);
        imgProfilePic = rootView.findViewById(R.id.profileIcon);
        profileEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        profileToggleButton = rootView.findViewById(R.id.profileToggleButton);
        gender = rootView.findViewById(R.id.genderToggleIcon);
        age = rootView.findViewById(R.id.ageToggleIcon);
        mobileNo = rootView.findViewById(R.id.mobileNoToggleIcon);
        place = rootView.findViewById(R.id.yourPlaceToggleIcon);
        email = rootView.findViewById(R.id.emailToggleIcon);


        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingsLandingFragment settingsLandingFragment = new SettingsLandingFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.SettingsPlaceHolder, settingsLandingFragment);
                fragmentTransaction.commit();
            }
        });

        profileToggleButton.setOnClickListener(new View.OnClickListener() {
            private boolean flag = true;

            @Override
            public void onClick(View v) {
                if (flag) {
                    flag = false;
                    profileToggleButton.setImageResource(R.drawable.toggle_icon_on_xxhdpi);
                } else {
                    flag = true;
                    profileToggleButton.setImageResource(R.drawable.toggle_icon_off_xxhdpi);
                }

            }
        });

        gender.setOnClickListener(new View.OnClickListener() {
            private boolean flag = true;

            @Override
            public void onClick(View v) {
                if (flag) {
                    flag = false;
                    gender.setImageResource(R.drawable.toggle_icon_on_xxhdpi);
                } else {
                    flag = true;
                    gender.setImageResource(R.drawable.toggle_icon_off_xxhdpi);
                }

            }
        });

        mobileNo.setOnClickListener(new View.OnClickListener() {
            private boolean flag = true;

            @Override
            public void onClick(View v) {
                if (flag) {
                    flag = false;
                    mobileNo.setImageResource(R.drawable.toggle_icon_off_xxhdpi);
                } else {
                    flag = true;
                    mobileNo.setImageResource(R.drawable.toggle_icon_on_xxhdpi);
                }

            }
        });

        age.setOnClickListener(new View.OnClickListener() {
            private boolean flag = true;

            @Override
            public void onClick(View v) {
                if (flag) {
                    flag = false;
                    age.setImageResource(R.drawable.toggle_icon_on_xxhdpi);
                } else {
                    flag = true;
                    age.setImageResource(R.drawable.toggle_icon_off_xxhdpi);
                }

            }
        });

        place.setOnClickListener(new View.OnClickListener() {
            private boolean flag = true;

            @Override
            public void onClick(View v) {
                if (flag) {
                    flag = false;
                    place.setImageResource(R.drawable.toggle_icon_off_xxhdpi);
                } else {
                    flag = true;
                    place.setImageResource(R.drawable.toggle_icon_on_xxhdpi);
                }

            }
        });


        email.setOnClickListener(new View.OnClickListener() {
            private boolean flag = true;

            @Override
            public void onClick(View v) {
                if (flag) {
                    flag = false;
                    email.setImageResource(R.drawable.toggle_icon_on_xxhdpi);
                } else {
                    flag = true;
                    email.setImageResource(R.drawable.toggle_icon_off_xxhdpi);
                }

            }
        });
        getMyProfile();
        getMyProfilePic();
        return rootView;
    }

    public void getMyProfile() {
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

        UserService userService = retrofit.create(UserService.class);
        Call<UserInfo> userInfoCall = userService.getMyProfile();
        userInfoCall.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                try {
                    UserInfo userInfo = response.body();
                    if (userInfo != null) {
                        if (userInfo.getId() != null) {
                            txtMyName.setText(userInfo.getFullName());
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

                            txtGender.setText(strGenderText);
                            txtAge.setText("");
                            if (userInfo.getAddressBook().getDateOfBirth() != null) {
                                Date birthDate = DateUtil.convertToDate(userInfo.getAddressBook().getDateOfBirth());
                                if (birthDate != null) {
                                    Date today = new Date();
                                    LocalDate localeBirthDate = Instant.ofEpochMilli(birthDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
                                    LocalDate localeToday = Instant.ofEpochMilli(today.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
                                    txtAge.setText(String.valueOf(AgeCalculator.calculateAge(localeBirthDate, localeToday)));
                                }
                            }
                            txtPhoneNo.setText(userInfo.getPhone());
                            txtEmail.setText(userInfo.getAddressBook().getEmail1());
                            txtPlaceOfOrigin.setText("");
                        }
                    }
                } catch (
                        Exception ex) {
                    showDialog(false, "There was an error fetching user profile, Please try after sometime");
                }
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                boolean hasError = false;
                showDialog(false, "There was an error fetching user profile, Please try after sometime");
            }
        });

    }

    public void getMyProfilePic() {
        AuthServiceHolder authServiceHolder = new AuthServiceHolder();
        SharedPreferences preferences = getContext().getSharedPreferences("GuestJini", Context.MODE_PRIVATE);
        String accessToken = preferences.getString("access_token", "");
        OkHttpClient okHttpClient = new OkHttpClientInstance.Builder(getActivity(), authServiceHolder)
                .addHeader("Authorization", accessToken)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(EndPoints.END_POINT_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        AuthService authService = retrofit.create(AuthService.class);
        authServiceHolder.set(authService);

        UserService userService = retrofit.create(UserService.class);
        Call<String> imageCall = userService.getMyProfilePic();
        imageCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    if (response.body() != null) {
                        if (!response.body().trim().equals("")) {
                            String strBase64 = response.body();
                            byte[] decodedString = Base64.decode(strBase64, Base64.DEFAULT);
                            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                            imgProfilePic.setImageBitmap(decodedByte);
                        }
                    }
                }catch (Exception ex){
                    boolean hasError = false;
                    showDialog(false, "There was an error fetching user profile pic, Please try after sometime");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                boolean hasError = false;
                showDialog(false, "There was an error fetching user profile pic, Please try after sometime");
            }
        });
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
