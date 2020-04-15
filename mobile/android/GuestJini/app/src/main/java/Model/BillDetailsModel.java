package Model;

public class BillDetailsModel {
    private String itemsName;
    private int itemsRate;
    private int itemsQuantity;
    private int itemsAmount;

    public BillDetailsModel(String itemsName, int itemsRate, int itemsQuantity, int itemsAmount) {
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

    public int getItemsRate() {
        return itemsRate;
    }

    public void setItemsRate(int itemsRate) {
        this.itemsRate = itemsRate;
    }

    public int getItemsQuantity() {
        return itemsQuantity;
    }

    public void setItemsQuantity(int itemsQuantity) {
        this.itemsQuantity = itemsQuantity;
    }

    public int getItemsAmount() {
        return itemsAmount;
    }

    public void setItemsAmount(int itemsAmount) {
        this.itemsAmount = itemsAmount;
    }
}
