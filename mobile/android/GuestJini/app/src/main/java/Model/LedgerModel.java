package Model;

public class LedgerModel {
    private String ledgerDate;
    private String ledgerType;
    private String ledgerName;
    private int creditAmount;
    private int debitAmount;
    private int balanceAmount;

    public LedgerModel(String ledgerDate, String ledgerType, String ledgerName, int debitAmount, int balanceAmount) {
        this.ledgerDate = ledgerDate;
        this.ledgerType = ledgerType;
        this.ledgerName = ledgerName;
        this.debitAmount = debitAmount;
        this.balanceAmount = balanceAmount;
    }

    public String getLedgerDate() {
        return ledgerDate;
    }

    public void setLedgerDate(String ledgerDate) {
        this.ledgerDate = ledgerDate;
    }

    public String getLedgerType() {
        return ledgerType;
    }

    public void setLedgerType(String ledgerType) {
        this.ledgerType = ledgerType;
    }

    public String getLedgerName() {
        return ledgerName;
    }

    public void setLedgerName(String ledgerName) {
        this.ledgerName = ledgerName;
    }

    public int getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(int creditAmount) {
        this.creditAmount = creditAmount;
    }

    public int getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(int debitAmount) {
        this.debitAmount = debitAmount;
    }

    public int getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(int balanceAmount) {
        this.balanceAmount = balanceAmount;
    }
}
