package com.carmel.guestjini.Screens.Community.PersonDetail;

import com.carmel.guestjini.Community.AddPersonToFavouriteUseCase;
import com.carmel.guestjini.Community.FetchPersonDetailUseCase;
import com.carmel.guestjini.Networking.Users.PeopleResponse;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsEventBus;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsManager;
import com.carmel.guestjini.Screens.Common.Dialogs.PromptDialog.PromptDialogEvent;
import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;

import java.io.Serializable;

public class PersonDetailController
        implements PersonDetailViewMVC.Listener,
        FetchPersonDetailUseCase.Listener,
        AddPersonToFavouriteUseCase.Listener,
        DialogsEventBus.Listener {

    public PersonDetailController(
            FetchPersonDetailUseCase fetchPersonDetailUseCase,
            AddPersonToFavouriteUseCase addPersonToFavouriteUseCase,
            ScreensNavigator screensNavigator,
            DialogsManager dialogsManager,
            DialogsEventBus dialogsEventBus
    ) {
        this.fetchPersonDetailUseCase = fetchPersonDetailUseCase;
        this.addPersonToFavouriteUseCase = addPersonToFavouriteUseCase;
        this.screensNavigator = screensNavigator;
        this.dialogsManager = dialogsManager;
        this.dialogsEventBus = dialogsEventBus;
    }

    private enum ScreenState {
        IDLE, FETCHING_PERSON_DETAIL, PERSON_DETAIL_SHOWN, NETWORK_ERROR,
        ADDING_PERSON_TO_FAVOURITE, ADDED_PERSON_TO_FAVOURITE
    }

    private final FetchPersonDetailUseCase fetchPersonDetailUseCase;
    private final AddPersonToFavouriteUseCase addPersonToFavouriteUseCase;
    private final ScreensNavigator screensNavigator;
    private final DialogsManager dialogsManager;
    private final DialogsEventBus dialogsEventBus;

    private PersonDetailViewMVC viewMVC;

    private ScreenState mScreenState = ScreenState.IDLE;

    private String personId;

    public void bindView(PersonDetailViewMVC viewMvc) {
        this.viewMVC = viewMvc;
    }

    public SavedState getSavedState() {
        return new SavedState(mScreenState);
    }

    public void restoreSavedState(SavedState savedState) {
        mScreenState = savedState.mScreenState;
    }

    public void onStart(String personId) {
        viewMVC.registerListener(this);
        fetchPersonDetailUseCase.registerListener(this);
        addPersonToFavouriteUseCase.registerListener(this);
        dialogsEventBus.registerListener(this);
        this.personId = personId;

        if (mScreenState != ScreenState.NETWORK_ERROR) {
            fetchPersonDetailsAndNotify();
        }
    }

    public void onStop() {
        viewMVC.unregisterListener(this);
        dialogsEventBus.unregisterListener(this);
        fetchPersonDetailUseCase.unregisterListener(this);
        addPersonToFavouriteUseCase.unregisterListener(this);
    }

    private void fetchPersonDetailsAndNotify() {
        mScreenState = ScreenState.FETCHING_PERSON_DETAIL;
        viewMVC.showProgressIndication();
        fetchPersonDetailUseCase.fetchPersonDetailAndNotify(personId);
    }

    private void addPersonToFavouriteAndNotify(String personId, int isFavourite) {
        mScreenState = ScreenState.ADDING_PERSON_TO_FAVOURITE;
        viewMVC.showProgressIndication();
        addPersonToFavouriteUseCase.addPersonToFavouriteAndNotify(personId, isFavourite);
    }

    @Override
    public void onFavouriteAdded(PeopleResponse peopleResponse) {
        mScreenState = ScreenState.ADDED_PERSON_TO_FAVOURITE;
        viewMVC.hideProgressIndication();

    }

    @Override
    public void onFavouriteAddFailed() {
        mScreenState = ScreenState.NETWORK_ERROR;
        viewMVC.hideProgressIndication();
        dialogsManager.showUseCaseFailedDialog("Person Detail", null);
    }

    @Override
    public void onPersonDetailFetched(PeopleResponse peopleResponse) {
        mScreenState = ScreenState.PERSON_DETAIL_SHOWN;
        viewMVC.hideProgressIndication();
        viewMVC.bindPerson(peopleResponse);
    }

    @Override
    public void onPersonDetailFetchFailed() {
        mScreenState = ScreenState.NETWORK_ERROR;
        viewMVC.hideProgressIndication();
        dialogsManager.showUseCaseFailedDialog("Person Detail", null);
    }

    @Override
    public void onNetworkFailed() {
        mScreenState = ScreenState.NETWORK_ERROR;
        viewMVC.hideProgressIndication();
        dialogsManager.showUseCaseFailedDialog("Person Detail", null);
    }

    @Override
    public void onDialogEvent(Object event) {
        if (event instanceof PromptDialogEvent) {
            switch (((PromptDialogEvent) event).getClickedButton()) {
                case POSITIVE:
                    fetchPersonDetailsAndNotify();
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
    public void onIsFavouriteClicked(String personId, Integer isFavourite) {
        addPersonToFavouriteAndNotify(personId, isFavourite);
    }

    public static class SavedState implements Serializable {
        private final ScreenState mScreenState;

        public SavedState(ScreenState screenState) {
            mScreenState = screenState;
        }
    }
}
