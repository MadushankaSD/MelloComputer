package io.project.mello.soft.entity;

import javax.persistence.*;

@Entity
public class OrderDetails implements SuperEntity {

    @EmbeddedId
    private OrderDetailPK orderDetailPK;

    private long qty;

    @Column(nullable = true)
    private double discount;
    @Column(nullable = true)
    private double warranty;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name="order_id",referencedColumnName = "id", insertable = false, updatable = false)
    private Order order;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name="item_code",referencedColumnName = "itemCode",insertable = false, updatable = false)
    private Item item;

    public OrderDetails() {
    }

    public OrderDetails(OrderDetailPK orderDetailPK, long qty,double discount, double warranty) {
        this.orderDetailPK = orderDetailPK;
        this.qty = qty;
        this.discount=discount;
        this.warranty=warranty;
    }

    public OrderDetails(String orderId, String itemCode, long qty, double discount, double warranty) {
        this.orderDetailPK = new OrderDetailPK(orderId, itemCode);
        this.qty = qty;
        this.discount=discount;
        this.warranty=warranty;
    }

    public OrderDetailPK getOrderDetailPK() {
        return orderDetailPK;
    }

    public void setOrderDetailPK(OrderDetailPK orderDetailPK) {
        this.orderDetailPK = orderDetailPK;
    }

    public long getQty() {
        return qty;
    }

    public void setQty(long qty) {
        this.qty = qty;
    }


    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getWarranty() {
        return warranty;
    }

    public void setWarranty(double warranty) {
        this.warranty = warranty;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "orderDetailPK=" + orderDetailPK +
                ", qty=" + qty +
                ", discount=" + discount +
                ", warranty=" + warranty +
                ", order=" + order +
                ", item=" + item +
                '}';
    }
}
