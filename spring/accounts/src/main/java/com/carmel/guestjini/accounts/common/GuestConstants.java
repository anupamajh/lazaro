package com.carmel.guestjini.accounts.common;

public abstract class GuestConstants {

    public static final int GUEST_STATUS_RESIDING = 1;
    public static final String GUEST_STATUS_RESIDING_LABEL = "RESIDING";

    public static final int GUEST_STATUS_CHECKEDOUT = 2;
    public static final String GUEST_STATUS_CHECKEDOUT_LABEL = "CHECKED OUT";

    public static final int ALLOW_CHECK_OUT_DECLINE_REASON_GUEST_CHECKEDOUT = 1;
    public static final int ALLOW_UNDO_CHECK_OUT_DECLINE_REASON_GUEST_RESIDING = 1;


    public static final int GENDER_MALE = 1;
    public static final String GENDER_MALE_LABEL = "MALE";
    public static final int GENDER_FEMALE = 2;
    public static final String GENDER_FEMALE_LABEL = "FEMALE";
    public static final int GENDER_COUPLE = 3;
    public static final String GENDER_COUPLE_LABEL = "COUPLE";


    public static final int DISCOUNT_VALUE_IDENTIFIER_FIXED = 1;
    public static final String DISCOUNT_VALUE_IDENTIFIER_LABEL = "FIXED AMOUNT";

    public static final int DISCOUNT_VALUE_IDENTIFIER_PERCENTAGE = 2;
    public static final String DISCOUNT_VALUE_IDENTIFIER_PERCENTAGE_LABEL = "PERCENTAGE OF RENT";


    public static final int DISCOUNT_IDENTIFIER_ONETIME = 1;
    public static final String DISCOUNT_IDENTIFIER_ONETIME_LABEL = "ONE TIME";

    public static final int DISCOUNT_IDENTIFIER_RECURRING = 2;
    public static final String DISCOUNT_IDENTIFIER_RECURRING_LABEL = "RECURRING EVERY RENT CYCLE";

    public static final int RENT_UNIT_MONTH = 1;
    public static final String RENT_UNIT_MONTH_LABEL = "PER MONTH";
    public static final int RENT_UNIT_DAY = 2;
    public static final String RENT_UNIT_DAY_LABEL = "PER DAY";


    public static final int CHARGE_METHOD_RENT_INVOICE = 1;
    public static final int CHARGE_METHOD_DEBIT_NOTE = 2;
    public static final int CHARGE_METHOD_BILL = 3;

    public static final String CHARGE_METHOD_RENT_INVOICE_LABEL = "Include in rent invoice";
    public static final String CHARGE_METHOD_DEBIT_NOTE_LABEL = "Create a debit note";
    public static final String CHARGE_METHOD_BILL_LABEL = "Create a bill";


    public static final int TRANSACTION_IDENTIFIER_RENT_INVOICE = 1;
    public static final String TRANSACTION_IDENTIFIER_RENT_INVOICE_LABEL = "Rent Invoice";

    public static final int TRANSACTION_IDENTIFIER_RENT_VOUCHER = 2;
    public static final String TRANSACTION_IDENTIFIER_RENT_VOUCHER_LABEL = "Rent Voucher";

    public static final int TRANSACTION_IDENTIFIER_BILL = 3;
    public static final String TRANSACTION_IDENTIFIER_BILL_LABEL = "Bill";

    public static final int TRANSACTION_IDENTIFIER_DEBIT_NOTE = 4;
    public static final String TRANSACTION_IDENTIFIER_DEBIT_NOTE_LABEL = "Debit Note";

    public static final int TRANSACTION_IDENTIFIER_CREDIT_NOTE = 5;
    public static final String TRANSACTION_IDENTIFIER_CREDIT_NOTE_LABEL = "Credit Note";

    public static final int TRANSACTION_IDENTIFIER_PAYMENT_RECEIPT = 6;
    public static final String TRANSACTION_IDENTIFIER_PAYMENT_RECEIPT_LABEL = "Payment Receipt";

    public static final int TRANSACTION_IDENTIFIER_BOOKING = 7;
    public static final String TRANSACTION_IDENTIFIER_BOOKING_LABEL = "Booking";

    public static final int TRANSACTION_IDENTIFIER_GUEST = 8;
    public static final String TRANSACTION_IDENTIFIER_GUEST_LABEL = "Guest";

    public static final int TRANSACTION_IDENTIFIER_TASK = 9;
    public static final String TRANSACTION_IDENTIFIER_TASK_LABEL = "Task Ticket";

    public static final int TRANSACTION_IDENTIFIER_VOUCHER = 10;
    public static final String TRANSACTION_IDENTIFIER_VOUCHER_LABEL = "VOUCHER";

    public static final int TAX_VALUE_IDENTIFIER_FIXED = 1;
    public static final String TAX_VALUE_IDENTIFIER_FIXED_LABEL = "FIXED";
    public static final int TAX_VALUE_IDENTIFIER_PERCENT = 2;
    public static final String TAX_VALUE_IDENTIFIER_PERCENT_LABEL = "PERCENT";

    public static final int TICKET_STATUS_ACTIVE = 1;
    public static final int TICKET_STATUS_CANCELLED = 2;

    public static final int STATUS_ACTIVE = 1;
    public static final String STATUS_ACTIVE_LABEL = "ACTIVE";
    public static final int STATUS_DISABLED = 2;
    public static final String STATUS_DISABLED_LABEL = "DISABLED";

    public static final int RENT_CYCLE_MONTH = 1;
    public static final String RENT_CYCLE_MONTH_LABEL = "PER MONTH";

    public static final int RENT_CYCLE_DAY = 2;
    public static final String RENT_CYCLE_DAY_LABEL = "PER DAY";

    public static final int RECEIPT_STATUS_ACTIVE     = 1;
    public static final int RECEIPT_STATUS_CANCELLED  = 2;

}
