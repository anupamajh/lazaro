package com.carmel.guestjini.Tickets;

import com.carmel.guestjini.Common.BaseObservable;
import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Networking.Tickets.Ticket;
import com.carmel.guestjini.Networking.Tickets.TicketResponse;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteTicketUseCase extends BaseObservable<DeleteTicketUseCase.Listener> {

    public interface Listener {
        void onTicketDeleted(Ticket ticket);

        void onTicketDeleteFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public DeleteTicketUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void deleteTicketAndNotify(String ticketId) {
        Map<String, String> postData = new HashMap<>();
        postData.put("id", ticketId);
        this.guestJiniAPI.deleteTicket(postData).enqueue(new Callback<TicketResponse>() {
            @Override
            public void onResponse(Call<TicketResponse> call, Response<TicketResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().isSuccess()) {
                        notifySuccess(response.body().getTaskTicket());
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
            listener.onTicketDeleteFailed();
        }
    }

    private void notifySuccess(Ticket ticket) {
        for (Listener listener : getListeners()) {
            listener.onTicketDeleted(ticket);
        }
    }
}