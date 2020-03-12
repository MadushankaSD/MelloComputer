package io.project.mello.soft.repository.custom.impl;

import io.project.mello.soft.entity.Order;
import io.project.mello.soft.repository.CrudDAOImpl;
import io.project.mello.soft.repository.custom.OrderDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.sql.Date;

@Repository
public class OrderDAOImpl extends CrudDAOImpl<Order,Integer> implements OrderDAO {
    @Override
    public String getLastOrderId() {
        try {
            return (String) entityManager.createQuery("SELECT o.id FROM Order o ORDER BY o.id DESC").setMaxResults(1).getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public double getDailyCollection(Date date) {
        double sumItem=0.0;
        double service=0.0;
        try {
            sumItem = (double) entityManager.createNativeQuery("SELECT SUM((i.unitPrice*OD.qty)-OD.discount) FROM Item i INNER JOIN OrderDetails OD on i.itemCode = OD.item_code INNER JOIN `Order` O on OD.order_id = O.id WHERE O.date = ?1").setParameter(1, date).getSingleResult();
        }catch (Exception e){
            sumItem=0.0;
        }
       try {
            service = (double)entityManager.createNativeQuery("SELECT SUM(S.serviceCharge) FROM `Order` O INNER JOIN Service S on O.id = S.orderId WHERE O.date=?1").setParameter(1, date).getSingleResult();
       }catch (Exception e){
            service=0.0;
       }

        return sumItem+service;
    }
}
