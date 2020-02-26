package com.carmel.guestjini.payments.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Table(name = "g_paytm_payment_status")
@Audited
public class PaymentStatus {
    @Id
    @Column(name = "id")
    @Length(max = 1000)
    private String id;

    @Column(name = "customer_id", columnDefinition = "TEXT")
    private String customerId;

    @Column(name = "bank_name", columnDefinition = "TEXT")
    private String bankName;

    @Column(name = "bank_txn_id", columnDefinition = "TEXT")
    private String bankTxnId;

    @Column(name = "currency", columnDefinition = "TEXT")
    private String currency;

    @Column(name = "gateway", columnDefinition = "TEXT")
    private String gateway;

    @Column(name = "mid", columnDefinition = "TEXT")
    private String mid;

    @Column(name = "order_id", columnDefinition = "TEXT")
    private String orderId;

    @Column(name = "payment_mode", columnDefinition = "TEXT")
    private String paymentMode;

    @Column(name = "response_code", columnDefinition = "TEXT")
    private String respCode;

    @Column(name = "response_message", columnDefinition = "TEXT")
    private String respMsg;

    @Column(name = "status")
    private String status;

    @Column(name = "transaction_amount", columnDefinition = "TEXT")
    private String txnAmount;

    @Column(name = "transaction_date", columnDefinition = "TEXT")
    private String txnDate;

    @Column(name = "transaction_id", columnDefinition = "TEXT")
    private String txnId;

    @Column(name = "is_complete")
    private int isComplete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankTxnId() {
        return bankTxnId;
    }

    public void setBankTxnId(String bankTxnId) {
        this.bankTxnId = bankTxnId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTxnAmount() {
        return txnAmount;
    }

    public void setTxnAmount(String txnAmount) {
        this.txnAmount = txnAmount;
    }

    public String getTxnDate() {
        return txnDate;
    }

    public void setTxnDate(String txnDate) {
        this.txnDate = txnDate;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public int getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(int isComplete) {
        this.isComplete = isComplete;
    }
}
