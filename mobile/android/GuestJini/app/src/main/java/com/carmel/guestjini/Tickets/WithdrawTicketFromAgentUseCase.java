package com.carmel.guestjini.Tickets;

import com.carmel.guestjini.Common.BaseObservable;
import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Networking.Tickets.TaskAssignee;
import com.carmel.guestjini.Networking.Tickets.TaskRunnerResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WithdrawTicketFromAgentUseCase extends BaseObservable<WithdrawTicketFromAgentUseCase.Listener> {

    public interface Listener {
        void onTicketWithdrawnFromAgent(TaskRunnerResponse taskAssigneeResponse);

        void onTicketWithdrawFromAgentFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public WithdrawTicketFromAgentUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void withdrawTicketFromAgentAndNotify(String ticketId) {
        TaskAssignee taskAssignee = new TaskAssignee();
        taskAssignee.setTicketId(ticketId);
        taskAssignee.setIsGroup(0);
        this.guestJiniAPI.withdrawTicketFromAgent(taskAssignee).enqueue(new Callback<TaskRunnerResponse>() {
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
            listener.onTicketWithdrawFromAgentFailed();
        }
    }

    private void notifySuccess(TaskRunnerResponse taskAssigneeResponse) {
        for (Listener listener : getListeners()) {
            listener.onTicketWithdrawnFromAgent(taskAssigneeResponse);
        }
    }
}