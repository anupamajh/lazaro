package com.carmel.guestjini.Tickets;

import com.carmel.guestjini.Common.BaseObservable;
import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Networking.Tickets.Ticket;
import com.carmel.guestjini.Networking.Tickets.TicketFeedBack;
import com.carmel.guestjini.Networking.Tickets.TicketFeedBackResponse;
import com.carmel.guestjini.Networking.Tickets.TicketRequest;
import com.carmel.guestjini.Networking.Tickets.TicketFeedBackResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaveTicketFeedbackUseCase extends BaseObservable<SaveTicketFeedbackUseCase.Listener> {

    public interface Listener {
        void onTicketFeedbackSaved(TicketFeedBackResponse ticketFeedBackResponse);

        void onTicketFeedbackSaveFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public SaveTicketFeedbackUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void saveTicketFeedbackAndNotify(String ticketId, int rating, String feedBack) {
        TicketFeedBack ticketFeedBack = new TicketFeedBack();
        ticketFeedBack.setTicketId(ticketId);
        ticketFeedBack.setRating(rating);
        ticketFeedBack.setFeedback(feedBack);
        this.guestJiniAPI.saveTicketFeedback(ticketFeedBack).enqueue(new Callback<TicketFeedBackResponse>() {
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
            listener.onTicketFeedbackSaveFailed();
        }
    }

    private void notifySuccess(TicketFeedBackResponse ticketFeedBackResponse) {
        for (Listener listener : getListeners()) {
            listener.onTicketFeedbackSaved(ticketFeedBackResponse);
        }
    }
}