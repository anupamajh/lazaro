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

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class ForgotPassword extends AppCompatActivity {
    TextView emailErrorField,getOneNow,forgotPasswordErrorMessage;
    EditText email;
    MaterialButton resetPassword;
    ImageView backIcon;
    String MobilePattern = "[0-9]{10}";
    String EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        emailErrorField=findViewById(R.id.emailErrorField);
        getOneNow=findViewById(R.id.getOneNow);
        email=findViewById(R.id.email);
        backIcon=findViewById(R.id.backArrowIcon);
        resetPassword=findViewById(R.id.resetPasswordButton);
        forgotPasswordErrorMessage=findViewById(R.id.forgotPasswordErrorMessage);


        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().trim().length()==0) {
                    emailErrorField.setVisibility(View.VISIBLE);
                    email.setBackgroundResource(R.drawable.edit_red_textbox);
                }
                else if(!email.getText().toString().trim().matches(EMAIL_PATTERN) && !email.getText().toString().trim().matches(MobilePattern))  {
                    forgotPasswordErrorMessage.setVisibility(View.VISIBLE);
                    emailErrorField.setVisibility(View.GONE);
                    backIcon.setVisibility(View.GONE);
                    email.setBackgroundResource(R.drawable.edit_textbox);
                }
                else{
                    final Dialog dialog=new Dialog(context);
                    dialog.setContentView(R.layout.alert_dailogbox);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                    TextView alertDailogTitle = (TextView) dialog.findViewById(R.id.alertDailogTitle);
                    alertDailogTitle.setText(getText(R.string.otp_success_title));

                    TextView alertDailogMessage = (TextView) dialog.findViewById(R.id.alertDailogDescription);
                    alertDailogMessage.setText(getText(R.string.forgot_password_dialog_description));

                    FloatingActionButton doneButton= (FloatingActionButton) dialog.findViewById(R.id.done_button);
                    doneButton.setBackgroundTintList(ColorStateList.valueOf(Color
                            .parseColor("#32BDD2")));
                    doneButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            Intent intent=new Intent(getApplicationContext(), ForgotPasswordOTPValidation.class);
                            startActivity(intent);
                        }
                    });

                    dialog.show();

                }
            }
        });

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });

        getOneNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), AppAccessRequest.class);
                startActivity(intent);
            }
        });
    }
}
