package com.carmel.android.guestjini;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.carmel.android.guestjini.datalayer.Common.EndPoints;
import com.carmel.android.guestjini.datalayer.Ticket.API.TicketAPI;
import com.carmel.android.guestjini.datalayer.User.API.UserAPI;
import com.carmel.android.guestjini.models.Ticket.Ticket;
import com.carmel.android.guestjini.models.Ticket.TicketResponse;
import com.carmel.android.guestjini.models.User.AuthData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateTicketPage extends AppCompatActivity {

    @BindView(R.id.txtTicketSubject)
    TextView txtTicketSubject;

    @BindView(R.id.txtTicketDescription)
    TextView txtTicketDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ticket_page);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btnCreateTicket)
    public void createTicket() {
        boolean hasError = false;
        String strSubject = txtTicketSubject.getText().toString().trim();
        String strDescription = txtTicketDescription.getText().toString().trim();

        if (strSubject.equals("")) {
            txtTicketSubject.requestFocus();
            txtTicketSubject.setError("This field is required");
            hasError = true;
        }

        if (strDescription.equals("")) {
            txtTicketDescription.requestFocus();
            txtTicketDescription.setError("This field is required.");
            hasError = true;
        }

        if (!hasError) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(EndPoints.END_POINT_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            UserAPI userAPI = retrofit.create(UserAPI.class);
            String credentials = Credentials.basic(EndPoints.CLIENT_ID, EndPoints.CLIENT_SECRETE);
            SharedPreferences preferences = getSharedPreferences("GuestJini", Context.MODE_PRIVATE);
            String refreshToken = preferences.getString("refresh_token", "");
            if (refreshToken.equals("")) {
                Intent intent = new Intent(CreateTicketPage.this, LoginPage.class);
                CreateTicketPage.this.startActivity(intent);
            }
            SharedPreferences.Editor editor = preferences.edit();
            Call<AuthData> call = userAPI.refreshToken(credentials, "refresh_token", refreshToken);
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
                        Intent intent = new Intent(CreateTicketPage.this, LoginPage.class);
                        CreateTicketPage.this.startActivity(intent);
                    } else {
                        TicketAPI ticketAPI = retrofit.create(TicketAPI.class);
                        Ticket ticket = new Ticket();
                        ticket.setTicketTitle(strSubject);
                        ticket.setTicketNarration(strDescription);
                        Call<TicketResponse> ticketSaveCall =
                                ticketAPI.saveTicket("Bearer " + authData.getAccess_token(),ticket);
                        ticketSaveCall.enqueue(new Callback<TicketResponse>() {
                            @Override
                            public void onResponse(Call<TicketResponse> call, Response<TicketResponse> response) {
                                if (response != null) {
                                    //TODO: Prcess Response Properly
                                    Intent intent = new Intent(CreateTicketPage.this, TicketListPage.class);
                                    CreateTicketPage.this.startActivity(intent);
                                }
                            }

                            @Override
                            public void onFailure(Call<TicketResponse> call, Throwable t) {
                                Log.d("Test",t.getMessage());
                                //TODO: SHow alert of failure
                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<AuthData> call, Throwable t) {

                }
            });

        }


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
