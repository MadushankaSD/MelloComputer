package io.project.mello.soft.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer implements SuperEntity{
    @Id
    private String customerId;
    private String name;
    private String address;
    private long telephonNo;
    @OneToMany(mappedBy = "customer", cascade = {CascadeType.PERSIST,CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE})
    private List<Order> orders = new ArrayList<>();

    public Customer() {
    }

    public Customer(String customerId, String name, String address, long telephonNo) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.telephonNo = telephonNo;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getTelephonNo() {
        return telephonNo;
    }

    public void setTelephonNo(long telephonNo) {
        this.telephonNo = telephonNo;
    }

    public void addOrder(Order order){
        order.setCustomer(this);
        this.orders.add(order);
    }

    public void removeOrder(Order order){
        if (order.getCustomer() != this){
            throw new RuntimeException("Invalid order");
        }
        order.setCustomer(null);
        this.orders.remove(order);
    }


    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", telephonNo=" + telephonNo +
                '}';
    }
}
