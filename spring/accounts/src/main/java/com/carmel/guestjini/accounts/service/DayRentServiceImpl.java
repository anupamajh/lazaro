package com.carmel.guestjini.accounts.service;

import com.carmel.guestjini.accounts.common.GuestStatus;
import com.carmel.guestjini.accounts.components.BookingAdditionalChargeService;
import com.carmel.guestjini.accounts.model.AccountTicket;
import com.carmel.guestjini.accounts.model.DTO.BookingAdditionalCharge;
import com.carmel.guestjini.accounts.model.DTO.Guest;
import com.carmel.guestjini.accounts.repository.AccountTicketRepository;
import com.carmel.guestjini.accounts.response.BookingAdditionalChargeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class DayRentServiceImpl implements DayRentService {
    @Autowired
    AccountTicketRepository accountTicketRepository;

    @Autowired
    BookingAdditionalChargeService bookingAdditionalChargeService;

    @Override
    public List<AccountTicket> generateInvoices(Guest guest) throws Exception {
        return null;
    }

    private void _computeRent(Guest guest) throws Exception {
        try {

            Date startDate = guest.getScheduledCheckIn();
            Date endDate = this.getGuestBillingUptoDate(guest);
            long noOfDays = TimeUnit.DAYS.convert((endDate.getTime() - startDate.getTime()), TimeUnit.MILLISECONDS);
            BookingAdditionalChargeResponse recurringCharges = bookingAdditionalChargeService.getRecurringPackageCharges(guest);
            BookingAdditionalChargeResponse oneTimeCharges = bookingAdditionalChargeService.getOneTimePackageCharges(guest);
            double recurringDiscount = this._getRecurringDiscount(guest);
            double oneTimeDiscount = this._getOneTimeDiscount(guest);


        } catch (Exception ex) {
            throw ex;
        }
    }

    private double _getOneTimeDiscount(Guest guest) {
        return 0;
    }

    private double _getRecurringDiscount(Guest guest) {
        return 0;
    }

    private Date getGuestBillingUptoDate(Guest guest) {
        Date uptoDate = new Date();
        if (guest.getGuestStatus() == GuestStatus.CHECKED_OUT.getValue()) {
            return guest.getActualCheckout().after(guest.getScheduledCheckout()) ? guest.getActualCheckout() : guest.getScheduledCheckout();
        }

        return uptoDate;
    }
}
