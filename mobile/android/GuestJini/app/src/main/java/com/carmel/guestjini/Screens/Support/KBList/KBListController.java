package com.carmel.guestjini.Screens.Support.KBList;

import com.carmel.guestjini.KnowledgeBase.FetchKBListUseCase;
import com.carmel.guestjini.Networking.KnowledgeBase.KB;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsEventBus;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsManager;
import com.carmel.guestjini.Screens.Common.Dialogs.PromptDialog.PromptDialogEvent;
import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class KBListController implements
        KBListViewMVC.Listener,
        FetchKBListUseCase.Listener,
        DialogsEventBus.Listener {
    private enum ScreenState {
        IDLE, FETCHING_KB_LIST, KB_LIST_SHOWN, NETWORK_ERROR
    }


    private final FetchKBListUseCase fetchKBListUseCase;
    private final ScreensNavigator screensNavigator;
    private final DialogsManager dialogsManager;
    private final DialogsEventBus dialogsEventBus;
    private List<KB> kbList = new ArrayList<>();

    private String strSearch = "";

    private KBListViewMVC viewMvc;

    private ScreenState mScreenState = ScreenState.IDLE;


    public KBListController(
            FetchKBListUseCase fetchKBListUseCase,
            ScreensNavigator screensNavigator,
            DialogsManager dialogsManager,
            DialogsEventBus dialogsEventBus
    ) {
        this.fetchKBListUseCase = fetchKBListUseCase;
        this.screensNavigator = screensNavigator;
        this.dialogsManager = dialogsManager;
        this.dialogsEventBus = dialogsEventBus;
    }

    public void bindView(KBListViewMVC viewMvc) {
        this.viewMvc = viewMvc;
    }

    public SavedState getSavedState() {
        return new SavedState(mScreenState);
    }

    public void restoreSavedState(SavedState savedState) {
        mScreenState = savedState.mScreenState;
    }

    public void onStart(String searchString) {
        strSearch = searchString;
        viewMvc.bindSearch(searchString);
        viewMvc.registerListener(this);
        fetchKBListUseCase.registerListener(this);
        dialogsEventBus.registerListener(this);

        if (mScreenState != ScreenState.NETWORK_ERROR) {
            fetchKBListAndNotify();
        }
    }

    public void onStop() {
        viewMvc.unregisterListener(this);
        dialogsEventBus.unregisterListener(this);
        fetchKBListUseCase.unregisterListener(this);
    }

    private void fetchKBListAndNotify() {
        mScreenState = ScreenState.FETCHING_KB_LIST;
        viewMvc.showProgressIndication();
        fetchKBListUseCase.fetchKBListAndNotify();
    }


    @Override
    public void onKBListFetched(List<KB> kbList) {
        this.kbList = kbList;
        mScreenState = ScreenState.KB_LIST_SHOWN;
        viewMvc.hideProgressIndication();
        searchKBList(strSearch);
    }

    @Override
    public void onKBListFetchFailed() {
        mScreenState = ScreenState.NETWORK_ERROR;
        viewMvc.hideProgressIndication();
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
                    fetchKBListAndNotify();
                    break;
                case NEGATIVE:
                    mScreenState = ScreenState.IDLE;
                    break;
            }
        }
    }

    @Override
    public void onKBItemClicked(KB kb) {
        screensNavigator.toKBDetails(kb.getId());

    }

    @Override
    public void onSearchClicked(String searchText) {
        searchKBList(searchText);
    }

    private void searchKBList(String searchText) {
        if (!searchText.equals("")) {
            List<KB> collection = kbList.stream()
                    .filter(kb -> (kb.getAuthorName() + " " +
                            kb.getTopicTitle() + " " +
                            kb.getTopicNarration()).toLowerCase().contains(searchText.toLowerCase()))
                    .collect(Collectors.toList());
            viewMvc.bindKB(collection, kbList.size());
        } else {
            viewMvc.bindKB(kbList, kbList.size());
        }
    }

    @Override
    public void onBackClicked() {
        screensNavigator.navigateUp();
    }

    public static class SavedState implements Serializable {
        private final ScreenState mScreenState;

        public SavedState(ScreenState screenState) {
            mScreenState = screenState;
        }
    }
}
