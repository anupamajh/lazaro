package com.carmel.guestjini.Accounts;


import android.app.Dialog;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.carmel.guestjini.R;
import com.carmel.guestjini.Support.MyTicketsRecyclerViewFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DebitCreditCardFragment extends Fragment {
    private Button payButton;
    private EditText cardNumberEditText,expiryDateEditText,cvvEditText,cardHolderNameEditText;
    private TextView cardNumberErrorField,expiryDateErrorField,cvvErrorField,cardHolderNameErrorField;
    private ConstraintLayout debitCreditMainLayout,processingLayout;
    private TextView amount;
    private ImageView backArrow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview= inflater.inflate(R.layout.fragment_debit_credit_card, container, false);
        payButton=rootview.findViewById(R.id.payButton);
        cardNumberEditText=rootview.findViewById(R.id.cardNumberEditText);
        expiryDateEditText=rootview.findViewById(R.id.expiryDateEditText);
        cvvEditText=rootview.findViewById(R.id.cvvEditText);
        cardHolderNameEditText=rootview.findViewById(R.id.cardHolderNameEditText);

        cardNumberErrorField=rootview.findViewById(R.id.cardNumberErrorField);
        expiryDateErrorField=rootview.findViewById(R.id.expiryDateErrorField);
        cvvErrorField=rootview.findViewById(R.id.cvvErrorField);
        cardHolderNameErrorField=rootview.findViewById(R.id.cardHolderNameErrorField);
        debitCreditMainLayout=rootview.findViewById(R.id.debitCreditMainLayout);
        processingLayout=rootview.findViewById(R.id.processingLayout);
        amount=rootview.findViewById(R.id.amount);
        backArrow=rootview.findViewById(R.id.backArrow);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaymentOptionsFragment paymentOptionsFragment=new PaymentOptionsFragment();
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.AccountsPlaceHolder,paymentOptionsFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        final String Amount=amount.getText().toString();
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cardNumberEditText.getText().toString().trim().length()==0 && expiryDateEditText.getText().toString().trim().length()==0
                && cvvEditText.getText().toString().trim().length()==0 && cardHolderNameEditText.getText().toString().trim().length()==0)
                {
                    cardNumberErrorField.setVisibility(View.VISIBLE);
                    expiryDateErrorField.setVisibility(View.VISIBLE);
                    cvvErrorField.setVisibility(View.VISIBLE);
                    cardHolderNameErrorField.setVisibility(View.VISIBLE);
//                    cardNumberEditText.setBackgroundResource(0);
                    cardNumberEditText.setBackgroundResource(R.drawable.card_red_edit_box);
                    expiryDateEditText.setBackgroundResource(R.drawable.card_red_edit_box);
                    cvvEditText.setBackgroundResource(R.drawable.card_red_edit_box);
                    cardHolderNameEditText.setBackgroundResource(R.drawable.card_red_edit_box);
                }else{
                    payButton.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.colorSummerSky));
                    cardNumberEditText.setBackgroundResource(R.drawable.card_edit_box);
                    expiryDateEditText.setBackgroundResource(R.drawable.card_edit_box);
                    cvvEditText.setBackgroundResource(R.drawable.card_edit_box);
                    cardHolderNameEditText.setBackgroundResource(R.drawable.card_edit_box);
                    cardNumberErrorField.setVisibility(View.GONE);
                    expiryDateErrorField.setVisibility(View.GONE);
                    cvvErrorField.setVisibility(View.GONE);
                    cardHolderNameErrorField.setVisibility(View.GONE);

                    final Dialog dialog=new Dialog(getContext());
                    dialog.setContentView(R.layout.dialogbox_processing);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    LinearLayout linearLayout=(LinearLayout)dialog.findViewById(R.id.proceesDialogLayout);
                    linearLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            debitCreditMainLayout.setVisibility(View.GONE);
                            processingLayout.setVisibility(View.VISIBLE);
                        }
                    });
                    dialog.show();

                }
            }
        });

        processingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog=new Dialog(getContext());
                dialog.setContentView(R.layout.alert_dailogbox);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                TextView alertDailogTitle = (TextView) dialog.findViewById(R.id.alertDailogTitle);
                alertDailogTitle.setText(getText(R.string.failed));
                alertDailogTitle.setTextColor(Color.parseColor("#E65959"));

                TextView alertDailogMessage = (TextView) dialog.findViewById(R.id.alertDailogDescription);
                alertDailogMessage.setText(R.string.ticket_failed);

                FloatingActionButton doneButton= (FloatingActionButton) dialog.findViewById(R.id.done_button);
                doneButton.setBackgroundTintList(ColorStateList.valueOf(Color
                        .parseColor("#E65959")));

                doneButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                        final Dialog dialog=new Dialog(getContext());
                        dialog.setContentView(R.layout.alert_dailogbox);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                        TextView alertDailogTitle = (TextView) dialog.findViewById(R.id.alertDailogTitle);
                        alertDailogTitle.setText(getText(R.string.success));

                        TextView alertDailogMessage = (TextView) dialog.findViewById(R.id.alertDailogDescription);
                        alertDailogMessage.setText(getText(R.string.ticket_success));

                        FloatingActionButton doneButton= (FloatingActionButton) dialog.findViewById(R.id.done_button);
                        doneButton.setBackgroundTintList(ColorStateList.valueOf(Color
                                .parseColor("#32BDD2")));
                        doneButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                RentInvoiceDetailsFragment rentInvoiceDetailsFragment=new RentInvoiceDetailsFragment();
                                FragmentManager fragmentManager=getFragmentManager();
                                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                                fragmentTransaction.replace(R.id.AccountsPlaceHolder,rentInvoiceDetailsFragment);
                                fragmentTransaction.addToBackStack(null);
                                fragmentTransaction.commit();
//                                Bundle bundle=new Bundle();
//                                bundle.putString("amount",Amount);
//                                rentInvoiceDetailsFragment.setArguments(bundle);
                            }
                        });
                        dialog.show();
                    }
                });

                dialog.show();
            }
        });
        return rootview;
    }

}
