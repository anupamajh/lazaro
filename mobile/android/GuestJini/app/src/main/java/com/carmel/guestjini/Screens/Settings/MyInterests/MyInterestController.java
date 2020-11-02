package com.carmel.guestjini.Screens.Settings.MyInterests;

import com.carmel.guestjini.Networking.Users.Interest;
import com.carmel.guestjini.Networking.Users.InterestCategory;
import com.carmel.guestjini.Networking.Users.UserInterests;
import com.carmel.guestjini.Networking.Users.UserInterestsResponse;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsEventBus;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsManager;
import com.carmel.guestjini.Screens.Common.Dialogs.PromptDialog.PromptDialogEvent;
import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;
import com.carmel.guestjini.Users.FetchInterestCategoryListUseCase;
import com.carmel.guestjini.Users.FetchMyInterestsUseCase;
import com.carmel.guestjini.Users.SaveMyInterestUseCase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MyInterestController
        implements MyInterestViewMVC.Listener,
        DialogsEventBus.Listener,
        FetchInterestCategoryListUseCase.Listener,
        FetchMyInterestsUseCase.Listener,
        SaveMyInterestUseCase.Listener {


    private enum ScreenState {
        IDLE,
        FETCHING_INTEREST_CATEGORY_LIST, INTEREST_CATEGORY_LIST_SHOWN,
        FETCHING_INTEREST_LIST, INTEREST_LIST_SHOWN,
        FETCHING_MY_INTEREST_LIST, MY_INTEREST_LIST_SHOWN,
        SAVING_MY_INTEREST, MY_INTEREST_SAVED,
        NETWORK_ERROR
    }

    private final FetchInterestCategoryListUseCase fetchInterestCategoryListUseCase;
    private final FetchMyInterestsUseCase fetchMyInterestsUseCase;
    private final SaveMyInterestUseCase saveMyInterestUseCase;
    private final ScreensNavigator screensNavigator;
    private final DialogsManager dialogsManager;
    private final DialogsEventBus dialogsEventBus;

    private List<UserInterests> userInterests = new ArrayList<>();

    private MyInterestViewMVC viewMvc;

    private ScreenState mScreenState = ScreenState.IDLE;

    public MyInterestController(
            FetchInterestCategoryListUseCase fetchInterestCategoryListUseCase,
            FetchMyInterestsUseCase fetchMyInterestsUseCase,
            SaveMyInterestUseCase saveMyInterestUseCase,
            ScreensNavigator screensNavigator,
            DialogsManager dialogsManager,
            DialogsEventBus dialogsEventBus
    ) {
        this.fetchInterestCategoryListUseCase = fetchInterestCategoryListUseCase;
        this.fetchMyInterestsUseCase = fetchMyInterestsUseCase;
        this.saveMyInterestUseCase = saveMyInterestUseCase;
        this.screensNavigator = screensNavigator;
        this.dialogsManager = dialogsManager;
        this.dialogsEventBus = dialogsEventBus;
    }

    public void bindView(MyInterestViewMVC viewMvc) {
        this.viewMvc = viewMvc;
    }

    public SavedState getSavedState() {
        return new SavedState(mScreenState);
    }

    public void restoreSavedState(SavedState savedState) {
        mScreenState = savedState.mScreenState;
    }

    public void onStart() {
        viewMvc.registerListener(this);
        fetchInterestCategoryListUseCase.registerListener(this);
        fetchMyInterestsUseCase.registerListener(this);
        saveMyInterestUseCase.registerListener(this);
        dialogsEventBus.registerListener(this);
        if (mScreenState != ScreenState.NETWORK_ERROR) {
            fetchMyInterestsAndNotify();
        }
    }

    public void onStop() {
        viewMvc.unregisterListener(this);
        fetchInterestCategoryListUseCase.unregisterListener(this);
        fetchMyInterestsUseCase.unregisterListener(this);
        saveMyInterestUseCase.unregisterListener(this);
        dialogsEventBus.unregisterListener(this);
    }

    private void fetchInterestCategoriesAndNotify() {
        mScreenState = ScreenState.FETCHING_INTEREST_CATEGORY_LIST;
        fetchInterestCategoryListUseCase.fetchInterestCategoryListAndNotify();
    }

    private void fetchMyInterestsAndNotify() {
        mScreenState = ScreenState.FETCHING_MY_INTEREST_LIST;
        fetchMyInterestsUseCase.fetchMyInterestAndNotify();
    }

    @Override
    public void onDialogEvent(Object event) {
        if (event instanceof PromptDialogEvent) {
            switch (((PromptDialogEvent) event).getClickedButton()) {
                case POSITIVE:
                    fetchInterestCategoriesAndNotify();
                    break;
                case NEGATIVE:
                    mScreenState = ScreenState.IDLE;
                    break;
            }
        }
    }

    @Override
    public void onBackClicked() {
        screensNavigator.navigateUp();
    }

    @Override
    public void onInterestCategoryListFetched(List<InterestCategory> response) {
        mScreenState = ScreenState.INTEREST_CATEGORY_LIST_SHOWN;
        viewMvc.hideProgressIndication();
        viewMvc.bindInterestCategories(response, userInterests);
    }

    @Override
    public void onInterestCategoryListFetchFailed() {
        mScreenState = ScreenState.NETWORK_ERROR;
        viewMvc.hideProgressIndication();
        dialogsManager.showUseCaseFailedDialog("Interest Categories", null);
    }

    @Override
    public void onUserInterestListFetched(List<UserInterests> response) {
        mScreenState = ScreenState.MY_INTEREST_LIST_SHOWN;
        viewMvc.hideProgressIndication();
        viewMvc.bindUserInterests(response);
        this.userInterests = response;
        fetchInterestCategoriesAndNotify();
    }

    @Override
    public void onUserInterestListFetchFailed() {
        mScreenState = ScreenState.NETWORK_ERROR;
        viewMvc.hideProgressIndication();
        dialogsManager.showUseCaseFailedDialog("User Interests", null);
    }

    @Override
    public void onUserInterestSaved(UserInterestsResponse response) {
        mScreenState = ScreenState.MY_INTEREST_SAVED;
        viewMvc.hideProgressIndication();
        viewMvc.showUserInterestSaved();
    }

    @Override
    public void onUserInterestSaveFailed() {
        dialogsManager.showNetworkFailedInfoDialog(null, "User Interests");
    }

    @Override
    public void onNetworkFailed() {
        mScreenState = ScreenState.NETWORK_ERROR;
        viewMvc.hideProgressIndication();
        dialogsManager.showNetworkFailedInfoDialog(null, "My Interests");
    }

    public static class SavedState implements Serializable {
        private final ScreenState mScreenState;

        public SavedState(ScreenState screenState) {
            mScreenState = screenState;
        }
    }

    @Override
    public void onInterestClicked(Interest interest, int isInterested) {
        viewMvc.showProgressIndication();
        saveMyInterestUseCase.saveMyInterestAndNotify(
                interest.getId(),
                isInterested
        );
    }
}
