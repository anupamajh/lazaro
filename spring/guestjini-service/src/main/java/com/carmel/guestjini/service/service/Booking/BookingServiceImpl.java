package com.carmel.guestjini.service.service.Booking;

import com.carmel.guestjini.service.common.Accounts.AccountTicketStatus;
import com.carmel.guestjini.service.common.Booking.BookingStatus;
import com.carmel.guestjini.service.common.Booking.GuestStatus;
import com.carmel.guestjini.service.common.Booking.TransactionIdentifier;
import com.carmel.guestjini.service.common.Inventory.BillingCycle;
import com.carmel.guestjini.service.common.Inventory.ChargeMethod;
import com.carmel.guestjini.service.components.UserService;
import com.carmel.guestjini.service.model.Booking.Booking;
import com.carmel.guestjini.service.model.Booking.BookingAdditionalCharge;
import com.carmel.guestjini.service.model.Booking.Guest;
import com.carmel.guestjini.service.model.Booking.KYC;
import com.carmel.guestjini.service.model.DTO.Accounts.AccountTicketDTO;
import com.carmel.guestjini.service.model.DTO.Common.UserDTO;
import com.carmel.guestjini.service.model.HelpDesk.TaskForce;
import com.carmel.guestjini.service.model.Principal.UserInfo;
import com.carmel.guestjini.service.repository.Booking.BookingRepository;
import com.carmel.guestjini.service.response.Accounts.AccountReceiptsResponse;
import com.carmel.guestjini.service.response.Accounts.AccountTicketResponse;
import com.carmel.guestjini.service.response.Booking.BookingResponse;
import com.carmel.guestjini.service.response.Common.UserResponse;
import com.carmel.guestjini.service.service.Accounts.AccountReceiptService;
import com.carmel.guestjini.service.service.Accounts.AccountTicketService;
import com.carmel.guestjini.service.service.HelpDesk.TaskForceService;
import com.carmel.guestjini.service.service.Inventory.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.carmel.guestjini.service.specification.Booking.BookingSpecification.checkInventoryAvailability;


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
    AccountReceiptService accountReceiptsService;

    @Autowired
    AccountTicketService accountTicketService;

    @Autowired
    UserService userService;

    @Autowired
    TaskForceService taskForceService;

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
            booking.setBookingStatus(BookingStatus.USED);
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
            booking.setBookingStatus(BookingStatus.ACTIVE);
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

        if (booking.getBookingStatus() != BookingStatus.USED) {
            throw new Exception("This booking is not valid for cancel check in.");
        }

        if (guest.getGuestStatus() != GuestStatus.RESIDING) {
            throw new Exception("This guest is not residing with us at the moment.");
        }
    }

    private void _isBookingValidForCheckIn(Booking booking) throws Exception {
        if (booking == null) {
            throw new Exception("Booking not found");
        }

        if (booking.getBookingStatus() != BookingStatus.ACTIVE) {
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
            guest.setGuestStatus(GuestStatus.RESIDING);
            guest = guestService.save(guest);
//            UserDTO user = new UserDTO(guest);
//            user.setPassword("");
//            UserResponse userResponse = userService.saveUser(user);
//            if (!userResponse.isSuccess()) {
//                if (!userResponse.getError().contains("Duplicate")) {
//                    throw new Exception(userResponse.getError());
//                }
//            }
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
                    0, booking.getClientId(), booking, BillingCycle.ONE_TIME
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
            AccountTicketDTO accountTicket = new AccountTicketDTO();
            accountTicket.setOrgId(bookingAdditionalCharge.getOrgId());
            accountTicket.setClientId(bookingAdditionalCharge.getClientId());
            accountTicket.setBookingId(guest.getBooking().getId());
            accountTicket.setGuestId(guest.getId());
            accountTicket.setTicketDate(new Date());
            accountTicket.setTicketNarration(bookingAdditionalCharge.getTitle());
            accountTicket.setItemTotal(bookingAdditionalCharge.getAmount());
            accountTicket.setNetTotal(bookingAdditionalCharge.getAmount());
            accountTicket.setTicketStatus(AccountTicketStatus.ACTIVE);
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
            AccountTicketDTO accountTicket = new AccountTicketDTO();
            accountTicket.setOrgId(bookingAdditionalCharge.getOrgId());
            accountTicket.setClientId(bookingAdditionalCharge.getClientId());
            accountTicket.setBookingId(guest.getBooking().getId());
            accountTicket.setGuestId(guest.getId());
            accountTicket.setTicketDate(new Date());
            accountTicket.setTicketNarration(bookingAdditionalCharge.getTitle());
            accountTicket.setItemTotal(bookingAdditionalCharge.getAmount());
            accountTicket.setNetTotal(bookingAdditionalCharge.getAmount());
            accountTicket.setTicketStatus(AccountTicketStatus.ACTIVE);
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

    @Override
    public Optional<Booking> findByPhoneAndBookingStatus(String mobileNumber, int bookingStatus) {
        return bookingRepository.findByPhoneAndBookingStatus(mobileNumber, bookingStatus);
    }

    @Override
    public BookingResponse checkPhoneNumber(Map<String, String> formData, UserInfo userInfo) throws Exception {
        BookingResponse bookingResponse = new BookingResponse();
        try {
            String phone = formData.get("phone") == null ? null : String.valueOf(formData.get("phone"));

            if (phone == null) {
                throw new Exception("Phone number not received");
            }
            UserResponse userResponse = userService.checkPhoneNumber(formData);
            if (userResponse.isSuccess() == false) {
                bookingResponse.setHasUser(true);
                bookingResponse.setSuccess(true);
                bookingResponse.setError("You already have an account");
                return bookingResponse;
            }
            List<Booking> bookings = bookingRepository.findAllByIsDeletedAndClientIdAndPhone(0, userInfo.getClient().getClientId(), phone);
            if (bookings.size() > 0) {
                bookingResponse.setHasUser(false);
                bookingResponse.setHasBooking(true);
                bookingResponse.setBooking(bookings.get(0));
                bookingResponse.setSuccess(true);
                Optional<Guest> optionalGuest = guestService.findByBooking(bookings.get(0));
                if (optionalGuest.isPresent()) {
                    Guest guest = optionalGuest.get();
                    bookingResponse.setCustomer(true);
                    if (guest.getGuestStatus() == GuestStatus.RESIDING) {
                        bookingResponse.setResiding(true);
                    } else {
                        bookingResponse.setResiding(false);
                        bookingResponse.setError("You are not residing guest");
                    }
                }
            } else {
                bookingResponse.setHasUser(false);
                bookingResponse.setHasBooking(false);

                Optional<TaskForce> optionalTaskForce =
                        taskForceService.findByPhone(phone);
                if (optionalTaskForce.isPresent()) {
                    bookingResponse.setHasSupportAccount(false);
                    TaskForce taskForce = optionalTaskForce.get();
                    bookingResponse.setSupportTeamMember(true);
                } else {
                    bookingResponse.setSupportTeamMember(false);
                    bookingResponse.setHasSupportAccount(false);
                    bookingResponse.setError("Your phone number is not registered with us.");
                }
                bookingResponse.setSuccess(true);
            }

            return bookingResponse;
        } catch (Exception ex) {
            throw ex;
        }
    }
}
