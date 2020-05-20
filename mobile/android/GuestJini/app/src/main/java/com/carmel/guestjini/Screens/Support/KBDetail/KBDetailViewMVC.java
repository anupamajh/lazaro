package com.carmel.guestjini.Screens.Support.KBDetail;

import com.carmel.guestjini.Networking.KnowledgeBase.KB;
import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

public interface KBDetailViewMVC extends ObservableViewMvc<KBDetailViewMVC.Listener> {
    public interface Listener {

        void onBackClicked();

        void onLikeClicked(String kbId);

        void onDislikeClicked(String kbId);

        void onSubmitReviewClicked(String kbId, String strReview);

    }

    void bindKB(KB kb);

    void showProgressIndication();

    void hideProgressIndication();

}
