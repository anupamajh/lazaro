package com.carmel.guestjini.Screens.Support.KBDetail.KBReviewItem;

import com.carmel.guestjini.Networking.KnowledgeBase.KBReview;
import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

public interface KBReviewItemViewMVC extends ObservableViewMvc<KBReviewItemViewMVC.Listener> {
    public interface Listener {

    }

    void bindReviewItem(KBReview kbReview);
}
