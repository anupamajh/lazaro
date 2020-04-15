package com.carmel.guestjini.Profile;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.carmel.guestjini.CommunityActivity;
import com.carmel.guestjini.MainActivity;
import com.carmel.guestjini.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment {
    ImageView editImage,drawerCameraIcon,drawerGalleryIcon,profileToggleButton,backArrow;
    Button setupButton;
    RelativeLayout cameraLayout,galleryLayout;
    DrawerLayout drawerLayout;
    ImageView genderToggleButton,ageToggleButton,mobileNoToggleButton,emailToggleButton,placeToggleButton;
    TextView uploadYourPhotoText;
    FloatingActionButton profileEditIcon;
    CircleImageView cameraIcon;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     final  View rootView=inflater.inflate(R.layout.fragment_profile, container, false);
       cameraIcon=rootView.findViewById(R.id.cameraIcon);
       drawerLayout=rootView.findViewById(R.id.profileDrawerLayout);
        setupButton=rootView.findViewById(R.id.setupButton);
        editImage=rootView.findViewById(R.id.editIcon);
        drawerCameraIcon=rootView.findViewById(R.id.cameraImage);
        drawerGalleryIcon=rootView.findViewById(R.id.galleryImage);
        genderToggleButton=rootView.findViewById(R.id.genderToggleIcon);

        ageToggleButton=rootView.findViewById(R.id.ageToggleIcon);
        mobileNoToggleButton=rootView.findViewById(R.id.mobileNoToggleIcon);
        emailToggleButton=rootView.findViewById(R.id.emailToggleIcon);
        placeToggleButton=rootView.findViewById(R.id.yourPlaceToggleIcon);
        cameraLayout=rootView.findViewById(R.id.cameraLayout);
        galleryLayout=rootView.findViewById(R.id.galleryLayout);

        uploadYourPhotoText=rootView.findViewById(R.id.uploadYourPhotoText);
        profileEditIcon=rootView.findViewById(R.id.profileEdit);
        profileToggleButton=rootView.findViewById(R.id.profileToggleButton);
        backArrow=rootView.findViewById(R.id.leftArrowMark);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext().getApplicationContext(), CommunityActivity.class);
                startActivity(intent);
            }
        });

        editImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.place_of_origin_dailog_box);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                ImageView doneButton = (ImageView) dialog.findViewById(R.id.originDoneButton);
                doneButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();

            }
        });
        profileEditIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerCameraIcon.setImageResource(R.drawable.round_edit_icon_xhdpi);
                drawerGalleryIcon.setImageResource(R.drawable.delete_icon_xhdpi);
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        genderToggleButton.setOnClickListener(new View.OnClickListener() {
            private boolean flag = true;
            @Override
            public void onClick(View v) {
                    if(flag)
                    {
                        genderToggleButton.setImageResource(R.drawable.toggle_icon_on_xxhdpi);
                        flag=false;
                        Toast toast=new Toast(getContext());
                        ViewGroup viewGroup = rootView.findViewById(android.R.id.content);
                        View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.toast_layout, viewGroup, false);
                        TextView text = (TextView) dialogView.findViewById(R.id.visibleToast);
                        text.setText("VISIBLE - Others can see your Gender and you can see others Gender.");
                        toast.setView(dialogView);
                        toast.makeText(getActivity(),"", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.getAbsoluteGravity(0,0),0,520);
                        toast.show();
                    }
                    else
                    {
                        flag=true;
                        genderToggleButton.setImageResource(R.drawable.toggle_icon_off_xxhdpi);
                        Toast toast=new Toast(getContext());
                        ViewGroup viewGroup = rootView.findViewById(android.R.id.content);
                        View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.toast_layout, viewGroup, false);
                        TextView text = (TextView) dialogView.findViewById(R.id.visibleToast);
                        text.setText("HIDDEN - Others can't see your Gender and you can't see others Gender.");
                        toast.setView(dialogView);
                        toast.makeText(getActivity(),"", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.getAbsoluteGravity(0,0),0,520);
                        toast.show();
                    }
                }
            });

        ageToggleButton.setOnClickListener(new View.OnClickListener() {
            private boolean flag = true;
            @Override
            public void onClick(View v) {

                if(flag)
                {
                    ageToggleButton.setImageResource(R.drawable.toggle_icon_on_xxhdpi);
                    flag=false;
                    Toast toast=new Toast(getContext());
                    ViewGroup viewGroup = rootView.findViewById(android.R.id.content);
                    View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.toast_layout, viewGroup, false);
                    TextView text = (TextView) dialogView.findViewById(R.id.visibleToast);
                    text.setText("VISIBLE - Others can see your Age and you can see others Age.");
                    toast.setView(dialogView);
                    toast.makeText(getActivity(),"", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.getAbsoluteGravity(0,0),0,520);
                    toast.show();
                }
                else
                {
                    flag=true;
                    ageToggleButton.setImageResource(R.drawable.toggle_icon_off_xxhdpi);
                    Toast toast=new Toast(getContext());
                    ViewGroup viewGroup = rootView.findViewById(android.R.id.content);
                    View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.toast_layout, viewGroup, false);
                    TextView text = (TextView) dialogView.findViewById(R.id.visibleToast);
                    text.setText("HIDDEN - Others can't see your Age and you can't see others Age.");
                    toast.setView(dialogView);
                    toast.makeText(getActivity(),"", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.getAbsoluteGravity(0,0),0,520);
                    toast.show();
                }
            }
        });
        mobileNoToggleButton.setOnClickListener(new View.OnClickListener() {
            private boolean flag = true;
            @Override
            public void onClick(View v) {
                if(flag)
                {
                    mobileNoToggleButton.setImageResource(R.drawable.toggle_icon_on_xxhdpi);
                    flag=false;
                    Toast toast=new Toast(getContext());
                    ViewGroup viewGroup = rootView.findViewById(android.R.id.content);
                    View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.toast_layout, viewGroup, false);
                    TextView text = (TextView) dialogView.findViewById(R.id.visibleToast);
                    text.setText("VISIBLE - Others can see your Mobile Number and you can see others Mobile Number.");
                    toast.setView(dialogView);
                    toast.makeText(getActivity(),"", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.getAbsoluteGravity(0,0),0,520);
                    toast.show();
                }
                else
                {
                    flag=true;
                    mobileNoToggleButton.setImageResource(R.drawable.toggle_icon_off_xxhdpi);
                    Toast toast=new Toast(getContext());
                    ViewGroup viewGroup = rootView.findViewById(android.R.id.content);
                    View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.toast_layout, viewGroup, false);
                    TextView text = (TextView) dialogView.findViewById(R.id.visibleToast);
                    text.setText("HIDDEN - Others can't see your Mobile Number and you can't see others Mobile Number.");
                    toast.setView(dialogView);
                    toast.makeText(getActivity(),"", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.getAbsoluteGravity(0,0),0,520);
                    toast.show();
                }
            }
        });
        emailToggleButton.setOnClickListener(new View.OnClickListener() {
            private boolean flag = true;
            @Override
            public void onClick(View v) {
                if(flag)
                {
                    emailToggleButton.setImageResource(R.drawable.toggle_icon_on_xxhdpi);
                    flag=false;
                    Toast toast=new Toast(getContext());
                    ViewGroup viewGroup = rootView.findViewById(android.R.id.content);
                    View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.toast_layout, viewGroup, false);
                    TextView text = (TextView) dialogView.findViewById(R.id.visibleToast);
                    text.setText("VISIBLE - Others can see your Email and you can see others Email.");
                    toast.setView(dialogView);
                    toast.makeText(getActivity(),"", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.getAbsoluteGravity(0,0),0,520);
                    toast.show();
                }
                else
                {
                    flag=true;
                    emailToggleButton.setImageResource(R.drawable.toggle_icon_off_xxhdpi);
                    Toast toast=new Toast(getContext());
                    ViewGroup viewGroup = rootView.findViewById(android.R.id.content);
                    View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.toast_layout, viewGroup, false);
                    TextView text = (TextView) dialogView.findViewById(R.id.visibleToast);
                    text.setText("HIDDEN - Others can't see your Email and you can't see others Email.");
                    toast.setView(dialogView);
                    toast.makeText(getActivity(),"", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.getAbsoluteGravity(0,0),0,520);
                    toast.show();
                }
            }
        });

        placeToggleButton.setOnClickListener(new View.OnClickListener() {
            private boolean flag = true;
            @Override
            public void onClick(View v) {
                if(flag)
                {
                    placeToggleButton.setImageResource(R.drawable.toggle_icon_on_xxhdpi);
                    flag=false;
                    Toast toast=new Toast(getContext());
                    ViewGroup viewGroup = rootView.findViewById(android.R.id.content);
                    View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.toast_layout, viewGroup, false);
                    TextView text = (TextView) dialogView.findViewById(R.id.visibleToast);
                    text.setText("VISIBLE - Others can see your Place and you can see others Place.");
                    toast.setView(dialogView);
                    toast.makeText(getActivity(),"", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.getAbsoluteGravity(0,0),0,520);
                    toast.show();
                }
                else
                {
                    flag=true;
                    placeToggleButton.setImageResource(R.drawable.toggle_icon_off_xxhdpi);
                    Toast toast=new Toast(getContext());
                    ViewGroup viewGroup = rootView.findViewById(android.R.id.content);
                    View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.toast_layout, viewGroup, false);
                    TextView text = (TextView) dialogView.findViewById(R.id.visibleToast);
                    text.setText("HIDDEN - Others can't see your Place and you can't see others Place.");
                    toast.setView(dialogView);
                    toast.makeText(getActivity(),"", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.getAbsoluteGravity(0,0),0,520);
                    toast.show();
                }
            }
        });
        profileToggleButton.setOnClickListener(new View.OnClickListener() {
            private boolean flag = true;
            @Override
            public void onClick(View v) {
                if(flag){
                    flag=false;
                    profileToggleButton.setImageResource(R.drawable.toggle_icon_on_xxhdpi);
                    Toast toast=new Toast(getContext());
                    ViewGroup viewGroup = rootView.findViewById(android.R.id.content);
                    View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.toast_layout, viewGroup, false);
                    TextView text = (TextView) dialogView.findViewById(R.id.visibleToast);
                    text.setText("VISIBLE - Others can see your Profile and you can see others Profile.");
                    toast.setView(dialogView);
                    toast.makeText(getActivity(),"", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.getAbsoluteGravity(0,0),0,520);
                    toast.show();
                } else
                {
                    flag=true;
                    profileToggleButton.setImageResource(R.drawable.toggle_icon_off_xxhdpi);
                    Toast toast=new Toast(getContext());
                    ViewGroup viewGroup = rootView.findViewById(android.R.id.content);
                    View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.toast_layout, viewGroup, false);
                    TextView text = (TextView) dialogView.findViewById(R.id.visibleToast);
                    text.setText("HIDDEN - Others can't see your Profile and you can't see others Profile.");
                    toast.setView(dialogView);
                    toast.makeText(getActivity(),"", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.getAbsoluteGravity(0,0),0,520);
                    toast.show();
                }

            }
        });

        cameraLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(Gravity.LEFT);
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.alert_dailog_box);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                TextView alertDailogTitle = (TextView) dialog.findViewById(R.id.alertBoxTitle);
                alertDailogTitle.setText("FAILED");
                alertDailogTitle.setTextColor(Color.parseColor("#E65959"));

                TextView alertDailogMessage = (TextView) dialog.findViewById(R.id.alertBoxMessage);
                alertDailogMessage.setText("There was some error while uploading, Please try again!!");

                ImageView alertDailogDoneButton = (ImageView) dialog.findViewById(R.id.doneButton);
                alertDailogDoneButton.setImageResource(R.drawable.not_done_icon_xxhdpi);

                ImageView doneButton = (ImageView) dialog.findViewById(R.id.doneButton);
                doneButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        cameraIcon.setImageResource(R.drawable.profile_image);
                               uploadYourPhotoText.setVisibility(View.GONE);
                               profileEditIcon.setVisibility(View.VISIBLE);
                               profileToggleButton.setVisibility(View.VISIBLE);
