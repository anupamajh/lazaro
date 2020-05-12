package com.carmel.guestjini.Support;


import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Common.EndPoints;
import com.carmel.guestjini.Components.OkHttpClientInstance;
import com.carmel.guestjini.Models.Ticket.KBRating;
import com.carmel.guestjini.Models.Ticket.KBRatingResponse;
import com.carmel.guestjini.Models.Ticket.KBResponse;
import com.carmel.guestjini.Models.Ticket.KBReview;
import com.carmel.guestjini.Models.Ticket.KBReviewResponse;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Services.Authentication.AuthService;
import com.carmel.guestjini.Services.Authentication.AuthServiceHolder;
import com.carmel.guestjini.Services.Ticket.KBRatingPercentResponse;
import com.carmel.guestjini.Services.Ticket.KBRatingService;
import com.carmel.guestjini.Services.Ticket.KBReviewService;
import com.carmel.guestjini.Services.Ticket.KBService;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Adapter.ReviewAdapter;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ArticlesDetailsFragment extends Fragment {
    private RecyclerView reviewRecyclerView;
    private ImageView backArrow;
    private MaterialButton submitButton;
    private TextView ticketAuthorName, ticketName, ticketDate, ticketDescription, reviewEditText,likeIconPercentage,unlikeIconPercentage;
    CircleImageView likeIcon, unlikeIcon;
    MediaController mediaController;
    ArrayList<KBReview> reviewModelArrayList = new ArrayList<>();
    String kbId;
    ReviewAdapter reviewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_articles_details, container, false);
        reviewRecyclerView = rootView.findViewById(R.id.reviewerRecyclerView);
        backArrow = rootView.findViewById(R.id.leftArrowMark);
        submitButton = rootView.findViewById(R.id.writeReviewSubmitButton);
        ticketAuthorName = rootView.findViewById(R.id.ticketAuthorName);
        ticketName = rootView.findViewById(R.id.ticketName);
        ticketDescription = rootView.findViewById(R.id.textView7);
        reviewEditText = rootView.findViewById(R.id.reviewEditText);
        likeIcon = rootView.findViewById(R.id.likeIcon);
        unlikeIcon = rootView.findViewById(R.id.unlikeIcon);
        ticketDate = rootView.findViewById(R.id.ticketDate);
        likeIconPercentage = rootView.findViewById(R.id.likeIconPercentage);
        unlikeIconPercentage = rootView.findViewById(R.id.dislikeIconPercentage);
        mediaController = new MediaController(getContext());

        VideoView videoView = rootView.findViewById(R.id.videoView);
        String videoPath = "android.resource://com.carmel.guestjini/" + R.raw.video;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        videoView.start();

        final Bundle bundle = this.getArguments();
        if (bundle != null) {
            kbId = bundle.getString("kbId");

//         String   ticket_author_name  = bundle.getString("ticket_author_name");
//         String   ticket_name  = bundle.getString("ticket_name");
//         String   ticket_date  = bundle.getString("ticket_date");
////            ticket_value  = bundle.getString("ticket_value");
//
//            ticketAuthorName.setText(ticket_author_name);
//            ticketName.setText(ticket_name);
//            ticketDate.setText(ticket_date);
//            ticketValue.setText(ticket_value);
        }
        getKbById(kbId);

        likeIcon.setOnClickListener(new View.OnClickListener() {
            private boolean flag = true;

            @Override
            public void onClick(View v) {
                if (flag) {
                    flag = false;
                    likeIcon.setImageResource(R.drawable.like_icon_black_xxhdpi);
                    saveKBRating(kbId, null);
                } else {
                    flag = true;
                    likeIcon.setImageResource(R.drawable.like_icon_xxhdpi);
                    saveKBRating(kbId, 1);
                }

            }
        });

        unlikeIcon.setOnClickListener(new View.OnClickListener() {
            private boolean flag = true;

            @Override
            public void onClick(View v) {
                if (flag) {
                    flag = false;
                    unlikeIcon.setImageResource(R.drawable.unlike_icon_black_xxhdpi);
                    saveKBRating(kbId, null);
                } else {
                    flag = true;
                    unlikeIcon.setImageResource(R.drawable.unlike_icon_xxhdpi);
                    saveKBRating(kbId, 0);
                }

            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveArticleReview(kbId);
            }
        });


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        reviewRecyclerView.setLayoutManager(linearLayoutManager);
        reviewAdapter = new ReviewAdapter(getContext(), reviewModelArrayList);
        reviewRecyclerView.setAdapter(reviewAdapter);


        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExploreFragment exploreFragment = new ExploreFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.SuppotPlaceHolder, exploreFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        getKBReviews(kbId);
        getKBRating(kbId);
        getKBRatingPercentage(kbId);
        return rootView;
    }

    private void getKbById(String kbId) {
        try {
            AuthServiceHolder authServiceHolder = new AuthServiceHolder();
            SharedPreferences preferences = getContext().getSharedPreferences("GuestJini", Context.MODE_PRIVATE);
            String accessToken = preferences.getString("access_token", "");
            OkHttpClient okHttpClient = new OkHttpClientInstance.Builder(getActivity(), authServiceHolder)
                    .addHeader("Authorization", accessToken)
                    .build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(EndPoints.END_POINT_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
            AuthService authService = retrofit.create(AuthService.class);
            authServiceHolder.set(authService);
            KBService kbService = retrofit.create(KBService.class);
            Map<String, String> postData = new HashMap<>();
            postData.put("id", kbId);
            Call<KBResponse> kbResponseCall = kbService.getById(postData);
            kbResponseCall.enqueue(new Callback<KBResponse>() {
                @Override
                public void onResponse(Call<KBResponse> call, Response<KBResponse> response) {
                    try {
                        KBResponse kbResponse = response.body();
                        if (kbResponse.getSuccess()) {
                            ticketAuthorName.setText(kbResponse.getKb().getAuthorName());
                            ticketName.setText(kbResponse.getKb().getTopicTitle());
                            //TODO: Format Date
                            ticketDate.setText(kbResponse.getKb().getCreationTime());
                            ticketDescription.setText(kbResponse.getKb().getTopicNarration());
                        } else {
                            //TODO: Show appropriate alert message
                        }

                    } catch (Exception ex) {
                        //TODO: Show appropriate alert message
                    }
                }

                @Override
                public void onFailure(Call<KBResponse> call, Throwable t) {
                    String message = t.getMessage();
                    //TODO: Show appropriate alert message
                }
            });

        } catch (Exception ex) {
            String message = ex.getMessage();

            //TODO: Show appropriate alert
        }
    }

    private void saveArticleReview(String kbId) {

        if (!reviewEditText.getText().toString().trim().equals("")) {
            KBReview kbReview = new KBReview();
            kbReview.setKbId(kbId);
            kbReview.setReviewComment(reviewEditText.getText().toString().trim());

            AuthServiceHolder authServiceHolder = new AuthServiceHolder();
            SharedPreferences preferences = getContext().getSharedPreferences("GuestJini", Context.MODE_PRIVATE);
            String accessToken = preferences.getString("access_token", "");
            OkHttpClient okHttpClient = new OkHttpClientInstance.Builder(getActivity(), authServiceHolder)
                    .addHeader("Authorization", accessToken)
                    .build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(EndPoints.END_POINT_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
            AuthService authService = retrofit.create(AuthService.class);
            authServiceHolder.set(authService);

            KBReviewService kbReviewService = retrofit.create(KBReviewService.class);
            Call<KBResponse> kbResponseCall = kbReviewService.save(kbReview);
            kbResponseCall.enqueue(new Callback<KBResponse>() {
                @Override
                public void onResponse(Call<KBResponse> call, Response<KBResponse> response) {
                    try {
                        KBResponse kbResponse = response.body();
                        if (kbResponse.getSuccess()) {
                            getKBReviews(kbId);

                        } else {
                            //TODO: Show appropriate alert
                        }

                    } catch (Exception ex) {
                        //TODO: Show appropriate alert

                    }
                }

                @Override
                public void onFailure(Call<KBResponse> call, Throwable t) {
                    //TODO: Show appropriate alert
                }
            });

        } else {
            //TODO: Show appropriate alert
        }

    }

    private void getKBReviews(String kbId) {
        try {
            AuthServiceHolder authServiceHolder = new AuthServiceHolder();
            SharedPreferences preferences = getContext().getSharedPreferences("GuestJini", Context.MODE_PRIVATE);
            String accessToken = preferences.getString("access_token", "");
            OkHttpClient okHttpClient = new OkHttpClientInstance.Builder(getActivity(), authServiceHolder)
                    .addHeader("Authorization", accessToken)
                    .build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(EndPoints.END_POINT_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
            AuthService authService = retrofit.create(AuthService.class);
            authServiceHolder.set(authService);

            KBReviewService kbReviewService = retrofit.create(KBReviewService.class);
            Map<String, String> postData = new HashMap<>();
            postData.put("kbId", kbId);
            Call<KBReviewResponse> kbResponseCall = kbReviewService.getAll(postData);
            kbResponseCall.enqueue(new Callback<KBReviewResponse>() {
                @Override
                public void onResponse(Call<KBReviewResponse> call, Response<KBReviewResponse> response) {
                    try {
                        KBReviewResponse kbReviewResponse = response.body();
                        if (kbReviewResponse.isSuccess()) {
                            reviewAdapter.update(kbReviewResponse.getKbReviewList());
                        } else {
                            //TODO: Show appropriate alert
                        }
                    } catch (Exception ex) {
                        //TODO: Show appropriate alert
                    }
                }

                @Override
                public void onFailure(Call<KBReviewResponse> call, Throwable t) {
                    //TODO: Show appropriate alert
                }
            });
        } catch (Exception ex) {
            //TODO: Show appropriate alert
        }
    }

    private void saveKBRating(String kbId, Integer isLiked) {
        try {
            AuthServiceHolder authServiceHolder = new AuthServiceHolder();
            SharedPreferences preferences = getContext().getSharedPreferences("GuestJini", Context.MODE_PRIVATE);
            String accessToken = preferences.getString("access_token", "");
            OkHttpClient okHttpClient = new OkHttpClientInstance.Builder(getActivity(), authServiceHolder)
                    .addHeader("Authorization", accessToken)
                    .build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(EndPoints.END_POINT_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
            AuthService authService = retrofit.create(AuthService.class);
            authServiceHolder.set(authService);

            KBRatingService kbRatingService = retrofit.create(KBRatingService.class);
            KBRating kbRating = new KBRating();
            kbRating.setKbId(kbId);
            kbRating.setIsLiked(isLiked);

            Call<KBRatingResponse> kbRatingResponseCall = kbRatingService.save(kbRating);
            kbRatingResponseCall.enqueue(new Callback<KBRatingResponse>() {
                @Override
                public void onResponse(Call<KBRatingResponse> call, Response<KBRatingResponse> response) {
                    try {
                        KBRatingResponse kbRatingResponse = response.body();
                        if (kbRatingResponse.getSuccess()) {

                        } else {
                            //TODO: Show appropriate alert
                        }
                    } catch (Exception ex) {
                        //TODO: Show appropriate alert
                    }

                }

                @Override
                public void onFailure(Call<KBRatingResponse> call, Throwable t) {
                    String message = t.getMessage();
                    //TODO: Show appropriate alert
                }
            });

        } catch (Exception ex) {
            //TODO: Show appropriate alert
        }
    }

    private void getKBRating(String kbId) {
        try {
            AuthServiceHolder authServiceHolder = new AuthServiceHolder();
            SharedPreferences preferences = getContext().getSharedPreferences("GuestJini", Context.MODE_PRIVATE);
            String accessToken = preferences.getString("access_token", "");
            OkHttpClient okHttpClient = new OkHttpClientInstance.Builder(getActivity(), authServiceHolder)
                    .addHeader("Authorization", accessToken)
                    .build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(EndPoints.END_POINT_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
            AuthService authService = retrofit.create(AuthService.class);
            authServiceHolder.set(authService);

            KBRatingService kbRatingService = retrofit.create(KBRatingService.class);
            Map<String, String> postData = new HashMap<>();
            postData.put("kbId", kbId);
            Call<KBRatingResponse> kbRatingResponseCall = kbRatingService.getRatings(postData);
            kbRatingResponseCall.enqueue(new Callback<KBRatingResponse>() {
                @Override
                public void onResponse(Call<KBRatingResponse> call, Response<KBRatingResponse> response) {
                    try {
                        KBRatingResponse kbRatingResponse = response.body();
                        if (kbRatingResponse.getSuccess()) {
                            if (kbRatingResponse.getKbRating().getIsLiked() == 1) {
                                likeIcon.callOnClick();
                            } else if (kbRatingResponse.getKbRating().getIsLiked() == 0) {
                                unlikeIcon.callOnClick();
                            }

                        } else {
                            //TODO: Show appropriate alert
                        }

                    } catch (Exception ex) {
                        //TODO: Show appropriate alert
                    }
                }

                @Override
                public void onFailure(Call<KBRatingResponse> call, Throwable t) {
                    //TODO: Show appropriate alert
                }
            });

        } catch (Exception ex) {
            //TODO: Show appropriate alert
        }

    }

    private void getKBRatingPercentage(String kbId) {
        try {
            AuthServiceHolder authServiceHolder = new AuthServiceHolder();
            SharedPreferences preferences = getContext().getSharedPreferences("GuestJini", Context.MODE_PRIVATE);
            String accessToken = preferences.getString("access_token", "");
            OkHttpClient okHttpClient = new OkHttpClientInstance.Builder(getActivity(), authServiceHolder)
                    .addHeader("Authorization", accessToken)
                    .build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(EndPoints.END_POINT_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
            AuthService authService = retrofit.create(AuthService.class);
            authServiceHolder.set(authService);
            KBRatingService kbRatingService = retrofit.create(KBRatingService.class);
            Map<String, String> postData = new HashMap<>();
            postData.put("kbId", kbId);
            Call<KBRatingPercentResponse> kbRatingPercentResponseCall = kbRatingService.getRatingPercentage(postData);
            kbRatingPercentResponseCall.enqueue(new Callback<KBRatingPercentResponse>() {
                @Override
                public void onResponse(Call<KBRatingPercentResponse> call, Response<KBRatingPercentResponse> response) {
                    try {
                        KBRatingPercentResponse kbRatingPercentResponse = response.body();
                        if(kbRatingPercentResponse.getSuccess()){
                            likeIconPercentage.setText(String.valueOf(kbRatingPercentResponse.likedPercent));
                            unlikeIconPercentage.setText(String.valueOf(kbRatingPercentResponse.disLikedPercent));
                        }

                    } catch (Exception ex) {
                        //TODO: Show appropriate alert

                    }
                }

                @Override
                public void onFailure(Call<KBRatingPercentResponse> call, Throwable t) {
                    //TODO: Show appropriate alert
                }
            });

        } catch (Exception ex) {
            //TODO: Show appropriate alert
        }
    }
}
