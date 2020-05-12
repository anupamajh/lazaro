package com.carmel.guestjini;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.carmel.guestjini.Common.EndPoints;
import com.carmel.guestjini.Models.Authentication.AccessToken;
import com.carmel.guestjini.Services.Authentication.AuthService;
import com.google.android.material.button.MaterialButton;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(logging)
            .build();

    TextView emailErrorField,passwordErrorField,forgotPassword,invalidCredentials,getOneNow;
    EditText email,password;
    ImageView passwordIcon;
    MaterialButton loginButton;
    ConstraintLayout passwordLayout;
    String MobilePattern = "[0-9]{10}";
    String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{3,})$";
//    String EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String  PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.* [@#$%^&+=])(?=\\S+$).{4,}$";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailErrorField=findViewById(R.id.emailErrorField);
        passwordErrorField=findViewById(R.id.passwordErrorField);
        forgotPassword=findViewById(R.id.forgotPassword);
        invalidCredentials=findViewById(R.id.invalidCredentials);
        getOneNow=findViewById(R.id.getOneNow);
        passwordIcon=findViewById(R.id.passwordMaskingIcon);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        loginButton=findViewById(R.id.login);
        passwordLayout=findViewById(R.id.passwordLayout);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().trim().length()==0 && password.getText().toString().trim().length()==0){
                    emailErrorField.setVisibility(View.VISIBLE);
                    passwordErrorField.setVisibility(View.VISIBLE);
                    passwordLayout.setBackgroundResource(R.drawable.edit_red_textbox);
                    email.setBackgroundResource(R.drawable.edit_red_textbox);
                }else {
                    emailErrorField.setVisibility(View.GONE);
                    passwordErrorField.setVisibility(View.GONE);
                    email.setBackgroundResource(R.drawable.edit_textbox);
                    passwordLayout.setBackgroundResource(R.drawable.edit_textbox);
                    if(email.getText().toString().trim().length()==0){
                        emailErrorField.setVisibility(View.VISIBLE);
                        email.setBackgroundResource(R.drawable.edit_red_textbox);
                        invalidCredentials.setVisibility(View.GONE);
                    }else if(password.getText().toString().trim().length()==0){
                        passwordErrorField.setVisibility(View.VISIBLE);
                        passwordLayout.setBackgroundResource(R.drawable.edit_red_textbox);
                        invalidCredentials.setVisibility(View.GONE);
                    }else if(!email.getText().toString().trim().matches(EMAIL_PATTERN) && !email.getText().toString().trim().matches(MobilePattern)){
                        invalidCredentials.setVisibility(View.VISIBLE);
                        emailErrorField.setVisibility(View.GONE);
                        email.setBackgroundResource(R.drawable.edit_textbox);
                        passwordLayout.setBackgroundResource(R.drawable.edit_textbox);
                        /*
                        else if (password.getText().toString().trim().length()<8){
                        passwordErrorField.setVisibility(View.GONE);
                        invalidCredentials.setVisibility(View.VISIBLE);
                        email.setBackgroundResource(R.drawable.edit_textbox);
                        passwordLayout.setBackgroundResource(R.drawable.edit_textbox);
                    }
                         */
                    }else {
                        //TODO: Implement login call
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(EndPoints.END_POINT_URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .client(client)
                                .build();
                        AuthService authService = retrofit.create(AuthService.class);
                        String credentials = Credentials.basic(EndPoints.CLIENT_ID, EndPoints.CLIENT_SECRETE);
                        SharedPreferences preferences = getSharedPreferences("GuestJini", Context.MODE_PRIVATE);
                        final SharedPreferences.Editor editor = preferences.edit();
                        Call<AccessToken> call = authService.attemptLogin(
                                credentials,
                                "password",
                                email.getText().toString(),
                                password.getText().toString());

                        call.enqueue(new Callback<AccessToken>() {
                            @Override
                            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                                boolean hasError = true;
                                AccessToken accessToken = response.body();
                                if (accessToken != null) {
                                    if (accessToken.getAccessToken() != null) {
                                        if (accessToken.getAccessToken().trim() != "") {
                                            editor.putBoolean("isLoggedIn", true);
                                            editor.putString("access_token", accessToken.getAccessToken());
                                            editor.putString("refresh_token", accessToken.getRefreshToken());
                                            editor.putString("token_type", accessToken.getTokenType());
                                            editor.putLong("expires_in", accessToken.getExpiresIn());
                                            editor.commit();
                                            hasError = false;
                                        }
                                    }
                                }
                                if (hasError) {
                                    invalidCredentials.setVisibility(View.VISIBLE);
                                    editor.putBoolean("isLoggedIn", false);
                                    editor.commit();

                                }else{
                                    Intent intent=new Intent(getApplicationContext(),SupportActivity.class);
                                    startActivity(intent);
                                }
                            }

                            @Override
                            public void onFailure(Call<AccessToken> call, Throwable t) {
                                Log.d("Exception", t.getMessage());
                                invalidCredentials.setVisibility(View.VISIBLE);
                                editor.putBoolean("isLoggedIn", false);
                                editor.commit();
                            }
                        });
                    }
                }
            }
        });

        passwordIcon.setOnClickListener(new View.OnClickListener() {
            private boolean flag = true;
            @Override
            public void onClick(View v) {
                if(flag) {
                    flag=false;
                    passwordIcon.setImageResource(R.drawable.password_unmasking_icon);
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                }else{
                    flag=true;
                    passwordIcon.setImageResource(R.drawable.password_masking_icon);
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ForgotPassword.class);
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
