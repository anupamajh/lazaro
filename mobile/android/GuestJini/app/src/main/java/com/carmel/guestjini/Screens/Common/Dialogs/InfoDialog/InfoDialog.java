package com.carmel.guestjini.Screens.Common.Dialogs.InfoDialog;

import android.app.Dialog;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.Dialogs.BaseDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class InfoDialog extends BaseDialog {

    protected static final String ARG_TITLE = "ARG_TITLE";
    protected static final String ARG_MESSAGE = "ARG_MESSAGE";
    protected static final String ARG_IS_SUCCESS = "ARG_IS_SUCCESS";

    public static InfoDialog createDialog(String title, String message, boolean isSuccess) {
        InfoDialog infoDialog = new InfoDialog();
        Bundle args = new Bundle(3);
        args.putString(ARG_TITLE, title);
        args.putString(ARG_MESSAGE, message);
        args.putBoolean(ARG_IS_SUCCESS, isSuccess);
        infoDialog.setArguments(args);
        return infoDialog;
    }

    @NonNull
    @Override
    public final Dialog onCreateDialog(Bundle savedInstanceState) {
        if (getArguments() == null) {
            throw new IllegalStateException("arguments mustn't be null");
        }

        Dialog dialog = new Dialog(requireContext());
        dialog.setContentView(R.layout.layout_base_info_dialog);

        TextView txtDialogTitle = dialog.findViewById(R.id.txtDialogTitle);
        TextView txtDialogMessage = dialog.findViewById(R.id.txtDialogMessage);
        FloatingActionButton btnDone = dialog.findViewById(R.id.btnDone);

        txtDialogTitle.setText(getArguments().getString(ARG_TITLE));
        txtDialogMessage.setText(getArguments().getString(ARG_MESSAGE));
        if (getArguments().getBoolean(ARG_IS_SUCCESS)) {
            btnDone.setBackgroundTintList(ColorStateList.valueOf(Color
                    .parseColor("#32BDD2")));
        } else {
            btnDone.setBackgroundTintList(ColorStateList.valueOf(Color
                    .parseColor("#E65959")));
        }
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClicked();
            }
        });

        return dialog;
    }

    protected void onButtonClicked() {
        dismiss();
    }

}
