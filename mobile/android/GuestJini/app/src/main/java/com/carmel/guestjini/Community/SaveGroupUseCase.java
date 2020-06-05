package com.carmel.guestjini.Community;

import com.carmel.guestjini.Common.BaseObservable;
import com.carmel.guestjini.Networking.Group.Group;
import com.carmel.guestjini.Networking.Group.GroupResponse;
import com.carmel.guestjini.Networking.GuestJiniAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaveGroupUseCase extends BaseObservable<SaveGroupUseCase.Listener> {

    public interface Listener {
        void onGroupSaved(GroupResponse groupResponse);

        void onGroupSaveFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public SaveGroupUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void saveGroupAndNotify(String groupName, String groupDescription) {
        Group group = new Group();
        group.setName(groupName);
        group.setDescription(groupDescription);
        group.setGroupType(2);

        this.guestJiniAPI.saveGroup(group).enqueue(new Callback<GroupResponse>() {
            @Override
            public void onResponse(Call<GroupResponse> call, Response<GroupResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSuccess()) {
                        notifySuccess(response.body());
                    } else {
                        notifyFailure();
                    }
                } else {
                    notifyFailure();
                }
            }

            @Override
            public void onFailure(Call<GroupResponse> call, Throwable t) {
                notifyNetworkFailure();
            }
        });
    }

    private void notifyNetworkFailure() {
        for (Listener listener : getListeners()) {
            listener.onNetworkFailed();
        }
    }

    private void notifyFailure() {
        for (Listener listener : getListeners()) {
            listener.onGroupSaveFailed();
        }
    }

    private void notifySuccess(GroupResponse groupResponse) {
        for (Listener listener : getListeners()) {
            listener.onGroupSaved(groupResponse);
        }
    }
}