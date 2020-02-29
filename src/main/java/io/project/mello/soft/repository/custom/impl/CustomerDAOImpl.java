package io.project.mello.soft.repository.custom.impl;

import io.project.mello.soft.entity.Customer;
import io.project.mello.soft.repository.CrudDAOImpl;
import io.project.mello.soft.repository.custom.CustomerDAO;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAOImpl extends CrudDAOImpl<Customer,String> implements CustomerDAO {
}
