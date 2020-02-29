package io.project.mello.soft.repository.custom.impl;

import io.project.mello.soft.entity.OrderDetailPK;
import io.project.mello.soft.entity.OrderDetails;
import io.project.mello.soft.repository.CrudDAOImpl;
import io.project.mello.soft.repository.custom.OrderDetailDAO;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDetailDAOImpl extends CrudDAOImpl<OrderDetails, OrderDetailPK> implements OrderDetailDAO {
}
