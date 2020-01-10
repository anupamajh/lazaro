package com.carmel.android.guestjini;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.carmel.android.guestjini.Presenters.LoginPresenter;
import com.carmel.android.guestjini.Presenters.LoginPresenterImpl;
import com.carmel.android.guestjini.View.LoginView;
import com.carmel.android.guestjini.datalayer.Common.EndPoints;
import com.carmel.android.guestjini.datalayer.User.API.UserAPI;
import com.carmel.android.guestjini.models.User.AuthData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginPage extends AppCompatActivity implements LoginView {
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(logging)
            .build();

    @BindView(R.id.btnLogin)
    Button btnLogin;

    @BindView(R.id.txtLoginId)
    EditText txtLoginId;

    @BindView(R.id.txtPassword)
    EditText txtPassword;

    @BindView(R.id.login_error)
    TextView txtLoginError;


    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        ButterKnife.bind(this);

        loginPresenter = new LoginPresenterImpl(this);
        txtLoginError.setVisibility(View.INVISIBLE);

    }

    @OnClick(R.id.btnLogin)
    public void login() {
        txtLoginError.setVisibility(View.INVISIBLE);
        String strLoginId = txtLoginId.getText().toString().trim();
        String strPassword = txtPassword.getText().toString().trim();
        Boolean hasError = false;
        if (strLoginId.equals("")) {
            txtLoginId.requestFocus();
            txtLoginId.setError("This Field is Required");
            hasError = true;
        }

        if (strPassword.equals("")) {
            txtPassword.requestFocus();
            txtPassword.setError("This Field is Required");
            hasError = true;
        }

        if (!hasError) {
            this.attemptLogin(strLoginId, strPassword);
        }

    }

    @Override
    public void attemptLogin(String userName, String password) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(EndPoints.END_POINT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        UserAPI userAPI = retrofit.create(UserAPI.class);
        String credentials = Credentials.basic(EndPoints.CLIENT_ID, EndPoints.CLIENT_SECRETE);
        SharedPreferences preferences = getSharedPreferences("GuestJini", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        Call<AuthData> call = userAPI.attemptLogin(credentials, "password", userName, password);
        call.enqueue(new Callback<AuthData>() {
            @Override
            public void onResponse(Call<AuthData> call, Response<AuthData> response) {
                boolean hasError = true;
                AuthData authData = response.body();
                if (authData != null) {
                    if (authData.getAccess_token() != null) {
                        if (authData.getAccess_token().trim() != "") {
                            editor.putBoolean("isLoggedIn", true);
                            editor.putString("access_token", authData.getAccess_token());
                            editor.putString("refresh_token", authData.getRefresh_token());
                            editor.putString("token_type", authData.getToken_type());
                            editor.putInt("expires_in", authData.getExpires_in());
                            editor.commit();
                            hasError = false;
                        }
                    }
                }
                if (hasError) {
                    txtLoginError.setVisibility(View.VISIBLE);
                    editor.putBoolean("isLoggedIn", false);
                    editor.commit();

                }else{
                    Intent intent = new Intent(LoginPage.this, HomePage.class);
                    LoginPage.this.startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<AuthData> call, Throwable t) {
                Log.d("Exception", t.getMessage());
                txtLoginError.setVisibility(View.VISIBLE);
                editor.putBoolean("isLoggedIn", false);
                editor.commit();
            }
        });
    }

    @Override
    public void processLogin(AuthData authData) {
        if (authData != null) {
            if (authData.getAccess_token() != null) {
                if (authData.getAccess_token().trim() != "") {
                    SharedPreferences preferences = getSharedPreferences("GuestJini", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("access_token", authData.getAccess_token());
                    editor.putString("refresh_token", authData.getRefresh_token());
                    editor.putString("token_type", authData.getToken_type());
                    editor.putInt("expires_in", authData.getExpires_in());
                    editor.commit();
                }
            }
        }
    }
}
