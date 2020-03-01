package io.project.mello.soft.tm;

import javafx.scene.control.Button;

public class PlaceOrderTM {
    private String itemName;
    private long qty;
    private double unitPrice;
    private double discount;
    private String service;
    private int warranty;
    private double subTotal;
    private Button delete;

    public PlaceOrderTM() {
    }

    public PlaceOrderTM(String itemName, long qty, double unitPrice, double discount, String service, int warranty, double subTotal, Button delete) {
        this.itemName = itemName;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.discount = discount;
        this.service = service;
        this.warranty = warranty;
        this.subTotal = subTotal;
        this.delete = delete;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public long getQty() {
        return qty;
    }

    public void setQty(long qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public Button getDelete() {
        return delete;
    }

    public void setDelete(Button delete) {
        this.delete = delete;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getWarranty() {
        return warranty;
    }

    public void setWarranty(int warranty) {
        this.warranty = warranty;
    }

    @Override
    public String toString() {
        return "PlaceOrderTM{" +
                "itemName='" + itemName + '\'' +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                ", discount=" + discount +
                ", service='" + service + '\'' +
                ", warranty=" + warranty +
                ", subTotal=" + subTotal +
                ", delete=" + delete +
                '}';
    }
}
