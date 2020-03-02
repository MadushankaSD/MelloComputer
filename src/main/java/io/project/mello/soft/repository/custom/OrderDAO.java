package io.project.mello.soft.repository.custom;

import io.project.mello.soft.entity.Order;
import io.project.mello.soft.repository.CrudDAO;

public interface OrderDAO extends CrudDAO<Order,Integer> {
    String getLastOrderId();
}
