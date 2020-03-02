package com.carmel.guestjini.booking.service;

import com.carmel.guestjini.booking.common.GuestStatus;
import com.carmel.guestjini.booking.common.RentUnit;
import com.carmel.guestjini.booking.components.AccountTicketService;
import com.carmel.guestjini.booking.model.Booking;
import com.carmel.guestjini.booking.model.Guest;
import com.carmel.guestjini.booking.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class GuestServiceImpl implements GuestService {

    @Autowired
    GuestRepository guestRepository;


    @Autowired
    AccountTicketService accountTicketService;


    @Override
    public Guest save(Guest guest) {
        return guestRepository.save(guest);
    }

    @Override
    public Optional<Guest> findByBooking(Booking booking) {
        return guestRepository.findByBooking(booking);
    }

    @Override
    public void delete(Guest guestToDelete) {
        guestRepository.delete(guestToDelete);
    }

    @Override
    public Optional<Guest> findById(String guestId) {
        return guestRepository.findById(guestId);
    }

    @Override
    public Guest doCheckout(Guest guest, Date actualCheckout) throws Exception {
        this._isGuestValidForCheckout(guest);
        this._reconcileAccounts(guest);
        return this._updateGuestRecord(guest, actualCheckout);
    }


    private void _isGuestValidForCheckout(Guest guest) throws Exception {
        try {
            if (guest == null) {
                throw new Exception("Guest record not found.");
            }
            if (guest.getGuestStatus() != GuestStatus.RESIDING.getValue()) {
                throw new Exception("This guest record is not valid for check out operation.");
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    private void _reconcileAccounts(Guest guest) throws Exception {
        try {
            if (guest.getRentUnit() == RentUnit.RENT_UNIT_DAY) {
                accountTicketService.generateDayRentInvoice(guest);
            } else if (guest.getRentUnit() == RentUnit.RENT_UNIT_MONTH) {
                accountTicketService.generateMonthRentInvoice(guest);
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    private Guest _updateGuestRecord(Guest guest, Date actualCheckout) throws Exception {
        try {
            guest.setActualCheckout(actualCheckout);
            return guestRepository.save(guest);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public Optional<Guest> findByEmail(String email) {
        return guestRepository.findByEmail(email);
    }
}
