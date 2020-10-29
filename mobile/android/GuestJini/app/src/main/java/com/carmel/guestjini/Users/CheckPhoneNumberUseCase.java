package com.carmel.guestjini.Users;

import com.carmel.guestjini.Networking.Booking.BookingResponse;
import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckPhoneNumberUseCase extends BaseObservableViewMvc<CheckPhoneNumberUseCase.Listener> {
    public interface Listener {
        void onCheckPhoneNumberSuccess(BookingResponse bookingResponse);

        void onCheckPhoneNumberFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public CheckPhoneNumberUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void checkPhoneNumberAndNotify(
            String mobile
    ) {
        Map<String, String> postData = new HashMap<>();
        postData.put("phone", mobile);
        postData.put("fullName", "fullName");

        this.guestJiniAPI.checkPhoneNumber(postData).enqueue(new Callback<BookingResponse>() {
            @Override
            public void onResponse(Call<BookingResponse> call, Response<BookingResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().isSuccess() ) {
                        notifySuccess(response.body());
                    } else {
                        notifyFailure();
                    }
                } else {
                    notifyFailure();
                }
            }

            @Override
            public void onFailure(Call<BookingResponse> call, Throwable t) {
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
            listener.onCheckPhoneNumberFailed();
        }
    }

    private void notifySuccess(BookingResponse bookingResponse) {
        for (Listener listener : getListeners()) {
            listener.onCheckPhoneNumberSuccess(bookingResponse);
        }

    }

}
