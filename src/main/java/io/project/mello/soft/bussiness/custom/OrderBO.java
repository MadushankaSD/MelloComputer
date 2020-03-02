package io.project.mello.soft.bussiness.custom;

import io.project.mello.soft.bussiness.SuperBO;
import io.project.mello.soft.dto.OrderDTO;
import io.project.mello.soft.dto.OrderDetailDTO;
import io.project.mello.soft.dto.ServiceDTO;
import io.project.mello.soft.entity.Order;

public interface OrderBO extends SuperBO {

    void placeOrder(OrderDTO service);
    String getLastOrderId();

}
