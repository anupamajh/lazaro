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
import com.carmel.guestjini.Models.User.ForgotPasswordResponse;
import com.carmel.guestjini.Services.User.UserService;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForgotPassword extends AppCompatActivity {
    TextView emailErrorField,getOneNow,forgotPasswordErrorMessage;
    EditText email;
    MaterialButton resetPassword;
    ImageView backIcon;
    String MobilePattern = "[0-9]{10}";
    String EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        emailErrorField=findViewById(R.id.emailErrorField);
        getOneNow=findViewById(R.id.getOneNow);
        email=findViewById(R.id.email);
        backIcon=findViewById(R.id.backArrowIcon);
        resetPassword=findViewById(R.id.resetPasswordButton);
        forgotPasswordErrorMessage=findViewById(R.id.forgotPasswordErrorMessage);


        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().trim().length()==0) {
                    emailErrorField.setVisibility(View.VISIBLE);
                    email.setBackgroundResource(R.drawable.edit_red_textbox);
                }
                else if(!email.getText().toString().trim().matches(EMAIL_PATTERN) && !email.getText().toString().trim().matches(MobilePattern))  {
                    forgotPasswordErrorMessage.setVisibility(View.VISIBLE);
                    emailErrorField.setVisibility(View.GONE);
                    backIcon.setVisibility(View.GONE);
                    email.setBackgroundResource(R.drawable.edit_textbox);
                }
                else{
                    try {
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(EndPoints.END_POINT_URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        UserService userService = retrofit.create(UserService.class);
                        Map<String, String> postData = new HashMap<>();
                        postData.put("user_name",email.getText().toString().trim());
                        Call<ForgotPasswordResponse> forgotPasswordResponseCall = userService.restPassword(postData);
                        forgotPasswordResponseCall.enqueue(new Callback<ForgotPasswordResponse>() {
                            @Override
                            public void onResponse(Call<ForgotPasswordResponse> call, Response<ForgotPasswordResponse> response) {
                                try{
                                    ForgotPasswordResponse forgotPasswordResponse = response.body();
                                    if(forgotPasswordResponse.getUserName() != null){
                                        //TODO: Show appropriate alert
                                    }else{
                                        //TODO: Show appropriate alert
                                    }
                                }catch (Exception ex){
                                    //TODO: Show appropriate alert
                                }

                            }

                            @Override
                            public void onFailure(Call<ForgotPasswordResponse> call, Throwable t) {
                                //TODO: Show appropriate alert
                            }
                        });
                    }catch (Exception ex){
                        //TODO: Show appropriate alert
                    }
                }
            }
        });

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });

        getOneNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), AppAccessRequest.class);
                startActivity(intent);
            }
        });
    }
}
