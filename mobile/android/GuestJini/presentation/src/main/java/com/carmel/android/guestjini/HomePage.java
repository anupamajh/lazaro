package com.carmel.android.guestjini;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomePage extends AppCompatActivity {

    @BindView(R.id.btnCreateTicket)
    Button btnCreateTicket;

    @BindView(R.id.btnTicketList)
    Button btnTicketList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnCreateTicket)
    public void showCreateTicket(){
        Intent intent = new Intent(HomePage.this, CreateTicketPage.class);
        HomePage.this.startActivity(intent);
    }

    @OnClick(R.id.btnTicketList)
    public void showTicketListPage(){
        Intent intent = new Intent(HomePage.this, TicketListPage.class);
        HomePage.this.startActivity(intent);
    }

}
