package com.carmel.guestjini.Screens.Community.PeopleList;

import com.carmel.guestjini.Community.AddPersonToFavouriteUseCase;
import com.carmel.guestjini.Community.FetchPeopleListUseCase;
import com.carmel.guestjini.Networking.Users.PeopleResponse;
import com.carmel.guestjini.Networking.Users.Person;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsEventBus;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsManager;
import com.carmel.guestjini.Screens.Common.Dialogs.PromptDialog.PromptDialogEvent;
import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;

import java.io.Serializable;

public class PeopleListController
        implements PeopleListViewMVC.Listener,
        FetchPeopleListUseCase.Listener,
        AddPersonToFavouriteUseCase.Listener,
        DialogsEventBus.Listener {

    private enum ScreenState {
        IDLE,
        FETCHING_PEOPLE_LIST, PEOPLE_LIST_SHOWN,
        ADDING_PERSON_TO_FAVOURITE, PERSON_TO_FAVOURITE_ADDED,
        NETWORK_ERROR
    }


    private final FetchPeopleListUseCase fetchPeopleListUseCase;
    private final AddPersonToFavouriteUseCase addPersonToFavouriteUseCase;
    private final ScreensNavigator screensNavigator;
    private final DialogsManager dialogsManager;
    private final DialogsEventBus dialogsEventBus;
    private PeopleResponse peopleResponse;

    private PeopleListViewMVC viewMvc;

    private ScreenState mScreenState = ScreenState.IDLE;


    public PeopleListController(
            FetchPeopleListUseCase fetchPeopleListUseCase,
            AddPersonToFavouriteUseCase addPersonToFavouriteUseCase,
            ScreensNavigator screensNavigator,
            DialogsManager dialogsManager,
            DialogsEventBus dialogsEventBus
    ) {
        this.fetchPeopleListUseCase = fetchPeopleListUseCase;
        this.addPersonToFavouriteUseCase = addPersonToFavouriteUseCase;
        this.screensNavigator = screensNavigator;
        this.dialogsManager = dialogsManager;
        this.dialogsEventBus = dialogsEventBus;
    }

    public void bindView(PeopleListViewMVC viewMvc) {
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
        fetchPeopleListUseCase.registerListener(this);
        dialogsEventBus.registerListener(this);

        if (mScreenState != ScreenState.NETWORK_ERROR) {
            fetchPeopleListAndNotify();
        }
    }

    public void onStop() {
        viewMvc.unregisterListener(this);
        dialogsEventBus.unregisterListener(this);
        fetchPeopleListUseCase.unregisterListener(this);
    }

    private void fetchPeopleListAndNotify() {
        mScreenState = ScreenState.FETCHING_PEOPLE_LIST;
        viewMvc.showProgressIndication();
        fetchPeopleListUseCase.fetchPeopleListAndNotify();
    }


    @Override
    public void onPeopleListFetched(PeopleResponse peopleResponse) {
        mScreenState = ScreenState.PEOPLE_LIST_SHOWN;
        viewMvc.hideProgressIndication();
        viewMvc.bindPeopleResponse(peopleResponse);
    }

    @Override
    public void onPeopleListFetchFailed() {
        mScreenState = ScreenState.NETWORK_ERROR;
        viewMvc.hideProgressIndication();
        dialogsManager.showUseCaseFailedDialog("People", null);
    }

    @Override
    public void onNetworkFailed() {
        dialogsManager.showNetworkFailedInfoDialog(null, "People");
    }

    @Override
    public void onDialogEvent(Object event) {
        if (event instanceof PromptDialogEvent) {
            switch (((PromptDialogEvent) event).getClickedButton()) {
                case POSITIVE:
                    fetchPeopleListAndNotify();
                    break;
                case NEGATIVE:
                    mScreenState = ScreenState.IDLE;
                    break;
            }
        }
    }

    @Override
    public void onPersonClicked(Person person) {
        screensNavigator.toPersonDetailsScreen(person.getAddressBook().getUserId());
    }

    @Override
    public void onFavouriteClicked(Person person, int isFavourite) {
        mScreenState = ScreenState.ADDING_PERSON_TO_FAVOURITE;
        viewMvc.showProgressIndication();
        addPersonToFavouriteUseCase.addPersonToFavouriteAndNotify(person.getAddressBook().getUserId(), isFavourite);
    }

    @Override
    public void onFavouriteAdded(PeopleResponse peopleResponse) {
        mScreenState = ScreenState.PERSON_TO_FAVOURITE_ADDED;
        viewMvc.hideProgressIndication();

    }

    @Override
    public void onFavouriteAddFailed() {
        mScreenState = ScreenState.NETWORK_ERROR;
        viewMvc.hideProgressIndication();
        dialogsManager.showUseCaseFailedDialog("People", null);
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
