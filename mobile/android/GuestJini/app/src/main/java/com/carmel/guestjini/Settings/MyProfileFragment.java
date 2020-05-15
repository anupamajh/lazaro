package com.carmel.guestjini.Settings;


import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.carmel.guestjini.Common.AgeCalculator;
import com.carmel.guestjini.Common.DateUtil;
import com.carmel.guestjini.Common.EndPoints;
import com.carmel.guestjini.CommunityActivity;
import com.carmel.guestjini.Components.OkHttpClientInstance;
import com.carmel.guestjini.Models.User.UserInfo;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Services.Authentication.AuthService;
import com.carmel.guestjini.Services.Authentication.AuthServiceHolder;
import com.carmel.guestjini.Services.User.UserService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static android.app.Activity.RESULT_OK;

public class MyProfileFragment extends Fragment implements ActivityCompat.OnRequestPermissionsResultCallback {
    ImageView backIcon, profileToggleButton, gender, age, mobileNo, place, email;
    DrawerLayout profileDrawerLayout;
    FloatingActionButton profileEdit;
    CircleImageView profileIcon;

    RelativeLayout cameraIconLayout;
    RelativeLayout galleryIconLayout;

    private TextView txtMyName;
    private TextView txtGender;
    private TextView txtAge;
    private TextView txtPhoneNo;
    private TextView txtEmail;
    private TextView txtPlaceOfOrigin;
    private CircleImageView imgProfilePic;
    private Uri file;

    AlertDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_my_profile, container, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false); // if you want user to wait for some process to finish,
        builder.setView(R.layout.layout_loading_dialog);
        progressDialog = builder.create();
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
        cameraIconLayout = rootView.findViewById(R.id.cameraLayout);
        galleryIconLayout = rootView.findViewById(R.id.galleryLayout);
        cameraIconLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileDrawerLayout.closeDrawer(GravityCompat.START);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                }
                if (Build.VERSION.SDK_INT < 24) {
                    file = Uri.fromFile(getOutputMediaFile());
                } else {
                    try {
                        file = Uri.parse(getOutputMediaFile().getPath()); // My work-around for new SDKs, doesn't work in Android 10.
                    } catch (Exception ex) {
                        String e = ex.getMessage();
                    }
                }
               // intent.putExtra(MediaStore.EXTRA_OUTPUT, file);
                startActivityForResult(intent, 100);
            }
        });
        galleryIconLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileDrawerLayout.closeDrawer(GravityCompat.START);
                Intent i = new Intent(
                        Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, 200);
            }
        });
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            profileIcon.setEnabled(false);
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
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
                int back = 0;
                final Bundle bundle = getArguments();
                if (bundle != null) {
                    if (bundle.containsKey("back_constant")) {
                        back = bundle.getInt("back_constant");
                    }
                }
                if (back == 0) {
                    SettingsLandingFragment settingsLandingFragment = new SettingsLandingFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.SettingsPlaceHolder, settingsLandingFragment);
                    fragmentTransaction.commit();
                } else if (back == 1) {
                    Intent intent = new Intent(getContext(), CommunityActivity.class);
                    startActivity(intent);
                }
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                profileIcon.setEnabled(true);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 100) {
            try {
                 Bitmap bm = (Bitmap) data.getExtras().get("data");
                profileIcon.setImageBitmap(bm);
                BitmapDrawable bitmapDrawable = ((BitmapDrawable) profileIcon.getDrawable());
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                String encoded = Base64.encodeToString(byteArray, Base64.NO_WRAP);
                uploadProfilePic(encoded);
            } catch (Exception ex) {
                String e = ex.getMessage();
            }
        } else if (requestCode == 200) {
            if (resultCode == RESULT_OK) {
                profileIcon.setImageURI(data.getData());
                try {
                    BitmapDrawable bitmapDrawable = ((BitmapDrawable) profileIcon.getDrawable());
                    Bitmap bitmap = bitmapDrawable.getBitmap();
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    String encoded = Base64.encodeToString(byteArray, Base64.NO_WRAP);
                    uploadProfilePic(encoded);
                } catch (Exception ex) {

                }
            }
        }
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
                } catch (Exception ex) {
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

    private static File getOutputMediaFile() {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "CameraDemo");

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return new File(mediaStorageDir.getPath() + File.separator +
                "IMG_" + timeStamp + ".jpg");
    }

    private void uploadProfilePic(String base64String) {
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

            UserService userService = retrofit.create(UserService.class);
            Map<String, String> postData = new HashMap<>();
            postData.put("imageData", base64String);
            Call<UserInfo> userInfoCall = userService.saveProfilePic(postData);
            userInfoCall.enqueue(new Callback<UserInfo>() {
                @Override
                public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                    progressDialog.dismiss();
                    try {
                        UserInfo userInfo = response.body();
                        if (userInfo.getAddressBook() != null) {
                            showDialog(true, "Profile pic changed successfully!");
                        } else {
                            showDialog(false, "There was a problem saving profile pic! Please try after sometime");
                        }
                    } catch (Exception ex) {
                        showDialog(false, "There was a problem saving profile pic! Please try after sometime");
                    }
                }

                @Override
                public void onFailure(Call<UserInfo> call, Throwable t) {
                    progressDialog.dismiss();
                    showDialog(false, "There was a problem saving profile pic! Please try after sometime");
                }
            });

        } catch (Exception ex) {
            progressDialog.dismiss();
            showDialog(false, "There was a problem saving profile pic! Please try after sometime");
        }
    }

}
