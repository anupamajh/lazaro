package com.carmel.guestjini.Community;

import com.carmel.guestjini.Common.BaseObservable;
import com.carmel.guestjini.Networking.Group.GroupConversationResponse;
import com.carmel.guestjini.Networking.GuestJiniAPI;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchGroupConversationByGroupUseCase extends BaseObservable<FetchGroupConversationByGroupUseCase.Listener> {

    public interface Listener {
        void onGroupConversationFetched(GroupConversationResponse groupConversationResponse);

        void onGroupConversationFetchFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public FetchGroupConversationByGroupUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void fetchGroupConversationByGroupAndNotify(String groupId) {
        Map<String, String> postData = new HashMap<>();
        postData.put("groupId", groupId);
        this.guestJiniAPI.getGroupConversationById(postData).enqueue(new Callback<GroupConversationResponse>() {
            @Override
            public void onResponse(Call<GroupConversationResponse> call, Response<GroupConversationResponse> response) {
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
            public void onFailure(Call<GroupConversationResponse> call, Throwable t) {
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
            listener.onGroupConversationFetchFailed();
        }
    }

    private void notifySuccess(GroupConversationResponse groupConversationResponse) {
        for (Listener listener : getListeners()) {
            listener.onGroupConversationFetched(groupConversationResponse);
        }
    }
}