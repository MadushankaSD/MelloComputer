package io.project.mello.soft.dto;

public class OrderDetailDTO {

    private String orderId;
    private String itemId;
    private double discount;
    private long qty;
    private double warranty;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String orderId, String itemId, double discount, long qty, double warranty) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.discount = discount;
        this.qty = qty;
        this.warranty = warranty;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public long getQty() {
        return qty;
    }

    public void setQty(long qty) {
        this.qty = qty;
    }

    public double getWarranty() {
        return warranty;
    }

    public void setWarranty(double warranty) {
        this.warranty = warranty;
    }

    @Override
    public String toString() {
        return "OrderDetailDTO{" +
                "orderId='" + orderId + '\'' +
                ", itemId='" + itemId + '\'' +
                ", discount=" + discount +
                ", qty=" + qty +
                ", warranty=" + warranty +
                '}';
    }
}
