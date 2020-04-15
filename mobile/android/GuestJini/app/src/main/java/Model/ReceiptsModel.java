package Model;

public class ReceiptsModel {
    private String receiptsDate;
    private String receiptsNo;
    private String receptsCreatedDateTime;
    private String amount;
    private int navigationIcon;

    public ReceiptsModel(String receiptsDate, String receiptsNo, String receptsCreatedDateTime, String amount, int navigationIcon) {
        this.receiptsDate = receiptsDate;
        this.receiptsNo = receiptsNo;
        this.receptsCreatedDateTime = receptsCreatedDateTime;
        this.amount = amount;
        this.navigationIcon = navigationIcon;
    }


    public String getReceiptsDate() {
        return receiptsDate;
    }

    public void setReceiptsDate(String receiptsDate) {
        this.receiptsDate = receiptsDate;
    }

    public String getReceiptsNo() {
        return receiptsNo;
    }

    public void setReceiptsNo(String receiptsNo) {
        this.receiptsNo = receiptsNo;
    }

    public String getReceptsCreatedDateTime() {
        return receptsCreatedDateTime;
    }

    public void setReceptsCreatedDateTime(String receptsCreatedDateTime) {
        this.receptsCreatedDateTime = receptsCreatedDateTime;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
    public int getNavigationIcon() {
        return navigationIcon;
    }

    public void setNavigationIcon(int navigationIcon) {
        this.navigationIcon = navigationIcon;
    }



}
