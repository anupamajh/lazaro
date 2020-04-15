package com.carmel.guestjini.Settings;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.carmel.guestjini.Login;
import com.carmel.guestjini.R;


public class SettingsLandingFragment extends Fragment {

ConstraintLayout changePasswordLayout,myProfileLayout,logOutLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootview=inflater.inflate(R.layout.fragment_settings_landing, container, false);
        changePasswordLayout=rootview.findViewById(R.id.changePasswordLayout);
        myProfileLayout=rootview.findViewById(R.id.myProfileLayout);
        logOutLayout=rootview.findViewById(R.id.logOutLayout);
        changePasswordLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangePasswordFragment changePasswordFragment=new ChangePasswordFragment();
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.SettingsPlaceHolder,changePasswordFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        myProfileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyProfileFragment myProfileFragment=new MyProfileFragment();
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.SettingsPlaceHolder,myProfileFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        logOutLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), Login.class);
                startActivity(intent);
                Toast toast=new Toast(getContext());
                ViewGroup viewGroup = rootview.findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.toast_layout, viewGroup, false);
                TextView text = (TextView) dialogView.findViewById(R.id.visibleToast);
                text.setText("Logged out successfully.");
                toast.setView(dialogView);
                toast.makeText(getActivity(),"", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.getAbsoluteGravity(0,0),0,700);
                toast.show();
            }
        });

        return rootview;
    }

}
