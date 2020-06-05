package com.carmel.guestjini.Screens.Support.KBDetail;

import com.carmel.guestjini.Networking.KnowledgeBase.KB;
import com.carmel.guestjini.Networking.KnowledgeBase.KBRating;
import com.carmel.guestjini.Networking.KnowledgeBase.KBRatingPercentResponse;
import com.carmel.guestjini.Networking.KnowledgeBase.KBReview;
import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

import java.util.List;

public interface KBDetailViewMVC extends ObservableViewMvc<KBDetailViewMVC.Listener> {

    public interface Listener {

        void onBackClicked();

        void onLikeClicked(String kbId, Integer isLiked);

        void onDislikeClicked(String kbId, Integer isDisliked);

        void onSubmitReviewClicked(String kbId, String strReview);

    }

    void bindKB(KB kb);

    void bindKBRating(KBRating kbRating);

    void bindKBReview(List<KBReview> kbReviewList);

    void bindKBRatingPercentage(KBRatingPercentResponse kbRatingPercentResponse);

    void clearReviewText();

    void showProgressIndication();

    void hideProgressIndication();

}
