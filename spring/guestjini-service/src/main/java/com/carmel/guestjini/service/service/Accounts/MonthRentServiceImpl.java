package com.carmel.guestjini.service.service.Accounts;

import com.carmel.guestjini.service.common.Accounts.GuestConstants;
import com.carmel.guestjini.service.components.UserInformation;
import com.carmel.guestjini.service.model.Accounts.AccountTicket;
import com.carmel.guestjini.service.model.Accounts.AccountTicketItem;
import com.carmel.guestjini.service.model.Booking.BookingAdditionalCharge;
import com.carmel.guestjini.service.model.DTO.Booking.BookingAdditionalChargeDTO;
import com.carmel.guestjini.service.model.DTO.Booking.GuestDTO;
import com.carmel.guestjini.service.model.Principal.UserInfo;
import com.carmel.guestjini.service.repository.Accounts.AccountTicketRepository;
import com.carmel.guestjini.service.response.Booking.BookingAdditionalChargeResponse;
import com.carmel.guestjini.service.response.Booking.GuestResponse;
import com.carmel.guestjini.service.service.Booking.BookingAdditionalChargeService;
import com.carmel.guestjini.service.service.Booking.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.*;

@Service
public class MonthRentServiceImpl implements MonthRentService {
    @Autowired
    AccountTicketRepository accountTicketRepository;

    @Autowired
    AccountTicketItemService accountTicketItemService;

    @Autowired
    GuestService guestService;

    @Autowired
    BookingAdditionalChargeService bookingAdditionalChargeService;

    @Autowired
    UserInformation userInformation;

    @Autowired
    EntityManager entityManager;


    private int month;
    private int year;
    private String guestId;
    private GuestDTO guest;
    private Date periodFrom;
    private Date periodUpto;

