package io.project.mello.soft.dto;

import io.project.mello.soft.entity.OrderDetails;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class OrderDTO {
    private String id;
    private Date date;
    private String customerName;
    private List<ServiceDTO> services;
    private List<OrderDetailDTO> orderDetaail;

    public OrderDTO() {
    }

    public OrderDTO(String id, Date date, String customerName, List<ServiceDTO> services, List<OrderDetailDTO> orderDetaail) {
        this.id = id;
        this.date = date;
        this.customerName = customerName;
        this.services = services;
        this.orderDetaail = orderDetaail;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<ServiceDTO> getServices() {
        return services;
    }

    public void setServices(List<ServiceDTO> services) {
        this.services = services;
    }

    public List<OrderDetailDTO> getOrderDetaail() {
        return orderDetaail;
    }

    public void setOrderDetaail(List<OrderDetailDTO> orderDetaail) {
        this.orderDetaail = orderDetaail;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", customerName='" + customerName + '\'' +
                '}';
    }
}
