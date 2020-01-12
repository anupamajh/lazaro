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

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewTicketPage extends AppCompatActivity {

    @BindView(R.id.ticket_status)
    TextView txtTicketStatus;

    @BindView(R.id.ticket_date)
    TextView txtTicketDate;

    @BindView(R.id.ticket_no)
    TextView txtTicketNo;

    @BindView(R.id.ticket_title)
    TextView txtTicketTitle;

    @BindView(R.id.ticket_description)
    TextView txtTicketDescription;

    @BindView(R.id.ticket_status_wrap)
    LinearLayout ticketStatusWrap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ticket_page);
        ButterKnife.bind(this);
        String ticketId = getIntent().getStringExtra("ticketId");
        this.fetchTicket(ticketId);
    }


    private void fetchTicket(String ticketId){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(EndPoints.END_POINT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserAPI userAPI = retrofit.create(UserAPI.class);
        String credentials = Credentials.basic(EndPoints.CLIENT_ID, EndPoints.CLIENT_SECRETE);
        SharedPreferences preferences = getSharedPreferences("GuestJini", Context.MODE_PRIVATE);
        String refreshToken = preferences.getString("refresh_token", "");
        if (refreshToken.equals("")) {
            Intent intent = new Intent(ViewTicketPage.this, LoginPage.class);
            ViewTicketPage.this.startActivity(intent);
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
                    Intent intent = new Intent(ViewTicketPage.this, LoginPage.class);
                    ViewTicketPage.this.startActivity(intent);
                } else {
                    TicketAPI ticketAPI = retrofit.create(TicketAPI.class);
                    Map<String, String > postData = new HashMap<>();
                    postData.put("id", ticketId);
                    Call<TicketResponse> ticketSaveCall =
                            ticketAPI.get("Bearer " + authData.getAccess_token(), postData);
                    ticketSaveCall.enqueue(new Callback<TicketResponse>() {
                        @Override
                        public void onResponse(Call<TicketResponse> call, Response<TicketResponse> response) {
                            if (response != null) {
                                TicketResponse ticketResponse = response.body();
                                Ticket ticket = ticketResponse.getTaskTicket();
                                if(ticket.getTicketStatus() == 3){
                                    txtTicketStatus.setText("OPEN");
                                    ticketStatusWrap.setBackgroundColor(getColor(R.color.coral));
                                }else if(ticket.getTicketStatus() == 2){
                                    txtTicketStatus.setText("STARTED");
                                    ticketStatusWrap.setBackgroundColor(getColor(R.color.squash));
                                }else if(ticket.getTicketStatus() == 1){
                                    txtTicketStatus.setText("CLOSED");
                                    ticketStatusWrap.setBackgroundColor(getColor(R.color.blueyGrey));
                                }else{
                                    txtTicketStatus.setText("NEW");
                                    ticketStatusWrap.setBackgroundColor(getColor(R.color.coral));
                                }

                                txtTicketDate.setText(ticket.getCreationTime());
                                txtTicketTitle.setText(ticket.getTicketTitle());
                                txtTicketDescription.setText(ticket.getTicketNarration());
                                txtTicketNo.setText(ticket.getTicketNo());
                            }
                        }

                        @Override
                        public void onFailure(Call<TicketResponse> call, Throwable t) {
                            Log.d("Test", t.getMessage());
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
