package com.carmel.guestjini.Screens.Community.CreateGroup;

import com.carmel.guestjini.Community.SaveGroupUseCase;
import com.carmel.guestjini.Networking.Group.GroupResponse;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsEventBus;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsManager;
import com.carmel.guestjini.Screens.Common.Dialogs.PromptDialog.PromptDialogEvent;
import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;

import java.io.Serializable;

public class CreateGroupController
        implements CreateGroupViewMVC.Listener,
        SaveGroupUseCase.Listener,
        DialogsEventBus.Listener {
    private enum ScreenState {
        IDLE, GROUP_SAVING, GROUP_SAVED, GROUP_SAVE_FAILED, NETWORK_ERROR
    }

    private final ScreensNavigator screensNavigator;
    private final SaveGroupUseCase saveGroupUseCase;
    private final DialogsManager dialogsManager;
    private final DialogsEventBus dialogsEventBus;

    private CreateGroupViewMVC viewMVC;
    private ScreenState mScreenState = ScreenState.IDLE;

    private String groupName;
    private String groupDescription;

    public CreateGroupController
            (
                    ScreensNavigator screensNavigator,
                    SaveGroupUseCase saveGroupUseCase,
                    DialogsManager dialogsManager,
                    DialogsEventBus dialogsEventBus
            ) {
        this.screensNavigator = screensNavigator;
        this.saveGroupUseCase = saveGroupUseCase;
        this.dialogsManager = dialogsManager;
        this.dialogsEventBus = dialogsEventBus;
    }

    public void onStart() {
        viewMVC.registerListener(this);
        dialogsEventBus.registerListener(this);
        saveGroupUseCase.registerListener(this);
    }

    public void onStop() {
        viewMVC.unregisterListener(this);
        dialogsEventBus.unregisterListener(this);
        saveGroupUseCase.unregisterListener(this);
    }

    public void bindView(CreateGroupViewMVC viewMVC) {
        this.viewMVC = viewMVC;
    }


    public Serializable getSavedState() {
        return new SavedState(mScreenState);
    }

    public void restoreSavedState(SavedState savedState) {
        mScreenState = savedState.mScreenState;
    }

    @Override
    public void onGroupSaved(GroupResponse groupResponse) {
        viewMVC.showProgressIndication();
        mScreenState = ScreenState.GROUP_SAVED;
        screensNavigator.toMyGroupsScreen();
    }

    @Override
    public void onGroupSaveFailed() {
        viewMVC.hideProgressIndication();
        dialogsManager.showUseCaseFailedDialog("Save Group", null);
    }

    @Override
    public void onNetworkFailed() {
        viewMVC.hideProgressIndication();
        dialogsManager.showNetworkFailedInfoDialog(null, "Save Group");
    }

    @Override
    public void onCreateGroupClicked(String name, String description) {
        this.groupName = name;
        this.groupDescription = description;
        viewMVC.showProgressIndication();
        mScreenState = ScreenState.GROUP_SAVING;
        saveGroupUseCase.saveGroupAndNotify(name, description);
    }

    @Override
    public void onBackClicked() {
        screensNavigator.navigateUp();
    }


    @Override
    public void onDialogEvent(Object event) {
        if (event instanceof PromptDialogEvent) {
            switch (((PromptDialogEvent) event).getClickedButton()) {
                case POSITIVE:
                    saveGroupUseCase.saveGroupAndNotify(groupName, groupDescription);
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
