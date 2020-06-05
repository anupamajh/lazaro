package com.carmel.guestjini.Screens.Community.GroupDetails;

import com.carmel.guestjini.Community.FetchGroupByIdUseCase;
import com.carmel.guestjini.Community.InviteToGroupUseCase;
import com.carmel.guestjini.Networking.Group.GroupResponse;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsEventBus;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsManager;
import com.carmel.guestjini.Screens.Common.Dialogs.PromptDialog.PromptDialogEvent;
import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;

import java.io.Serializable;

public class GroupDetailsController implements
        GroupDetailsViewMVC.Listener,
        FetchGroupByIdUseCase.Listener,
        InviteToGroupUseCase.Listener,
        DialogsEventBus.Listener {
    private enum ScreenState {
        IDLE, FETCHING_GROUP, GROUP_SHOWN, INVITE_TO_GROUP, INVITED_TO_GROUP, NETWORK_ERROR
    }


    private final FetchGroupByIdUseCase fetchGroupByIdUseCase;
    private final InviteToGroupUseCase inviteToGroupUseCase;
    private final ScreensNavigator screensNavigator;
    private final DialogsManager dialogsManager;
    private final DialogsEventBus dialogsEventBus;

    private GroupResponse groupResponse;

    private GroupDetailsViewMVC viewMvc;
    private String groupId;
    private int groupType;

    private ScreenState mScreenState = ScreenState.IDLE;

    public GroupDetailsController(
            FetchGroupByIdUseCase fetchGroupByIdUseCase,
            InviteToGroupUseCase inviteToGroupUseCase,
            ScreensNavigator screensNavigator,
            DialogsManager dialogsManager,
            DialogsEventBus dialogsEventBus
    ) {
        this.fetchGroupByIdUseCase = fetchGroupByIdUseCase;
        this.inviteToGroupUseCase = inviteToGroupUseCase;
        this.screensNavigator = screensNavigator;
        this.dialogsManager = dialogsManager;
        this.dialogsEventBus = dialogsEventBus;
    }

    public void bindView(GroupDetailsViewMVC viewMvc) {
        this.viewMvc = viewMvc;
    }

    public SavedState getSavedState() {
        return new SavedState(mScreenState);
    }

    public void restoreSavedState(SavedState savedState) {
        mScreenState = savedState.mScreenState;
    }

    public void onStart(String groupId, Integer groupType) {
        this.groupId = groupId;
        this.groupType = groupType;
        viewMvc.registerListener(this);
        fetchGroupByIdUseCase.registerListener(this);
        inviteToGroupUseCase.registerListener(this);
        dialogsEventBus.registerListener(this);

        if (mScreenState != ScreenState.NETWORK_ERROR) {
            fetchGroupByIdAndNotify();
        }
    }

    public void onStop() {
        viewMvc.unregisterListener(this);
        dialogsEventBus.unregisterListener(this);
        fetchGroupByIdUseCase.unregisterListener(this);
        inviteToGroupUseCase.unregisterListener(this);

    }

    private void fetchGroupByIdAndNotify() {
        mScreenState = ScreenState.FETCHING_GROUP;
        viewMvc.showProgressIndication();
        fetchGroupByIdUseCase.fetchGroupByIdAndNotify(groupId);
    }

    private void inviteToGroupAndNotify(String userId, String groupId) {
        mScreenState = ScreenState.INVITE_TO_GROUP;
        viewMvc.showProgressIndication();
        inviteToGroupUseCase.inviteAndNotify(groupId, userId);
    }

    @Override
    public void onGroupFetched(GroupResponse groupResponse) {
        this.groupId = groupResponse.getGroup().getId();
        this.groupResponse = groupResponse;
        mScreenState = ScreenState.GROUP_SHOWN;
        viewMvc.hideProgressIndication();
        viewMvc.bindGroupResponse(groupResponse, groupType);
    }

    @Override
    public void onGroupFetchFailed() {
        mScreenState = ScreenState.NETWORK_ERROR;
        viewMvc.hideProgressIndication();
        dialogsManager.showUseCaseFailedDialog("Group", null);
    }

    @Override
    public void onInviteClicked(String userId, String groupId) {
        inviteToGroupAndNotify(userId, groupId);
    }

    @Override
    public void onMessageClicked(String groupId) {
        screensNavigator.toGroupConversationScreen(groupId, groupType);
    }

    @Override
    public void onGroupInvited(GroupResponse groupResponse) {
        mScreenState = ScreenState.INVITED_TO_GROUP;
        viewMvc.hideProgressIndication();
        fetchGroupByIdAndNotify();
    }

    @Override
    public void onGroupInviteFailed() {
        mScreenState = ScreenState.NETWORK_ERROR;
        viewMvc.hideProgressIndication();
        dialogsManager.showUseCaseFailedDialog("Group Invitation", null);
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
                    fetchGroupByIdAndNotify();
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

    public static class SavedState implements Serializable {
        private final ScreenState mScreenState;

        public SavedState(ScreenState screenState) {
            mScreenState = screenState;
        }
    }
}