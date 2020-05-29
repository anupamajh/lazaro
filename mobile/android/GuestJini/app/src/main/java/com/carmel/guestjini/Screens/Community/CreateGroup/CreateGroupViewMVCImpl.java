package com.carmel.guestjini.Screens.Community.CreateGroup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

public class CreateGroupViewMVCImpl
        extends BaseObservableViewMvc<CreateGroupViewMVC.Listener>
        implements CreateGroupViewMVC {

    private final ProgressBar progressBar;
    private final EditText txtGroupName;
    private final EditText txtGroupDescription;
    private final TextView txtGroupNameError;

    public CreateGroupViewMVCImpl(
            LayoutInflater inflater,
            @Nullable ViewGroup parent
    ) {
        setRootView(inflater.inflate(R.layout.layout_community_create_group, parent, false));
        txtGroupName = findViewById(R.id.txtGroupName);
        txtGroupDescription = findViewById(R.id.txtGroupDescription);
        txtGroupNameError = findViewById(R.id.txtGroupNameError);
        progressBar = findViewById(R.id.progress);
        Button btnSaveGroup = findViewById(R.id.btnSaveGroup);
        ImageView btnBack = findViewById(R.id.btnBack);
        btnSaveGroup.setOnClickListener(view -> {
            if (isValid()) {
                for (Listener listener : getListeners()) {
                    listener.onCreateGroupClicked(
                            txtGroupName.getText().toString().trim(),
                            txtGroupDescription.getText().toString().trim()
                    );
                }
            }
        });

        btnBack.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onBackClicked();
            }
        });

    }

    @Override
    public void showProgressIndication() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressIndication() {
        progressBar.setVisibility(View.GONE);
    }


    private boolean isValid() {
        if (txtGroupName.getText().toString().trim().equals("")) {
            txtGroupNameError.setVisibility(View.VISIBLE);
            return false;
        }
        txtGroupNameError.setVisibility(View.GONE);
        return true;
    }
}
