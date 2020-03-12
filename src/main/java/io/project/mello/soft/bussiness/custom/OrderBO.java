package io.project.mello.soft.bussiness.custom;

import io.project.mello.soft.bussiness.SuperBO;
import io.project.mello.soft.dto.OrderDTO;

import java.sql.Date;

public interface OrderBO extends SuperBO {

    void placeOrder(OrderDTO service);
    String getLastOrderId();

    double getDailyCollection(Date day);
}
