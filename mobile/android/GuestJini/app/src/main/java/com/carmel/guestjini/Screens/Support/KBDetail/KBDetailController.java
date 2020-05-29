package com.carmel.guestjini.Screens.Support.KBDetail;

import com.carmel.guestjini.KnowledgeBase.FetchKBDetailsUseCase;
import com.carmel.guestjini.KnowledgeBase.FetchKBRatingPercentageUseCase;
import com.carmel.guestjini.KnowledgeBase.FetchKBRatingUseCase;
import com.carmel.guestjini.KnowledgeBase.FetchKBReviewListUseCase;
import com.carmel.guestjini.KnowledgeBase.KBDislikeArticleUseCase;
import com.carmel.guestjini.KnowledgeBase.KBLikeArticleUseCase;
import com.carmel.guestjini.KnowledgeBase.SaveKBReviewUseCase;
import com.carmel.guestjini.Networking.KnowledgeBase.KB;
import com.carmel.guestjini.Networking.KnowledgeBase.KBRating;
import com.carmel.guestjini.Networking.KnowledgeBase.KBRatingPercentResponse;
import com.carmel.guestjini.Networking.KnowledgeBase.KBReview;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsEventBus;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsManager;
import com.carmel.guestjini.Screens.Common.Dialogs.PromptDialog.PromptDialogEvent;
import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;

import java.io.Serializable;
import java.util.List;

