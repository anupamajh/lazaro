package com.carmel.guestjini.Screens.Support.KBDetail;

import com.carmel.guestjini.KnowledgeBase.FetchKBDetailsUseCase;
import com.carmel.guestjini.Networking.KnowledgeBase.KB;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsEventBus;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsManager;
import com.carmel.guestjini.Screens.Common.Dialogs.PromptDialog.PromptDialogEvent;
import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;

import java.io.Serializable;

public class KBDetailController
        implements KBDetailViewMVC.Listener,
        FetchKBDetailsUseCase.Listener,
        DialogsEventBus.Listener {

    private enum ScreenState {
        IDLE, FETCHING_KB_DETAIL, KB_DETAIL_SHOWN, NETWORK_ERROR
    }

    private final FetchKBDetailsUseCase fetchKBDetailsUseCase;
    private final ScreensNavigator screensNavigator;
    private final DialogsManager dialogsManager;
    private final DialogsEventBus dialogsEventBus;

    private KBDetailViewMVC viewMVC;

    private ScreenState mScreenState = ScreenState.IDLE;

    private String kbId;

    public KBDetailController(
            FetchKBDetailsUseCase fetchKBDetailsUseCase,
            ScreensNavigator screensNavigator,
            DialogsManager dialogsManager,
            DialogsEventBus dialogsEventBus
    ) {
        this.fetchKBDetailsUseCase = fetchKBDetailsUseCase;
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
    }

    private void fetchKBDetailsAndNotify() {
        mScreenState = ScreenState.FETCHING_KB_DETAIL;
        viewMVC.showProgressIndication();
        fetchKBDetailsUseCase.fetchKBDetailsAndNotify(kbId);
    }


    @Override
    public void onBackClicked() {
        screensNavigator.navigateUp();
    }

    @Override
    public void onLikeClicked(String kbId) {

    }

    @Override
    public void onDislikeClicked(String kbId) {

    }

    @Override
    public void onSubmitReviewClicked(String kbId, String strReview) {

    }

    @Override
    public void onKBDetailsFetched(KB kb) {
        mScreenState = ScreenState.KB_DETAIL_SHOWN;
        viewMVC.hideProgressIndication();
        viewMVC.bindKB(kb);
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
