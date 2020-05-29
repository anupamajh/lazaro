package com.carmel.guestjini.Screens.Community.GroupConversation;

import com.carmel.guestjini.Community.FetchGroupByIdUseCase;
import com.carmel.guestjini.Community.FetchGroupConversationByGroupUseCase;
import com.carmel.guestjini.Community.SaveGroupConversationUseCase;
import com.carmel.guestjini.Networking.Group.Group;
import com.carmel.guestjini.Networking.Group.GroupConversation;
import com.carmel.guestjini.Networking.Group.GroupConversationResponse;
import com.carmel.guestjini.Networking.Group.GroupResponse;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsEventBus;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsManager;
import com.carmel.guestjini.Screens.Common.Dialogs.PromptDialog.PromptDialogEvent;
import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GroupConversationController implements
        GroupConversationViewMVC.Listener,
        FetchGroupByIdUseCase.Listener,
        FetchGroupConversationByGroupUseCase.Listener,
        SaveGroupConversationUseCase.Listener,
        DialogsEventBus.Listener {

    private enum ScreenState {
        IDLE,
        FETCHING_GROUP_CONVERSATION_LIST, GROUP_CONVERSATION_LIST_SHOWN,
        FETCHING_GROUP, GROUP_SHOWN,
        SAVING_GROUP_CONVERSATION, SAVED_GROUP_CONVERSATION, SAVING_GROUP_CONVERSATION_FAILED,
        NETWORK_ERROR
    }


    private final FetchGroupByIdUseCase fetchGroupByIdUseCase;
    private final FetchGroupConversationByGroupUseCase fetchGroupConversationByGroupUseCase;
    private final SaveGroupConversationUseCase saveGroupConversationUseCase;
    private final ScreensNavigator screensNavigator;
    private final DialogsManager dialogsManager;
    private final DialogsEventBus dialogsEventBus;
    private List<GroupConversation> groupConversations = new ArrayList<>();
    private Group group;
    private String groupId;

    private GroupConversationViewMVC viewMvc;

    private ScreenState mScreenState = ScreenState.IDLE;

    private int groupType;


    public GroupConversationController(
            FetchGroupByIdUseCase fetchGroupByIdUseCase,
            FetchGroupConversationByGroupUseCase fetchGroupConversationByGroupUseCase,
            SaveGroupConversationUseCase saveGroupConversationUseCase,
            ScreensNavigator screensNavigator,
            DialogsManager dialogsManager,
            DialogsEventBus dialogsEventBus
    ) {
        this.fetchGroupByIdUseCase = fetchGroupByIdUseCase;
        this.fetchGroupConversationByGroupUseCase = fetchGroupConversationByGroupUseCase;
        this.saveGroupConversationUseCase = saveGroupConversationUseCase;
        this.screensNavigator = screensNavigator;
        this.dialogsManager = dialogsManager;
        this.dialogsEventBus = dialogsEventBus;
    }

    public void bindView(GroupConversationViewMVC viewMvc) {
        this.viewMvc = viewMvc;
    }

    public SavedState getSavedState() {
        return new SavedState(mScreenState);
    }

    public void restoreSavedState(SavedState savedState) {
        mScreenState = savedState.mScreenState;
    }

    public void onStart(String groupId, int groupType) {
        this.groupId = groupId;
        this.groupType = groupType;
        viewMvc.registerListener(this);
        fetchGroupByIdUseCase.registerListener(this);
        fetchGroupConversationByGroupUseCase.registerListener(this);
        saveGroupConversationUseCase.registerListener(this);
        dialogsEventBus.registerListener(this);

        if (mScreenState != ScreenState.NETWORK_ERROR) {
            fetchGroupByIdAndNotify();
        }
    }

    public void onStop() {
        viewMvc.unregisterListener(this);
        dialogsEventBus.unregisterListener(this);
        fetchGroupByIdUseCase.unregisterListener(this);
        fetchGroupConversationByGroupUseCase.unregisterListener(this);
        saveGroupConversationUseCase.unregisterListener(this);
    }

    private void fetchGroupByIdAndNotify() {
        mScreenState = ScreenState.FETCHING_GROUP;
        viewMvc.showProgressIndication();
        fetchGroupByIdUseCase.fetchGroupByIdAndNotify(groupId);
    }

    private void fetchGroupConversationByIdAndNotify() {
        mScreenState = ScreenState.FETCHING_GROUP_CONVERSATION_LIST;
        viewMvc.showProgressIndication();
        fetchGroupConversationByGroupUseCase.fetchGroupConversationByGroupAndNotify(groupId);
    }

    private void saveGroupConversationAndNotify(String groupId, String message) {
        mScreenState = ScreenState.SAVING_GROUP_CONVERSATION;
        viewMvc.showProgressIndication();
        saveGroupConversationUseCase.saveGroupConversationAndNotify(groupId, message);
    }

    @Override
    public void onGroupFetched(GroupResponse groupResponse) {
        this.group = groupResponse.getGroup();
        mScreenState = ScreenState.GROUP_SHOWN;
        viewMvc.hideProgressIndication();
        viewMvc.bindGroup(groupResponse.getGroup(), groupType);
        fetchGroupConversationByIdAndNotify();
    }

    @Override
    public void onGroupFetchFailed() {
        mScreenState = ScreenState.NETWORK_ERROR;
        viewMvc.hideProgressIndication();
        dialogsManager.showUseCaseFailedDialog("Group Conversation", null);
    }

    @Override
    public void onGroupConversationFetched(GroupConversationResponse groupConversationResponse) {
        mScreenState = ScreenState.GROUP_CONVERSATION_LIST_SHOWN;
        viewMvc.hideProgressIndication();
        viewMvc.bindGroupConversations(groupConversationResponse.getGroupConversationList());
    }

    @Override
    public void onGroupConversationFetchFailed() {
        mScreenState = ScreenState.NETWORK_ERROR;
        viewMvc.hideProgressIndication();
        dialogsManager.showUseCaseFailedDialog("Group Conversation", null);
    }

    @Override
    public void onSendMessageClicked(String groupId, String message) {
        saveGroupConversationAndNotify(groupId, message);
    }

    @Override
    public void onInfoClicked(String groupId, int groupType) {
        screensNavigator.toGroupDetailScreen(groupId, groupType);
    }

    @Override
    public void onGroupConversationSaved(GroupConversationResponse groupConversationResponse) {
        mScreenState = ScreenState.SAVED_GROUP_CONVERSATION;
        viewMvc.hideProgressIndication();
        viewMvc.clearMessage();
        fetchGroupConversationByIdAndNotify();
    }

    @Override
    public void onGroupConversationSaveFailed() {
        mScreenState = ScreenState.NETWORK_ERROR;
        viewMvc.hideProgressIndication();
        dialogsManager.showUseCaseFailedDialog("Group Conversation", null);
    }

    @Override
    public void onBackClicked() {
        screensNavigator.navigateUp();
    }

    @Override
    public void onNetworkFailed() {
        dialogsManager.showNetworkFailedInfoDialog(null, "Group conversation");
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


    public static class SavedState implements Serializable {
        private final ScreenState mScreenState;

        public SavedState(ScreenState screenState) {
            mScreenState = screenState;
        }
    }
}