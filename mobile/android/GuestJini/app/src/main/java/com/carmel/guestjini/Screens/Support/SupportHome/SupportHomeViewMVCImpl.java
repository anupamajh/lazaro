package com.carmel.guestjini.Screens.Support.SupportHome;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SupportHomeViewMVCImpl extends BaseObservableViewMvc<SupportHomeViewMVC.Listener> implements SupportHomeViewMVC {

    public SupportHomeViewMVCImpl(
            LayoutInflater inflater,
            @Nullable ViewGroup parent
    ) {
        setRootView(inflater.inflate(R.layout.layout_support_home, parent, false));
        EditText txtSearch = findViewById(R.id.txtSearch);
        ImageView btnSearch = findViewById(R.id.btnSearch);
        FloatingActionButton btnExploreKB = findViewById(R.id.btnExploreKB);
        MaterialButton btnCreateTicket = findViewById(R.id.btnCreateTicket);
        FloatingActionButton btnTicketList = findViewById(R.id.btnTicketList);

        btnSearch.setOnClickListener(view -> {
            String strSearch = txtSearch.getText().toString().trim();
            for (Listener listener : getListeners()) {
                listener.onKBSearchClicked(strSearch);
            }
        });

        btnExploreKB.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onKBExploreClicked();
            }
        });

        btnCreateTicket.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onCreateTicketClicked();
            }
        });

        btnTicketList.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onGotoTicketsClicked();
            }
        });
    }
}
