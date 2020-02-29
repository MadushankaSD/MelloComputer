package io.project.mello.soft.bussiness.custom.impl;

import io.project.mello.soft.bussiness.custom.CustomerBO;
import io.project.mello.soft.dto.CustomerDTO;
import io.project.mello.soft.repository.custom.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Component
public class CustomerBOImpl implements CustomerBO {
    @Autowired
    private CustomerDAO customerDAO;

    @Override
    public void saveCustomer(CustomerDTO customer) {

    }

    @Override
    public void updateCustomer(CustomerDTO customer) {

    }

    @Override
    public void deleteCustomer(String customerId) {

    }

    @Override
    public List<CustomerDTO> findAllCustomers() {
        return null;
    }

    @Override
    public String getLastCustomerId() {
        return null;
    }

    @Override
    public CustomerDTO findCustomer(String customerId) {
        return null;
    }

    @Override
    public List<String> getAllCustomerIDs() {
        return null;
    }
}
