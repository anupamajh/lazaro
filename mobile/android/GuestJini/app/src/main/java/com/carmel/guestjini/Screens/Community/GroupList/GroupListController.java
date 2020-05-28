package com.carmel.guestjini.Screens.Community.GroupList;

import com.carmel.guestjini.Community.FetchGroupListByTypeUseCase;
import com.carmel.guestjini.Networking.Group.Group;
import com.carmel.guestjini.Networking.Group.GroupResponse;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsEventBus;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsManager;
import com.carmel.guestjini.Screens.Common.Dialogs.PromptDialog.PromptDialogEvent;
import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GroupListController implements
        GroupListViewMVC.Listener,
        FetchGroupListByTypeUseCase.Listener,
        DialogsEventBus.Listener {
    private enum ScreenState {
        IDLE, FETCHING_GROUP_LIST, GROUP_LIST_SHOWN, NETWORK_ERROR
    }


    private final FetchGroupListByTypeUseCase fetchGroupListByTypeUseCase;
    private final ScreensNavigator screensNavigator;
    private final DialogsManager dialogsManager;
    private final DialogsEventBus dialogsEventBus;
    private List<Group> groupList = new ArrayList<>();

    private Integer groupType = 0;

    private GroupListViewMVC viewMvc;

    private ScreenState mScreenState = ScreenState.IDLE;


    public GroupListController(
            FetchGroupListByTypeUseCase fetchGroupListByTypeUseCase,
            ScreensNavigator screensNavigator,
            DialogsManager dialogsManager,
            DialogsEventBus dialogsEventBus
    ) {
        this.fetchGroupListByTypeUseCase = fetchGroupListByTypeUseCase;
        this.screensNavigator = screensNavigator;
        this.dialogsManager = dialogsManager;
        this.dialogsEventBus = dialogsEventBus;
    }

    public void bindView(GroupListViewMVC viewMvc) {
        this.viewMvc = viewMvc;
    }

    public SavedState getSavedState() {
        return new SavedState(mScreenState);
    }

    public void restoreSavedState(SavedState savedState) {
        mScreenState = savedState.mScreenState;
    }

    public void onStart(Integer groupType) {
        this.groupType = groupType;
        viewMvc.registerListener(this);
        fetchGroupListByTypeUseCase.registerListener(this);
        dialogsEventBus.registerListener(this);

        if (mScreenState != ScreenState.NETWORK_ERROR) {
            fetchGroupByTypeAndNotify();
        }
    }

    public void onStop() {
        viewMvc.unregisterListener(this);
        dialogsEventBus.unregisterListener(this);
        fetchGroupListByTypeUseCase.unregisterListener(this);
    }

    private void fetchGroupByTypeAndNotify() {
        mScreenState = ScreenState.FETCHING_GROUP_LIST;
        viewMvc.showProgressIndication();
        fetchGroupListByTypeUseCase.fetchGroupListByTypeAndNotify(String.valueOf(groupType));
    }


    @Override
    public void onGroupListByTypeFetched(GroupResponse groupResponse) {
        this.groupList = groupResponse.getGroupList();
        if(this.groupList == null){
            this.groupList = new ArrayList<>();
        }
        mScreenState = ScreenState.GROUP_LIST_SHOWN;
        viewMvc.hideProgressIndication();
        viewMvc.bindGroups(this.groupList);
    }

    @Override
    public void onGroupListByTypeFetchFailed() {
        mScreenState = ScreenState.NETWORK_ERROR;
        viewMvc.hideProgressIndication();
        dialogsManager.showUseCaseFailedDialog("Groups", null);
    }

    @Override
    public void onGroupClicked(Group group) {
        screensNavigator.toGroupDetailScreen(group.getId());
    }

    @Override
    public void onBackClicked() {
        screensNavigator.navigateUp();
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
                    fetchGroupByTypeAndNotify();
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