//                        final Dialog dialog = new Dialog(getContext());
//                        dialog.setContentView(R.layout.alert_dailog_box);
//                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//
//                        TextView alertDailogTitle = (TextView) dialog.findViewById(R.id.alertBoxTitle);
//                        alertDailogTitle.setText("SUCCESS");
//
//                        TextView alertDailogMessage = (TextView) dialog.findViewById(R.id.alertBoxMessage);
//                        alertDailogMessage.setText("Your profile picture has been successfully updated.");
//
//                        ImageView alertDailogDoneButton = (ImageView) dialog.findViewById(R.id.doneButton);
//                        alertDailogDoneButton.setImageResource(R.drawable.done_icon);
//
//                        ImageView doneButton = (ImageView) dialog.findViewById(R.id.doneButton);
//                        doneButton.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                dialog.dismiss();
//                               cameraIcon.setImageResource(R.drawable.profile_image);
//                               uploadYourPhotoText.setVisibility(View.GONE);
//                               profileEditIcon.setVisibility(View.VISIBLE);
//                               profileToggleButton.setVisibility(View.VISIBLE);
//
//                            }
//                        });
//                        dialog.show();
                    }
                });
                dialog.show();

            }
        });
       galleryLayout.setOnClickListener(new View.OnClickListener() {
           private boolean flag=true;
            @Override
            public void onClick(View v) {
                if(flag) {
                    flag=false;
                    drawerLayout.closeDrawer(Gravity.LEFT);
                    final Dialog dialog = new Dialog(getContext());
                    dialog.setContentView(R.layout.alert_dailog_box);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                    TextView alertDailogTitle = (TextView) dialog.findViewById(R.id.alertBoxTitle);
                    alertDailogTitle.setText("FAILED");
                    alertDailogTitle.setTextColor(Color.parseColor("#E65959"));

                    TextView alertDailogMessage = (TextView) dialog.findViewById(R.id.alertBoxMessage);
                    alertDailogMessage.setText("There was some error while uploading, Please try again!!");

                    ImageView alertDailogDoneButton = (ImageView) dialog.findViewById(R.id.doneButton);
                    alertDailogDoneButton.setImageResource(R.drawable.not_done_icon_xxhdpi);

                    ImageView doneButton = (ImageView) dialog.findViewById(R.id.doneButton);
                    doneButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            cameraIcon.setImageResource(R.drawable.profile_image);
                                    uploadYourPhotoText.setVisibility(View.GONE);
                                    profileEditIcon.setVisibility(View.VISIBLE);
                                    profileToggleButton.setVisibility(View.VISIBLE);
//                            final Dialog dialog = new Dialog(getContext());
//                            dialog.setContentView(R.layout.alert_dailog_box);
//                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//
//                            TextView alertDailogTitle = (TextView) dialog.findViewById(R.id.alertBoxTitle);
//                            alertDailogTitle.setText("SUCCESS");
//
//                            TextView alertDailogMessage = (TextView) dialog.findViewById(R.id.alertBoxMessage);
//                            alertDailogMessage.setText("Your profile picture has been successfully updated.");
//
//                            ImageView alertDailogDoneButton = (ImageView) dialog.findViewById(R.id.doneButton);
//                            alertDailogDoneButton.setImageResource(R.drawable.done_icon);
//
//                            ImageView doneButton = (ImageView) dialog.findViewById(R.id.doneButton);
//                            doneButton.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    dialog.dismiss();
//                                    cameraIcon.setImageResource(R.drawable.profile_image);
//                                    uploadYourPhotoText.setVisibility(View.GONE);
//                                    profileEditIcon.setVisibility(View.VISIBLE);
//                                    profileToggleButton.setVisibility(View.VISIBLE);
//                                }
//                            });
//                            dialog.show();
                        }
                    });
                    dialog.show();
                }
                else
                    {
                        flag=true;
                        galleryLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            drawerLayout.closeDrawer(Gravity.LEFT);
                            final Dialog dialog = new Dialog(getContext());
                            dialog.setContentView(R.layout.alert_dailog_box);
                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            TextView alertDailogTitle = (TextView) dialog.findViewById(R.id.alertBoxTitle);
                            alertDailogTitle.setText("DELETE?");
                            alertDailogTitle.setTextColor(Color.parseColor("#E65959"));

                            TextView alertDailogMessage = (TextView) dialog.findViewById(R.id.alertBoxMessage);
                            alertDailogMessage.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec dignissim felis et magna mattis finibus. Nulla elit ligula, placerat tincidunt ipsum eu, ornare semper felis.");

                            ImageView alertDailogDoneButton = (ImageView) dialog.findViewById(R.id.doneButton);
                            alertDailogDoneButton.setImageResource(R.drawable.not_done_icon_xxhdpi);

                            ImageView doneButton = (ImageView) dialog.findViewById(R.id.doneButton);
                            doneButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                    dialog.dismiss();

                                 }
                             });
                            dialog.show();
                        }
                        });
                    }
                }
            });
        setupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              ProfileInterestsFragment profileInterestsFragment=new ProfileInterestsFragment();
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.add(R.id.profilePlaceHolder,profileInterestsFragment).commit();
            }
        });
        cameraIcon.setOnClickListener(new View.OnClickListener() {
            private boolean flag=true;
            @Override
            public void onClick(View v) {
                if(flag) {
                    flag=false;
                    drawerLayout.openDrawer(GravityCompat.START);
                }else{
                    flag=true;
                    drawerLayout.openDrawer(GravityCompat.START);
                    drawerCameraIcon.setImageResource(R.drawable.round_edit_icon_xhdpi);
                    drawerGalleryIcon.setImageResource(R.drawable.delete_icon_xhdpi);
                    drawerGalleryIcon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            drawerLayout.closeDrawer(Gravity.LEFT);
                            final Dialog dialog = new Dialog(getContext());
                            dialog.setContentView(R.layout.alert_dailog_box);
                            TextView alertDailogTitle = (TextView) dialog.findViewById(R.id.alertBoxTitle);
                            alertDailogTitle.setText("DELETE?");
                            alertDailogTitle.setTextColor(Color.parseColor("#E65959"));

                            TextView alertDailogMessage = (TextView) dialog.findViewById(R.id.alertBoxMessage);
                            alertDailogMessage.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec dignissim felis et magna mattis finibus. Nulla elit ligula, placerat tincidunt ipsum eu, ornare semper felis.");

                            ImageView alertDailogDoneButton = (ImageView) dialog.findViewById(R.id.doneButton);
                            alertDailogDoneButton.setImageResource(R.drawable.not_done_icon_xxhdpi);

                            ImageView doneButton = (ImageView) dialog.findViewById(R.id.doneButton);
                            doneButton.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dialog.dismiss();
                                        }
                                    });
                            dialog.show();
                        }
                    });

                }
            }
        });
       return rootView;
    }
}
