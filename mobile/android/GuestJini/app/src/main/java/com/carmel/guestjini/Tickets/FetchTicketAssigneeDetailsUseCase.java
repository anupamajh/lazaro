package com.carmel.guestjini.Tickets;

import com.carmel.guestjini.Common.BaseObservable;
import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Networking.Tickets.TaskAssigneeResponse;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchTicketAssigneeDetailsUseCase  extends BaseObservable<FetchTicketAssigneeDetailsUseCase.Listener> {

    public interface Listener {
        void onTicketAssigneeDetailFetched(TaskAssigneeResponse taskAssigneeResponse);

        void onTicketAssigneeDetailFetchFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public FetchTicketAssigneeDetailsUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void fetchAssigneeAndNotify(String ticketId) {
        Map<String, String> postData = new HashMap<>();
        postData.put("ticketId", ticketId);
        this.guestJiniAPI.fetchTaskAssignee(postData).enqueue(new Callback<TaskAssigneeResponse>() {
            @Override
            public void onResponse(Call<TaskAssigneeResponse> call, Response<TaskAssigneeResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().isSuccess()) {
                        notifySuccess(response.body());
                    } else {
                        notifyFailure();
                    }
                } else {
                    notifyFailure();
                }
            }

            @Override
            public void onFailure(Call<TaskAssigneeResponse> call, Throwable t) {
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
            listener.onTicketAssigneeDetailFetchFailed();
        }
    }

    private void notifySuccess(TaskAssigneeResponse taskAssigneeResponse) {
        for (Listener listener : getListeners()) {
            listener.onTicketAssigneeDetailFetched(taskAssigneeResponse);
        }
    }
}