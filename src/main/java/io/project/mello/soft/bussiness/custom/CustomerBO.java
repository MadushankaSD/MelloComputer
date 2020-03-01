package io.project.mello.soft.bussiness.custom;

import io.project.mello.soft.bussiness.SuperBO;
import io.project.mello.soft.dto.CustomerDTO;

import java.util.List;

public interface CustomerBO extends SuperBO {
    void saveCustomer(CustomerDTO customer);

    void updateCustomer(CustomerDTO customer);

    void deleteCustomer(String customerId) ;

    List<CustomerDTO> findAllCustomers();

    String getLastCustomerId();

    CustomerDTO findCustomer(String customerId);

    List<String> getAllCustomerIDs();

    List<CustomerDTO> searchCustomer(String data);
}
