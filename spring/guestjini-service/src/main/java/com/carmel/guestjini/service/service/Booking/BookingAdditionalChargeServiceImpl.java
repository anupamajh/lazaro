package com.carmel.guestjini.service.service.Booking;

import com.carmel.guestjini.service.common.Inventory.BillingCycle;
import com.carmel.guestjini.service.model.Booking.Booking;
import com.carmel.guestjini.service.model.Booking.BookingAdditionalCharge;
import com.carmel.guestjini.service.model.DTO.Booking.GuestDTO;
import com.carmel.guestjini.service.repository.Booking.BookingAdditionalChargeRepository;
import com.carmel.guestjini.service.response.Booking.BookingAdditionalChargeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingAdditionalChargeServiceImpl implements BookingAdditionalChargeService {

    @Autowired
    BookingAdditionalChargeRepository bookingAdditionalChargeRepository;

    @Autowired
    BookingService bookingService;

    @Override
    public BookingAdditionalCharge save(BookingAdditionalCharge bookingAdditionalCharge) {
        return bookingAdditionalChargeRepository.save(bookingAdditionalCharge);
    }

    @Override
    public List<BookingAdditionalCharge> findAllByPackageId(String packageId) {
        return bookingAdditionalChargeRepository.findAllByPackageId(packageId);
    }

    @Override
    public void deleteAll(List<BookingAdditionalCharge> bookingAdditionalCharges) {
        bookingAdditionalChargeRepository.deleteAll(bookingAdditionalCharges);
    }

    @Override
    public Optional<BookingAdditionalCharge> findById(String id) {
        return bookingAdditionalChargeRepository.findById(id);
    }

    @Override
    public List<BookingAdditionalCharge> findAllByIsDeletedAndClientIdAndBooking(int isDeleted, String clientId, Booking booking) {
        return bookingAdditionalChargeRepository.findAllByIsDeletedAndClientIdAndBooking(isDeleted, clientId, booking);
    }

    @Override
    public Page<BookingAdditionalCharge> findAllByClientIdAndIsDeletedAndBooking(String clientId, int isDeleted, Booking booking, Pageable pageable) {
        return bookingAdditionalChargeRepository.findAllByClientIdAndIsDeletedAndBooking(clientId, isDeleted, booking, pageable);
    }

    @Override
    public Page<BookingAdditionalCharge> findAll(Specification<BookingAdditionalCharge> textInAllColumns, Pageable pageable) {
        return bookingAdditionalChargeRepository.findAll(textInAllColumns, pageable);
    }

    @Override
    public List<BookingAdditionalCharge> findAllByIsDeletedAndClientIdAndBookingAndBillingCycle(int isDeleted, String clientId, Booking booking, int billingCycle) {
        return bookingAdditionalChargeRepository.findAllByIsDeletedAndClientIdAndBookingAndBillingCycle(isDeleted, clientId, booking, billingCycle);
    }

    @Override
    public BookingAdditionalChargeResponse getRecurringPackageCharges(GuestDTO guest) throws Exception {
        try {
            Optional<Booking> optionalBooking = bookingService.findById(guest.getBooking().getId());
            List<BookingAdditionalCharge> bookingAdditionalCharges
                    = this.findAllByIsDeletedAndClientIdAndBookingAndBillingCycle(
                    0, guest.getClientId(),  optionalBooking.get(), BillingCycle.RENT_CYCLE
            );
            BookingAdditionalChargeResponse bookingAdditionalChargeResponse = new BookingAdditionalChargeResponse();
            bookingAdditionalChargeResponse.setSuccess(true);
            bookingAdditionalChargeResponse.setBookingAdditionalChargeList(bookingAdditionalCharges);
            return bookingAdditionalChargeResponse;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public BookingAdditionalChargeResponse getOneTimePackageCharges(GuestDTO guest) throws Exception {
        try {
            Optional<Booking> optionalBooking = bookingService.findById(guest.getBooking().getId());
            List<BookingAdditionalCharge> bookingAdditionalCharges
                    = this.findAllByIsDeletedAndClientIdAndBookingAndBillingCycle(
                    0, guest.getClientId(), optionalBooking.get(), BillingCycle.ONE_TIME
            );
            BookingAdditionalChargeResponse bookingAdditionalChargeResponse = new BookingAdditionalChargeResponse();
            bookingAdditionalChargeResponse.setSuccess(true);
            bookingAdditionalChargeResponse.setBookingAdditionalChargeList(bookingAdditionalCharges);
            return bookingAdditionalChargeResponse;
        } catch (Exception ex) {
            throw ex;
        }
    }
}
