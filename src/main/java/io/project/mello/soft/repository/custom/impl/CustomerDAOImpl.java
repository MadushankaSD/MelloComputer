package io.project.mello.soft.repository.custom.impl;

import io.project.mello.soft.entity.Customer;
import io.project.mello.soft.repository.CrudDAOImpl;
import io.project.mello.soft.repository.custom.CustomerDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOImpl extends CrudDAOImpl<Customer,String> implements CustomerDAO {
    @Override
    public String getLastCustomerId() {
        try {
            return (String) entityManager.createQuery("SELECT c.id FROM Customer c ORDER BY c.id DESC").setMaxResults(1).getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Customer> searchCustomer(String data) {
        return entityManager.createQuery("SELECT c FROM Customer c WHERE c.name LIKE ?1",Customer.class).setParameter(1,data).getResultList();
    }
}
