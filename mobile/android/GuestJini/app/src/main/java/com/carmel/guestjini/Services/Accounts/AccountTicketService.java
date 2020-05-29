package com.carmel.guestjini.Services.Accounts;

import com.carmel.guestjini.Common.EndPoints;
import com.carmel.guestjini.Models.Accounts.AccountTicketResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AccountTicketService {
    String BASE_URL = EndPoints.END_POINT_URL;

    @POST("/guest-jini/account-tickets/get-my-rent-invoice")
    Call<AccountTicketResponse> getMyRentInvoices();

    @POST("/guest-jini/account-tickets/get")
    Call<AccountTicketResponse> getRentInvoice(
            @Body Map<String, String> postData
    );

}
