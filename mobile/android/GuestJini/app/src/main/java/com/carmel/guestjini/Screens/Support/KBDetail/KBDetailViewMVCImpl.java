package com.carmel.guestjini.Screens.Support.KBDetail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Networking.KnowledgeBase.KB;
import com.carmel.guestjini.Networking.KnowledgeBase.KBRating;
import com.carmel.guestjini.Networking.KnowledgeBase.KBRatingPercentResponse;
import com.carmel.guestjini.Networking.KnowledgeBase.KBReview;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.ViewMVCFactory;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;
import com.google.android.material.button.MaterialButton;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class KBDetailViewMVCImpl
        extends BaseObservableViewMvc<KBDetailViewMVC.Listener>
        implements KBDetailViewMVC,
        KBReviewRecyclerAdapter.Listener {

    private final TextView txtKBAuthorName;
    private final TextView txtKBDate;
    private final TextView txtKBTitle;
    private final TextView txtKBNarration;
    private final TextView txtLikePercentage;
    private final TextView txtDislikePercentage;
    private final ProgressBar progressBar;
    private final CircleImageView btnLikeIcon;
    private final CircleImageView btnDislikeIcon;
    private final RecyclerView kbReviewRecyclerView;
    private final KBReviewRecyclerAdapter kbReviewRecyclerAdapter;
    private final MaterialButton btnSubmitReview;
    private EditText txtReviewText;

    private String kbId;

    public KBDetailViewMVCImpl(
            LayoutInflater inflater,
            @Nullable ViewGroup parent,
            ViewMVCFactory viewMVCFactory
    ) {
        setRootView(inflater.inflate(R.layout.layout_support_kb_detail, parent, false));
        kbReviewRecyclerView = findViewById(R.id.lstKBReview);
        kbReviewRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        kbReviewRecyclerAdapter = new KBReviewRecyclerAdapter(this, viewMVCFactory);
        kbReviewRecyclerView.setAdapter(kbReviewRecyclerAdapter);
        txtKBAuthorName = findViewById(R.id.txtKBAuthorName);
        txtKBDate = findViewById(R.id.txtKBDate);
        txtKBTitle = findViewById(R.id.txtKBTitle);
        txtKBNarration = findViewById(R.id.txtKBNarration);
        txtLikePercentage = findViewById(R.id.txtLikePercentage);
        txtDislikePercentage = findViewById(R.id.txtDislikePercentage);
        btnLikeIcon = findViewById(R.id.btnLikeIcon);
        btnDislikeIcon = findViewById(R.id.btnDislikeIcon);
        btnSubmitReview = findViewById(R.id.btnSubmitReview);
        txtReviewText = findViewById(R.id.txtReviewText);
        progressBar = findViewById(R.id.progress);
        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onBackClicked();
            }
        });

        btnSubmitReview.setOnClickListener(view -> {
            if (!txtReviewText.getText().toString().trim().equals("")) {
                for (Listener listener : getListeners()) {
                    listener.onSubmitReviewClicked(kbId, txtReviewText.getText().toString().trim());
                }
            }
        });

        btnLikeIcon.setOnClickListener(new View.OnClickListener() {
            private boolean flag = true;

            @Override
            public void onClick(View view) {
                if (flag) {
                    flag = false;
                    btnLikeIcon.setImageResource(R.drawable.like_icon_black_xxhdpi);
                    for (Listener listener : getListeners()) {
                        listener.onLikeClicked(kbId, 1);
                    }
                } else {
                    flag = true;
                    btnLikeIcon.setImageResource(R.drawable.like_icon_xxhdpi);
                    for (Listener listener : getListeners()) {
                        listener.onLikeClicked(kbId, null);
                    }
                }
            }
        });

        btnDislikeIcon.setOnClickListener(new View.OnClickListener() {
            private boolean flag = true;

            @Override
            public void onClick(View view) {
                if (flag) {
                    flag = false;
                    btnDislikeIcon.setImageResource(R.drawable.unlike_icon_black_xxhdpi);
                    for (Listener listener : getListeners()) {
                        listener.onDislikeClicked(kbId, 0);
                    }
                } else {
                    flag = true;
                    btnDislikeIcon.setImageResource(R.drawable.unlike_icon_xxhdpi);
                    for (Listener listener : getListeners()) {
                        listener.onDislikeClicked(kbId, null);
                    }
                }
            }
        });
    }


    @Override
    public void bindKB(KB kb) {
        this.kbId = kb.getId();
        txtKBAuthorName.setText(kb.getAuthorName());
        txtKBDate.setText(kb.getCreationTime()); //TODO: Format the date
        txtKBTitle.setText(kb.getTopicTitle());
        txtKBNarration.setText(kb.getTopicNarration());
    }

    @Override
    public void bindKBRating(KBRating kbRating) {
        if (kbRating.getIsLiked() == 0) {
            btnDislikeIcon.callOnClick();
        } else if (kbRating.getIsLiked() == 1) {
            btnDislikeIcon.callOnClick();
        }
    }

    @Override
    public void bindKBReview(List<KBReview> kbReviewList) {
        kbReviewRecyclerAdapter.bindKBReviews(kbReviewList);
    }

    @Override
    public void bindKBRatingPercentage(KBRatingPercentResponse kbRatingPercentResponse) {
        txtLikePercentage.setText(String.valueOf(kbRatingPercentResponse.likedPercent));
        txtDislikePercentage.setText(String.valueOf(kbRatingPercentResponse.disLikedPercent));
    }

    @Override
    public void showProgressIndication() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressIndication() {
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void clearReviewText() {
        txtReviewText.setText("");
    }
}
