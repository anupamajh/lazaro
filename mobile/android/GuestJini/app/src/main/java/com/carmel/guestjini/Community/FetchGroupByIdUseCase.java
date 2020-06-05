package com.carmel.guestjini.Community;

import com.carmel.guestjini.Common.BaseObservable;
import com.carmel.guestjini.Networking.Group.GroupResponse;
import com.carmel.guestjini.Networking.GuestJiniAPI;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchGroupByIdUseCase extends BaseObservable<FetchGroupByIdUseCase.Listener> {

    public interface Listener {
        void onGroupFetched(GroupResponse groupResponse);

        void onGroupFetchFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public FetchGroupByIdUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void fetchGroupByIdAndNotify(String groupId) {
        Map<String, String> postData = new HashMap<>();
        postData.put("id", groupId);
        this.guestJiniAPI.getGroupById(postData).enqueue(new Callback<GroupResponse>() {
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
            listener.onGroupFetchFailed();
        }
    }

    private void notifySuccess(GroupResponse groupResponse) {
        for (Listener listener : getListeners()) {
            listener.onGroupFetched(groupResponse);
        }
    }
}