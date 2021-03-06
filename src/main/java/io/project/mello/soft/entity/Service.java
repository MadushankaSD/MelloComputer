package io.project.mello.soft.entity;

import org.springframework.jmx.export.annotation.ManagedOperation;

import javax.persistence.*;

@Entity
public class Service implements SuperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long serviceId;
    private String discription;
    private Double serviceCharge;
    private long warranty;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name = "orderId",referencedColumnName = "id",nullable = false)
    private Order order;

    public Service() {
    }

    public Service(String discription, Double serviceCharge, long warranty, Order order) {
        this.discription = discription;
        this.serviceCharge = serviceCharge;
        this.warranty = warranty;
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

    public long getWarranty() {
        return warranty;
    }

    public void setWarranty(long warranty) {
        this.warranty = warranty;
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
