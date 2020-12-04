package com.carmel.guestjini.Tickets;

import com.carmel.guestjini.Common.BaseObservable;
import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Networking.Tickets.TicketResponse;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CloseTicketUseCase extends BaseObservable<CloseTicketUseCase.Listener> {
    public interface Listener {
        void onTicketClosed(TicketResponse ticketResponse);

        void onCloseTicketFailed();

        void onNetworkFailed();
    }


    private final GuestJiniAPI guestJiniAPI;

    public CloseTicketUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void closeTaskNoteAndNotify(String ticketId, String userId, String message) {
        Map<String, String> postData = new HashMap<>();
        postData.put("ticketId", ticketId);
        postData.put("userId", userId);
        postData.put("message", message);
        this.guestJiniAPI.closeTicket(postData).enqueue(new Callback<TicketResponse>() {
            @Override
            public void onResponse(Call<TicketResponse> call, Response<TicketResponse> response) {
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
            public void onFailure(Call<TicketResponse> call, Throwable t) {
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
            listener.onCloseTicketFailed();
        }
    }

    private void notifySuccess(TicketResponse taskAssigneeResponse) {
        for (Listener listener : getListeners()) {
            listener.onTicketClosed(taskAssigneeResponse);
        }
    }
}
