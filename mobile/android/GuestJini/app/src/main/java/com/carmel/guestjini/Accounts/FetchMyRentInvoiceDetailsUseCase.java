package com.carmel.guestjini.Accounts;

import com.carmel.guestjini.Common.BaseObservable;
import com.carmel.guestjini.Networking.Accounts.AccountTicket;
import com.carmel.guestjini.Networking.Accounts.AccountTicketResponse;
import com.carmel.guestjini.Networking.GuestJiniAPI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchMyRentInvoiceDetailsUseCase extends BaseObservable<FetchMyRentInvoiceDetailsUseCase.Listener> {

    public interface Listener {
        void onRentInvoiceDetailFetched(AccountTicket accountTicket);

        void onRentInvoiceDetailFetchFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public FetchMyRentInvoiceDetailsUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void fetchRentInvoiceDetailAndNotify(String id) {
        Map<String, String> postData = new HashMap<>();
        postData.put("id",id);
        this.guestJiniAPI.getMyRentInvoiceDetails(postData).enqueue(new Callback<AccountTicketResponse>() {
            @Override
            public void onResponse(Call<AccountTicketResponse> call, Response<AccountTicketResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSuccess()) {
                        notifySuccess(response.body().getAccountTicket());
                    } else {
                        notifyFailure();
                    }
                } else {
                    notifyFailure();
                }
            }

            @Override
            public void onFailure(Call<AccountTicketResponse> call, Throwable t) {
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
            listener.onRentInvoiceDetailFetchFailed();
        }
    }

    private void notifySuccess(AccountTicket accountTicket) {
        for (Listener listener : getListeners()) {
            listener.onRentInvoiceDetailFetched(accountTicket);
        }
    }
}