package io.project.mello.soft.repository.custom;

import io.project.mello.soft.dto.CustomerDTO;
import io.project.mello.soft.entity.Customer;
import io.project.mello.soft.repository.CrudDAO;

import java.util.List;

public interface CustomerDAO extends CrudDAO<Customer, String> {

    String getLastCustomerId();

    List<Customer> searchCustomer(String data);

}
