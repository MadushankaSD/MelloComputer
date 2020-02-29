package io.project.mello.soft.repository.custom.impl;

import io.project.mello.soft.entity.Order;
import io.project.mello.soft.repository.CrudDAOImpl;
import io.project.mello.soft.repository.custom.OrderDAO;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDAOImpl extends CrudDAOImpl<Order,Integer> implements OrderDAO {
}