package io.project.mello.soft.bussiness.custom.impl;

import io.project.mello.soft.bussiness.custom.CustomerBO;
import io.project.mello.soft.dto.CustomerDTO;
import io.project.mello.soft.entity.Customer;
import io.project.mello.soft.repository.custom.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Component
public class CustomerBOImpl implements CustomerBO {
    @Autowired
    private CustomerDAO customerDAO;

    @Override
    public void saveCustomer(CustomerDTO customer) {
        customerDAO.save(new Customer(customer.getCustomerId(),customer.getName(),customer.getAddress(),customer.getTelephonNo()));
    }

    @Override
    public void updateCustomer(CustomerDTO customer) {
        customerDAO.update(new Customer(customer.getCustomerId(),customer.getName(),customer.getAddress(),customer.getTelephonNo()));
    }

    @Override
    public void deleteCustomer(String customerId) {
        customerDAO.delete(customerId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CustomerDTO> findAllCustomers() {
        List<Customer> all = customerDAO.findAll();
        List<CustomerDTO> customers = new ArrayList<>();
        for (Customer date :
             all) {
            customers.add(new CustomerDTO(date.getCustomerId(),date.getName(),date.getAddress(),date.getTelephonNo()));
        }
        return customers;
    }

    @Transactional(readOnly = true)
    @Override
    public String getLastCustomerId() {
        return customerDAO.getLastCustomerId();
    }

    @Transactional(readOnly = true)
    @Override
    public CustomerDTO findCustomer(String customerId) {
        Customer customer = customerDAO.find(customerId);
        return new CustomerDTO(customer.getCustomerId(),customer.getName(),customer.getAddress(),customer.getTelephonNo());
    }

    @Transactional(readOnly = true)
    @Override
    public List<String> getAllCustomerIDs() {
        return null;
    }

    @Override
    public List<CustomerDTO> searchCustomer(String data) {
        List<Customer> all = customerDAO.searchCustomer(data);
        List<CustomerDTO> customers = new ArrayList<>();
        for (Customer date :
                all) {
            customers.add(new CustomerDTO(date.getCustomerId(),date.getName(),date.getAddress(),date.getTelephonNo()));
        }
        return customers;
    }
}
