package com.carmel.android.guestjini;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.carmel.android.guestjini.adapters.TicketListAdapter;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TicketListPage extends AppCompatActivity implements TicketListAdapter.RecyclerViewClickListener {

    List<Ticket> ticketList;
    private RecyclerView recyclerView;
    private TicketListAdapter ticketListAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_list_page);
        ButterKnife.bind(this);
        ticketList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.ticketList);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getApplication());
        recyclerView.setLayoutManager(layoutManager);
        ticketListAdapter = new TicketListAdapter(ticketList, getApplicationContext(),this);
        recyclerView.setAdapter(ticketListAdapter);

        this.fetchTickets();

    }

    @Override
    public void onItemClick(View view, int position) {

        Ticket ticket = ticketList.get(position);
        Intent intent = new Intent(TicketListPage.this, ViewTicketPage.class);
        intent.putExtra("ticketId", ticket.getId());
        TicketListPage.this.startActivity(intent);

    }

    private void fetchTickets() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(EndPoints.END_POINT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserAPI userAPI = retrofit.create(UserAPI.class);
        String credentials = Credentials.basic(EndPoints.CLIENT_ID, EndPoints.CLIENT_SECRETE);
        SharedPreferences preferences = getSharedPreferences("GuestJini", Context.MODE_PRIVATE);
        String refreshToken = preferences.getString("refresh_token", "");
        if (refreshToken.equals("")) {
            Intent intent = new Intent(TicketListPage.this, LoginPage.class);
            TicketListPage.this.startActivity(intent);
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
                    Intent intent = new Intent(TicketListPage.this, LoginPage.class);
                    TicketListPage.this.startActivity(intent);
                } else {
                    TicketAPI ticketAPI = retrofit.create(TicketAPI.class);
                    Call<TicketResponse> ticketSaveCall =
                            ticketAPI.getAll("Bearer " + authData.getAccess_token());
                    ticketSaveCall.enqueue(new Callback<TicketResponse>() {
                        @Override
                        public void onResponse(Call<TicketResponse> call, Response<TicketResponse> response) {
                            if (response != null) {
                                TicketResponse ticketResponse = response.body();
                                ticketList = ticketResponse.getTaskTicketList();
                                ticketListAdapter.swapDataSet(ticketList);
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
