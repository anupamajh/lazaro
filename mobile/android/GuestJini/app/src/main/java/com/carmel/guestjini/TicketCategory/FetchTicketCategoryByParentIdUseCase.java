package com.carmel.guestjini.TicketCategory;

import com.carmel.guestjini.Common.BaseObservable;
import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Networking.Tickets.Ticket;
import com.carmel.guestjini.Networking.Tickets.TicketCategory;
import com.carmel.guestjini.Networking.Tickets.TicketCategoryResponse;
import com.carmel.guestjini.Networking.Tickets.TicketResponse;
import com.carmel.guestjini.Tickets.FetchTicketListUseCase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchTicketCategoryByParentIdUseCase extends BaseObservable<FetchTicketCategoryByParentIdUseCase.Listener> {
    public interface Listener {
        void onTicketCategoryListFetched(List<TicketCategory> ticketCategoryList);

        void onTicketCategoryListFetchFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public FetchTicketCategoryByParentIdUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void fetchTicketTicketCategoryListAndNotify(String parentId) {
        Map<String, String> postData = new HashMap<>();
        postData.put("parentId", parentId);
        this.guestJiniAPI.getTicketCategoriesByParent(postData).enqueue(new Callback<TicketCategoryResponse>() {
            @Override
            public void onResponse(Call<TicketCategoryResponse> call, Response<TicketCategoryResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().isSuccess()) {
                        notifySuccess(response.body().getTaskTicketCategoriesList());
                    } else {
                        notifyFailure();
                    }
                } else {
                    notifyFailure();
                }
            }

            @Override
            public void onFailure(Call<TicketCategoryResponse> call, Throwable t) {
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
            listener.onTicketCategoryListFetchFailed();
        }
    }

    private void notifySuccess(List<TicketCategory> ticketCategoryList) {
        for (Listener listener : getListeners()) {
            listener.onTicketCategoryListFetched(ticketCategoryList);
        }
    }
}
