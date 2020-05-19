package com.carmel.guestjini.Screens.Common.Dialogs.PromptDialog;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;


public class PromptViewMvcImpl extends BaseObservableViewMvc<PromptViewMvc.Listener>
        implements PromptViewMvc {

    private TextView txtDialogTitle;
    private TextView txtDialogMessage;
    private Button btnDone;
    private Button btnCancel;

    
    public PromptViewMvcImpl(LayoutInflater inflater,
                             @Nullable ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.layout_base_prompt_dialog, parent, false));

        txtDialogTitle = findViewById(R.id.txtDialogTitle);
        txtDialogMessage = findViewById(R.id.txtDialogMessage);
        btnDone = findViewById(R.id.btnDone);
        btnCancel = findViewById(R.id.btnCancel);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Listener listener : getListeners()) {
                    listener.onPositiveButtonClicked();
                };
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Listener listener : getListeners()) {
                    listener.onNegativeButtonClicked();
                }
            }
        });
        
    }

    @Override
    public void setTitle(String title) {
        txtDialogTitle.setText(title);
    }

    @Override
    public void setMessage(String message) {
        txtDialogMessage.setText(message);
    }

    @Override
    public void setPositiveButtonCaption(String caption) {
        btnDone.setText(caption);
    }

    @Override
    public void setNegativeButtonCaption(String caption) {
        btnCancel.setText(caption);
    }
}
