package com.carmel.guestjini.Support;


import android.app.Dialog;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.carmel.guestjini.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DraftViewFragment extends Fragment {
  private ImageView leftArrowMark;
  private MaterialCardView attachmentsCardView;
  private MaterialButton submitButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_draft_view, container, false);
        leftArrowMark=rootView.findViewById(R.id.leftArrowMark);
        attachmentsCardView=rootView.findViewById(R.id.attchmentCardView);
        submitButton=rootView.findViewById(R.id.newTicketSubmitButton);

        leftArrowMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyTicketsRecyclerViewFragment myTicketsRecyclerViewFragment=new MyTicketsRecyclerViewFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.SuppotPlaceHolder, myTicketsRecyclerViewFragment);
                fragmentTransaction.commit();
            }
        });

        attachmentsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AttachFilesFragment attachFilesFragment=new AttachFilesFragment();
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.SuppotPlaceHolder,attachFilesFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
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
                                    MyTicketsRecyclerViewFragment myTicketsRecyclerViewFragment=new MyTicketsRecyclerViewFragment();
                                    FragmentManager fragmentManager=getFragmentManager();
                                    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                                    fragmentTransaction.replace(R.id.SuppotPlaceHolder,myTicketsRecyclerViewFragment);
                                    fragmentTransaction.addToBackStack(null);
                                    fragmentTransaction.commit();
                                }
                            });
                            dialog.show();
                        }
                    });

                    dialog.show();
            }
        });
        return rootView;
    }

}
