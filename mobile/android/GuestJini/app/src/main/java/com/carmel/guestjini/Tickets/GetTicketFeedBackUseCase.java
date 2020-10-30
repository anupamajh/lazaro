package com.carmel.guestjini.Tickets;

import com.carmel.guestjini.Common.BaseObservable;
import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Networking.Tickets.TicketFeedBackResponse;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetTicketFeedBackUseCase  extends BaseObservable<GetTicketFeedBackUseCase.Listener> {

    public interface Listener {
        void onTicketFeedbackFetched(TicketFeedBackResponse TicketFeedBackResponse);

        void onTicketFeedbackFetchFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public GetTicketFeedBackUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void fetchKTicketFeedbackByIdAndNotify(String ticketId) {
        Map<String, String> postData = new HashMap<>();
        postData.put("ticketId", ticketId);
        this.guestJiniAPI.getTicketFeedbackByTicketId(postData).enqueue(new Callback<TicketFeedBackResponse>() {
            @Override
            public void onResponse(Call<TicketFeedBackResponse> call, Response<TicketFeedBackResponse> response) {
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
            public void onFailure(Call<TicketFeedBackResponse> call, Throwable t) {
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
            listener.onTicketFeedbackFetchFailed();
        }
    }

    private void notifySuccess(TicketFeedBackResponse TicketFeedBackResponse) {
        for (Listener listener : getListeners()) {
            listener.onTicketFeedbackFetched(TicketFeedBackResponse);
        }
    }
}