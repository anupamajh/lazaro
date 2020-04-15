package com.carmel.guestjini;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AppAccessRequest extends AppCompatActivity {
    TextView emailErrorField,mobileNumberErrorField,invalidCredentials;
    EditText email,mobileNumber;

    FloatingActionButton doneButton;
    ImageView backArrow;
    String MobilePattern = "[0-9]{10}";
    String EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    final Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_access_request);
        email=findViewById(R.id.registeredEmailEditbox);
        mobileNumber=findViewById(R.id.registeredMobileNumberEditbox);

        emailErrorField=findViewById(R.id.regesteredEmailErrorField);
        mobileNumberErrorField=findViewById(R.id.mobileNumberErrorField);
        invalidCredentials=findViewById(R.id.invalidCredentialsErrorField);
        doneButton=findViewById(R.id.appAccessDoneButton);
        backArrow=(ImageView)findViewById(R.id.backArrow);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().trim().length()==0 && mobileNumber.getText().toString().trim().length()==0){
                    emailErrorField.setVisibility(View.VISIBLE);
                    mobileNumberErrorField.setVisibility(View.VISIBLE);

                    email.setBackgroundResource(R.drawable.edit_red_textbox);
                    mobileNumber.setBackgroundResource(R.drawable.edit_red_textbox);
                }else{
                    emailErrorField.setVisibility(View.GONE);
                    mobileNumberErrorField.setVisibility(View.GONE);
                    email.setBackgroundResource(R.drawable.edit_textbox);
                    mobileNumber.setBackgroundResource(R.drawable.edit_textbox);
                    if(email.getText().toString().trim().length()==0){
                        emailErrorField.setVisibility(View.VISIBLE);
                        email.setBackgroundResource(R.drawable.edit_red_textbox);
                        invalidCredentials.setVisibility(View.GONE);
                    }else if(mobileNumber.getText().toString().trim().length()==0){
                        mobileNumberErrorField.setVisibility(View.VISIBLE);
                        mobileNumber.setBackgroundResource(R.drawable.edit_red_textbox);
                        invalidCredentials.setVisibility(View.GONE);
                    }else if(!email.getText().toString().trim().matches(EMAIL_PATTERN)){
                        invalidCredentials.setVisibility(View.VISIBLE);
                        emailErrorField.setVisibility(View.GONE);
                        email.setBackgroundResource(R.drawable.edit_textbox);
                        mobileNumber.setBackgroundResource(R.drawable.edit_textbox);
                    }else if (!mobileNumber.getText().toString().trim().matches(MobilePattern)){
                        mobileNumberErrorField.setVisibility(View.GONE);
                        invalidCredentials.setVisibility(View.VISIBLE);
                        email.setBackgroundResource(R.drawable.edit_textbox);
                        mobileNumberErrorField.setBackgroundResource(R.drawable.edit_textbox);
                    }else {
                        final Dialog dialog = new Dialog(context);
                        dialog.setContentView(R.layout.alert_dailogbox);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                        TextView alertDailogTitle = (TextView) dialog.findViewById(R.id.alertDailogTitle);
                        alertDailogTitle.setText(getText(R.string.app_access_request));

                        TextView alertDailogMessage = (TextView) dialog.findViewById(R.id.alertDailogDescription);
                        alertDailogMessage.setText(getText(R.string.app_access_request_dialog_description));

                        FloatingActionButton doneButton = (FloatingActionButton) dialog.findViewById(R.id.done_button);
                        doneButton.setBackgroundTintList(ColorStateList.valueOf(Color
                                .parseColor("#32BDD2")));
                        doneButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                Intent intent = new Intent(getApplicationContext(), Login.class);
                                startActivity(intent);
                            }
                        });

                        dialog.show();
                    }
                }
            }
        });
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });
    }
}