public class KBDetailController
        implements KBDetailViewMVC.Listener,
        FetchKBDetailsUseCase.Listener,
        FetchKBRatingUseCase.Listener,
        FetchKBRatingPercentageUseCase.Listener,
        FetchKBReviewListUseCase.Listener,
        KBDislikeArticleUseCase.Listener,
        KBLikeArticleUseCase.Listener,
        SaveKBReviewUseCase.Listener,
        DialogsEventBus.Listener {

    private enum ScreenState {
        IDLE, FETCHING_KB_DETAIL, KB_DETAIL_SHOWN, NETWORK_ERROR,
        FETCHING_KB_RATING, KB_RATING_SHOWN,
        FETCHING_KB_RATING_PERCENTAGE, KB_RATING_PERCENTAGE_SHOWN,
        FETCHING_KB_REVIEW_LIST, KB_REVIEW_LIST_SHOWN,
        SAVE_KB_LIKE, KB_LIKE_SAVED,
        SAVE_KB_DISLIKE, KB_DISLIKE_SAVED,
        SAVE_KB_REVIEW, KB_REVIEW_SAVED
    }

    private final FetchKBDetailsUseCase fetchKBDetailsUseCase;
    private final FetchKBRatingUseCase fetchKBRatingUseCase;
    private final FetchKBRatingPercentageUseCase fetchKBRatingPercentageUseCase;
    private final FetchKBReviewListUseCase fetchKBReviewListUseCase;
    private final KBDislikeArticleUseCase kbDislikeArticleUseCase;
    private final KBLikeArticleUseCase kbLikeArticleUseCase;
    private final SaveKBReviewUseCase saveKBReviewUseCase;
    private final ScreensNavigator screensNavigator;
    private final DialogsManager dialogsManager;
    private final DialogsEventBus dialogsEventBus;

    private KBDetailViewMVC viewMVC;

    private ScreenState mScreenState = ScreenState.IDLE;

    private String kbId;

    public KBDetailController(
            FetchKBDetailsUseCase fetchKBDetailsUseCase,
            FetchKBRatingUseCase fetchKBRatingUseCase,
            FetchKBRatingPercentageUseCase fetchKBRatingPercentageUseCase,
            FetchKBReviewListUseCase fetchKBReviewListUseCase,
            KBDislikeArticleUseCase kbDislikeArticleUseCase,
            KBLikeArticleUseCase kbLikeArticleUseCase,
            SaveKBReviewUseCase saveKBReviewUseCase,
            ScreensNavigator screensNavigator,
            DialogsManager dialogsManager,
            DialogsEventBus dialogsEventBus
    ) {
        this.fetchKBDetailsUseCase = fetchKBDetailsUseCase;
        this.fetchKBRatingUseCase = fetchKBRatingUseCase;
        this.fetchKBRatingPercentageUseCase = fetchKBRatingPercentageUseCase;
        this.fetchKBReviewListUseCase = fetchKBReviewListUseCase;
        this.kbDislikeArticleUseCase = kbDislikeArticleUseCase;
        this.kbLikeArticleUseCase = kbLikeArticleUseCase;
        this.saveKBReviewUseCase = saveKBReviewUseCase;
        this.screensNavigator = screensNavigator;
        this.dialogsManager = dialogsManager;
        this.dialogsEventBus = dialogsEventBus;
    }

    public void bindView(KBDetailViewMVC viewMvc) {
        this.viewMVC = viewMvc;
    }

    public SavedState getSavedState() {
        return new SavedState(mScreenState);
    }

    public void restoreSavedState(SavedState savedState) {
        mScreenState = savedState.mScreenState;
    }

    public void onStart(String kbId) {
        viewMVC.registerListener(this);
        fetchKBDetailsUseCase.registerListener(this);
        fetchKBRatingUseCase.registerListener(this);
        fetchKBRatingPercentageUseCase.registerListener(this);
        fetchKBReviewListUseCase.registerListener(this);
        kbDislikeArticleUseCase.registerListener(this);
        kbLikeArticleUseCase.registerListener(this);
        saveKBReviewUseCase.registerListener(this);
        dialogsEventBus.registerListener(this);
        this.kbId = kbId;

        if (mScreenState != ScreenState.NETWORK_ERROR) {
            fetchKBDetailsAndNotify();
        }
    }

    public void onStop() {
        viewMVC.unregisterListener(this);
        dialogsEventBus.unregisterListener(this);
        fetchKBDetailsUseCase.unregisterListener(this);
        fetchKBRatingUseCase.unregisterListener(this);
        fetchKBRatingPercentageUseCase.unregisterListener(this);
        fetchKBReviewListUseCase.unregisterListener(this);
        kbDislikeArticleUseCase.unregisterListener(this);
        kbLikeArticleUseCase.unregisterListener(this);
        saveKBReviewUseCase.unregisterListener(this);

    }

    private void fetchKBDetailsAndNotify() {
        mScreenState = ScreenState.FETCHING_KB_DETAIL;
        viewMVC.showProgressIndication();
        fetchKBDetailsUseCase.fetchKBDetailsAndNotify(kbId);
    }

    private void fetchKBRatingAndNotify() {
        mScreenState = ScreenState.FETCHING_KB_RATING;
        viewMVC.showProgressIndication();
        fetchKBRatingUseCase.fetchKBRatingAndNotify(kbId);
    }

    private void fetchKBRatingPercentageAndNotify() {
        mScreenState = ScreenState.FETCHING_KB_RATING_PERCENTAGE;
        viewMVC.showProgressIndication();
        fetchKBRatingPercentageUseCase.fetchKBRatingPercentageAndNotify(kbId);
    }

    private void fetchKBReviewListAndNotify() {
        mScreenState = ScreenState.FETCHING_KB_REVIEW_LIST;
        viewMVC.showProgressIndication();
        fetchKBReviewListUseCase.fetchKBReviewsAndNotify(kbId);
    }

    @Override
    public void onBackClicked() {
        screensNavigator.navigateUp();
    }

    @Override
    public void onLikeClicked(String kbId, Integer isLiked) {
        mScreenState = ScreenState.SAVE_KB_LIKE;
        viewMVC.showProgressIndication();
        kbLikeArticleUseCase.likeKBArticleAndNotify(kbId, isLiked);
    }

    @Override
    public void onDislikeClicked(String kbId, Integer isDisliked) {
        mScreenState = ScreenState.SAVE_KB_DISLIKE;
        viewMVC.showProgressIndication();
        kbDislikeArticleUseCase.likeKBArticleAndNotify(kbId, isDisliked);
    }

    @Override
    public void onSubmitReviewClicked(String kbId, String strReview) {
        mScreenState = ScreenState.SAVE_KB_REVIEW;
        viewMVC.showProgressIndication();
        saveKBReviewUseCase.saveKBReviewAndNotify(kbId, strReview);
    }

    @Override
    public void onKBDetailsFetched(KB kb) {
        mScreenState = ScreenState.KB_DETAIL_SHOWN;
        viewMVC.hideProgressIndication();
        viewMVC.bindKB(kb);
        fetchKBRatingAndNotify();
    }

    @Override
    public void onKBDetailsFetchFailed() {
        mScreenState = ScreenState.NETWORK_ERROR;
        viewMVC.hideProgressIndication();
        dialogsManager.showUseCaseFailedDialog("Knowledge base", null);
    }

    @Override
    public void onNetworkFailed() {
        dialogsManager.showNetworkFailedInfoDialog(null, "Knowledge base");
    }

    @Override
    public void onKBRatingFetched(KBRating kbRating) {
        mScreenState = ScreenState.KB_RATING_SHOWN;
        viewMVC.hideProgressIndication();
        viewMVC.bindKBRating(kbRating);
        fetchKBRatingPercentageAndNotify();
    }

    @Override
    public void onKBRatingFetchFailed() {

    }

    @Override
    public void onKBRatingPercentageFetched(KBRatingPercentResponse kbRatingPercentResponse) {
        mScreenState = ScreenState.KB_RATING_PERCENTAGE_SHOWN;
        viewMVC.hideProgressIndication();
        viewMVC.bindKBRatingPercentage(kbRatingPercentResponse);
        fetchKBReviewListAndNotify();
    }

    @Override
    public void onKBRatingPercentageFetchFailed() {
        mScreenState = ScreenState.NETWORK_ERROR;
        viewMVC.hideProgressIndication();
        dialogsManager.showUseCaseFailedDialog("Knowledge base", null);
    }

    @Override
    public void onKBReviewFetched(List<KBReview> kbReviewList) {
        mScreenState = ScreenState.KB_REVIEW_LIST_SHOWN;
        viewMVC.hideProgressIndication();
        viewMVC.bindKBReview(kbReviewList);
    }

    @Override
    public void onKBReviewFetchFailed() {
        mScreenState = ScreenState.NETWORK_ERROR;
        viewMVC.hideProgressIndication();
        dialogsManager.showUseCaseFailedDialog("Knowledge base", null);
    }

    @Override
    public void onKBDisliked(KBRating kbRating) {
        mScreenState = ScreenState.KB_DISLIKE_SAVED;
        viewMVC.hideProgressIndication();
        fetchKBRatingPercentageAndNotify();
        dialogsManager.showInfoDialog(null, "Knowledge Base", "Your rating has been submitted!", true);

    }

    @Override
    public void onKBDislikeFailed() {
        mScreenState = ScreenState.NETWORK_ERROR;
        viewMVC.hideProgressIndication();
        dialogsManager.showUseCaseFailedDialog("Knowledge base", null);
    }

    @Override
    public void onKBLiked(KBRating kbRating) {
        mScreenState = ScreenState.KB_LIKE_SAVED;
        viewMVC.hideProgressIndication();
        fetchKBRatingPercentageAndNotify();
        dialogsManager.showInfoDialog(null, "Knowledge Base", "Your rating has been submitted!", true);
    }

    @Override
    public void onKBLikeFailed() {
        mScreenState = ScreenState.NETWORK_ERROR;
        viewMVC.hideProgressIndication();
        dialogsManager.showUseCaseFailedDialog("Knowledge base", null);
    }

    @Override
    public void onKBReviewSaved(KB kb) {
        mScreenState = ScreenState.KB_REVIEW_SAVED;
        viewMVC.hideProgressIndication();
        viewMVC.clearReviewText();
        fetchKBReviewListAndNotify();
        dialogsManager.showInfoDialog(null, "Knowledge Base", "Your review has been submitted!", true);
    }

    @Override
    public void onKBReviewSaveFailed() {
        mScreenState = ScreenState.NETWORK_ERROR;
        viewMVC.hideProgressIndication();
        dialogsManager.showUseCaseFailedDialog("Knowledge base", null);
    }

    @Override
    public void onDialogEvent(Object event) {
        if (event instanceof PromptDialogEvent) {
            switch (((PromptDialogEvent) event).getClickedButton()) {
                case POSITIVE:
                    fetchKBDetailsAndNotify();
                    break;
                case NEGATIVE:
                    mScreenState = ScreenState.IDLE;
                    break;
            }
        }
    }

    public static class SavedState implements Serializable {
        private final ScreenState mScreenState;

        public SavedState(ScreenState screenState) {
            mScreenState = screenState;
        }
    }
}
