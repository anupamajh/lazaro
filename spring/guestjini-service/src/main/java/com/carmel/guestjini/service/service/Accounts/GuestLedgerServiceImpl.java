package com.carmel.guestjini.service.service.Accounts;

import com.carmel.guestjini.service.common.Accounts.GuestConstants;
import com.carmel.guestjini.service.model.Accounts.AccountReceipts;
import com.carmel.guestjini.service.model.Accounts.AccountTicket;
import com.carmel.guestjini.service.model.DTO.Accounts.TransactionData;
import com.carmel.guestjini.service.model.DTO.Booking.GuestDTO;
import com.carmel.guestjini.service.repository.Accounts.AccountTicketRepository;
import com.carmel.guestjini.service.response.Booking.GuestResponse;
import com.carmel.guestjini.service.service.Booking.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GuestLedgerServiceImpl implements GuestLedgerService {

    @Autowired
    AccountTicketRepository accountTicketRepository;

    @Autowired
    GuestService guestService;

    @Autowired
    DayRentService dayRentService;

    @Autowired
    AccountReceiptService accountReceiptService;


    private String guestId = "";
    private GuestDTO guest = null;

    @Override
    public List<TransactionData> getGuestLedger(String guestId) {
        List<TransactionData> rent = new ArrayList<>();
        try {
            this.guestId = guestId;
            this._getGuest();
            List<TransactionData> bills = new ArrayList<>();
            List<TransactionData> debitNotes = new ArrayList<>();
            List<TransactionData> creditNotes = new ArrayList<>();
            if (this.guest.getRentUnit() == GuestConstants.RENT_CYCLE_DAY) {

                if (this.guest.getGuestStatus() == GuestConstants.GUEST_STATUS_RESIDING) {
                    rent =
                            this._getTransactionData(
                                    dayRentService.getGuestTickets(guest, GuestConstants.TRANSACTION_IDENTIFIER_RENT_INVOICE),
                                    GuestConstants.TRANSACTION_IDENTIFIER_RENT_INVOICE);

                    bills =
                            this._getTransactionData(
                                    dayRentService.getGuestTickets(guest, GuestConstants.TRANSACTION_IDENTIFIER_BILL),
                                    GuestConstants.TRANSACTION_IDENTIFIER_BILL);

                    debitNotes =
                            this._getTransactionData(
                                    dayRentService.getGuestTickets(guest, GuestConstants.TRANSACTION_IDENTIFIER_DEBIT_NOTE),
                                    GuestConstants.TRANSACTION_IDENTIFIER_DEBIT_NOTE);
                    creditNotes =
                            this._getTransactionData(
                                    dayRentService.getGuestTickets(guest, GuestConstants.TRANSACTION_IDENTIFIER_CREDIT_NOTE),
                                    GuestConstants.TRANSACTION_IDENTIFIER_CREDIT_NOTE);
                } else {
                    rent = this._getTransactionData(
                            accountTicketRepository
                                    .findAllByGuestIdAndTicketIdentifierAndTicketStatus(
                                            guest.getId(),
                                            GuestConstants.TRANSACTION_IDENTIFIER_RENT_INVOICE,
                                            GuestConstants.TICKET_STATUS_ACTIVE
                                    ),
                            GuestConstants.TRANSACTION_IDENTIFIER_RENT_INVOICE);
                    bills = this._getTransactionData(
                            accountTicketRepository
                                    .findAllByGuestIdAndTicketIdentifierAndTicketStatus(
                                            guest.getId(),
                                            GuestConstants.TRANSACTION_IDENTIFIER_BILL,
                                            GuestConstants.TICKET_STATUS_ACTIVE
                                    ),
                            GuestConstants.TRANSACTION_IDENTIFIER_BILL);
                    debitNotes = this._getTransactionData(
                            accountTicketRepository
                                    .findAllByGuestIdAndTicketIdentifierAndTicketStatus(
                                            guest.getId(),
                                            GuestConstants.TRANSACTION_IDENTIFIER_DEBIT_NOTE,
                                            GuestConstants.TICKET_STATUS_ACTIVE
                                    ),
                            GuestConstants.TRANSACTION_IDENTIFIER_DEBIT_NOTE);

                    creditNotes = this._getTransactionData(
                            accountTicketRepository
                                    .findAllByGuestIdAndTicketIdentifierAndTicketStatus(
                                            guest.getId(),
                                            GuestConstants.TRANSACTION_IDENTIFIER_CREDIT_NOTE,
                                            GuestConstants.TICKET_STATUS_ACTIVE
                                    ),
                            GuestConstants.TRANSACTION_IDENTIFIER_CREDIT_NOTE);

                }
            } else if (this.guest.getRentUnit() == GuestConstants.RENT_CYCLE_MONTH) {
                rent = this._getTransactionData(
                        accountTicketRepository
                                .findAllByGuestIdAndTicketIdentifierAndTicketStatus(
                                        guest.getId(),
                                        GuestConstants.TRANSACTION_IDENTIFIER_RENT_INVOICE,
                                        GuestConstants.TICKET_STATUS_ACTIVE
                                ),
                        GuestConstants.TRANSACTION_IDENTIFIER_RENT_INVOICE);
                bills = this._getTransactionData(
                        accountTicketRepository
                                .findAllByGuestIdAndTicketIdentifierAndTicketStatus(
                                        guest.getId(),
                                        GuestConstants.TRANSACTION_IDENTIFIER_BILL,
                                        GuestConstants.TICKET_STATUS_ACTIVE
                                ),
                        GuestConstants.TRANSACTION_IDENTIFIER_BILL);
                debitNotes = this._getTransactionData(
                        accountTicketRepository
                                .findAllByGuestIdAndTicketIdentifierAndTicketStatus(
                                        guest.getId(),
                                        GuestConstants.TRANSACTION_IDENTIFIER_DEBIT_NOTE,
                                        GuestConstants.TICKET_STATUS_ACTIVE
                                ),
                        GuestConstants.TRANSACTION_IDENTIFIER_DEBIT_NOTE);

                creditNotes = this._getTransactionData(
                        accountTicketRepository
                                .findAllByGuestIdAndTicketIdentifierAndTicketStatus(
                                        guest.getId(),
                                        GuestConstants.TRANSACTION_IDENTIFIER_CREDIT_NOTE,
                                        GuestConstants.TICKET_STATUS_ACTIVE
                                ),
                        GuestConstants.TRANSACTION_IDENTIFIER_CREDIT_NOTE);
            }

            List<TransactionData> receipts = this._getReceiptTransactionData(accountReceiptService
                    .findAllByBookingIdAndReceiptStatusAndIsDeleted(this.guest.getBooking().getId(),
                            GuestConstants.RECEIPT_STATUS_ACTIVE, 0));
            rent.addAll(bills);
            rent.addAll(creditNotes);
            rent.addAll(debitNotes);
            rent.addAll(receipts);
            rent.sort((o1, o2) -> (int) (o1.getTransactionDate().getTime() - o2.getTransactionDate().getTime()));
            rent = this._calculateRunningBalance(rent);
        } catch (Exception ex) {
            return new ArrayList<>();
        }
        return rent;
    }

    private List<TransactionData> _getTransactionData(List<AccountTicket> transactions, int transactionType) {
        List<TransactionData> transactionDataList = new ArrayList<>();
        transactions.forEach(accountTicket -> {
            double debitAmount = 0;
            double creditAmount = 0;
            switch (transactionType) {
                case GuestConstants.TRANSACTION_IDENTIFIER_RENT_INVOICE:
                case GuestConstants.TRANSACTION_IDENTIFIER_BILL:
                case GuestConstants.TRANSACTION_IDENTIFIER_DEBIT_NOTE:
                case GuestConstants.TRANSACTION_IDENTIFIER_RENT_VOUCHER: {
                    debitAmount = accountTicket.getNetTotal();
                    creditAmount = 0;
                }
                break;
                case GuestConstants.TRANSACTION_IDENTIFIER_CREDIT_NOTE: {
                    debitAmount = 0;
                    creditAmount = accountTicket.getNetTotal();
                }
                break;
            }
            if (debitAmount > 0 || creditAmount > 0) {
                TransactionData transactionData = new TransactionData();
                transactionData.setTransactionDate(accountTicket.getTicketDate());
                transactionData.setTransactionType(transactionType);
                transactionData.setTransactionNumber(accountTicket.getTicketNumber());
                transactionData.setTransactionNarration(accountTicket.getTicketNarration());
                transactionData.setDebitAmount(debitAmount);
                transactionData.setCreditAmount(creditAmount);
                transactionData.setRunningBalance(0.0);
                transactionData.setTransactionId(accountTicket.getId());
                transactionDataList.add(transactionData);
            }
        });
        return transactionDataList;
    }

    private List<TransactionData> _getReceiptTransactionData(List<AccountReceipts> transactions) {
        List<TransactionData> transactionDataList = new ArrayList<>();
        transactions.forEach(receipt -> {
            TransactionData transactionData = new TransactionData();
            transactionData.setTransactionDate(receipt.getReceiptDate());
            transactionData.setTransactionType(GuestConstants.TRANSACTION_IDENTIFIER_PAYMENT_RECEIPT);
            transactionData.setTransactionNumber(receipt.getId());
            transactionData.setTransactionNarration(receipt.getReceiptNarration());
            transactionData.setDebitAmount(0.0);
            transactionData.setCreditAmount(receipt.getAmount());
            transactionData.setRunningBalance(0.0);
            transactionData.setTransactionId(receipt.getId());
            transactionDataList.add(transactionData);
        });
        return transactionDataList;
    }

    private List<TransactionData> _calculateRunningBalance(List<TransactionData> rent) {
        double runningBalance = 0;
        List<TransactionData> transactionDataList = new ArrayList<>();
        for (TransactionData transactionData : rent) {
            switch (transactionData.getTransactionType()) {
                case GuestConstants.TRANSACTION_IDENTIFIER_BILL:
                case GuestConstants.TRANSACTION_IDENTIFIER_RENT_INVOICE:
                case GuestConstants.TRANSACTION_IDENTIFIER_DEBIT_NOTE:
                case GuestConstants.TRANSACTION_IDENTIFIER_RENT_VOUCHER: {
                    runningBalance -= transactionData.getDebitAmount();
                }
                break;
                case GuestConstants.TRANSACTION_IDENTIFIER_CREDIT_NOTE:
                case GuestConstants.TRANSACTION_IDENTIFIER_PAYMENT_RECEIPT: {
                    runningBalance += transactionData.getDebitAmount();
                }
                break;

            }
            transactionData.setRunningBalance(runningBalance);
            transactionDataList.add(transactionData);

        }
        return transactionDataList;
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

}
