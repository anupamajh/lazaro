package com.carmel.guestjini.Community;

import com.carmel.guestjini.Common.BaseObservable;
import com.carmel.guestjini.Networking.Group.GroupResponse;
import com.carmel.guestjini.Networking.GuestJiniAPI;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubscribeToGroupUseCase extends BaseObservable<SubscribeToGroupUseCase.Listener> {

    public interface Listener {
        void onGroupSubscribed(GroupResponse groupResponse);

        void onGroupSubscribeFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public SubscribeToGroupUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void subscribeToGroupAndNotify(String groupId) {
        Map<String, String> postData = new HashMap<>();
        postData.put("groupId", groupId);
        this.guestJiniAPI.subscribeToGroup(postData).enqueue(new Callback<GroupResponse>() {
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
            listener.onGroupSubscribeFailed();
        }
    }

    private void notifySuccess(GroupResponse groupResponse) {
        for (Listener listener : getListeners()) {
            listener.onGroupSubscribed(groupResponse);
        }
    }
}