package io.project.mello.soft.entity;

import org.springframework.jmx.export.annotation.ManagedOperation;

import javax.persistence.*;

@Entity
public class Service implements SuperEntity {
    private String discription;
    private Double serviceCharge;

    @Id
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name = "orderId",referencedColumnName = "id",nullable = false)
    private Order order;

    public Service() {
    }

    public Service(String discription, Double serviceCharge, Order order) {
        this.discription = discription;
        this.serviceCharge = serviceCharge;
        this.order = order;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public Double getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(Double serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Service{" +
                "discription='" + discription + '\'' +
                ", serviceCharge=" + serviceCharge +
                ", order=" + order +
                '}';
    }
}
