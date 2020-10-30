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

public class FetchTicketUseCase extends BaseObservable<FetchTicketUseCase.Listener> {

    public interface Listener {
        void onTicketFetched(TicketResponse ticketResponse);

        void onTicketFetchFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public FetchTicketUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void fetchKTicketByIdAndNotify(String ticketId) {
        Map<String, String> postData = new HashMap<>();
        postData.put("id", ticketId);
        this.guestJiniAPI.getTicketById(postData).enqueue(new Callback<TicketResponse>() {
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
            listener.onTicketFetchFailed();
        }
    }

    private void notifySuccess(TicketResponse ticketResponse) {
        for (Listener listener : getListeners()) {
            listener.onTicketFetched(ticketResponse);
        }
    }
}