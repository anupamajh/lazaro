package com.carmel.guestjini.Tickets;

import com.carmel.guestjini.Common.BaseObservable;
import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Networking.Tickets.TaskCountResponse;

public class FetchInboxCountUseCase  extends BaseObservable<FetchInboxCountUseCase.Listener> {
    public interface Listener {
        void onInboxCountFetched(TaskCountResponse taskCountResponse);

        void onTicketCountFetchFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public FetchInboxCountUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void fetchTaskCountAndNotify() {
        TaskCountResponse taskCountResponse = new TaskCountResponse();
        taskCountResponse.setSharedInboxNewCount(1);
        taskCountResponse.setSharedInboxUnassignedCount(1);
        taskCountResponse.setSharedInboxOpenCount(1);
        taskCountResponse.setSharedInboxClosedCount(1);
        taskCountResponse.setYourInboxNewCount(1);
        taskCountResponse.setYourInboxOpenCount(1);
        taskCountResponse.setYourInboxClosedCount(1);
        taskCountResponse.setTeamInboxNewCount(1);
        taskCountResponse.setTeamInboxUnassignedCount(1);
        taskCountResponse.setTeamInboxOpenCount(1);
        taskCountResponse.setTeamInboxClosedCount(1);

        notifySuccess(taskCountResponse);

        //TODO: Uncomment after implementation
//        this.guestJiniAPI.getTaskCount().enqueue(new Callback<TaskCountResponse>() {
//            @Override
//            public void onResponse(Call<TaskCountResponse> call, Response<TaskCountResponse> response) {
//                if (response.isSuccessful()) {
//                    if (response.body().isSuccess()) {
//                        notifySuccess(response.body());
//                    } else {
//                        notifyFailure();
//                    }
//                } else {
//                    notifyFailure();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<TaskCountResponse> call, Throwable t) {
//                notifyNetworkFailure();
//            }
//        });
    }

    private void notifyNetworkFailure() {
        for (Listener listener : getListeners()) {
            listener.onNetworkFailed();
        }
    }

    private void notifyFailure() {
        for (Listener listener : getListeners()) {
            listener.onTicketCountFetchFailed();
        }
    }

    private void notifySuccess(TaskCountResponse taskCountResponse) {
        for (Listener listener : getListeners()) {
            listener.onInboxCountFetched(taskCountResponse);
        }
    }
}