package com.carmel.guestjini.Tickets;

import com.carmel.guestjini.Common.BaseObservable;
import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Networking.Tickets.TaskAssignee;
import com.carmel.guestjini.Networking.Tickets.TaskRunnerResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssignTaskTicketUseCase extends BaseObservable<AssignTaskTicketUseCase.Listener> {

    public interface Listener {
        void onTicketAssigned(TaskRunnerResponse taskAssigneeResponse);

        void onAssignTicketFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public AssignTaskTicketUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void saveTaskNoteAndNotify(String id, String ticketId, int isGroup) {
        TaskAssignee taskAssignee = new TaskAssignee();
        taskAssignee.setId(id);
        taskAssignee.setTicketId(ticketId);
        taskAssignee.setIsGroup(isGroup);
        this.guestJiniAPI.assignTaskTicket(taskAssignee).enqueue(new Callback<TaskRunnerResponse>() {
            @Override
            public void onResponse(Call<TaskRunnerResponse> call, Response<TaskRunnerResponse> response) {
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
            public void onFailure(Call<TaskRunnerResponse> call, Throwable t) {
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
            listener.onAssignTicketFailed();
        }
    }

    private void notifySuccess(TaskRunnerResponse taskAssigneeResponse) {
        for (Listener listener : getListeners()) {
            listener.onTicketAssigned(taskAssigneeResponse);
        }
    }
}