package com.carmel.guestjini;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.carmel.guestjini.Common.EndPoints;
import com.carmel.guestjini.Models.User.AppAccessRequestResponse;
import com.carmel.guestjini.Models.User.ForgotPasswordResponse;
import com.carmel.guestjini.Services.User.UserService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppAccessRequest extends AppCompatActivity {
    TextView emailErrorField,mobileNumberErrorField,invalidCredentials;
    EditText email,mobileNumber;

    FloatingActionButton doneButton;
    ImageView backArrow;
    String MobilePattern = "[0-9]{10}";
    String EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    final Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_access_request);
        email=findViewById(R.id.registeredEmailEditbox);
        mobileNumber=findViewById(R.id.registeredMobileNumberEditbox);

        emailErrorField=findViewById(R.id.regesteredEmailErrorField);
        mobileNumberErrorField=findViewById(R.id.mobileNumberErrorField);
        invalidCredentials=findViewById(R.id.invalidCredentialsErrorField);
        doneButton=findViewById(R.id.appAccessDoneButton);
        backArrow=(ImageView)findViewById(R.id.backArrow);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().trim().length()==0 && mobileNumber.getText().toString().trim().length()==0){
                    emailErrorField.setVisibility(View.VISIBLE);
                    mobileNumberErrorField.setVisibility(View.VISIBLE);

                    email.setBackgroundResource(R.drawable.edit_red_textbox);
                    mobileNumber.setBackgroundResource(R.drawable.edit_red_textbox);
                }else{
                    emailErrorField.setVisibility(View.GONE);
                    mobileNumberErrorField.setVisibility(View.GONE);
                    email.setBackgroundResource(R.drawable.edit_textbox);
                    mobileNumber.setBackgroundResource(R.drawable.edit_textbox);
                    if(email.getText().toString().trim().length()==0){
                        emailErrorField.setVisibility(View.VISIBLE);
                        email.setBackgroundResource(R.drawable.edit_red_textbox);
                        invalidCredentials.setVisibility(View.GONE);
                    }else if(mobileNumber.getText().toString().trim().length()==0){
                        mobileNumberErrorField.setVisibility(View.VISIBLE);
                        mobileNumber.setBackgroundResource(R.drawable.edit_red_textbox);
                        invalidCredentials.setVisibility(View.GONE);
                    }else if(!email.getText().toString().trim().matches(EMAIL_PATTERN)){
                        invalidCredentials.setVisibility(View.VISIBLE);
                        emailErrorField.setVisibility(View.GONE);
                        email.setBackgroundResource(R.drawable.edit_textbox);
                        mobileNumber.setBackgroundResource(R.drawable.edit_textbox);
                    }else if (!mobileNumber.getText().toString().trim().matches(MobilePattern)){
                        mobileNumberErrorField.setVisibility(View.GONE);
                        invalidCredentials.setVisibility(View.VISIBLE);
                        email.setBackgroundResource(R.drawable.edit_textbox);
                        mobileNumberErrorField.setBackgroundResource(R.drawable.edit_textbox);
                    }else {
                        try {
                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(EndPoints.END_POINT_URL)
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();
                            UserService userService = retrofit.create(UserService.class);
                            Map<String, String> postData = new HashMap<>();
                            postData.put("email",email.getText().toString().trim());
                            postData.put("mobile",mobileNumber.getText().toString().trim());
                            Call<AppAccessRequestResponse> appAccessRequestCall = userService.appAccessRequest(postData);
                            appAccessRequestCall.enqueue(new Callback<AppAccessRequestResponse>() {
                                @Override
                                public void onResponse(Call<AppAccessRequestResponse> call, Response<AppAccessRequestResponse> response) {
                                    try{
                                        AppAccessRequestResponse appAccessRequestResponse = response.body();
                                        if(appAccessRequestResponse.getUserName() != null){
                                            //TODO: Show appropriate alert
                                        }else{
                                            //TODO: Show appropriate alert
                                        }
                                    }catch (Exception ex){
                                        //TODO: Show appropriate alert
                                    }

                                }

                                @Override
                                public void onFailure(Call<AppAccessRequestResponse> call, Throwable t) {
                                    //TODO: Show appropriate alert
                                }
                            });
                        }catch (Exception ex){
                            //TODO: Show appropriate alert
                        }
                    }
                }
            }
        });
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });
    }
}
