package com.carmel.guestjini.accounts.service;

import com.carmel.guestjini.accounts.common.GuestConstants;
import com.carmel.guestjini.accounts.common.GuestStatus;
import com.carmel.guestjini.accounts.components.BookingAdditionalChargeService;
import com.carmel.guestjini.accounts.components.UserInformation;
import com.carmel.guestjini.accounts.model.AccountTicket;
import com.carmel.guestjini.accounts.model.AccountTicketItem;
import com.carmel.guestjini.accounts.model.DTO.BookingAdditionalCharge;
import com.carmel.guestjini.accounts.model.DTO.Guest;
import com.carmel.guestjini.accounts.model.Principal.UserInfo;
import com.carmel.guestjini.accounts.repository.AccountTicketItemRepository;
import com.carmel.guestjini.accounts.repository.AccountTicketRepository;
import com.carmel.guestjini.accounts.response.BookingAdditionalChargeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class DayRentServiceImpl implements DayRentService {

    private List<AccountTicketItem> rentItems = new ArrayList<>();
    private List<AccountTicketItem> debitNoteItems = new ArrayList<>();
    private List<AccountTicketItem> billItems = new ArrayList<>();

    private AccountTicket accountTicket;
    private AccountTicket debitNote;
    private AccountTicket bill;
    double finalRecurringDiscount = 0;
    double oneTimeDiscount = 0;

    @Autowired
    AccountTicketRepository accountTicketRepository;

    @Autowired
    AccountTicketItemRepository accountTicketItemRepository;

    @Autowired
    UserInformation userInformation;

    @Autowired
    BookingAdditionalChargeService bookingAdditionalChargeService;


    @Override
    public List<AccountTicket> getGuestTickets(Guest guest, int ticketIdentifier) {
        List<AccountTicket> result = new ArrayList<>();
        try {
            this._computeRent(guest);
            switch (ticketIdentifier) {
                case GuestConstants.TRANSACTION_IDENTIFIER_RENT_INVOICE: {
                    result = accountTicketRepository
                            .findAllByGuestIdAndTicketIdentifierAndTicketStatus(
                                    guest.getId(),
                                    GuestConstants.TRANSACTION_IDENTIFIER_RENT_INVOICE,
                                    GuestConstants.TICKET_STATUS_ACTIVE
                            );
                }
                break;
                case GuestConstants.TRANSACTION_IDENTIFIER_BILL: {
                    result = accountTicketRepository
                            .findAllByGuestIdAndTicketIdentifierAndTicketStatus(
                                    guest.getId(),
                                    GuestConstants.TRANSACTION_IDENTIFIER_BILL,
                                    GuestConstants.TICKET_STATUS_ACTIVE
                            );
                }
                break;
                case GuestConstants.TRANSACTION_IDENTIFIER_DEBIT_NOTE: {
                    result = accountTicketRepository
                            .findAllByGuestIdAndTicketIdentifierAndTicketStatus(
                                    guest.getId(),
                                    GuestConstants.TRANSACTION_IDENTIFIER_DEBIT_NOTE,
                                    GuestConstants.TICKET_STATUS_ACTIVE
                            );
                }
                break;
            }
        } catch (Exception ex) {

        }
        return result;
    }

    @Override
    public void generateInvoices(Guest guest) throws Exception {
        this._computeRent(guest);
        this._saveTransaction(guest);
        this.rentItems = new ArrayList<>();
        this.debitNoteItems = new ArrayList<>();
        this.billItems = new ArrayList<>();
    }

    private void _saveTransaction(Guest guest) {
        List<AccountTicket> savedAccountTickets =
                accountTicketRepository
                        .findAllByGuestIdAndTicketIdentifierAndTicketStatus(
                                guest.getId(),
                                GuestConstants.TRANSACTION_IDENTIFIER_RENT_INVOICE,
                                GuestConstants.TICKET_STATUS_ACTIVE
                        );

        if (savedAccountTickets.isEmpty()) {
            this._createTransactions(guest);
        } else {
            this._cancelTransactions(savedAccountTickets.get(0));
            this._createTransactions(guest);
        }

    }

    private void _createTransactions(Guest guest) {
        AccountTicket savedRentTicket = accountTicketRepository.save(accountTicket);
        for (AccountTicketItem rentItem : rentItems) {
            rentItem.setTicketId(savedRentTicket.getId());
            accountTicketItemRepository.save(rentItem);
        }
        debitNote.setParentId(savedRentTicket.getParentId());
        AccountTicket savedDebitNote = accountTicketRepository.save(debitNote);
        for (AccountTicketItem debitNoteItem : debitNoteItems) {
            debitNoteItem.setTicketId(savedDebitNote.getId());
            accountTicketItemRepository.save(debitNoteItem);
        }

        bill.setParentId(savedRentTicket.getParentId());
        AccountTicket savedBill = accountTicketRepository.save(bill);
        for (AccountTicketItem billItem : billItems) {
            billItem.setTicketId(savedBill.getId());
            accountTicketItemRepository.save(billItem);
        }
    }


    private void _cancelTransactions(AccountTicket accountTicket) {
        accountTicket.setTicketStatus(GuestConstants.TICKET_STATUS_CANCELLED);
        accountTicketRepository.save(accountTicket);
        List<AccountTicket> childTickets = accountTicketRepository.findAllByParentId(accountTicket.getId());
        for (AccountTicket childTicket : childTickets) {
            childTicket.setTicketStatus(GuestConstants.TICKET_STATUS_CANCELLED);
            accountTicketRepository.save(childTicket);
        }
    }

    private void _computeRent(Guest guest) throws Exception {
        try {

            Date startDate = guest.getScheduledCheckIn();
            Date endDate = this.getGuestBillingUptoDate(guest);
            long noOfDays = TimeUnit.DAYS.convert((endDate.getTime() - startDate.getTime()), TimeUnit.MILLISECONDS);
            BookingAdditionalChargeResponse recurringCharges = bookingAdditionalChargeService.getRecurringPackageCharges(guest);
            BookingAdditionalChargeResponse oneTimeCharges = bookingAdditionalChargeService.getOneTimePackageCharges(guest);
            double recurringDiscount = this._getRecurringDiscount(guest);
            this.oneTimeDiscount = this._getOneTimeDiscount(guest);
            for (int i = 0; i < noOfDays; i++) {
                List<BookingAdditionalCharge> bookingAdditionalCharges = new ArrayList<>();
                Date day = startDate;
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(day);
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                day = calendar.getTime();
                this._makeRentItem(guest, day.toString());
                if (i == 0 && oneTimeCharges.getBookingAdditionalChargeList().size() > 0) {
                    bookingAdditionalCharges.addAll(recurringCharges.getBookingAdditionalChargeList());
                    bookingAdditionalCharges.addAll(oneTimeCharges.getBookingAdditionalChargeList());
                    this._makePackageChargeItem(
                            guest,
                            day.toString(),
                            bookingAdditionalCharges
                    );
                } else {
                    this._makePackageChargeItem(
                            guest,
                            day.toString(),
                            recurringCharges.getBookingAdditionalChargeList()
                    );
                }
                this.finalRecurringDiscount += recurringDiscount;
            }
            this._prepareTransactions(guest, startDate, endDate);
        } catch (Exception ex) {
            throw ex;
        }
    }

    private void _prepareTransactions(Guest guest, Date startDate, Date endDate) {
        this._makeRentInvoice(guest, startDate, endDate);
        this._makeDebitNote(guest, startDate, endDate);
        this._makeBill(guest, startDate, endDate);

    }

    private void _makeBill(Guest guest, Date startDate, Date endDate) {
        UserInfo userInfo = userInformation.getUserInfo();
        Date periodFrom = startDate;
        Date periodTo = endDate;

        String narration = "Towards rent for the period " + periodFrom.toString() + " through " + periodTo.toString();
        narration += System.lineSeparator() + "PACKAGE: " + guest.getPackageTitle();
        narration += System.lineSeparator() + "UNIT: " + guest.getInventoryPath();
        narration += System.lineSeparator() + "RENT: Rs." + guest.getRent() + " " + guest.getRentUnitLabel();
        double grossTotal = this._getItemsSumTotal(this.billItems);

        bill = new AccountTicket();
        bill.setClientId(userInfo.getClient().getClientId());
        if (userInfo.getDefaultOrganization() != null) {
            if (userInfo.getDefaultOrganization() != null) {
                bill.setOrgId(userInfo.getDefaultOrganization().getId());
            }
        }
        bill.setTicketIdentifier(GuestConstants.TRANSACTION_IDENTIFIER_BILL);
        bill.setTicketNumber(String.valueOf(System.nanoTime()));
        bill.setGuestId(guest.getId());
        bill.setBookingId(null);
        bill.setAccountHeadId(null);
        bill.setTemplateId(null);
        bill.setTicketDate(new Date());
        bill.setPeriodFrom(startDate);
        bill.setPeriodUpto(endDate);
        bill.setTicketNarration(narration);
        bill.setItemTotal(grossTotal);
        bill.setTaxTotal(0.0);
        bill.setDiscount(0.0);
        bill.setNetTotal(
                grossTotal
        );
        bill.setTicketStatus(GuestConstants.TICKET_STATUS_ACTIVE);
    }

    private void _makeDebitNote(Guest guest, Date startDate, Date endDate) {
        UserInfo userInfo = userInformation.getUserInfo();
        Date periodFrom = startDate;
        Date periodTo = endDate;

        String narration = "Towards rent for the period " + periodFrom.toString() + " through " + periodTo.toString();
        narration += System.lineSeparator() + "PACKAGE: " + guest.getPackageTitle();
        narration += System.lineSeparator() + "UNIT: " + guest.getInventoryPath();
        narration += System.lineSeparator() + "RENT: Rs." + guest.getRent() + " " + guest.getRentUnitLabel();
        double grossTotal = this._getItemsSumTotal(this.debitNoteItems);

        debitNote = new AccountTicket();
        debitNote.setClientId(userInfo.getClient().getClientId());
        if (userInfo.getDefaultOrganization() != null) {
            if (userInfo.getDefaultOrganization() != null) {
                debitNote.setOrgId(userInfo.getDefaultOrganization().getId());
            }
        }
        debitNote.setTicketIdentifier(GuestConstants.TRANSACTION_IDENTIFIER_DEBIT_NOTE);
        debitNote.setTicketNumber(String.valueOf(System.nanoTime()));
        debitNote.setGuestId(guest.getId());
        debitNote.setBookingId(null);
        debitNote.setAccountHeadId(null);
        debitNote.setTemplateId(null);
        debitNote.setTicketDate(new Date());
        debitNote.setPeriodFrom(startDate);
        debitNote.setPeriodUpto(endDate);
        debitNote.setTicketNarration(narration);
        debitNote.setItemTotal(grossTotal);
        debitNote.setTaxTotal(0.0);
        debitNote.setDiscount(0.0);
        debitNote.setNetTotal(
                grossTotal
        );
        debitNote.setTicketStatus(GuestConstants.TICKET_STATUS_ACTIVE);

    }

    private double _getItemsSumTotal(List<AccountTicketItem> accountTicketItems) {
        return accountTicketItems.stream().mapToDouble(AccountTicketItem::getItemTotal).sum();
    }

    private void _makeRentInvoice(Guest guest, Date startDate, Date endDate) {
        UserInfo userInfo = userInformation.getUserInfo();
        Date periodFrom = startDate;
        Date periodTo = endDate;

        String narration = "Towards rent for the period " + periodFrom.toString() + " through " + periodTo.toString();
        narration += System.lineSeparator() + "PACKAGE: " + guest.getPackageTitle();
        narration += System.lineSeparator() + "UNIT: " + guest.getInventoryPath();
        narration += System.lineSeparator() + "RENT: Rs." + guest.getRent() + " " + guest.getRentUnitLabel();
        double grossTotal = this._getItemsSumTotal(this.rentItems);

        accountTicket = new AccountTicket();
        accountTicket.setClientId(userInfo.getClient().getClientId());
        if (userInfo.getDefaultOrganization() != null) {
            if (userInfo.getDefaultOrganization() != null) {
                accountTicket.setOrgId(userInfo.getDefaultOrganization().getId());
            }
        }
        accountTicket.setTicketIdentifier(GuestConstants.TRANSACTION_IDENTIFIER_RENT_INVOICE);
        accountTicket.setTicketNumber(String.valueOf(System.nanoTime()));
        accountTicket.setGuestId(guest.getId());
        accountTicket.setBookingId(null);
        accountTicket.setAccountHeadId(null);
        accountTicket.setTemplateId(null);
        accountTicket.setTicketDate(new Date());
        accountTicket.setPeriodFrom(startDate);
        accountTicket.setPeriodUpto(endDate);
        accountTicket.setTicketNarration(narration);
        accountTicket.setItemTotal(grossTotal);
        accountTicket.setTaxTotal(0.0);
        accountTicket.setDiscount(this.finalRecurringDiscount + this.oneTimeDiscount);
        accountTicket.setNetTotal(
                grossTotal - (this.finalRecurringDiscount + this.oneTimeDiscount)
        );
        accountTicket.setTicketStatus(GuestConstants.TICKET_STATUS_ACTIVE);
    }

    private void _makePackageChargeItem(
            Guest guest,
            String day,
            List<BookingAdditionalCharge> bookingAdditionalCharges
    ) {
        if (bookingAdditionalCharges.isEmpty()) {
            return;
        }

        for (int i = 0; i < bookingAdditionalCharges.size(); i++) {
            switch (bookingAdditionalCharges.get(i).getChargeMethod()) {
                case GuestConstants.CHARGE_METHOD_RENT_INVOICE: {
                    this._appendInvoiceCharge(
                            guest,
                            day,
                            bookingAdditionalCharges.get(i)
                    );
                }
                break;
                case GuestConstants.CHARGE_METHOD_DEBIT_NOTE: {
                    this._appendDebitNoteCharge(
                            guest,
                            day,
                            bookingAdditionalCharges.get(i)
                    );
                }
                break;
                case GuestConstants.CHARGE_METHOD_BILL: {
                    this._appendBillCharge(
                            guest,
                            day,
                            bookingAdditionalCharges.get(i)
                    );
                }
                break;


            }
        }
    }

    private void _appendDebitNoteCharge(Guest guest, String day, BookingAdditionalCharge bookingAdditionalCharge) {
        int rentItemCounter = debitNoteItems.size() + 1;
        AccountTicketItem accountTicketItem = new AccountTicketItem();
        accountTicketItem.setLineNo(rentItemCounter);
        accountTicketItem.setItemNarration(bookingAdditionalCharge.getTitle() + " for the day " + day);
        accountTicketItem.setRate(bookingAdditionalCharge.getAmount());
        accountTicketItem.setQty(1.0);
        accountTicketItem.setQtyUnit(null);
        accountTicketItem.setSubTotal(bookingAdditionalCharge.getAmount());
        accountTicketItem.setTaxValue(0.0);
        accountTicketItem.setTaxValueIdentifier(null);
        accountTicketItem.setItemTotal(bookingAdditionalCharge.getAmount());
        debitNoteItems.add(accountTicketItem);
    }

    private void _appendInvoiceCharge(Guest guest, String day, BookingAdditionalCharge bookingAdditionalCharge) {
        int rentItemCounter = rentItems.size() + 1;
        AccountTicketItem accountTicketItem = new AccountTicketItem();
        accountTicketItem.setLineNo(rentItemCounter);
        accountTicketItem.setItemNarration(bookingAdditionalCharge.getTitle() + " for the day " + day);
        accountTicketItem.setRate(bookingAdditionalCharge.getAmount());
        accountTicketItem.setQty(1.0);
        accountTicketItem.setQtyUnit(null);
        accountTicketItem.setSubTotal(bookingAdditionalCharge.getAmount());
        accountTicketItem.setTaxValue(0.0);
        accountTicketItem.setTaxValueIdentifier(null);
        accountTicketItem.setItemTotal(bookingAdditionalCharge.getAmount());
        rentItems.add(accountTicketItem);
    }

    private void _appendBillCharge(Guest guest, String day, BookingAdditionalCharge bookingAdditionalCharge) {
        int rentItemCounter = billItems.size() + 1;
        AccountTicketItem accountTicketItem = new AccountTicketItem();
        accountTicketItem.setLineNo(rentItemCounter);
        accountTicketItem.setItemNarration(bookingAdditionalCharge.getTitle() + " for the day " + day);
        accountTicketItem.setRate(bookingAdditionalCharge.getAmount());
        accountTicketItem.setQty(1.0);
        accountTicketItem.setQtyUnit(null);
        accountTicketItem.setSubTotal(bookingAdditionalCharge.getAmount());
        accountTicketItem.setTaxValue(0.0);
        accountTicketItem.setTaxValueIdentifier(null);
        accountTicketItem.setItemTotal(bookingAdditionalCharge.getAmount());
        billItems.add(accountTicketItem);
    }


    private void _makeRentItem(Guest guest, String rentDay) {
        int rentItemCounter = rentItems.size() + 1;
        AccountTicketItem accountTicketItem = new AccountTicketItem();
        accountTicketItem.setLineNo(rentItemCounter);
        accountTicketItem.setItemNarration("Rent for the day " + rentDay);
        accountTicketItem.setRate(guest.getRent());
        accountTicketItem.setQty(1.0);
        accountTicketItem.setQtyUnit(null);
        accountTicketItem.setSubTotal(guest.getRent());
        accountTicketItem.setTaxValue(0.0);
        accountTicketItem.setTaxValueIdentifier(null);
        accountTicketItem.setItemTotal(guest.getRent());
        rentItems.add(accountTicketItem);
    }

    private double _getOneTimeDiscount(Guest guest) {
        double discountAmount = 0;
        if (guest.getDiscountIdentifier() == GuestConstants.DISCOUNT_IDENTIFIER_ONETIME) {
            switch (guest.getDiscountValueIdentifier()) {
                case GuestConstants.DISCOUNT_VALUE_IDENTIFIER_FIXED: {
                    discountAmount = _getFixedDiscount(guest, false);
                }
                break;
                case GuestConstants.DISCOUNT_VALUE_IDENTIFIER_PERCENTAGE: {
                    discountAmount = _getPercentageDiscount(guest, false);
                }
                break;
            }
        }
        return discountAmount;
    }

    private double _getRecurringDiscount(Guest guest) {
        double discountAmount = 0;
        if (guest.getDiscountIdentifier() == GuestConstants.DISCOUNT_IDENTIFIER_RECURRING) {
            switch (guest.getDiscountValueIdentifier()) {
                case GuestConstants.DISCOUNT_VALUE_IDENTIFIER_FIXED: {
                    discountAmount = _getFixedDiscount(guest, false);
                }
                break;
                case GuestConstants.DISCOUNT_VALUE_IDENTIFIER_PERCENTAGE: {
                    discountAmount = _getPercentageDiscount(guest, false);
                }
                break;
            }
        }
        return discountAmount;
    }

    private double _getPercentageDiscount(Guest guest, boolean firstInvoice) {
        double discountAmount = 0;
        switch (guest.getDiscountIdentifier()) {
            case GuestConstants.DISCOUNT_IDENTIFIER_ONETIME: {
                if (firstInvoice) {
                    discountAmount = Math.round((guest.getDiscountValue() * guest.getRent())) / 100;
                }
            }
            break;
            case GuestConstants.DISCOUNT_IDENTIFIER_RECURRING: {
                discountAmount = Math.round((guest.getDiscountValue() * guest.getRent())) / 100;
            }
            break;
        }

        return discountAmount;
    }

    private double _getFixedDiscount(Guest guest, Boolean firstInvoice) {
        firstInvoice = (firstInvoice == null) ? false : firstInvoice;
        double discountAmount = 0;
        switch (guest.getDiscountIdentifier()) {
            case GuestConstants.DISCOUNT_IDENTIFIER_ONETIME: {
                if (firstInvoice) {
                    discountAmount = guest.getDiscountValue();
                }
            }
            break;
            case GuestConstants.DISCOUNT_IDENTIFIER_RECURRING: {
                discountAmount = guest.getDiscountValue();
            }
            break;
        }

        return discountAmount;
    }


    private Date getGuestBillingUptoDate(Guest guest) {
        Date uptoDate = new Date();
        if (guest.getGuestStatus() == GuestStatus.CHECKED_OUT.getValue()) {
            return guest.getActualCheckout().after(guest.getScheduledCheckout()) ? guest.getActualCheckout() : guest.getScheduledCheckout();
        }

        return uptoDate;
    }
}
