package io.project.mello.soft.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "`Order`")
public class Order implements SuperEntity{

    @Id
    private String id;
    private Date date;
    private String customerName;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name = "customerId", referencedColumnName = "customerId")
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST,CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE})
    private List<OrderDetails> orderDetails = new ArrayList<>();

    @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST,CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE})
    private List<Service> service = new ArrayList<>();

    public Order() {
    }

    public Order(String id, Date date, String customerName, Customer customer) {
        this.id = id;
        this.date = date;
        this.customerName = customerName;
        this.customer = customer;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void addOrderDetail(OrderDetails orderDetail){
        this.orderDetails.add(orderDetail);
    }

    public List<Service> getService() {
        return service;
    }
    public void setService(Service service) {
        this.service.add(service);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date=" + date +
                ", customer=" + customer +
                '}';
    }
}
