package com.carmel.guestjini.Screens.Support.KBDetail.KBReviewItem;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.carmel.guestjini.Networking.KnowledgeBase.KBReview;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

public class KBReviewItemViewMVCImpl
        extends BaseObservableViewMvc<KBReviewItemViewMVC.Listener>
        implements KBReviewItemViewMVC {

    private final TextView txtReviewerName;
    private final TextView txtReviewDate;
    private final TextView txtReviewText;

    private KBReview kbReview;

    public KBReviewItemViewMVCImpl(
            LayoutInflater inflater,
            @Nullable ViewGroup parent
    ) {
        setRootView(inflater.inflate(R.layout.layout_support_kb_review_item, parent, false));
        txtReviewerName = findViewById(R.id.txtReviewerName);
        txtReviewDate = findViewById(R.id.txtReviewDate);
        txtReviewText = findViewById(R.id.txtReviewText);
    }

    @Override
    public void bindReviewItem(KBReview kbReview) {
        txtReviewerName.setText(kbReview.getReviewByName());
        txtReviewDate.setText(kbReview.getCreationTime());
        txtReviewText.setText(kbReview.getReviewComment());
        //TODO: find a nice way to bind image
    }
}
