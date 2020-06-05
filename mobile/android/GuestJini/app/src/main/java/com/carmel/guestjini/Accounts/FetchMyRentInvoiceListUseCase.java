package com.carmel.guestjini.Accounts;

import com.carmel.guestjini.Common.BaseObservable;
import com.carmel.guestjini.Networking.Accounts.AccountTicket;
import com.carmel.guestjini.Networking.Accounts.AccountTicketResponse;
import com.carmel.guestjini.Networking.GuestJiniAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchMyRentInvoiceListUseCase extends BaseObservable<FetchMyRentInvoiceListUseCase.Listener> {

    public interface Listener {
        void onRentInvoiceListFetched(List<AccountTicket> accountTickets);

        void onRentInvoiceListFetchFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public FetchMyRentInvoiceListUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void fetchRentInvoiceListAndNotify() {
        this.guestJiniAPI.getMyRentInvoices().enqueue(new Callback<AccountTicketResponse>() {
            @Override
            public void onResponse(Call<AccountTicketResponse> call, Response<AccountTicketResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSuccess()) {
                        notifySuccess(response.body().getAccountTicketList());
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
            listener.onRentInvoiceListFetchFailed();
        }
    }

    private void notifySuccess(List<AccountTicket> accountTickets) {
        for (Listener listener : getListeners()) {
            listener.onRentInvoiceListFetched(accountTickets);
        }
    }
}