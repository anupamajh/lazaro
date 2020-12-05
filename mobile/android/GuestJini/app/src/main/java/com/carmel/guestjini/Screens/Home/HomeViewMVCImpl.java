package com.carmel.guestjini.Screens.Home;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.carmel.guestjini.Networking.Guest.GuestResponse;
import com.carmel.guestjini.Networking.Users.UserInfo;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeViewMVCImpl extends BaseObservableViewMvc<HomeViewMVC.Listener> implements HomeViewMVC {

    private final ProgressBar mProgressBar;
    private final TextView txtUserFullName;
    private final TextView txtWelcomeText;
    private final CircleImageView imgUserProfilePic;
    private final RelativeLayout layoutGuestStayDetails;
    private final RelativeLayout layoutGuestDetails;
    private final TextView txtUnitHeading;
    private final TextView txtUnitName;
    private final TextView txtGuestNumber;
    private final TextView txtEmergencyContact;

    public HomeViewMVCImpl(LayoutInflater inflater,
                           @Nullable ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.layout_base_home, parent, false));
        mProgressBar = findViewById(R.id.progress);
        txtUserFullName = findViewById(R.id.txtUserFullName);
        txtWelcomeText = findViewById(R.id.txtWelcomeText);
        imgUserProfilePic = findViewById(R.id.imgUserProfilePic);
        layoutGuestStayDetails = findViewById(R.id.layoutGuestStayDetails);
        layoutGuestDetails = findViewById(R.id.layoutGuestDetails);
        txtUnitHeading = findViewById(R.id.txtUnitHeading);
        txtUnitName = findViewById(R.id.txtUnitName);
        txtGuestNumber = findViewById(R.id.txtGuestNumber);
        txtEmergencyContact = findViewById(R.id.txtEmergencyContact);
        layoutGuestDetails.setVisibility(View.GONE);

    }


    @Override
    public void showProgressIndication() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressIndication() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void bindGuestDetails(GuestResponse guestResponse) {
        layoutGuestDetails.setVisibility(View.VISIBLE);
        txtUnitName.setText(guestResponse.getInventoryPath());
        txtGuestNumber.setText(guestResponse.getGuest().getGuestNo());
        txtEmergencyContact.setText(guestResponse.getGuest().getPhone());
    }

    @Override
    public void bindUserInfo(UserInfo userInfo) {
        this.txtUserFullName.setText(userInfo.getFullName());
        Date nowDate  = new Date();
        Date dt = new Date();
        int hours = dt.getHours();
        int min = dt.getMinutes();

        if(hours>=1 || hours<=12){
           txtWelcomeText.setText("Good Morning");
        }else if(hours>=12 || hours<=16){
            txtWelcomeText.setText("Good Afternoon");
        }else if(hours>=16 || hours<=24){
            txtWelcomeText.setText("Good Evening");
        }

    }

    @Override
    public void bindProfilePic(String response) {
        if (response == null) {
            response = "";
        }
        RelativeLayout.LayoutParams layoutGuestDetailsParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        RelativeLayout.LayoutParams txtUnitHeadingParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        if (!response.equals("")) {
            byte[] decodedString = Base64.decode(response, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            imgUserProfilePic.setImageBitmap(decodedByte);
            imgUserProfilePic.setVisibility(View.VISIBLE);
           // layoutGuestDetailsParams.setMargins(0, -40, 0, 0);
            //txtUnitHeading.setMargins(0, 45, 0, 0);
        } else {
            imgUserProfilePic.setVisibility(View.GONE);
            layoutGuestDetailsParams.setMargins(0, 0, 0, 0);
            txtUnitHeadingParams.setMargins(0, 0, 0, 0);
            layoutGuestDetails.setLayoutParams(layoutGuestDetailsParams);
            txtUnitHeading.setLayoutParams(txtUnitHeadingParams);

        }

    }
}
