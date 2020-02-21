package com.carmel.guestjini.booking.service;

import com.carmel.guestjini.booking.common.*;
import com.carmel.guestjini.booking.components.AccountReceiptsService;
import com.carmel.guestjini.booking.components.AccountTicketService;
import com.carmel.guestjini.booking.components.InventoryService;
import com.carmel.guestjini.booking.components.UserService;
import com.carmel.guestjini.booking.model.Booking;
import com.carmel.guestjini.booking.model.BookingAdditionalCharge;
import com.carmel.guestjini.booking.model.DTO.AccountTicket;
import com.carmel.guestjini.booking.model.DTO.User;
import com.carmel.guestjini.booking.model.Guest;
import com.carmel.guestjini.booking.model.KYC;
import com.carmel.guestjini.booking.repository.BookingRepository;
import com.carmel.guestjini.booking.response.AccountReceiptsResponse;
import com.carmel.guestjini.booking.response.AccountTicketResponse;
import com.carmel.guestjini.booking.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.carmel.guestjini.booking.specifications.BookingSpecification.checkInventoryAvailability;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    InventoryService inventoryService;

    @Autowired
    GuestService guestService;

    @Autowired
    KYCService kycService;

    @Autowired
    BookingAdditionalChargeService bookingAdditionalChargeService;

    @Autowired
    AccountReceiptsService accountReceiptsService;

    @Autowired
    AccountTicketService accountTicketService;

    @Autowired
    UserService userService;

    @Override
    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Optional<Booking> findById(String id) {
        return bookingRepository.findById(id);
    }

    @Override
    public List<Booking> findAll(Specification<Booking> bookingSpecification) {
        return bookingRepository.findAll(bookingSpecification);
    }

    @Override
    public List<Booking> findAllByIsDeletedAndClientId(int isDeleted, String clientId) {
        return bookingRepository.findAllByIsDeletedAndClientId(isDeleted, clientId);
    }

    @Override
    public Page<Booking> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable) {
        return bookingRepository.findAllByClientIdAndIsDeleted(clientId, isDeleted, pageable);
    }

    @Override
    public Page<Booking> findAll(Specification<Booking> textInAllColumns, Pageable pageable) {
        return bookingRepository.findAll(textInAllColumns, pageable);
    }

    @Override
    public Guest doCheckIn(Booking booking, Date actualCheckInDate) throws Exception {
        try {
            this._isBookingValidForCheckIn(booking);
            this._isInventoryAvailable(booking);
            Guest guest = this._createGuestRecord(booking, actualCheckInDate);
            this._updateKYC(guest, booking);
            this._updateDocuments(guest, booking);
            this._updatePackageCharge(guest, booking);
            booking.setBookingStatus(BookingStatus.USED.getValue());
            this._chargeGuest(guest, booking);
            this._updateReceipts(guest, booking);
            this.save(booking);
            return guest;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public Booking doCancelCheckIn(Booking booking) throws Exception {
        try {
            Optional<Guest> optionalGuest = guestService.findByBooking(booking);
            Guest guest = new Guest();
            Guest guestToDelete = new Guest();
            if (optionalGuest.isPresent()) {
                guest = optionalGuest.get();
                guestToDelete = optionalGuest.get();
            }

            this._isBookingValidForCancelCheckIn(booking, guest);
            guest.setId(null);
            this._updateKYC(guest, booking);
            this._updatePackageCharge(guest, booking);
            booking.setBookingStatus(BookingStatus.ACTIVE.getValue());
            this._revertChargeGuest(guestToDelete);
            this._updateReceipts(guest, booking);
            guestService.delete(guestToDelete);
        } catch (Exception ex) {
            throw ex;
        }
        return null;
    }

    private void _isBookingValidForCancelCheckIn(Booking booking, Guest guest) throws Exception {
        if (booking == null) {
            throw new Exception("Booking not found");
        }

        if (booking.getBookingStatus() != BookingStatus.USED.getValue()) {
            throw new Exception("This booking is not valid for cancel check in.");
        }

        if (guest.getGuestStatus() != GuestStatus.RESIDING.getValue()) {
            throw new Exception("This guest is not residing with us at the moment.");
        }
    }

    private void _isBookingValidForCheckIn(Booking booking) throws Exception {
        if (booking == null) {
            throw new Exception("Booking not found");
        }

        if (booking.getBookingStatus() != BookingStatus.ACTIVE.getValue()) {
            throw new Exception("This booking is not valid for check in.");
        }
    }

    private void _isInventoryAvailable(Booking booking) throws Exception {
        String parentIds = inventoryService.getParentIds(booking.getInventoryId());
        List<String> inventoryIds = Arrays.asList(parentIds.split("\\s*,\\s*"));
        List<Booking> bookings = bookingRepository.findAll(
                checkInventoryAvailability(inventoryIds, booking.getCheckInTime(),
                        booking.getCheckOutTime(), booking.getReferenceNo()));
        if (bookings.size() > 0) {
            throw new Exception("The selected inventory is not available right now.");
        }

    }

    private Guest _createGuestRecord(Booking booking, Date actualCheckInDate) throws Exception {
        try {
            Guest guest = new Guest(booking);
            guest.setGuestNo(String.valueOf(System.nanoTime()));
            guest.setActualCheckIn(actualCheckInDate == null ? new Date() : actualCheckInDate);
            guest.setActualCheckout(null);
            guest.setGuestStatus(GuestStatus.RESIDING.getValue());
            guest = guestService.save(guest);
            User user = new User(guest);
            user.setPassword("");
            UserResponse userResponse = userService.saveUser(user);
            if (!userResponse.isSuccess()) {
                if(!userResponse.getError().contains("Duplicate")) {
                    throw new Exception(userResponse.getError());
                }
            }
            return guest;
        } catch (Exception ex) {
            throw ex;
        }
    }

    private void _updateKYC(Guest guest, Booking booking) throws Exception {
        try {
            List<KYC> kycs = kycService.findAllByIsDeletedAndClientIdAndBooking(0, booking.getClientId(), booking);
            for (KYC kyc : kycs) {
                kyc.setGuestId(guest.getId());
                kycService.save(kyc);
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    private void _updateDocuments(Guest guest, Booking booking) throws Exception {
        try {
            //TODO : Fill this when Documents is implemented
        } catch (Exception ex) {
            throw ex;
        }
    }

    private void _updatePackageCharge(Guest guest, Booking booking) throws Exception {
        try {
            List<BookingAdditionalCharge> bookingAdditionalCharges
                    = bookingAdditionalChargeService.findAllByIsDeletedAndClientIdAndBooking(
                    0, booking.getClientId(), booking);
            for (BookingAdditionalCharge bookingAdditionalCharge : bookingAdditionalCharges) {
                bookingAdditionalCharge.setGuestId(guest.getId());
                bookingAdditionalChargeService.save(bookingAdditionalCharge);
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    private void _updateReceipts(Guest guest, Booking booking) throws Exception {
        try {
            AccountReceiptsResponse accountReceiptsResponse = accountReceiptsService.updateGuestReceipts(booking.getId(),
                    guest.getId());
            if (!accountReceiptsResponse.isSuccess()) {
                throw new Exception(accountReceiptsResponse.getError());
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    private void _chargeGuest(Guest guest, Booking booking) throws Exception {
        try {
            List<BookingAdditionalCharge> bookingAdditionalCharges
                    = bookingAdditionalChargeService.findAllByIsDeletedAndClientIdAndBookingAndBillingCycle(
                    0, booking.getClientId(), booking, BillingCycle.ONE_TIME.getValue()
            );
            AccountTicketResponse accountTicketResponse;
            for (BookingAdditionalCharge bookingAdditionalCharge : bookingAdditionalCharges) {
                switch (bookingAdditionalCharge.getChargeMethod()) {
                    case ChargeMethod.CHARGE_METHOD_DEBIT_NOTE: {
                        accountTicketResponse = this._chargeDebitNote(bookingAdditionalCharge, guest);
                        if (!accountTicketResponse.isSuccess()) {
                            throw new Exception(accountTicketResponse.getError());
                        }
                    }
                    break;
                    case ChargeMethod.CHARGE_METHOD_BILL: {
                        accountTicketResponse = this._chargeBill(bookingAdditionalCharge, guest);
                        if (!accountTicketResponse.isSuccess()) {
                            throw new Exception(accountTicketResponse.getError());
                        }
                    }
                    break;
                }
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    private AccountTicketResponse _chargeDebitNote(BookingAdditionalCharge bookingAdditionalCharge, Guest guest) throws Exception {
        AccountTicketResponse accountTicketResponse;
        try {
            AccountTicket accountTicket = new AccountTicket();
            accountTicket.setOrgId(bookingAdditionalCharge.getOrgId());
            accountTicket.setClientId(bookingAdditionalCharge.getClientId());
            accountTicket.setBookingId(guest.getBooking().getId());
            accountTicket.setGuestId(guest.getId());
            accountTicket.setTicketDate(new Date());
            accountTicket.setTicketNarration(bookingAdditionalCharge.getTitle());
            accountTicket.setItemTotal(bookingAdditionalCharge.getAmount());
            accountTicket.setNetTotal(bookingAdditionalCharge.getAmount());
            accountTicket.setTicketStatus(AccountTicketStatus.ACTIVE.getValue());
            accountTicket.setTicketIdentifier(TransactionIdentifier.TRANSACTION_IDENTIFIER_DEBIT_NOTE);
            accountTicketResponse = accountTicketService.saveAccountTicket(accountTicket);
            if (!accountTicketResponse.isSuccess()) {
                throw new Exception(accountTicketResponse.getError());
            }
        } catch (Exception ex) {
            throw ex;
        }
        return accountTicketResponse;
    }

    private AccountTicketResponse _chargeBill(BookingAdditionalCharge bookingAdditionalCharge, Guest guest) throws Exception {
        AccountTicketResponse accountTicketResponse;
        try {
            AccountTicket accountTicket = new AccountTicket();
            accountTicket.setOrgId(bookingAdditionalCharge.getOrgId());
            accountTicket.setClientId(bookingAdditionalCharge.getClientId());
            accountTicket.setBookingId(guest.getBooking().getId());
            accountTicket.setGuestId(guest.getId());
            accountTicket.setTicketDate(new Date());
            accountTicket.setTicketNarration(bookingAdditionalCharge.getTitle());
            accountTicket.setItemTotal(bookingAdditionalCharge.getAmount());
            accountTicket.setNetTotal(bookingAdditionalCharge.getAmount());
            accountTicket.setTicketStatus(AccountTicketStatus.ACTIVE.getValue());
            accountTicket.setTicketIdentifier(TransactionIdentifier.TRANSACTION_IDENTIFIER_BILL);
            accountTicketResponse = accountTicketService.saveAccountTicket(accountTicket);
            if (!accountTicketResponse.isSuccess()) {
                throw new Exception(accountTicketResponse.getError());
            }
        } catch (Exception ex) {
            throw ex;
        }
        return accountTicketResponse;
    }

    private AccountTicketResponse _revertChargeGuest(Guest guest) throws Exception {
        AccountTicketResponse accountTicketResponse;
        try {
            accountTicketResponse = accountTicketService.deleteAccountTicketsByGuest(guest.getId());

        } catch (Exception ex) {
            throw ex;
        }
        return accountTicketResponse;
    }

}
