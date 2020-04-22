package Model;

public class BillDetailsModel {
    private String itemsName;
    private Double itemsRate;
    private Double itemsQuantity;
    private Double itemsAmount;

    public BillDetailsModel(String itemsName, Double itemsRate, Double itemsQuantity, Double itemsAmount) {
        this.itemsName = itemsName;
        this.itemsRate = itemsRate;
        this.itemsQuantity = itemsQuantity;
        this.itemsAmount = itemsAmount;
    }

    public String getItemsName() {
        return itemsName;
    }

    public void setItemsName(String itemsName) {
        this.itemsName = itemsName;
    }

    public Double getItemsRate() {
        return itemsRate;
    }

    public void setItemsRate(Double itemsRate) {
        this.itemsRate = itemsRate;
    }

    public Double getItemsQuantity() {
        return itemsQuantity;
    }

    public void setItemsQuantity(Double itemsQuantity) {
        this.itemsQuantity = itemsQuantity;
    }

    public Double getItemsAmount() {
        return itemsAmount;
    }

    public void setItemsAmount(Double itemsAmount) {
        this.itemsAmount = itemsAmount;
    }
}