    private Boolean isFirstInvoice = false;
    private double grossRent = 0.0;
    private double grossDiscount = 0.0;
    private List<BookingAdditionalChargeDTO> packageChargesRecurring = new ArrayList<>();
    private List<BookingAdditionalChargeDTO> packageChargesOneTime = new ArrayList<>();


    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<AccountTicket> generateInvoices(int month, int year, String guestId) throws Exception {
        if (month != 0) {
            month -= 1;
        }
        this.month = month;
        this.year = year;
        this.guestId = guestId;
        HashMap<String, Date> period = this._getRentPeriod();
        this.periodFrom = period.get("start");
        this.periodUpto = period.get("end");
        if (guestId == null) {
            GuestResponse guestResponse = guestService.getGuestWithinPeriod(this.month, this.year);
            guestResponse.getGuestList().forEach(guest1 -> {
                this.isFirstInvoice = false;
                this.grossDiscount = 0.0;
                this.grossDiscount = 0.0;
                this.packageChargesRecurring = new ArrayList<>();
                this.packageChargesOneTime = new ArrayList<>();
                this.guestId = guest1.getId();
                try {
                    this._getGuest();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (this.guest.getRentUnit() == GuestConstants.RENT_CYCLE_MONTH) {
                    try {
                        this._computeRent();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        this._saveGeneratedInvoice();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });


        } else {
            this._getGuest();
            if (this.guest.getRentUnit() == GuestConstants.RENT_CYCLE_MONTH) {
                this._computeRent();
                this._saveGeneratedInvoice();
            }
        }
        return null;
    }

    private void _computeRent() throws Exception {
        try {
            this._validatePeriod();
            BookingAdditionalChargeResponse recurringCharges = bookingAdditionalChargeService.getRecurringPackageCharges(guest);
            BookingAdditionalChargeResponse oneTimeCharges = bookingAdditionalChargeService.getOneTimePackageCharges(guest);
            this.packageChargesRecurring = recurringCharges.getBookingAdditionalChargeList();
            this.packageChargesOneTime = oneTimeCharges.getBookingAdditionalChargeList();
            this.isFirstInvoice = this._isCheckedInThisMonth();
            if (this.isFirstInvoice == true) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(this.guest.getScheduledCheckIn());
                int guestDays = 0;
                int scheduledCheckin = calendar.get(Calendar.DAY_OF_MONTH);
                if (scheduledCheckin == 1) {
                    guestDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                } else {
                    int lastDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                    guestDays = (int) (lastDayOfMonth - scheduledCheckin);
                }
                int scheduledCheckInMonthDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                this.grossRent = Math.floor(((this.guest.getRent() * guestDays) / scheduledCheckInMonthDays) * 100) / 100;
                this.grossDiscount = this._getDiscount();
            } else if (this._isStayedInThisMonth()) {
                this.grossRent = this.guest.getRent();
                this.grossDiscount = this._getDiscount();
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    private void _getGuest() throws Exception {
        try {
            GuestResponse guestResponse = guestService.getGuestById(this.guestId);
            if (guestResponse.isSuccess()) {
                this.guest = guestResponse.getGuest();
            } else {
                throw new Exception(guestResponse.getError());
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    private HashMap<String, Date> _getRentPeriod() {
        int periodDay = 1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(this.year, this.month, periodDay, 0, 0);
        Date start = calendar.getTime();
        calendar.set(this.year, this.month, calendar.getActualMaximum(Calendar.DAY_OF_MONTH), 0, 0);
        Date end = calendar.getTime();
        HashMap<String, Date> retVal = new HashMap<>();
        retVal.put("start", start);
        retVal.put("end", end);
        return retVal;
    }

    private void _saveGeneratedInvoice() throws Exception {
        try {
            List<AccountTicket> accountTickets = accountTicketRepository.findAllByGuestIdAndPeriodFromAndPeriodUptoAndTicketIdentifierAndTicketStatus(
                    this.guestId,
                    this.periodFrom,
                    this.periodUpto,
                    GuestConstants.TRANSACTION_IDENTIFIER_RENT_INVOICE,
                    GuestConstants.TICKET_STATUS_ACTIVE
            );
            AccountTicket accountTicket = null;
            if (!accountTickets.isEmpty()) {
                accountTicket = accountTickets.get(0);
            }

            if (accountTicket == null) {
                accountTicket = this._createGeneratedRentInvoice();
                this.reTotal(accountTicket);
                accountTicketRepository.save(accountTicket);
            } else {
                accountTicket = this._updateGeneratedRentInvoice(accountTicket);
                this.reTotal(accountTicket);
                accountTicketRepository.save(accountTicket);
            }


        } catch (Exception ex) {
            throw ex;
        }

    }

    private AccountTicket _createGeneratedRentInvoice() throws Exception {
        try {
            AccountTicket accountTicket = this._makeNewTicketModel();
            AccountTicket savedTicket = accountTicketRepository.save(accountTicket);
            String ticketId = savedTicket.getId();
            this._createRentItem(ticketId);
            this._setPackageCharges(ticketId);
            return accountTicket;
        } catch (Exception ex) {
            throw ex;
        }
    }

    private AccountTicket _updateGeneratedRentInvoice(AccountTicket accountTicket) throws Exception {
        try {
            String oldTicketId = accountTicket.getId();

            accountTicket.setId("");
            accountTicket.setDiscount(this.grossDiscount);
            accountTicket.setNetTotal(this.grossRent - this.grossDiscount);
            accountTicket.setItemTotal(this.grossRent);
            accountTicket = accountTicketRepository.save(accountTicket);
            this._cancelTransactions(oldTicketId);
            this._createRentItem(accountTicket.getId());
            this._setPackageCharges(accountTicket.getId());
            return accountTicket;
        } catch (Exception ex) {
            throw ex;
        }
    }

    private void _createRentItem(String ticketId) throws Exception {
        try {
            AccountTicketItem accountTicketItem = new AccountTicketItem();
            accountTicketItem.setTicketId(ticketId);
            accountTicketItem.setLineNo(1);
            accountTicketItem.setItemNarration("Rent");
            accountTicketItem.setRate(this.grossRent);
            accountTicketItem.setQty(1.0);
            accountTicketItem.setQtyUnit(null);
            accountTicketItem.setSubTotal(this.grossRent);
            accountTicketItem.setTaxValue(0.0);
            accountTicketItem.setTaxValueIdentifier(null);
            accountTicketItem.setItemTotal(this.grossRent);
            accountTicketItemService.save(accountTicketItem);
        } catch (Exception ex) {
            throw ex;
        }
    }

    private void _setPackageCharges(String ticketId) throws Exception {
        try {
            if (!this.packageChargesOneTime.isEmpty() && this.isFirstInvoice) {
                this._applyPackageCharges(ticketId, this.packageChargesOneTime);
            }
            if (!this.packageChargesRecurring.isEmpty()) {
                this._applyPackageCharges(ticketId, this.packageChargesRecurring);
            }
        } catch (Exception ex) {

        }
    }

    private void _applyPackageCharges(String ticketId, List<BookingAdditionalChargeDTO> packageCharges) throws Exception {
        try {
            for (BookingAdditionalChargeDTO bookingAdditionalCharge : packageCharges) {
                switch (bookingAdditionalCharge.getChargeMethod()) {
                    case GuestConstants.CHARGE_METHOD_RENT_INVOICE: {
                        this._chargeInvoice(ticketId, bookingAdditionalCharge);
                    }
                    break;
                    case GuestConstants.CHARGE_METHOD_DEBIT_NOTE: {
                        this._chargeDebitNote(ticketId, bookingAdditionalCharge);
                    }
                    break;
                    case GuestConstants.CHARGE_METHOD_BILL: {
                        this._chargeBill(ticketId, bookingAdditionalCharge);
                    }
                    break;
                }
            }

        } catch (Exception ex) {
            throw ex;
        }
    }

    private void _chargeInvoice(String ticketId, BookingAdditionalChargeDTO bookingAdditionalCharge) throws Exception {
        try {
            AccountTicketItem accountTicketItem = new AccountTicketItem();
            accountTicketItem.setTicketId(ticketId);
            accountTicketItem.setLineNo(0);
            accountTicketItem.setItemNarration(bookingAdditionalCharge.getTitle());
            accountTicketItem.setRate(bookingAdditionalCharge.getAmount());
            accountTicketItem.setQty(1.0);
            accountTicketItem.setQtyUnit(null);
            accountTicketItem.setSubTotal(bookingAdditionalCharge.getAmount());
            accountTicketItem.setTaxValue(0.0);
            accountTicketItem.setTaxValueIdentifier(0);
            accountTicketItem.setItemTotal(bookingAdditionalCharge.getAmount());
            accountTicketItemService.save(accountTicketItem);
        } catch (Exception ex) {
            throw ex;
        }
    }

    private void _chargeDebitNote(String ticketId, BookingAdditionalChargeDTO bookingAdditionalCharge) throws Exception {
        try {
            AccountTicket accountTicket = new AccountTicket();
            UserInfo userInfo = userInformation.getUserInfo();
            accountTicket.setClientId(userInfo.getClient().getClientId());
            if (userInfo.getDefaultOrganization() != null) {
                if (userInfo.getDefaultOrganization() != null) {
                    accountTicket.setOrgId(userInfo.getDefaultOrganization().getId());
                }
            }
            accountTicket.setParentId(ticketId);
            accountTicket.setTicketIdentifier(GuestConstants.TRANSACTION_IDENTIFIER_DEBIT_NOTE);
            accountTicket.setTicketNumber(String.valueOf(System.nanoTime()));
            accountTicket.setGuestId(guest.getId());
            accountTicket.setBookingId(null);
            accountTicket.setAccountHeadId(null);
            accountTicket.setTemplateId(null);
            accountTicket.setTicketDate(this.periodUpto);
            accountTicket.setPeriodFrom(this.periodFrom);
            accountTicket.setPeriodUpto(this.periodUpto);
            accountTicket.setTicketNarration(bookingAdditionalCharge.getTitle());
            accountTicket.setItemTotal(bookingAdditionalCharge.getAmount());
            accountTicket.setTaxTotal(0.0);
            accountTicket.setDiscount(bookingAdditionalCharge.getAmount());
            accountTicket.setNetTotal(
                    bookingAdditionalCharge.getAmount()
            );
            accountTicket.setTicketStatus(GuestConstants.TICKET_STATUS_ACTIVE);
            accountTicketRepository.save(accountTicket);
        } catch (Exception ex) {
            throw ex;
        }
    }

    private void _chargeBill(String ticketId, BookingAdditionalChargeDTO bookingAdditionalCharge) throws Exception {
        try {
            AccountTicket accountTicket = new AccountTicket();
            UserInfo userInfo = userInformation.getUserInfo();
            accountTicket.setClientId(userInfo.getClient().getClientId());
            if (userInfo.getDefaultOrganization() != null) {
                if (userInfo.getDefaultOrganization() != null) {
                    accountTicket.setOrgId(userInfo.getDefaultOrganization().getId());
                }
            }
            accountTicket.setParentId(ticketId);
            accountTicket.setTicketIdentifier(GuestConstants.TRANSACTION_IDENTIFIER_BILL);
            accountTicket.setTicketNumber(String.valueOf(System.nanoTime()));
            accountTicket.setGuestId(guest.getId());
            accountTicket.setBookingId(null);
            accountTicket.setAccountHeadId(null);
            accountTicket.setTemplateId(null);
            accountTicket.setTicketDate(this.periodUpto);
            accountTicket.setPeriodFrom(this.periodFrom);
            accountTicket.setPeriodUpto(this.periodUpto);
            accountTicket.setTicketNarration(bookingAdditionalCharge.getTitle());
            accountTicket.setItemTotal(bookingAdditionalCharge.getAmount());
            accountTicket.setTaxTotal(0.0);
            accountTicket.setDiscount(bookingAdditionalCharge.getAmount());
            accountTicket.setNetTotal(
                    bookingAdditionalCharge.getAmount()
            );
            accountTicket.setTicketStatus(GuestConstants.TICKET_STATUS_ACTIVE);
            accountTicketRepository.save(accountTicket);
        } catch (Exception ex) {
            throw ex;
        }
    }

    private void reTotal(AccountTicket accountTicket) throws Exception {
        try {
            double itemsTotal;
            List<AccountTicketItem> accountTicketItems = accountTicketItemService.findAllByTicketId(accountTicket.getId());
            itemsTotal = accountTicketItems.stream().mapToDouble(AccountTicketItem::getItemTotal).sum();

            if (itemsTotal > 0 && (accountTicket.getItemTotal() != itemsTotal)) {
                accountTicket.setItemTotal(itemsTotal);
                accountTicket.setDiscount(this.grossDiscount);
                accountTicket.setNetTotal(itemsTotal - this.grossDiscount);
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    private double _getDiscount() {
        double discountAmount = 0.0;
        switch (this.guest.getDiscountValueIdentifier()) {
            case GuestConstants.DISCOUNT_VALUE_IDENTIFIER_FIXED: {
                discountAmount = this._getFixedDiscount();
            }
            break;
            case GuestConstants.DISCOUNT_VALUE_IDENTIFIER_PERCENTAGE: {
                discountAmount = this._getPercentageDiscount();
            }
            break;
        }

        return discountAmount;
    }

    private double _getFixedDiscount() {
        double discountAmount = 0.0;
        switch (this.guest.getDiscountIdentifier()) {
            case GuestConstants.DISCOUNT_IDENTIFIER_ONETIME: {
                if (this.isFirstInvoice) {
                    discountAmount = this.guest.getDiscountValue();
                }
            }
            break;
            case GuestConstants.DISCOUNT_IDENTIFIER_RECURRING: {
                discountAmount = this.guest.getDiscountValue();
            }
            break;
        }
        return discountAmount;
    }

    private double _getPercentageDiscount() {
        double discountAmount = 0.0;
        switch (this.guest.getDiscountIdentifier()) {
            case GuestConstants.DISCOUNT_IDENTIFIER_ONETIME: {
                if (this.isFirstInvoice) {
                    discountAmount = Math.floor(((this.guest.getDiscountValue() * this.guest.getRent()) / 100));
                }
            }
            break;
            case GuestConstants.DISCOUNT_IDENTIFIER_RECURRING: {
                discountAmount = Math.floor(((this.guest.getDiscountValue() * this.guest.getRent()) / 100));
            }
            break;
        }
        return discountAmount;
    }

    private AccountTicket _makeNewTicketModel() {
        AccountTicket accountTicket = new AccountTicket();
        UserInfo userInfo = userInformation.getUserInfo();
        Date periodFrom = this.periodFrom;
        Date periodTo = this.periodUpto;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(periodFrom);
        String narration = "Towards rent for the month of " + calendar.get(Calendar.MONTH) + ",  " + calendar.get(Calendar.YEAR);
        narration += System.lineSeparator() + "PACKAGE: " + guest.getPackageTitle();
        narration += System.lineSeparator() + "UNIT: " + guest.getInventoryPath();
        narration += System.lineSeparator() + "RENT: Rs." + guest.getRent() + " " + guest.getRentUnitLabel();

        accountTicket = new AccountTicket();
        accountTicket.setClientId(userInfo.getClient().getClientId());
        if (userInfo.getDefaultOrganization() != null) {
            accountTicket.setOrgId(userInfo.getDefaultOrganization().getId());
        }
        accountTicket.setTicketIdentifier(GuestConstants.TRANSACTION_IDENTIFIER_RENT_INVOICE);
        accountTicket.setTicketNumber(String.valueOf(System.nanoTime()));
        accountTicket.setGuestId(guest.getId());
        accountTicket.setBookingId(null);
        accountTicket.setAccountHeadId(null);
        accountTicket.setTemplateId(null);
        accountTicket.setTicketDate(new Date());
        accountTicket.setPeriodFrom(this.periodFrom);
        accountTicket.setPeriodUpto(this.periodUpto);
        accountTicket.setTicketNarration(narration);
        accountTicket.setItemTotal(this.grossRent);
        accountTicket.setTaxTotal(0.0);
        accountTicket.setDiscount(this.grossDiscount);
        accountTicket.setNetTotal(
                this.grossRent - this.grossDiscount
        );
        accountTicket.setTicketStatus(GuestConstants.TICKET_STATUS_ACTIVE);

        return accountTicket;
    }

    private void _validatePeriod() throws Exception {
        try {

            if (this.guest.getScheduledCheckIn() == null) {
                throw new Exception("Bad date, Scheduled check in ");
            }

            if (this.guest.getScheduledCheckout() == null) {
                throw new Exception("Bad date, Scheduled check out");
            }

        } catch (Exception ex) {
            throw ex;
        }
    }

    private Boolean _isCheckedInThisMonth() throws Exception {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(this.periodFrom);
            Calendar calendarScheduledCheckin = Calendar.getInstance();
            calendarScheduledCheckin.setTime(this.guest.getScheduledCheckIn());
            return (calendar.get(Calendar.MONTH) == calendarScheduledCheckin.get(Calendar.MONTH)) &&
                    (calendar.get(Calendar.YEAR) == calendarScheduledCheckin.get(Calendar.YEAR));

        } catch (Exception ex) {
            throw ex;
        }
    }

    private void _cancelTransactions(String ticketId) {
        Optional<AccountTicket> optionalAccountTicket = accountTicketRepository.findById(ticketId);
        if (optionalAccountTicket.isPresent()) {
            AccountTicket accountTicket = optionalAccountTicket.get();
            accountTicket.setTicketStatus(GuestConstants.TICKET_STATUS_CANCELLED);
            accountTicketRepository.save(accountTicket);
            List<AccountTicket> childTickets = accountTicketRepository.findAllByParentId(accountTicket.getId());
            for (AccountTicket childTicket : childTickets) {
                childTicket.setTicketStatus(GuestConstants.TICKET_STATUS_CANCELLED);
                accountTicketRepository.save(childTicket);
            }
        }
    }

    private boolean _isStayedInThisMonth() throws Exception {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(this.periodFrom);
            Calendar calendarScheduledCheckin = Calendar.getInstance();
            calendarScheduledCheckin.setTime(this.periodUpto);
            return (this.guest.getScheduledCheckIn().before(this.periodFrom) && this.guest.getScheduledCheckout().after(this.periodFrom));
        } catch (Exception ex) {
            throw ex;
        }
    }

}