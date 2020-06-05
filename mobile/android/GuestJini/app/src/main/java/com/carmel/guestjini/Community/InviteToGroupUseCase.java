package com.carmel.guestjini.Community;

import com.carmel.guestjini.Common.BaseObservable;
import com.carmel.guestjini.Networking.Group.GroupResponse;
import com.carmel.guestjini.Networking.GuestJiniAPI;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InviteToGroupUseCase extends BaseObservable<InviteToGroupUseCase.Listener> {

    public interface Listener {
        void onGroupInvited(GroupResponse groupResponse);

        void onGroupInviteFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public InviteToGroupUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void inviteAndNotify(String groupId, String userId) {
        Map<String, String> postData = new HashMap<>();
        postData.put("groupId", groupId);
        postData.put("userId", userId);
        this.guestJiniAPI.inviteToGroup(postData).enqueue(new Callback<GroupResponse>() {
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
            listener.onGroupInviteFailed();
        }
    }

    private void notifySuccess(GroupResponse groupResponse) {
        for (Listener listener : getListeners()) {
            listener.onGroupInvited(groupResponse);
        }
    }
}