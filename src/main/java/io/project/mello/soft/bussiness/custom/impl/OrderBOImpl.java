package io.project.mello.soft.bussiness.custom.impl;

import io.project.mello.soft.bussiness.custom.OrderBO;
import io.project.mello.soft.dto.OrderDTO;
import io.project.mello.soft.dto.OrderDetailDTO;
import io.project.mello.soft.dto.ServiceDTO;
import io.project.mello.soft.entity.Customer;
import io.project.mello.soft.entity.Order;
import io.project.mello.soft.entity.OrderDetails;
import io.project.mello.soft.entity.Service;
import io.project.mello.soft.repository.custom.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;


@Component
public class OrderBOImpl implements OrderBO {

    @Autowired
    private ServiceDAO serviceDAO;
    @Autowired
    private OrderDetailDAO orderDetailDAO;
    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private ItemDAO itemDAO;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public void placeOrder(OrderDTO service) {
        Order order;
        try{
          Customer customer = customerDAO.getCustomerByName(service.getCustomerName());
             order = new Order(service.getId(), service.getDate(), service.getCustomerName(), customer);
                orderDAO.save(order);

        }catch (NullPointerException e){
             order = new Order(service.getId(), service.getDate(), service.getCustomerName(), null);
            orderDAO.save(order);
        }

        if(!service.getServices().isEmpty()){
            for (ServiceDTO services:service.getServices()) {
                serviceDAO.save(new Service(services.getDiscription(),services.getServiceCharge(),services.getWarranty(),order));
            }
        }

        if(!service.getOrderDetaail().isEmpty()){
            for (OrderDetailDTO orderDetail: service.getOrderDetaail()) {
                orderDetailDAO.save(new OrderDetails(orderDetail.getOrderId(),orderDetail.getItemId(),orderDetail.getQty(),orderDetail.getDiscount(),orderDetail.getWarranty()));
                itemDAO.updateQty(orderDetail.getItemId(),orderDetail.getQty());
            }
        }


    }
    @Transactional
    @Override
    public String getLastOrderId() {
        return orderDAO.getLastOrderId();
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public double getDailyCollection(Date day) {
       return orderDAO.getDailyCollection(day);
    }
}
