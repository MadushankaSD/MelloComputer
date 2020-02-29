package io.project.mello.soft.repository.custom.impl;

import io.project.mello.soft.entity.Service;
import io.project.mello.soft.repository.CrudDAOImpl;
import io.project.mello.soft.repository.custom.ServiceDAO;
import org.springframework.stereotype.Repository;

@Repository
public class ServiceDAOImpl extends CrudDAOImpl<Service,Integer> implements ServiceDAO {
}
