package com.carmel.guestjini.Community;

import com.carmel.guestjini.Common.BaseObservable;
import com.carmel.guestjini.Networking.Group.GroupConversationResponse;
import com.carmel.guestjini.Networking.GuestJiniAPI;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaveGroupConversationUseCase extends BaseObservable<SaveGroupConversationUseCase.Listener> {

    public interface Listener {
        void onGroupConversationSaved(GroupConversationResponse groupConversationResponse);

        void onGroupConversationSaveFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public SaveGroupConversationUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void saveGroupConversationAndNotify(String groupId, String message) {
        Map<String, String> postData = new HashMap<>();
        postData.put("groupId", groupId);
        postData.put("message", message);
        this.guestJiniAPI.saveGroupConversation(postData).enqueue(new Callback<GroupConversationResponse>() {
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
            listener.onGroupConversationSaveFailed();
        }
    }

    private void notifySuccess(GroupConversationResponse groupConversationResponse) {
        for (Listener listener : getListeners()) {
            listener.onGroupConversationSaved(groupConversationResponse);
        }
    }
}