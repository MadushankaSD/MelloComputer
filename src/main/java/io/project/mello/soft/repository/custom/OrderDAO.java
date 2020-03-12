package io.project.mello.soft.repository.custom;

import io.project.mello.soft.entity.Order;
import io.project.mello.soft.repository.CrudDAO;

import java.sql.Date;

public interface OrderDAO extends CrudDAO<Order,Integer> {
    String getLastOrderId();
    double getDailyCollection(Date date);
}
